package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import app.DTO.CarDTO;
import app.entities.Car;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.jpa.CarJPA;
import app.mapping.MapEntityToDTO;
import app.services.CarService;

@RunWith(MockitoJUnitRunner.class)
public class CarTest {

	@InjectMocks
	private CarService carService;
	@Mock
	private CarJPA jpa;

	@Test
	public void testGetAll() {

		List<Car> carsToTest = new ArrayList<Car>();
		carsToTest.add(createCarForTest(1L, "BMW", "Germany"));
		carsToTest.add(createCarForTest(2L, "SEAT", "Spain"));

		Mockito.when(jpa.getAll()).thenReturn(carsToTest);

		// METHOD TO BE TESTED
		List<CarDTO> carResult = carService.getAll();

		Mockito.verify(jpa).getAll();
		assertNotNull(carResult);
		assertEquals(carsToTest.size(), carResult.size());
		for (int i = 0; i < carsToTest.size(); i++) {
			assertCars(carsToTest.get(i), carResult.get(i));
		}
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetAllShouldThrowException() {

		List<Car> cars = new ArrayList<Car>();

		Mockito.when(jpa.getAll()).thenReturn(cars);

		// METHOD TO BE TESTED
		carService.getAll();

		fail("Should throw DataNotFoundException because empty response");
	}

	@Test
	public void testGetCarByCountry() {
		List<Car> carsToTest = new ArrayList<Car>();
		carsToTest.add(createCarForTest(2L, "SEAT", "Spain"));

		Mockito.when(jpa.getEntityByCountry(Mockito.anyString())).thenReturn(carsToTest);

		// METHOD TO BE TESTED
		List<CarDTO> carResult = carService.getCarByCountry(Mockito.anyString());

		Mockito.verify(jpa).getEntityByCountry(Mockito.anyString());
		assertNotNull(carResult);
		assertEquals(carsToTest.size(), carResult.size());
		for (int i = 0; i < carsToTest.size(); i++) {
			assertCars(carsToTest.get(i), carResult.get(i));
		}
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetCarByCountryShouldThrowException() {

		List<Car> cars = new ArrayList<Car>();

		Mockito.when(jpa.getEntityByCountry(Mockito.anyString())).thenReturn(cars);

		// METHOD TO BE TESTED
		carService.getCarByCountry(Mockito.anyString());

		fail("Should throw DataNotFoundException because empty response");
	}

	@Test
	public void testPutCar() {
		Long id = 1L;
		Car carToTest = createCarForTest(id, "SEAT", "Spain");
		CarDTO carDTOToTest = MapEntityToDTO.mapToResponse(carToTest);

		Mockito.when(jpa.getEntityById(id)).thenReturn(carToTest);
		Mockito.when(jpa.putEntity(carToTest)).thenReturn(carToTest);

		// METHOD TO BE TESTED
		CarDTO carResult = carService.putCar(id, carDTOToTest);

		Mockito.verify(jpa).putEntity(carToTest);
		assertNotNull(carResult);
		assertEquals(carToTest, carResult);
	}

	@Test(expected = EmptyBodyException.class)
	public void testPutCarShouldThrowEmptyBodyException() {
		Long id = 1L;
		Car carToTest = createCarForTest(id, null, "Spain");
		CarDTO carDTOToTest = MapEntityToDTO.mapToResponse(carToTest);
		Mockito.when(jpa.getEntityById(id)).thenReturn(carToTest);
//		Mockito.when(jpa.putEntity(carToTest)).thenReturn(null);

		// METHOD TO BE TESTED
		carService.putCar(id, carDTOToTest);

		fail("Should throw EmptyBodyException because empty body");
	}

	@Test
	public void testGetCarById() {
		Long id = 1L;

		Car carToTest = createCarForTest(1L, "SEAT", "Spain");

		Mockito.when(jpa.getEntityById(id)).thenReturn(carToTest);

		// METHOD TO BE TESTED
		CarDTO carResult = carService.getCarById(id);

		Mockito.verify(jpa).getEntityById(id);
		assertNotNull(carResult);
		assertCars(carToTest, carResult);
	}

	@Test(expected = DataNotFoundException.class)
	public void testGetCarByIdShouldThrowException() {
		Car carToTest = null;

		Mockito.when(jpa.getEntityById(Mockito.anyLong())).thenReturn(carToTest);

		// METHOD TO BE TESTED
		carService.getCarById(Mockito.anyLong());

		fail("Should throw DataNotFoundException because empty response");
	}

	@Test
	public void testCreateCar() throws ParseException {
		Car car = createCarForTest(1L, "BMW", "Germany");
		CarDTO carDTO = MapEntityToDTO.mapToResponse(car);
		// METHOD TO BE TESTED
		carService.addCar(carDTO);
		Mockito.verify(jpa).addEntity(car);
	}

	private Car createCarForTest(Long id, String brand, String country) {
		Car carForTest = new Car(id, brand, country);
		carForTest.setCreatedAt(LocalDateTime.now());
		carForTest.setLastUpdated(LocalDateTime.now());
		carForTest.setRegistration(LocalDateTime.now());

		return carForTest;
	}

	private void assertCars(Car car, CarDTO carDTO) {
		assertEquals(car.getCountry(), carDTO.getCountry());
		assertEquals(car.getBrand(), carDTO.getBrand());
		assertEquals(car.getId(), carDTO.getId());
	}

}
