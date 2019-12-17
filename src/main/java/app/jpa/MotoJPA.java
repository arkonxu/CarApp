package app.jpa;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import app.entities.Moto;

@Stateless
@LocalBean
public class MotoJPA implements GenericJPA<Moto> {

	private static final long serialVersionUID = -927645013890576970L;
	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<Moto> getAll() {
		String query = "SELECT d FROM Moto d";
		TypedQuery<Moto> createQuery = em.createQuery(query, Moto.class);
		return createQuery.getResultList();
	}

	public Moto addEntity(Moto moto) {
		em.persist(moto);
		return moto;
	}

	public Moto deleteEntity(Long id) {
		Moto moto = em.find(Moto.class, id);
		em.remove(moto);
		return moto;
	}

	public Moto getEntityById(Long id) {
		return em.find(Moto.class, id);
	}

	public Moto putEntity(Moto moto) {
		return em.merge(moto);
	}

	public List<Moto> getEntityByCountry(String country) {
		String query = "SELECT d FROM Moto d WHERE UPPER(country) = '" + country.toUpperCase() + "'";
		TypedQuery<Moto> createQuery = em.createQuery(query, Moto.class);
		return createQuery.getResultList();
	}
}
