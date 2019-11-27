package app.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.entities.Car;

@Stateless
public class CarJPA {

	private static final long serialVersionUID = -927645013890576970L;
	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<Car> getAll() {
		String query = "SELECT d FROM Car d";
		TypedQuery<Car> createQuery = em.createQuery(query, Car.class);
		return createQuery.getResultList();
	}

	public Car addEntity(Car car) {
		em.persist(car);
		return car;
	}

	public Car deleteEntity(Long id) {
		Car car = em.find(Car.class, id);
		em.remove(car);
		return car;
	}

	public Car getEntityById(Long id) {
		return em.find(Car.class, id);
	}

	public Car putEntity(Car car) {
		return em.merge(car);
	}

	public List<Car> getCarByCountry(String country) {
		String query = "SELECT d FROM Car d WHERE UPPER(country) = '" + country.toUpperCase() + "'";
		TypedQuery<Car> createQuery = em.createQuery(query, Car.class);
		return createQuery.getResultList();
	}

}
