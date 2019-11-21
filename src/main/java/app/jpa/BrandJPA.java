package app.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.entities.Brand;

@Stateless
public class BrandJPA {

	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<Brand> getAll() {
		String query = "SELECT d FROM Brand d";
		TypedQuery<Brand> createQuery = em.createQuery(query, Brand.class);
		return createQuery.getResultList();
	}

	public Brand addEntity(Brand brand) {
		em.persist(brand);
		return brand;
	}

	public void deleteEntity(Long id) {
		Brand brand = em.find(Brand.class, id);
		em.remove(brand);
	}

	public Brand getEntityById(Long id, Class<Brand> clase) {
		return em.find(clase, id);
	}

	public Brand putEntity(Brand brand) {
		return em.merge(brand);
	}
}
