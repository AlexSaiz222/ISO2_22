package negocio.entities;

import java.util.Objects;

public class PersonalVicerrectorado {

	private String dni;
	private String nombre;
	private String apellidos;
	private String password;
	private boolean jefe;
	
	

	public PersonalVicerrectorado(String dni,String nombre, String apellidos, String password, boolean jefe) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.jefe = jefe;
	}
	public PersonalVicerrectorado() {
		
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, jefe, nombre, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalVicerrectorado other = (PersonalVicerrectorado) obj;
		return Objects.equals(apellidos, other.apellidos) && jefe == other.jefe && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password);
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
	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isJefe() {
		return jefe;
	}
	public void setJefe(boolean jefe) {
		this.jefe = jefe;
	}
	
}
