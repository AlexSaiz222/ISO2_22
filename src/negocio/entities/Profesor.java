package negocio.entities;

import java.util.Objects;

public class Profesor {

	private String dni;
	private String nombre;
	private String apellidos;
	private boolean esDoctor;
	private String password;

	public Profesor() {

	}

	public Profesor(String dni, String nombre, String apellidos, String password, boolean doctor) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.esDoctor = doctor;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDoctor() {
		return esDoctor;
	}

	public void setDoctor(boolean doctor) {
		this.esDoctor = doctor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, esDoctor, nombre, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& esDoctor == other.esDoctor && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password);
	}

}