package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.entities.Centro;
import negocio.entities.Profesor;
import negocio.entities.ProfesorExterno;

public class ProfesorExternoDAO extends AbstractEntityDAO {
	
	private static Logger logJava = Logger.getLogger(ProfesorExternoDAO.class);
	private final String logFatal = "LOG FATAL: ";

	/**
	 * 
	 * @param profeExterno
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException
	 */

	public int crearProfesorExterno(ProfesorExterno profeExterno) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD.prepareStatement("insert into profesoresExternos (dni ,titulacion) " + "values (?,?)");
			pstmt.setString(1, profeExterno.getDni());
			pstmt.setString(2, profeExterno.getTitulacion());

			resultado = agente.insert(pstmt);
			pstmt.close();

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
	 * @param profeExterno
	 * @throws SQLException
	 */
	public ProfesorExterno seleccionarProfesorExterno(String profeExterno) throws SQLException {

		GestorBD gestor = new GestorBD();
		List<Object> profeExternoListado = gestor
				.select("select * from profesoresExternos where dni = " + profeExterno);
		List<Object> c = (List<Object>) profeExternoListado.get(0);

		ProfesorDAO profeDAO = new ProfesorDAO();
		Profesor profesor = profeDAO.seleccionarProfesor(c.get(0).toString());

		CentroDAO centroDAO = new CentroDAO();
		Centro centro = centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString()));

		ProfesorExterno profe1 = new ProfesorExterno();

		profe1.setDni(c.get(0).toString());
		profe1.setApellidos(profesor.getApellidos());
		profe1.setPassword(profesor.getPassword());
		profe1.setDoctor(profesor.isDoctor());
		profe1.setTitulacion(c.get(1).toString());
		profe1.setNombre(profesor.getNombre());

		return profe1;
	}

	/**
	 * 
	 * @param profeExterno
	 * @throws SQLException
	 */
	public int editarProfesorExterno(ProfesorExterno profeExterno) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		resultado = agente.update("update profesoresExternos " + "set( dni = '" + profeExterno.getDni()
				+ "',titulacion='" + profeExterno.getTitulacion() + "')");

		return resultado;
	}
}