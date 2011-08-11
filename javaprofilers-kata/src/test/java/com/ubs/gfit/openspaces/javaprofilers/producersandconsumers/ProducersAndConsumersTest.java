package com.ubs.gfit.openspaces.javaprofilers.producersandconsumers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProducersAndConsumersTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducersAndConsumersTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier(value = "numberOfProducers")
	private Integer numberOfProducers;

	@Autowired
	@Qualifier(value = "numberOfConsumers")
	private Integer numberOfConsumers;

	@Test
	public void testRunProducersAndConsumers() throws InterruptedException, ExecutionException {
		ExecutorService producersService = Executors.newFixedThreadPool(numberOfProducers);
		ExecutorService consumersService = Executors.newFixedThreadPool(numberOfConsumers);

		List<Future<Long>> producersFutures = startProducers(producersService);

		List<Consumer> consumers = getConsumers();

		List<Future<Long>> consumersFutures = startConsumers(consumersService, consumers);

		waitForProducersToFinish(producersFutures);

		tellConsumersToStopWaiting(consumers);

		waitForConsumersToFinish(consumersFutures);

		producersService.shutdown();
		consumersService.shutdown();
	}

	private void tellConsumersToStopWaiting(List<Consumer> consumers) {
		for (Consumer consumer : consumers) {
			consumer.stopWaitingForMessages();
		}
	}

	private void waitForConsumersToFinish(List<Future<Long>> consumersFutures) throws InterruptedException,
			ExecutionException {
		for (Future<Long> future : consumersFutures) {
			LOGGER.info("Consumer finished after processing {} messages", future.get());
		}
	}

	private void waitForProducersToFinish(List<Future<Long>> producersFutures) throws InterruptedException,
			ExecutionException {
		for (Future<Long> future : producersFutures) {
			LOGGER.info("Producer finished after generating {} messages", future.get());
		}
	}

	private List<Consumer> getConsumers() {
		List<Consumer> consumersList = new ArrayList<Consumer>();

		for (int i = 0; i < numberOfConsumers; i++) {
			Consumer consumer = applicationContext.getBean(Consumer.class);
			consumersList.add(consumer);
		}

		return consumersList;
	}

	private List<Future<Long>> startConsumers(ExecutorService consumersThreadPool, List<Consumer> consumers) {
		List<Future<Long>> consumersFutures = new ArrayList<Future<Long>>();

		for (Consumer consumer : consumers) {
			Future<Long> consumerFuture = consumersThreadPool.submit(consumer);
			consumersFutures.add(consumerFuture);
		}

		return consumersFutures;
	}

	private List<Future<Long>> startProducers(ExecutorService producersThreadPool) {
		List<Future<Long>> producerFutures = new ArrayList<Future<Long>>();

		for (int i = 0; i < numberOfProducers; i++) {
			Future<Long> producerFuture = producersThreadPool.submit(applicationContext
					.getBean(SlowStartingProducer.class));
			producerFutures.add(producerFuture);

		}

		return producerFutures;
	}
}
