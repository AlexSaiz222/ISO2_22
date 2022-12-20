package negocio.entities;

import java.sql.Date;
import java.util.Objects;

public class Matricula {

	private int idMatricula;
	Estudiante estudiante;
	CursoPropio titulo;
	ModoPago tipoPago;
	private Date fecha;
	private boolean pagado;
	
	public Matricula(int idMatricula, Estudiante estudiante, CursoPropio titulo, ModoPago tipoPago, Date fecha, boolean pagado) {
		super();
		this.idMatricula = idMatricula;
		this.estudiante = estudiante;
		this.titulo = titulo;
		this.tipoPago = tipoPago;
		this.fecha = fecha;
		this.pagado = pagado;
	}
	
	public Matricula() {
		// TODO Auto-generated constructor stub
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

	@Override
	public int hashCode() {
		return Objects.hash(estudiante, fecha, idMatricula, pagado, tipoPago, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return Objects.equals(estudiante, other.estudiante) && Objects.equals(fecha, other.fecha)
				&& idMatricula == other.idMatricula && pagado == other.pagado && tipoPago == other.tipoPago
				&& Objects.equals(titulo, other.titulo);
	}
	
}