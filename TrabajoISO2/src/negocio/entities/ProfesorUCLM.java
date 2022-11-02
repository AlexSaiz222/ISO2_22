package negocio.entities;

public class ProfesorUCLM extends Profesor {

	Centro centroAdscripcion;
	CategoriaProfesor categoria;
	
	public ProfesorUCLM() {
		
	}
	
	public ProfesorUCLM(String dni, String nombre, String apellidos, boolean doctor, Centro centroAdscripcion,
			CategoriaProfesor categoria) {
		super(dni, nombre, apellidos, doctor);
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
	
}