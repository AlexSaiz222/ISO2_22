package negocio.entities;

import java.sql.Date;

public class Matricula {

	private int idMatricula;
	Estudiante estudiante;
	CursoPropio titulo;
	ModoPago tipoPago;
	private Date fecha;
	private boolean pagado;
	private int attribute;
	
	public Matricula(int idMatricula, Estudiante estudiante, CursoPropio titulo, ModoPago tipoPago, Date fecha, boolean pagado,
			int attribute) {
		super();
		this.idMatricula = idMatricula;
		this.estudiante = estudiante;
		this.titulo = titulo;
		this.tipoPago = tipoPago;
		this.fecha = fecha;
		this.pagado = pagado;
		this.attribute = attribute;
	}
	
	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public CursoPropio getTitulo() {
		return titulo;
	}

	public void setTitulo(CursoPropio titulo) {
		this.titulo = titulo;
	}

	public ModoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(ModoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}


}