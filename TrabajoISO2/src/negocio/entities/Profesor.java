package negocio.entities;

public class Profesor {

	private String dni;
	private String nombre;
	private String apellidos;
	private boolean doctor;
	private char[] password;
	
	public Profesor() {
		
	}
	
	public Profesor(String dni, String nombre, String apellidos, boolean doctor, char[] password) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.doctor = doctor;
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean isDoctor() {
		return doctor;
	}

	public void setDoctor(boolean doctor) {
		this.doctor = doctor;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password1) {
		this.password = password1;
	}
	
	
}