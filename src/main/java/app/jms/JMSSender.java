package app.jms;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import app.DTO.CarDTO;
import app.exceptions.DataNotFoundException;
import app.services.CarService;

@Stateless
public class JMSSender {

	@Resource(name = "jms/factory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/queue")
	private Queue queue;

	@EJB
	CarService service;

	private final static Logger logger = Logger.getLogger(JMSSender.class);

	public CarDTO addCar(CarDTO car) {
		try (final Connection connection = connectionFactory.createConnection();
				final Session session = connection.createSession(true, 0);) {

			ObjectMessage message = session.createObjectMessage(car);

			MessageProducer producer = session.createProducer(queue);
			message.setStringProperty("action", "add");
			producer.send(message);

		} catch (JMSException | DataNotFoundException e) {
			logger.warn("Error in the sender JMS: " + e.getMessage());
			logger.error(e.getMessage());
		}
		return service.getAll().get(service.getAll().size() - 1);
	}

	public void deleteCar(long id) {
		try (final Connection connection = connectionFactory.createConnection();
				final Session session = connection.createSession(true, 0);) {

			TextMessage message = (TextMessage) session.createTextMessage();

			message.setLongProperty("id", id);

			MessageProducer producer = session.createProducer(queue);
			message.setStringProperty("action", "delete");
			producer.send(message);

		} catch (JMSException | DataNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	public void putCars(CarDTO car, long id) {
		try (final Connection connection = connectionFactory.createConnection();
				final Session session = connection.createSession(true, 0);) {

			ObjectMessage message = (ObjectMessage) session.createObjectMessage(car);

			message.setLongProperty("id", id);

			MessageProducer producer = session.createProducer(queue);
			message.setStringProperty("action", "update");
			producer.send(message);

		} catch (JMSException | DataNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
}