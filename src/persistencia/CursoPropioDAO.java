package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;


public class CursoPropioDAO extends AbstractEntityDAO {

	private static Logger logJava = Logger.getLogger(CursoPropioDAO.class);
	/**
	 * 
	 * @param curso
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException
	 */

	public int crearCurso(CursoPropio curso) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		// Formateo de las fechas para la insercion en la BD
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date fechaInicio = Date.valueOf(simpleDateFormat.format(curso.getFechaInicio()));
		Date fechaFin = Date.valueOf(simpleDateFormat.format(curso.getFechaFin()));

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD.prepareStatement("insert into cursospropios (idcentro, iddirector, idsecretario, "
					+ "estado, tipo, nombre, ects, fechaInicio, fechaFin, tasamatricula, edicion) values (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, curso.getCentro().getIdCentro());
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

		} catch (SQLException e) {
			logJava.fatal("LOG FATAL: "+e.toString());
		} finally {
			if (pstmt != null)
				pstmt.close();
		}

		return resultado;

	}

	/**
	 * 
	 * @param curso
	 * @throws SQLException
	 * @throws ParseException
	 */
	public CursoPropio seleccionarCurso(int curso) throws SQLException {
		GestorBD agente = new GestorBD();
		CursoPropio curso1 = new CursoPropio();
		List<Object> cursoListado = agente.select("select * from cursospropios where idcursopropio=" + curso);

		if (cursoListado.size() == 1) {
			List<Object> c = (List<Object>) cursoListado.get(0);

			CentroDAO centroDAO = new CentroDAO();
			Centro centro = centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString()));

			ProfesorUCLMDAO profeUCLMDAO = new ProfesorUCLMDAO();
			ProfesorUCLM profeUCLM = profeUCLMDAO.seleccionarProfesorUCLM(c.get(2).toString());

			ProfesorDAO secretarioDAO = new ProfesorDAO();
			Profesor secretario = secretarioDAO.seleccionarProfesor(c.get(3).toString());

			EstadoCurso estado = EstadoCurso.valueOf(c.get(4).toString());

			TipoCurso tipo = TipoCurso.valueOf(c.get(5).toString());

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date fechainicio = null, fechafin = null;
			try {
				fechainicio = (Date) simpleDateFormat.parse(c.get(8).toString());
				fechafin = (Date) simpleDateFormat.parse(c.get(9).toString());
			} catch (ParseException e) {
				logJava.fatal("LOG FATAL: "+e.toString());
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
			// setMatriculas y setMaterias faltan
		} else {
			curso1.setId(-1);
		}

		return curso1;

	}
	
	public int eliminarUltimoCurso() throws SQLException {
		int resultado = -1;
		
		int idUltimoCurso = obtenerUltimoCurso();
		if(idUltimoCurso != -1)
			resultado = eliminarCurso(idUltimoCurso);
		
		return resultado;
		
	}
	
	public int obtenerUltimoCurso() throws SQLException {
		GestorBD agente = new GestorBD();
		List<Object> cursoListado = agente.select("select idcursopropio from cursospropios where idcursopropio=(select max(idcursopropio) from cursospropios)");
		if(cursoListado.size() == 1) {
			List<Object> c = (List<Object>) cursoListado.get(0);
			return Integer.parseInt(c.get(0).toString());
		} else {
			return -1;
		}
	}
	
	public int eliminarCurso(int idCurso) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		resultado = agente.delete("delete from cursospropios where idcursopropio="+idCurso);
		
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 * @throws SQLException
	 */
	public int editarCurso(CursoPropio curso) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		// Formateo de las fechas para la insercion en la BD
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date fechaInicio = Date.valueOf(simpleDateFormat.format(curso.getFechaInicio()));
		Date fechaFin = Date.valueOf(simpleDateFormat.format(curso.getFechaFin()));
	
		resultado = agente.update("update cursospropios "
				+ "set idcentro = "+curso.getCentro().getIdCentro()+", iddirector='"+curso.getDirector().getDni()
				+ "', idsecretario='"+curso.getSecretario().getDni()+"', estado='"+curso.getEstado().name()
				+ "', tipo='"+curso.getTipo().name()+"', nombre='"+curso.getNombre()+"', ects="+curso.getECTS()
				+ ", fechaInicio='"+fechaInicio+"', fechaFin='"+fechaFin+"', tasamatricula="+curso.getTasaMatricula()+", edicion="+curso.getEdicion()
				+ " where idcursopropio="+curso.getId());
		
		return resultado;
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws SQLException {
		// TODO Auto-generated method stub
		// mirar si las variables de las columans de la tabla Cursospropios esta
		// correctamente
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select("select * from cursospropios where estado='" + estado + "'");
		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> curso = (List<Object>) cursosListados.get(i);
			cursoPropio.setId(Integer.parseInt(curso.get(0).toString()));
			cursoPropio.setNombre(curso.get(6).toString());

			cursos.add(cursoPropio);
		}

		return cursos;
	}

	/**
	 * m
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
	 * @throws SQLException
	 */
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) throws SQLException {
		// TODO Auto-generated method stub
		List<CursoPropio> cursos = new ArrayList<CursoPropio>();
		GestorBD gestor = new GestorBD();

		List<Object> cursosListados = gestor.select(
				"select * from cursospropios where " + "fechainicio >=" + fechaInicio + "and fechafin <=" + fechaFin);

		for (int i = 0; i < cursosListados.size(); i++) {
			CursoPropio cursoPropio = new CursoPropio();
			List<Object> t = (List<Object>) cursosListados.get(i);
			cursoPropio.setNombre(t.get(1).toString());

			cursos.add(cursoPropio);
		}

		return cursos;
	}

}