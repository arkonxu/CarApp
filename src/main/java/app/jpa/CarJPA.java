package app.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.entities.Car;

@Stateless
public class CarJPA {

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

	public void deleteEntity(Long id) {
		Car car = em.find(Car.class, id);
		em.remove(car);
	}

	public Car getEntityById(Long id, Class<Car> clase) {
		return em.find(clase, id);
	}

	public Car putEntity(Car car) {
		return em.merge(car);
	}

}
