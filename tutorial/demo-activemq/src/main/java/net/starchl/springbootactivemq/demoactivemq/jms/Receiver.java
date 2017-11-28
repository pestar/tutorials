package net.starchl.springbootactivemq.demoactivemq.jms;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	@Autowired
	private Queue inQueue;

	@Autowired
	private Queue testQueue;

	@Autowired
	private JmsTemplate jmsTemplate;

	private String outFileName;

	protected Logger log = Logger.getLogger(this.getClass().getName());

	public Receiver(String filename) {
		this.outFileName = filename;
	}

	public void receiveMessage(String messageText) throws UnsupportedEncodingException, IOException {
		log.info("IIIII Empfange Text :" + messageText);
		write2file(messageText);
	}

	private void write2file(String messageText) throws UnsupportedEncodingException, IOException {
		log.info("IIIII Schreibe in File " + outFileName);
		File f = new File(outFileName);
		java.nio.file.Files.write(Paths.get(f.toURI()), messageText.getBytes("utf-8"), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}
}
