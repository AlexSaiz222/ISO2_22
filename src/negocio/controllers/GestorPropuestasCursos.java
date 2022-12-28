package negocio.controllers;

import java.sql.SQLException;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import persistencia.CursoPropioDAO;

public class GestorPropuestasCursos {

	public int realizarPropuestaCurso(CursoPropio curso) throws SQLException {

		curso.setCursoPropioDao(new CursoPropioDAO());
		int resultado = curso.getCursoPropioDao().crearCurso(curso);

		return resultado;

	}

	/**
	 * 
	 * @param curso
	 * @throws SQLException
	 */
	public int editarPropuestaCurso(CursoPropio curso) throws SQLException {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		CursoPropioDAO cursoDAO = new CursoPropioDAO();
		int resultado = cursoDAO.editarCurso(curso);
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 */
	public EstadoCurso evaluarPropuesta(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.evaluarPropuesta
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public void altaCursoAprobado(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.altaCursoAprobado
		throw new UnsupportedOperationException();
	}

}