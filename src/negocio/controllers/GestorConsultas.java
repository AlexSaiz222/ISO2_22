package negocio.controllers;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;

import negocio.entities.*;
import persistencia.CursoPropioDAO;
import persistencia.GestorBD;

public class GestorConsultas {


	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<Double> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
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
	 */
	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarEstadoCursos
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();
		
		if(ComprobarEstadoCursoConFecha(estadoCurso, fechaInicio, fechaFin)==true) {
			
			List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estadoCurso+"'"
					+ "and fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);

			for(int i=0; i<cursosListados.size(); i++) {
				CursoPropio cursoPropio = new CursoPropio();
				List<Object> t = (List<Object>) cursosListados.get(i);
				cursoPropio.setNombre(t.get(1).toString());

				cursos.add(cursoPropio);
			}
		}else {
			return null;
		}

		gestor.desconectarBD();
		return cursos;
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
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

		        gestor.desconectarBD();
		        return cursos;
	        }else {
	        	return null;
	        }
	        
	}
	
	public  List<CursoPropio> listarCursosPropiosPorEstado(EstadoCurso estado) {
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

             gestor.desconectarBD();
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