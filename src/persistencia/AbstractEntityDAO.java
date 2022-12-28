package persistencia;

import java.sql.Date;

public abstract class AbstractEntityDAO<E> {

	private String id;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	/**
	 * 
	 * @param id
	 */
	public E get(String id) {
		// TODO - implement AbstractEntityDAO.get
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param entity
	 */
	public int insert(E entity) {
		int resultado = -1;

		return resultado;
	}

	/**
	 * 
	 * @param entity
	 */
	public E update(E entity) {
		// TODO - implement AbstractEntityDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param entity
	 */
	public int delete(E entity) {
		// TODO - implement AbstractEntityDAO.delete
		throw new UnsupportedOperationException();
	}

}