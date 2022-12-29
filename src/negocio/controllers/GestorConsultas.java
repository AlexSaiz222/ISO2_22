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

	private static Logger logJava = Logger.getLogger(GestorConsultas.class);
	
	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 */
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
		
		if(ComprobarEstadoCursoConFecha(estadoCurso, fechaInicio, fechaFin)==true) {
			List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estadoCurso+"'"
					+ "and fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);

			for (int i = 0; i < cursosListados.size(); i++) {
				CursoPropio cursoPropio = new CursoPropio();
				List<Object> t = (List<Object>) cursosListados.get(i);
				cursoPropio.setNombre(t.get(1).toString());
				cursos.add(cursoPropio);
			}
			
			return cursos;
		}else {
			return null;
		}
		
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
	        if (ComprobarFechas(fechaInicio, fechaFin) == true) {
	        	List<Object> cursosListados = gestor.select("select * from cursospropios where "
		        		+ "fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);

		        for(int i=0; i<cursosListados.size(); i++) {
		            CursoPropio cursoPropio = new CursoPropio();
		            List<Object> t = (List<Object>) cursosListados.get(i);
		            cursoPropio.setNombre(t.get(1).toString());
		            cursos.add(cursoPropio);
		        }

		        return cursos;
	        }else {
	        	return null;
	        }
	}
	
	public List<CursoPropio> listarCursosPropiosPorEstado(EstadoCurso estado) throws SQLException {
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = new GestorBD();
        if(ComprobarEstado(estado)==true) {
        	 List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estado+"'");

             for(int i=0; i<cursosListados.size(); i++) {
                 CursoPropio cursoPropio = new CursoPropio();
                 List<Object> t = (List<Object>) cursosListados.get(i);
                 cursoPropio.setNombre(t.get(1).toString());
                 cursos.add(cursoPropio);
             }
             
             return cursos;
        }else {
        	return null;
        }
    }
	
	public boolean ComprobarFechas(Date FechaInicio, Date FechaFin) {
		boolean bool = true;
		
		if (FechaFin == null || FechaInicio == null) {
			bool = false;
		}else {
			if (FechaFin.compareTo(FechaInicio)==-1 || FechaFin.compareTo(FechaInicio)==0){
				bool = false;
			}
		}
		
		return bool;
	}
	
	public boolean ComprobarEstadoCursoConFecha(EstadoCurso estado,Date FechaInicio, Date FechaFin) {
		boolean bool = true;
		
		if(estado==null) {
			bool=false;
		}else {
			if (FechaFin == null || FechaInicio == null) {
				bool = false;
			}else {
				if (FechaFin.compareTo(FechaInicio)==-1 || FechaFin.compareTo(FechaInicio)==0){
					bool = false;
				}
			}
		}

		return bool;
	}
	
	public  boolean ComprobarEstado(EstadoCurso estado) {
		boolean bool = true;
		
		if(estado==null ) {
			bool=false;
		}
		
		return bool;
	}
}