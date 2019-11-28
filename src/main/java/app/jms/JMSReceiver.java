package app.jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import app.entities.Car;
import app.exceptions.DataNotFoundException;

@MessageDriven(mappedName = "jms/queue")
public class JMSReceiver implements MessageListener {

	final static Logger logger = Logger.getLogger(JMSReceiver.class);

	@Override
	public void onMessage(Message message) {
		ObjectMessage object = (ObjectMessage) message;

		try {
			
			Car car = object.getBody(Car.class);

//			logger.info("Message received: " + textMessage.getText().toString());
			logger.info("CAR: " + car);

		} catch (JMSException | DataNotFoundException e) {
			logger.info("Error while trying to consume messages: " + e.getMessage());
		}

	}

}
