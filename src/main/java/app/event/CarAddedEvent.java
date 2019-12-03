package app.event;

import app.entities.Car;

public class CarAddedEvent {
	
	private Car car;
	
	public CarAddedEvent(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
