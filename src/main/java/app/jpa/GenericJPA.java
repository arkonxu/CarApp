package app.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericJPA<T> extends Serializable {

	T getEntityById(Long id);

	T putEntity(T t);

	T addEntity(T t);

	T deleteEntity(Long id);

	List<T> getAll();
}
