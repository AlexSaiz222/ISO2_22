package negocio.entities;

import java.sql.Date;
import java.util.Objects;

public class Materia {

	Profesor responsable;
	private int idMateria;
	private String nombre;
	private double horas;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Materia(Profesor responsable, int idMateria, String nombre, double horas, Date fechaInicio, Date fechaFin) {
		super();
		this.responsable = responsable;
		this.idMateria = idMateria;
		this.nombre = nombre;
		this.horas = horas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Materia() {
		// TODO Auto-generated constructor stub
	}

	public Profesor getResponsable() {
		return responsable;
	}
	public void setResponsable(Profesor responsable) {
		this.responsable = responsable;
	}
	
	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaFin, fechaInicio, horas, idMateria, nombre, responsable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return Objects.equals(fechaFin, other.fechaFin) && Objects.equals(fechaInicio, other.fechaInicio)
				&& Double.doubleToLongBits(horas) == Double.doubleToLongBits(other.horas)
				&& idMateria == other.idMateria && Objects.equals(nombre, other.nombre)
				&& Objects.equals(responsable, other.responsable);
	}

	
}