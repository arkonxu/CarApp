package app.jms;

import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import app.entities.Car;
import app.exceptions.DataNotFoundException;
import app.services.CarService;

@MessageDriven(mappedName = "jms/queue")
public class JMSReceiver implements MessageListener {

	final static Logger logger = Logger.getLogger(JMSReceiver.class);

	@EJB
	private CarService carService;

	@Override
	public void onMessage(Message message) {

		Car car;
		ObjectMessage object;
		long id;

		try {

			switch (message.getStringProperty("action")) {

			case "add":
				object = (ObjectMessage) message;
				car = object.getBody(Car.class);
				logger.info("CAR: " + car);
				carService.addCar(car);
				break;

			case "delete":
				TextMessage idMessage = (TextMessage) message;
				id = idMessage.getLongProperty("id");
				carService.deleteCar(id);
				break;

			case "update":
				object = (ObjectMessage) message;
				car = object.getBody(Car.class);
				id = message.getLongProperty("id");
				carService.putCar(id, car);
				break;
			}
		} catch (JMSException | DataNotFoundException | ParseException e) {
			throw new DataNotFoundException("Not found");
		}

	}

}
