package app.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.log4j.Logger;

import app.entities.Car;
import app.exceptions.DataNotFoundException;

@Stateless
public class JMSSender {

	@Resource(name = "jms/factory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/queue")
	private Queue queue;

	private final static Logger logger = Logger.getLogger(JMSSender.class);

	public void sendMessage(Car car) {
		try (final Connection connection = connectionFactory.createConnection();
				final Session session = connection.createSession(true, 0);) {

			ObjectMessage message = session.createObjectMessage(car);
				
			MessageProducer producer = session.createProducer(queue);
			message.setJMSType("Object");
			producer.send(message);

		} catch (JMSException | DataNotFoundException e) {
			logger.warn("Error in the sender JMS: " + e.getMessage());
		}
	}
}