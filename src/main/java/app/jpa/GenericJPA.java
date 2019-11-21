package app.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericJPA<T> extends Serializable {

	public T getEntityById(Long id, Class<T> clase);

	public T addEntity(T t);

	public void deleteEntity(Long id);

	public List<T> getAll();
	
	public T putEntity(T t);

}
