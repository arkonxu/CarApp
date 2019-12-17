package app.jpa;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.entities.Brand;

@Stateless
@LocalBean
public class BrandJPA implements GenericJPA<Brand> {

	private static final long serialVersionUID = -3078378072165435917L;
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

	public Brand deleteEntity(Long id) {
		Brand brand = em.find(Brand.class, id);
		em.remove(brand);
		return brand;
	}

	public Brand getEntityById(Long id) {
		return em.find(Brand.class, id);
	}

	public Brand putEntity(Brand brand) {
		return em.merge(brand);
	}

	public List<Brand> getEntityByCountry(String country) {
		String query = "SELECT d FROM Brand d WHERE UPPER(country) = '" + country.toUpperCase() + "'";
		TypedQuery<Brand> createQuery = em.createQuery(query, Brand.class);
		return createQuery.getResultList();
	}
}
