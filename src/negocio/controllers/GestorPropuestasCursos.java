package negocio.controllers;

import java.sql.SQLException;

import negocio.entities.CursoPropio;

public class GestorPropuestasCursos {
	
	/**
	 * 
	 * @param curso
	 * @return resultado
	 * 	resultado = 0 	--> Inserción correcta
	 * 	resultado = -1 	--> Inserción incorrecta
	 * 	resultado = 1	--> Nombre incorrecto
	 * 	resultado = 2 	--> ECTS incorrecto
	 * 	resultado = 3	--> FechaInicio no seleccionada
	 * 	resultado = 4	--> FechaFin no seleccionada
	 *  resultado = 5	--> TasaMatricula incorrecta
	 *  resultado = 6	--> Edición incorrecta
	 * @throws SQLException 
	 */
	public int realizarPropuestaCurso(CursoPropio curso) throws SQLException {
		int resultado = comprobarCurso(curso);
			
		if(resultado == 0)
			resultado = curso.getCursoPropioDao().crearCurso(curso);

		return resultado;
	}

	/**
	 * 
	 * @param curso
	 * @return resultado
	 * 	resultado = 0 	--> Actualización correcta
	 * 	resultado = -1 	--> Actualización incorrecta
	 * 	resultado = 1	--> Nombre incorrecto
	 * 	resultado = 2 	--> ECTS incorrecto
	 * 	resultado = 3	--> FechaInicio no seleccionada
	 * 	resultado = 4	--> FechaFin no seleccionada
	 *  resultado = 5	--> TasaMatricula incorrecta
	 *  resultado = 6	--> Edición incorrecta
	 * @throws SQLException 
	 */
	public int editarPropuestaCurso(CursoPropio curso) throws SQLException {
		int resultado = comprobarCurso(curso);
			
		if(resultado == 0)
			resultado = curso.getCursoPropioDao().editarCurso(curso);

		return resultado;
	}

	/**
	 * 
	 * @param curso
	 * @return resultado
	 * 	resultado = 0 	--> Curso correcto
	 * 	resultado = 1	--> Nombre incorrecto
	 * 	resultado = 2 	--> ECTS incorrecto
	 * 	resultado = 3	--> FechaInicio no seleccionada
	 * 	resultado = 4	--> FechaFin no seleccionada
	 *  resultado = 5	--> TasaMatricula incorrecta
	 *  resultado = 6	--> Edición incorrecta
	 */
	public int comprobarCurso(CursoPropio curso) {
		int resultado = 0;

		if(curso.getNombre().equals("") || curso.getNombre().length()>50)
			resultado = 1;
		else
			if(curso.getECTS()<=0)
				resultado = 2;
			else
				if(curso.getFechaInicio() == null)
					resultado = 3;
				else
					if(curso.getFechaFin() == null || 
					curso.getFechaFin().compareTo(curso.getFechaInicio())<0 || 
					curso.getFechaFin().compareTo(curso.getFechaInicio())==0)
						resultado = 4;
					else
						if(curso.getTasaMatricula()<=0)
							resultado = 5;
						else
							if(curso.getEdicion()<=0)
								resultado = 6;
		
		return resultado;
	}

}