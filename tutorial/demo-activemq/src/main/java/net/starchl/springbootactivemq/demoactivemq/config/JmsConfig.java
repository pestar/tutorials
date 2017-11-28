package net.starchl.springbootactivemq.demoactivemq.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import net.starchl.springbootactivemq.demoactivemq.jms.Receiver;

@Configuration
public class JmsConfig {

	@Value(value = "${inqueue.name}")
	private String INQUEUE;

	@Value(value = "${testqueue.name}")
	private String TESTQUEUE;

	@Value("${activemq.broker-url}")
	private String brokerUrl;

	@Value("${activemq.user}")
	private String user;

	@Value("${activemq.password}")
	private String password;

	@Value(value = "${outfile.name}")
	private String OUTFILENAME;

	@Bean
	public String outFileName() {
		return OUTFILENAME;
	}

	@Bean
	public Queue inQueue() {
		return new ActiveMQQueue(INQUEUE);
	}

	@Bean
	public Queue testQueue() {
		return new ActiveMQQueue(TESTQUEUE);
	}


	@Bean
	public ConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		factory.setUserName(user);
		factory.setPassword(password);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}

	@Bean
	MessageListenerAdapter receiver() {
		return new MessageListenerAdapter(new Receiver(OUTFILENAME)) {
			{
				setDefaultListenerMethod("receiveMessage");
			}
		};
	}

	@Bean
	SimpleMessageListenerContainer container(final MessageListenerAdapter messageListener,
			final ConnectionFactory connectionFactory) {
		return new SimpleMessageListenerContainer() {
			{
				setMessageListener(messageListener);
				setConnectionFactory(connectionFactory);
				setDestinationName(INQUEUE);
			}
		};
	}
}
