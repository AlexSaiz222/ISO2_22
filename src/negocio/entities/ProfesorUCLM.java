package negocio.entities;

import java.util.Objects;

public class ProfesorUCLM extends Profesor {

	private Centro centroAdscripcion;
	private CategoriaProfesor categoria;
	
	public ProfesorUCLM() {
		
	}
	
	public ProfesorUCLM(String dni, String nombre, String apellidos, String password, boolean esDoctor, Centro centroAdscripcion,
			CategoriaProfesor categoria) {
		super(dni, nombre, apellidos, password, esDoctor);
		this.centroAdscripcion = centroAdscripcion;
		this.categoria = categoria;
	}

	public Centro getCentroAdscripcion() {
		return centroAdscripcion;
	}

	public void setCentroAdscripcion(Centro centroAdscripcion) {
		this.centroAdscripcion = centroAdscripcion;
	}

	public CategoriaProfesor getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProfesor categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(categoria, centroAdscripcion);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfesorUCLM other = (ProfesorUCLM) obj;
		return categoria == other.categoria && Objects.equals(centroAdscripcion, other.centroAdscripcion);
	}
	
}