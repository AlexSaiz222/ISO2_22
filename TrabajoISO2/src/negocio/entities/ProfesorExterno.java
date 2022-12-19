package negocio.entities;

public class ProfesorExterno extends Profesor {

	private String titulacion;
	
	public ProfesorExterno() {
		
	}

	public ProfesorExterno(String dni, String nombre, String apellidos, char[] password, boolean doctor, String titulacion) {
		super(dni, nombre, apellidos, password, doctor);
		this.titulacion = titulacion;
	}

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	
	

}