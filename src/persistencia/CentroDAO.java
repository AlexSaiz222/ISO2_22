package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.controllers.GestorMatriculacion;
import negocio.entities.Centro;

public class CentroDAO {

	private static Logger logJava = Logger.getLogger(CentroDAO.class);
	
	public List<Centro> listarCentros() throws SQLException {
		List<Centro> centros = new ArrayList<Centro>();
		GestorBD gestor = new GestorBD();

		List<Object> centrosListados = gestor.select("select * from centros");

		for (int i = 0; i < centrosListados.size(); i++) {
			Centro centro = new Centro();
			List<Object> c = (List<Object>) centrosListados.get(i);
			centro.setIdCentro(Integer.parseInt(c.get(0).toString()));
			centro.setNombre(c.get(1).toString());
			centro.setLocalizacion(c.get(2).toString());
			centros.add(centro);
		}

		return centros;
	}

	public Centro seleccionarCentro(int idCentro) throws SQLException {
		GestorBD gestor = new GestorBD();
		List<Object> centroListado = gestor.select("select * from centros where idcentro=" + idCentro);
		List<Object> c = (List<Object>) centroListado.get(0);
		Centro centro = new Centro(Integer.parseInt(c.get(0).toString()), c.get(1).toString(), c.get(2).toString());

		gestor.desconectarBD();
		return centro;
	}

	/**
	 * 
	 * @param centro
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException
	 */

	public int crearCentro(Centro centro) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		PreparedStatement pstmt = null;
		try {
			pstmt = agente.mBD
					.prepareStatement("insert into centros (idcentro, nombre, localizacion) " + "values (?,?,?)");
			pstmt.setInt(1, centro.getIdCentro());
			pstmt.setString(2, centro.getNombre());
			pstmt.setString(3, centro.getLocalizacion());

			resultado = agente.insert(pstmt);

		} catch (SQLException e) {
			logJava.fatal("LOG FATAL: "+e.toString());
		} finally {
			if (pstmt != null)
				pstmt.close();
		}

		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param centro
	 * @throws SQLException
	 */
	public int editarCentro(Centro centro) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();

		resultado = agente.update("update centros " + "set( idcentro = " + centro.getIdCentro() + ",localizacion ='"
				+ centro.getLocalizacion() + ", nombre =" + centro.getNombre() + ")");

		agente.desconectarBD();
		return resultado;
	}

}