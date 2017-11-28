package net.starchl.springbootactivemq.demoactivemq.jms;

import java.util.logging.Logger;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class BlockingReceiver {
	@Autowired
	private Queue inQueue;
	@Autowired
	private Queue testQueue;
	@Autowired
	private JmsTemplate jmsTemplate;
	protected Logger log = Logger.getLogger(this.getClass().getName());

	public String receive(boolean isTest) {
		String retVal = "";
		if (isTest) {
			retVal = "" + jmsTemplate.receiveAndConvert(testQueue);
			log.info("IIIII Empfange: " + retVal);
		} else {
			retVal = "" + jmsTemplate.receiveAndConvert(inQueue);
			log.info("IIIII Empfange: " + retVal);
		}
		return retVal;
	}
}
