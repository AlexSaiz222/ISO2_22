package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.*;

public class CursoPropioDAO extends AbstractEntityDAO<Object> {

	/**
	 * 
	 * @param curso
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearCurso(CursoPropio curso) {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		// Formateo de las fechas para la insercion en la BD
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date fechaInicio = Date.valueOf(simpleDateFormat.format(curso.getFechaInicio()));
		Date fechaFin = Date.valueOf(simpleDateFormat.format(curso.getFechaFin()));
		
		PreparedStatement pstmt;
		try {
			pstmt = agente.mBD.prepareStatement("insert into cursospropios (idcentro, iddirector, idsecretario, "
					+ "estado, tipo, nombre, ects, fechaInicio, fechaFin, tasamatricula, edicion) values (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, curso.getId());
			pstmt.setString(2, curso.getDirector().getDni());
			pstmt.setString(3, curso.getSecretario().getDni());
			pstmt.setString(4, curso.getEstado().name());
			pstmt.setString(5, curso.getTipo().name());
			pstmt.setString(6, curso.getNombre());
			pstmt.setInt(7, curso.getECTS());
			pstmt.setDate(8, fechaInicio);
			pstmt.setDate(9, fechaFin);
			pstmt.setDouble(10, curso.getTasaMatricula());
			pstmt.setInt(11, curso.getEdicion());
			
			resultado = agente.insert(pstmt);
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("CursoPropioDAO: "+e.getMessage());
		}
		
		return resultado;
		
	}

	/**
	 * 
	 * @param curso
	 * @throws ParseException 
	 */
	public CursoPropio seleccionarCurso(int curso) {
		GestorBD agente = new GestorBD();
		CursoPropio curso1 = new CursoPropio();
		List<Object> cursoListado = agente.select("select * from cursospropios where idcursopropio="+curso);
		
		if(cursoListado.size() == 1) {
			List<Object> c = (List<Object>) cursoListado.get(0);
			
			System.out.println(c);
			
			CentroDAO centroDAO = new CentroDAO();
			Centro centro = centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString()));
			
			ProfesorUCLMDAO profeUCLMDAO = new ProfesorUCLMDAO();
			ProfesorUCLM profeUCLM = profeUCLMDAO.seleccionarProfesorUCLM(c.get(2).toString());
			
			ProfesorDAO secretarioDAO = new ProfesorDAO();
			Profesor secretario = secretarioDAO.seleccionarProfesor(c.get(3).toString());
			
			EstadoCurso estado = null;
			estado.valueOf(c.get(4).toString());
			
			TipoCurso tipo = null;
			tipo.valueOf(c.get(5).toString());
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date fechainicio = null, fechafin = null;
			try {
				fechainicio = (Date) simpleDateFormat.parse(c.get(8).toString());
				fechafin = (Date) simpleDateFormat.parse(c.get(9).toString());
			} catch (ParseException e) {
				System.out.println("CursoPropioDAO: "+e.getMessage());
			}
			
			curso1.setId(Integer.parseInt(c.get(0).toString()));
			curso1.setCentro(centro);
			curso1.setDirector(profeUCLM);
			curso1.setSecretario(secretario);
			curso1.setEstado(estado);
			curso1.setTipo(tipo);
			curso1.setNombre(c.get(6).toString());
			curso1.setECTS(Integer.parseInt(c.get(7).toString()));
			curso1.setFechaInicio(fechainicio);
			curso1.setFechaFin(fechafin);
			curso1.setTasaMatricula(Double.parseDouble(c.get(10).toString()));
			curso1.setEdicion(Integer.parseInt(c.get(11).toString()));
			//setMatriculas y setMaterias faltan
		} else {
			curso1.setId(-1);
		}
		
		return curso1;
		
	}

	/**
	 * 
	 * @param curso
	 */
	public int editarCurso(CursoPropio curso) {
		int resultado = -1;
		GestorBD agente = new GestorBD();
	
		resultado = agente.update("update cursospropios "
				+ "set( idcentro = "+ curso.getCentro()+",iddirector="+curso.getDirector().getDni()
				+ ",idsecretario ="+curso.getSecretario().getDni()+", estado = "+curso.getEstado()
				+ ", tipo ="+curso.getTipo()+", nombre ="+curso.getNombre()+", ects = "+curso.getECTS()
				+ ", tasamatricula = " +curso.getTasaMatricula()+", edicion ="+curso.getEdicion()+")");
		
		return resultado;
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) {
		// TODO Auto-generated method stub
		//mirar si las variables de las columans de la tabla Cursospropios esta correctamente
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = new GestorBD();

        List<Object> cursosListados = gestor.select("select * from cursospropios where estado='"+estado+"'");
        for(int i=0; i<cursosListados.size(); i++) {
            CursoPropio cursoPropio = new CursoPropio();
            List<Object> curso = (List<Object>) cursosListados.get(i);
            cursoPropio.setId(Integer.parseInt(curso.get(0).toString()));
            cursoPropio.setNombre(curso.get(6).toString());

            cursos.add(cursoPropio);
        }

        return cursos;
	}

	/**m
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<Double> listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
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
        GestorBD gestor = new GestorBD();

        List<Object> cursosListados = gestor.select("select * from cursospropios where "
        		+ "fechainicio >="+fechaInicio+"and fechafin <="+fechaFin);
        

        for(int i=0; i<cursosListados.size(); i++) {
            CursoPropio cursoPropio = new CursoPropio();
            List<Object> t = (List<Object>) cursosListados.get(i);
            cursoPropio.setNombre(t.get(1).toString());

            cursos.add(cursoPropio);
        }

        return cursos;
	}

}