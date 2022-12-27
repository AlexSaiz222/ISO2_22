package negocio.controllers;

import negocio.entities.*;
import persistencia.CursoPropioDAO;

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
	 */
	public int realizarPropuestaCurso(CursoPropio curso) {
		int resultado = 0;
		resultado =comprobacioncurso(curso);
			
		if(resultado == 0) {
			curso.setCursoPropioDao(new CursoPropioDAO());
			resultado = curso.getCursoPropioDao().crearCurso(curso);
		}
		
		return resultado;
		
	}
	
	/**
	 * 
	 * @param curso
	 */
	public int editarPropuestaCurso(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		int resultado = 0;
		resultado =comprobacioncurso(curso);
			
		if(resultado == 0) {
			curso.setCursoPropioDao(new CursoPropioDAO());
			resultado = curso.getCursoPropioDao().editarCurso(curso);
		}
		
		return resultado;
	}

	public int comprobacioncurso(CursoPropio curso) {
		int resultado = 0;

		System.out.println("Entra al gestor");

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
					curso.getFechaFin().compareTo(curso.getFechaInicio())==-1 || 
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