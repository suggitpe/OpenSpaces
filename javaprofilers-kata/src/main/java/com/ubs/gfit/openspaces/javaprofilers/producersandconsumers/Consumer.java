package com.ubs.gfit.openspaces.javaprofilers.producersandconsumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Consumer implements Callable<Long> {

    private static final int MESSAGE_POLLING_INTERVAL_MILLISECONDS = 500;

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(Consumer.class);

    @Resource
    private BlockingQueue<Message> messageQueue;

    private final AtomicBoolean waitForMoreWork = new AtomicBoolean(true);

    private volatile boolean interrupted = false;

    private long consumedMessages = 0;

    @Override
    public Long call() throws Exception {
	setThreadId();
	consumeMessages();
	return consumedMessages;
    }

    private void setThreadId() {
	Thread.currentThread().setName(
		"Consumer-" + Thread.currentThread().getId());
    }

    private void consumeMessages() {
	try {
	    while (shouldProcessMessages(interrupted)) {
		try {
		    processIncomingMessage();
		} catch (InterruptedException e) {
		    interrupted = true;
		}
	    }
	} catch (Exception ex) {
	    LOGGER.error(ex.getMessage(), ex);
	}
    }

    private void processIncomingMessage() throws InterruptedException {
	Message message = pollForMessage();

	if (message != null) {
	    persistMessage(message);
	    consumedMessages++;
	}
    }

    private void persistMessage(Message extractedMessage)
	    throws InterruptedException {
	LOGGER.trace(
		"Persisting incoming message from {} to {}, with subject: {}",
		new Object[] { extractedMessage.getSender(),
			extractedMessage.getRecipient(),
			extractedMessage.getSubject() });

	byte[] attachments = extractedMessage.getAttachments();

	simulatePersistenceOperation(attachments);
    }

    private void simulatePersistenceOperation(byte[] attachments)
	    throws InterruptedException {
	// Simulate serialization
	for (int i = 0; i < 100 * Math.random() ; i++) {
	    byte[] attachmentsCopy = new byte[attachments.length];
	    System.arraycopy(attachments, 0, attachmentsCopy, 0,
		    attachments.length);
	}

	// Simulate wait
	Thread.sleep((int) Math.floor(Math.random() * 5));
    }

    private Message pollForMessage() throws InterruptedException {
	return messageQueue.poll(MESSAGE_POLLING_INTERVAL_MILLISECONDS,
		TimeUnit.MILLISECONDS);
    }

    private boolean shouldProcessMessages(boolean interrupted) {
	return !interrupted && waitForMoreWork.get();
    }

    public void stopWaitingForMessages() {
	waitForMoreWork.set(false);
    }

}
