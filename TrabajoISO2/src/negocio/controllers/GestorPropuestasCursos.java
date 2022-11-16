package negocio.controllers;

import negocio.entities.*;

public class GestorPropuestasCursos {
	
	/**
	 * 
	 * @param curso
	 */
	public void editarPropuestaCurso(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public void altaCursoAprobado(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.altaCursoAprobado
		EstadoCurso Aprobado= EstadoCurso.VALIDADO;
		
		curso.setEstado(Aprobado);
	}

	public void rechazarCurso(CursoPropio curso) {
		// TODO Auto-generated method stub
		EstadoCurso Rechazado= EstadoCurso.PROPUESTA_RECHAZADA;
		curso.setEstado(Rechazado);
	}

}