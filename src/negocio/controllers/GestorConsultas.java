package negocio.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.*;
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
	public List<Double> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException {
		// TODO - implement GestorConsultas.consultarIngresos
		 List<Double> cursos = new ArrayList<Double>();
	        GestorBD gestor = new GestorBD();

	        List<Object> cursosListados = gestor.select("select * from cursospropios where tipo='"+tipo+"'"
	        		+ "and fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);

	        for(int i=0; i<cursosListados.size(); i++) {
	        	CursoPropioDAO cursoPropioDAO = new CursoPropioDAO();
	            CursoPropio cursoPropio = new CursoPropio();
	            List<Object> t = (List<Object>) cursosListados.get(i);
	            cursoPropio.setNombre(t.get(1).toString());
	            try {
					cursoPropio = cursoPropioDAO.seleccionarCurso((int) t.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
	            //habria que saber como poner un precio a la matricula, en este caso, he puesto 1250 euros
	            //habria que poner cursos.add(cursoPropio.getPrecioMatricula)
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
	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) throws SQLException {
		// TODO - implement GestorConsultas.consultarEstadoCursos
		 List<CursoPropio> cursos = new ArrayList<CursoPropio>();
	        GestorBD gestor = new GestorBD();

	        List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estadoCurso+"'"
	        		+ "and fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);

	        for(int i=0; i<cursosListados.size(); i++) {
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
		// TODO - implement GestorConsultas.listarEdicionesCursos
		 List<CursoPropio> cursos = new ArrayList<CursoPropio>();
	        GestorBD gestor = new GestorBD();

	        List<Object> cursosListados = gestor.select("select * from cursospropios where "
	        		+ "fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);
	        

	        for(int i=0; i<cursosListados.size(); i++) {
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

        List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estado+"'");

        for(int i=0; i<cursosListados.size(); i++) {
            CursoPropio cursoPropio = new CursoPropio();
            List<Object> t = (List<Object>) cursosListados.get(i);
            cursoPropio.setNombre(t.get(1).toString());
            cursos.add(cursoPropio);
        }

        gestor.desconectarBD();
        return cursos;
    }
}