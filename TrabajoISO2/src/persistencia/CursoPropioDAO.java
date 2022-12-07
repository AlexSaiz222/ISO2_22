package persistencia;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.*;

public class CursoPropioDAO extends AbstractEntityDAO {

	/**
	 * 
	 * @param curso
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearNuevoCurso(CursoPropio curso) {
		int resultado = -1;
		GestorBD agente = GestorBD.getAgente();
		
		/*
		String pattern = "yyyy-mm-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String fechaInicio = simpleDateFormat.format(curso.getFechaInicio());
		String fechaFin = simpleDateFormat.format(curso.getFechaFin());
		System.out.println(fechaInicio);
		System.out.println(fechaFin);
		*/
		// TODO Insertar fechas
		resultado = agente.insert("insert into cursospropios (idcentro, iddirector, idsecretario, "
				+ "estado, tipo, nombre, ects, tasamatricula, edicion) "
				+ "values ("+curso.getCentro()+",'"+curso.getDirector().getDni()+"',"
				+ "'"+curso.getSecretario().getDni()+"','"+curso.getEstado()+"',"
				+ "'"+curso.getTipo()+"','"+curso.getNombre()+"',"+curso.getECTS()+","
				+ curso.getTasaMatricula()+","+curso.getEdicion()+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) {
		GestorBD agente = GestorBD.getAgente();
		List<Object>  resultado = new ArrayList<Object>();
				
		GestorBD gestor = GestorBD.getAgente();
		List<Object> cursoListado = gestor.select("select * from cursospropios where idcurso = "+curso.getId());
		List<Object> c = (List<Object>) cursoListado.get(0);
		CursoPropio curso1 = new CursoPropio();
		curso1.setId(Integer.parseInt(c.get(0).toString()));
		CentroDAO centroDAO = new CentroDAO();
		Centro centro = centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString()));
		curso1.setCentro(centro);
		curso1.setDirector(null);
		gestor.desconectarBD();
		return curso1;
	}

	/**
	 * 
	 * @param curso
	 */
	public int editarCurso(CursoPropio curso) {
		int resultado = -1;
	GestorBD agente = GestorBD.getAgente();

	resultado = agente.update("update cursospropios "
			+ "set( idcentro = "+ curso.getCentro()+",iddirector="+curso.getDirector().getDni()
			+ ",idsecretario ="+curso.getSecretario().getDni()+", estado = "+curso.getEstado()
			+ ", tipo ="+curso.getTipo()+", nombre ="+curso.getNombre()+", ects = "+curso.getECTS()
			+ ", tasamatricula = " +curso.getTasaMatricula()+", edicion ="+curso.getEdicion()+")");
	
	agente.desconectarBD();
	return resultado;
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
		//mirar si las variables de las columans de la tabla Cursospropios esta correctamente
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = GestorBD.getAgente();

        List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estado+"'"
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
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO Auto-generated method stub
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = GestorBD.getAgente();

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
	
	public static List<CursoPropio> listarCursosPropiosPorEstado(EstadoCurso estado) {
        // TODO Auto-generated method stub
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = GestorBD.getAgente();

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