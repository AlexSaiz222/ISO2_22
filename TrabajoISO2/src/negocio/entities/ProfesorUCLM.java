package negocio.entities;

import java.awt.Window.Type;

public class ProfesorUCLM extends Profesor {

	Centro centroAdscripcion;
	CategoriaProfesor categoria;
	
	
	public ProfesorUCLM() {
		
	}
	
	public ProfesorUCLM(String dni, String nombre, String apellidos, boolean doctor, char[] password, Centro centroAdscripcion,
			CategoriaProfesor categoria) {
		super(dni, nombre, apellidos, doctor, password);
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