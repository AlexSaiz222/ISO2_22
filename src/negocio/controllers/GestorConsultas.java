package negocio.controllers;

import org.apache.log4j.Logger;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.TipoCurso;
import persistencia.CursoPropioDAO;
import persistencia.GestorBD;

public class GestorConsultas {

	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 */
	private static Logger logJava = Logger.getLogger(GestorConsultas.class);

	public List<Double> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException {
		List<Double> cursos = new ArrayList<Double>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select("select * from cursospropios where tipo='" + tipo + "'"
				+ "and fechainicio >=" + fechaInicio + "and fechafin <=" + fechaFin);

		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropioDAO cursoPropioDAO = new CursoPropioDAO();
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> t = (List<Object>) cursosListados.get(i);
			cursoPropio.setNombre(t.get(1).toString());
			try {
				cursoPropioDAO.seleccionarCurso((int) t.get(0));
			} catch (Exception e) {
				logJava.fatal("LOG FATAL: "+e.toString());
			}

			cursos.add(1250.99);
		}

		gestor.desconectarBD();
		return cursos;
	}

	/**
	 * 
	 * @param estadoCurso
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 */
	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin)
			throws SQLException {
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select("select * from cursospropios where estado='" + estadoCurso + "'"
				+ "and fechainicio >=" + fechaInicio + "and fechafin <=" + fechaFin);

		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> t = (List<Object>) cursosListados.get(i);
			cursoPropio.setNombre(t.get(1).toString());

			cursos.add(cursoPropio);
		}

		gestor.desconectarBD();
		return cursos;
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 */
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) throws SQLException {
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select(
				"select * from cursospropios where " + "fechainicio >=" + fechaInicio + "and fechafin <=" + fechaFin);

		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> t = (List<Object>) cursosListados.get(i);
			cursoPropio.setNombre(t.get(1).toString());

			cursos.add(cursoPropio);
		}

		gestor.desconectarBD();
		return cursos;
	}

	public static List<CursoPropio> listarCursosPropiosPorEstado(EstadoCurso estado) throws SQLException {
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select("select * from cursospropios where estado='" + estado + "'");

		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> t = (List<Object>) cursosListados.get(i);
			cursoPropio.setNombre(t.get(1).toString());
			cursos.add(cursoPropio);
		}

		gestor.desconectarBD();
		return cursos;
	}

}