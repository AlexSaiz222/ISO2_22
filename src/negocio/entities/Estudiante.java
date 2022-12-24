package negocio.entities;

import java.util.*;

public class Estudiante {

	Collection<Matricula> matriculas;
	private String dni;
	private String nombre;
	private String apellidos;
	private String titulacion;
	private String cualificacion;
	private String password;
	private int idEstudiante;
	
	public Estudiante() {
		
	}
	
	public Estudiante(Collection<Matricula> matriculas, String dni, String nombre, String apellidos, String titulacion,
			String cualificacion, int idEstudiante, String password) {
		super();
		this.matriculas = matriculas;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.titulacion = titulacion;
		this.cualificacion = cualificacion;
		this.setIdEstudiante(idEstudiante);
		this.password = password;
	}

	public Collection<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Collection<Matricula> matriculas) {
		this.matriculas = matriculas;
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

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public String getCualificacion() {
		return cualificacion;
	}

	public void setCualificacion(String cualificacion) {
		this.cualificacion = cualificacion;
	}

	public int getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String psswd) {
		this.password = psswd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, cualificacion, dni, idEstudiante, matriculas, nombre, password, titulacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(cualificacion, other.cualificacion)
				&& Objects.equals(dni, other.dni) && idEstudiante == other.idEstudiante
				&& Objects.equals(matriculas, other.matriculas) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(titulacion, other.titulacion);
	}

	
}