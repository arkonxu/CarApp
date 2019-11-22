package app.jpa;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

public interface GenericJPA<T> extends Serializable {

	T getEntityById(Long id, Class<T> clase);

	T putEntity(T t);

	T addEntity(T t);

	void deleteEntity(Long id);

	List<T> getAll();
}
