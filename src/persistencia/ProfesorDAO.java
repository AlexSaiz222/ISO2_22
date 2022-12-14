package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.entities.Profesor;

public class ProfesorDAO {
	private static Logger logJava = Logger.getLogger(ProfesorDAO.class);
	private final String logFatal = "LOG FATAL: ";

	public List<Profesor> listarProfesores() throws SQLException {
		List<Profesor> profesores = new ArrayList<Profesor>();
		GestorBD gestor = new GestorBD();

		List<Object> profesoresListados = gestor.select("select * from profesores");

		for (int i = 0; i < profesoresListados.size(); i++) {
			Profesor profesor = new Profesor();
			List<Object> c = (List<Object>) profesoresListados.get(i);
			profesor.setDni(c.get(0).toString());
			profesor.setNombre(c.get(1).toString());
			profesor.setApellidos(c.get(2).toString());
			profesor.setPassword(c.get(3).toString());
			profesor.setDoctor(Boolean.getBoolean(c.get(4).toString()));

			profesores.add(profesor);
		}

		return profesores;
	}

	public Profesor seleccionarProfesor(String dni) throws SQLException {
		GestorBD gestor = new GestorBD();
		List<Object> profesorListado = gestor.select("select * from profesores where dni='" + dni + "'");
		List<Object> c = (List<Object>) profesorListado.get(0);
		Profesor profesor = new Profesor(c.get(0).toString(), c.get(1).toString(), c.get(2).toString(),
				c.get(3).toString(), Boolean.getBoolean(c.get(4).toString()));

		return profesor;
	}

	/**
	 * 
	 * @param profesor
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException
	 */

	public int crearProfesor(Profesor profesor) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD.prepareStatement(
					"insert into profesores (dni, nombre, apellidos,password, doctor) " + "values (?,?,?,?,?)");
			pstmt.setString(1, profesor.getDni());
			pstmt.setString(2, profesor.getNombre());
			pstmt.setString(3, profesor.getApellidos());
			pstmt.setString(4, profesor.getPassword());
			pstmt.setBoolean(5, profesor.isDoctor());

			resultado = agente.insert(pstmt);

		} catch (SQLException e) {
			logJava.fatal(logFatal+e.toString());
		} finally {
			if (pstmt != null)
				pstmt.close();
		}

		return resultado;
	}

	/**
	 * 
	 * @param profesor
	 * @throws SQLException
	 */
	public int editarProfesor(Profesor profesor) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		resultado = agente.update("update profesores " + "set( dni = '" + profesor.getDni() + "'," + "nombre='"
				+ profesor.getNombre() + "',apellidos ='" + profesor.getApellidos() + "'password ='"
				+ profesor.getNombre() + profesor.getApellidos() + "', " + "doctor = " + profesor.isDoctor() + ")");

		return resultado;
	}
}