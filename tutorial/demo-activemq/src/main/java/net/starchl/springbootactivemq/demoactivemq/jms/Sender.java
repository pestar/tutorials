package net.starchl.springbootactivemq.demoactivemq.jms;

import java.util.logging.Logger;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private Queue inQueue;

	@Autowired
	private Queue testQueue;

	@Autowired
	private JmsTemplate jmsTemplate;
	protected Logger log = Logger.getLogger(this.getClass().getName());

	public String send(String message, boolean isTest) throws Exception {
		if (isTest) {
			jmsTemplate.convertAndSend(testQueue, message);
			log.info("IIIII In Queue <" + testQueue.getQueueName() + "> gesendet: " + message);
		} else {
			jmsTemplate.convertAndSend(inQueue, message);
			log.info("IIIII In Queue <" + inQueue.getQueueName() + "> gesendet: " + message);
		}

		return "OK";
	}
}
