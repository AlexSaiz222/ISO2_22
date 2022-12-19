package negocio.entities;

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
	
}