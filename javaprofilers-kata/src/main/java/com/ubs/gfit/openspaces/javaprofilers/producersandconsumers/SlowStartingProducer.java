package com.ubs.gfit.openspaces.javaprofilers.producersandconsumers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class SlowStartingProducer implements Callable<Long> {

    @Resource
    private BlockingQueue<Message> messageQueue;

    @Resource(name = "numberOfMessagesPerProducer")
    private Long numberOfMessages;

    @Resource(name = "recipients")
    private List<String> recipients;

    @Resource(name = "senders")
    private List<String> senders;

    @Resource(name = "messageMap")
    private Map<String, String> messageMap;

    @Resource(name = "producersSlowStartPeriod")
    private Integer slowStartPeriodDurationMs;

    @Resource(name = "producersWaitPeriod")
    private Integer waitPeriodDurationMs;

    private long startTime;

    @Override
    public Long call() throws Exception {
	setThreadId();

	waitPeriod();

	setStartTime();

	return produceMessages();
    }

    private void waitPeriod() throws InterruptedException {
	Thread.sleep(waitPeriodDurationMs);
    }

    private void setStartTime() {
	this.startTime = new Date().getTime();
    }

    private void setThreadId() {
	Thread.currentThread().setName(
		"Producer-" + Thread.currentThread().getId());
    }

    private Long produceMessages() throws InterruptedException {
	long producedMessages = 0;
	for (; producedMessages < numberOfMessages; producedMessages++) {
	    waitIfWithinSlowStartPeriod();

	    Message message = createMessage();
	    messageQueue.put(message);
	}

	return producedMessages;
    }

    private void waitIfWithinSlowStartPeriod() throws InterruptedException {
	long now = new Date().getTime();
	long timeElapsedSinceStart = now - startTime;

	if (timeElapsedSinceStart <= slowStartPeriodDurationMs) {
	    Thread.sleep(1,500000);
	}
    }

    private Message createMessage() {
	Message message = new Message();

	String sender = getRandomItem(senders);
	String recipient = getRandomItem(recipients);
	Entry<String, String> messageEntry = getRandomEntry(messageMap);

	Date now = new Date();
	Timestamp createdOn = new Timestamp(now.getTime());

	message.setSender(sender);
	message.setRecipient(recipient);
	message.setSubject(messageEntry.getKey());
	message.setBody(messageEntry.getValue());
	message.setCreatedOn(createdOn);
	message.setAttachments(new byte[getRandomNumber(32 * 1024)]);

	return message;
    }

    private String getRandomItem(List<String> list) {
	int index = getRandomNumber(list.size());

	return senders.get(index);
    }

    private Entry<String, String> getRandomEntry(Map<String, String> map) {
	Entry<String, String> entry = null;

	int index = getRandomNumber(map.size());
	Iterator<Entry<String, String>> mapIterator = map.entrySet().iterator();

	for (int i = 0; i <= index; i++) {
	    entry = mapIterator.next();
	}

	return entry;
    }

    private int getRandomNumber(int lessThan) {
	return (int) Math.floor(Math.random() * lessThan);
    }
}
