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
				+ "values ("+curso.getCentro().getIdCentro()+",'"+curso.getDirector().getDni()+"',"
				+ "'"+curso.getSecretario().getDni()+"','"+curso.getEstado()+"',"
				+ "'"+curso.getTipo()+"','"+curso.getNombre()+"',"+curso.getECTS()+","
				+ curso.getTasaMatricula()+","+curso.getECTS()+")");
		
		agente.desconectarBD();
		return resultado;
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
	
	public static List<CursoPropio> listarCursosPropiosPorEstado(EstadoCurso estado) {
        // TODO Auto-generated method stub
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = GestorBD.getAgente();

        List<Object> cursosListados = gestor.select("select * from cursosPropios where estado='"+estado+"'");

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