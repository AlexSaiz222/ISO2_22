package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.entities.Estudiante;

public class EstudianteDAO extends AbstractEntityDAO {

	private static Logger logJava = Logger.getLogger(EstudianteDAO.class);
	
	public int crearEstudiante(Estudiante estudiante) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD.prepareStatement("insert into ESTUDIANTES (DNI, NOMBRE, APELLIDOS, "
					+ "PASSWORD, TITULACION, CUALIFICACION) values (?,?,?,?,?,?)");
			pstmt.setString(1, estudiante.getDni());
			pstmt.setString(2, estudiante.getNombre());
			pstmt.setString(3, estudiante.getApellidos());
			pstmt.setString(4, estudiante.getPassword());
			pstmt.setString(5, estudiante.getTitulacion());
			pstmt.setString(6, estudiante.getCualificacion());

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
	 * @param estudiante
	 * @throws SQLException
	 */
	public Estudiante seleccionarEstudiante(String dni) throws SQLException {

		GestorBD gestor = new GestorBD();
		List<Object> estudianteListado = gestor.select("select * from ESTUDIANTES where dni = " + dni);
		List<Object> c = (List<Object>) estudianteListado.get(0);
		if (estudianteListado.size() == 0) {
			gestor.desconectarBD();
			return null;
		}
		Estudiante student1 = new Estudiante();

		student1.setDni(c.get(0).toString());
		student1.setNombre(c.get(1).toString());
		student1.setApellidos(c.get(2).toString());
		student1.setPassword(c.get(3).toString());
		student1.setTitulacion(c.get(4).toString());
		student1.setCualificacion(c.get(5).toString());

		gestor.desconectarBD();

		return student1;
	}

	/**
	 * 
	 * @param estudiante
	 * @throws SQLException
	 */
	public int editarEstudiante(Estudiante estudiante) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		resultado = agente.update("update estudiantes " + "set( dni = '" + estudiante.getDni() + "',nombre='"
				+ estudiante.getNombre() + "',apellidos = '" + estudiante.getApellidos() + "', password = '"
				+ estudiante.getPassword() + "', titulacion ='" + estudiante.getTitulacion() + "', cualificacion ='"
				+ estudiante.getCualificacion() + "')");

		agente.desconectarBD();
		return resultado;
	}
}
