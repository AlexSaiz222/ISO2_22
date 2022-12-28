package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import negocio.entities.PersonalVicerrectorado;

public class PersonalVicerrectoradoDAO {
	public PersonalVicerrectorado seleccionarProfesor(String dni) throws SQLException {
		GestorBD gestor = new GestorBD();
		List<Object> vicListado = gestor.select("select * from vicerrectorado where dni='" + dni + "'");
		List<Object> c = (List<Object>) vicListado.get(0);
		PersonalVicerrectorado vicerrectorado = new PersonalVicerrectorado(c.get(0).toString(), c.get(1).toString(),
				c.get(2).toString(), c.get(3).toString(), Boolean.getBoolean(c.get(4).toString()));

		return vicerrectorado;
	}

	/**
	 * 
	 * @param profesor
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException
	 */

	public int crearVicerrectorado(PersonalVicerrectorado vicerrectorado) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD.prepareStatement(
					"insert into vicerrectorado (dni, nombre, password, apellidos, doctor) " + "values (?,?,?,?,?)");
			pstmt.setString(1, vicerrectorado.getDni());
			pstmt.setString(2, vicerrectorado.getNombre());
			pstmt.setString(3, vicerrectorado.getApellidos());
			pstmt.setString(4, vicerrectorado.getNombre() + vicerrectorado.getApellidos());
			pstmt.setBoolean(5, vicerrectorado.isJefe());

			resultado = agente.insert(pstmt);

		} catch (SQLException e) {
			System.out.println("PersonalVicerrectoradorDAO: " + e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
		}

		return resultado;
	}

	/**
	 * 
	 * @param vicerrectorado
	 * @throws SQLException
	 */
	public int editarVicerrecotrado(PersonalVicerrectorado vicerrectorado) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		resultado = agente.update("update vicerrectorado " + "set( dni = '" + vicerrectorado.getDni() + "',"
				+ "nombre='" + vicerrectorado.getNombre() + "',apellidos ='" + vicerrectorado.getApellidos()
				+ "'password ='" + vicerrectorado.getNombre() + vicerrectorado.getApellidos() + "', " + "jefe = "
				+ vicerrectorado.isJefe() + ")");

		return resultado;
	}
}
