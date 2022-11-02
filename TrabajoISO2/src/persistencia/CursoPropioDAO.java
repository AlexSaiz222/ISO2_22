package persistencia;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import negocio.entities.*;

public class CursoPropioDAO extends AbstractEntityDAO {

	/**
	 * 
	 * @param curso
	 */
	public static int crearNuevoCurso(String nombre, int ETCS,java.util.Date date, java.util.Date date2, double tasaMatricula, int edicion)
			throws SQLException, Exception {

		int id = (int) (Math.random()*999);
		String insertSQL = "INSERT INTO CursoPropio (id, nombre, ETCS, fechaInicio, fechaFin, tasaMatricula, edicion) VALUES ("
				+ id + ", " + nombre + ", " + ETCS + ", " +date + ", " + date2 + ","+ tasaMatricula + "," + edicion + ")";
		//este seria el metodo insert en la base de datos
		GestorBD.insert(insertSQL);

	}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.seleccionarCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio editarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.editarCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarCursosPorEstado
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public double listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarIngresos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public void listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}

}