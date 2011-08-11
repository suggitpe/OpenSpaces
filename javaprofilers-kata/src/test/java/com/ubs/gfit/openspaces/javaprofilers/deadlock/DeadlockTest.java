package com.ubs.gfit.openspaces.javaprofilers.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeadlockTest.class);

	@Test
	public void testDeadLock() throws InterruptedException, ExecutionException {
		CountDownLatch synchronizationLatch = new CountDownLatch(2);

		ExecutorService threadPool = initializeThreadPool();

		Future<Void> firstFuture = startDeadlockable(threadPool, synchronizationLatch, AClassToLockOn.class,
				AnotherClassToLockOn.class);

		Future<Void> secondFuture = startDeadlockable(threadPool, synchronizationLatch, AnotherClassToLockOn.class,
				AClassToLockOn.class);

		waitForDeadlock(synchronizationLatch);

		logDeadlock();

		// The following call will block ad aeternum
		waitForThreadsToComplete(firstFuture, secondFuture);

		// This is never going to run, as the threads will deadlock
		shutdownThreadPool(threadPool);
	}

	private void shutdownThreadPool(ExecutorService threadPool) {
		threadPool.shutdown();
	}

	private void logDeadlock() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		long[] threadIds = bean.findDeadlockedThreads();

		if (threadIds != null) {
			ThreadInfo[] infos = bean.getThreadInfo(threadIds);

			for (ThreadInfo info : infos) {
				LOGGER.error("Deadlocked thread: " + info.toString().trim());
			}
		}
	}

	private void waitForDeadlock(CountDownLatch synchronizationLatch) throws InterruptedException {
		synchronizationLatch.await();
	}

	private ExecutorService initializeThreadPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		return threadPool;
	}

	private void waitForThreadsToComplete(Future<Void> firstFuture, Future<Void> secondFuture)
			throws InterruptedException, ExecutionException {
		firstFuture.get();
		secondFuture.get();
	}

	private Future<Void> startDeadlockable(ExecutorService threadPool, final CountDownLatch synchronizationLatch,
			Class<?> firstClassToLockOn, Class<?> secondClassToLockOn) {

		Future<Void> future = threadPool.submit(new Deadlockable(synchronizationLatch, firstClassToLockOn,
				secondClassToLockOn));

		return future;
	}

	private static class Deadlockable implements Callable<Void> {

		private final CountDownLatch synchronizationLatch;

		private final Class<?> firstClassToLockOn;
		private final Class<?> secondClassToLockOn;

		public Deadlockable(CountDownLatch synchronizationLatch, Class<?> firstClassToLockOn,
				Class<?> secondClassToLockOn) {
			this.synchronizationLatch = synchronizationLatch;
			this.firstClassToLockOn = firstClassToLockOn;
			this.secondClassToLockOn = secondClassToLockOn;
		}

		public Void call() throws Exception {
			synchronized (firstClassToLockOn) {

				// Synchronise with the other instance, to ensure that the next
				// monitor is only acquired once the other instance already has
				// it.
				synchronizeWithTheOtherDeadlockable();

				LOGGER.info("Thread {} acquired monitor on {}", new Object[] { Thread.currentThread().getName(),
						firstClassToLockOn });

				synchronized (secondClassToLockOn) {
					// This point will never be reached
				}
			}
			return null;
		}

		private void synchronizeWithTheOtherDeadlockable() throws InterruptedException {
			synchronizationLatch.countDown();
			synchronizationLatch.await();
		}
	}

	private static final class AClassToLockOn {
	}

	private static final class AnotherClassToLockOn {
	}
}
