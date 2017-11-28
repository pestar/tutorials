package net.starchl.springbootactivemq.demoactivemq;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import net.starchl.springbootactivemq.demoactivemq.config.JmsConfig;
import net.starchl.springbootactivemq.demoactivemq.jms.BlockingReceiver;
import net.starchl.springbootactivemq.demoactivemq.jms.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(JmsConfig.class)
public class DemoActivemqApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void testSend() throws Exception {
		assertEquals("OK", sender.send("TestMessage", true));
	}

	@Autowired
	private BlockingReceiver receiver;

	@Test
	public void testReceive() {
		assertEquals("TestMessage", receiver.receive(true));
	}

}
