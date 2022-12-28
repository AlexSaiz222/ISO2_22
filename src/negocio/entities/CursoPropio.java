package negocio.entities;

import java.util.*;
import persistencia.*;

public class CursoPropio {

	Collection<Matricula> matriculas;
	Centro centro;
	ProfesorUCLM director;
	Profesor secretario;
	Collection<Materia> materias;
	EstadoCurso estado;
	TipoCurso tipo;
	CursoPropioDAO cursoPropioDao;
	private int id;
	private String nombre;
	private int ECTS;
	private Date fechaInicio;
	private Date fechaFin;
	private double tasaMatricula;
	private int edicion;
	
	public CursoPropio() {
		this.cursoPropioDao = new CursoPropioDAO();
	}
	
	public CursoPropio(Centro centro, ProfesorUCLM director, Profesor secretario, EstadoCurso estado, TipoCurso tipo, int id,
			String nombre, int eCTS, Date fechaInicio, Date fechaFin, double tasaMatricula, int edicion) {
		this.centro = centro;
		this.director = director;
		this.secretario = secretario;
		this.estado = estado;
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.ECTS = eCTS;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tasaMatricula = tasaMatricula;
		this.edicion = edicion;
		this.cursoPropioDao = new CursoPropioDAO();
	}
	
	public CursoPropio(Collection<Matricula> matriculas, Centro centro, ProfesorUCLM director, Profesor secretario,
			Collection<Materia> materias, EstadoCurso estado, TipoCurso tipo, int id,
			String nombre, int eCTS, Date fechaInicio, Date fechaFin, double tasaMatricula, int edicion) {
		super();
		this.matriculas = matriculas;
		this.centro = centro;
		this.director = director;
		this.secretario = secretario;
		this.materias = materias;
		this.estado = estado;
		this.tipo = tipo;
		this.cursoPropioDao = new CursoPropioDAO();
		this.id = id;
		this.nombre = nombre;
		this.ECTS = eCTS;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tasaMatricula = tasaMatricula;
		this.edicion = edicion;
	}

	public Collection<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Collection<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public ProfesorUCLM getDirector() {
		return director;
	}

	public void setDirector(ProfesorUCLM director) {
		this.director = director;
	}

	public Profesor getSecretario() {
		return secretario;
	}

	public void setSecretario(Profesor secretario) {
		this.secretario = secretario;
	}

	public Collection<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Collection<Materia> materias) {
		this.materias = materias;
	}

	public EstadoCurso getEstado() {
		return estado;
	}

	public void setEstado(EstadoCurso estado) {
		this.estado = estado;
	}

	public TipoCurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}

	public CursoPropioDAO getCursoPropioDao() {
		return cursoPropioDao;
	}

	public void setCursoPropioDao(CursoPropioDAO cursoPropioDao) {
		this.cursoPropioDao = cursoPropioDao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getECTS() {
		return ECTS;
	}

	public void setECTS(int eCTS) {
		ECTS = eCTS;
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

	public double getTasaMatricula() {
		return tasaMatricula;
	}

	public void setTasaMatricula(double tasaMatricula) {
		this.tasaMatricula = tasaMatricula;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ECTS, cursoPropioDao, director, edicion, estado, fechaFin, fechaInicio, id, centro,
				materias, matriculas, nombre, secretario, tasaMatricula, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursoPropio other = (CursoPropio) obj;
		return ECTS == other.ECTS && Objects.equals(cursoPropioDao, other.cursoPropioDao)
				&& Objects.equals(director, other.director) && edicion == other.edicion && estado == other.estado
				&& Objects.equals(fechaFin, other.fechaFin) && Objects.equals(fechaInicio, other.fechaInicio)
				&& id == other.id && centro == other.centro && Objects.equals(materias, other.materias)
				&& Objects.equals(matriculas, other.matriculas) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(secretario, other.secretario)
				&& Double.doubleToLongBits(tasaMatricula) == Double.doubleToLongBits(other.tasaMatricula)
				&& tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "CursoPropio [matriculas=" + matriculas + ", centro=" + centro + ", director=" + director
				+ ", secretario=" + secretario + ", materias=" + materias + ", estado=" + estado + ", tipo=" + tipo
				+ ", cursoPropioDao=" + cursoPropioDao + ", id=" + id + ", nombre=" + nombre + ", ECTS=" + ECTS
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tasaMatricula=" + tasaMatricula
				+ ", edicion=" + edicion + "]";
	}
}