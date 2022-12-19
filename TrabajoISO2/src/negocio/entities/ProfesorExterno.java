package negocio.entities;

public class ProfesorExterno extends Profesor {

	private String titulacion;
	
	public ProfesorExterno() {
		
	}

	public ProfesorExterno(String dni, String nombre, String apellidos, String password, boolean esDoctor, String titulacion) {
		super(dni, nombre, apellidos, password, esDoctor);
		this.titulacion = titulacion;
	}

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	
	

}