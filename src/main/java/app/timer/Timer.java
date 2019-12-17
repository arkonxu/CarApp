package app.timer;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

import app.entities.Car;
import app.jpa.CarJPA;
import app.services.CarService;

@Singleton
public class Timer {

	@EJB
	CarJPA jpa;

	@EJB
	CarService service;

	Logger logger = Logger.getLogger(Timer.class);

	@Schedule(hour = "*", minute = "*/1", persistent = false)
	public void timer() throws ParseException {
		List<Car> cars = jpa.getAll();
		if (cars != null) {
			cars.forEach(car -> {
				car.setChecked(true);
				logger.info("CAR CHECK: " + car.isChecked());
				jpa.putEntity(car);
			});
			logger.info("HA PASADO 1 MINUTO");
		} else {
			logger.info("No hay coches en BBDD");
		}
	}
}
