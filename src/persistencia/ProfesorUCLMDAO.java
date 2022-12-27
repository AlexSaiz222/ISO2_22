package persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.CategoriaProfesor;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;

public class ProfesorUCLMDAO extends AbstractEntityDAO{
	
	public List<ProfesorUCLM> listarProfesoresUCLM() throws SQLException {
		List<ProfesorUCLM> profesoresUCLM = new ArrayList<ProfesorUCLM>();
		GestorBD gestor = new GestorBD();
		
		List<Object> profesoresUCLMListados = gestor.select("select * from profesoresUCLM");
		CentroDAO centroDAO = new CentroDAO();
		ProfesorDAO profesorDAO = new ProfesorDAO();
		
		for(int i=0; i<profesoresUCLMListados.size(); i++) {
			ProfesorUCLM profesorUCLM = new ProfesorUCLM();
			List<Object> c = (List<Object>) profesoresUCLMListados.get(i);
			profesorUCLM.setDni(c.get(0).toString());
			profesorUCLM.setCentroAdscripcion(centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString())));
			profesorUCLM.setCategoria(CategoriaProfesor.valueOf(c.get(2).toString()));
			
			Profesor profesor = profesorDAO.seleccionarProfesor(profesorUCLM.getDni());
			profesorUCLM.setNombre(profesor.getNombre());
			profesorUCLM.setApellidos(profesor.getApellidos());
			profesorUCLM.setPassword(profesor.getPassword());
			profesorUCLM.setDoctor(profesor.isDoctor());
			
			profesoresUCLM.add(profesorUCLM);
		}
		
		return profesoresUCLM;
	}
	
	public ProfesorUCLM listarProfesorUCLM(String dni) throws SQLException {
		GestorBD gestor = new GestorBD();
		List<Object> profesorUCLMListado = gestor.select("select * from profesoresUCLM where dni='"+dni+"'");
		List<Object> profesorUCLM = (List<Object>) profesorUCLMListado.get(0);
		CentroDAO centroDAO = new CentroDAO();
		ProfesorDAO profesorDAO = new ProfesorDAO();
		Profesor profesorObtenido = profesorDAO.seleccionarProfesor(dni);
		
		// TODO Crear constructor vacío y añadir los atributos
		ProfesorUCLM profesor = new ProfesorUCLM(
				profesorObtenido.getDni(),
				profesorObtenido.getNombre(),
				profesorObtenido.getApellidos(),
				profesorObtenido.getPassword(),
				profesorObtenido.isDoctor(),
				centroDAO.seleccionarCentro(Integer.parseInt(profesorUCLM.get(0).toString())),
				CategoriaProfesor.valueOf(profesorUCLM.get(1).toString()));
		
		return profesor;
	}

	/**
	 * 
	 * @param profeUCLM
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 * @throws SQLException 
	 */

	public int crearProfesorUCLM(ProfesorUCLM profeUCLM) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		PreparedStatement pstmt;
		try {
			pstmt = agente.mBD.prepareStatement("insert into profesoresUCLM (dni, centroAdscripcion, categoria) "
					+ "values (?,?,?)");
			pstmt.setString(1, profeUCLM.getDni());
			pstmt.setInt(2, profeUCLM.getCentroAdscripcion().getIdCentro());
			pstmt.setString(3, profeUCLM.getCategoria().name());
			
			resultado = agente.insert(pstmt);
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("ProfesorUCLMDAO: "+e.getMessage());
		}
		
		return resultado;
	}

	/**
	 * 
	 * @param profeUCLM
	 * @throws SQLException 
	 */
	public ProfesorUCLM seleccionarProfesorUCLM(String profeUCLM) throws SQLException {
		List<Object>  resultado = new ArrayList<Object>();
		GestorBD gestor = new GestorBD();
		List<Object> profeUCLMListado = gestor.select("select * from profesoresUCLM where dni = "+profeUCLM);
		List<Object> c = (List<Object>) profeUCLMListado.get(0);
		
		ProfesorDAO profeDAO = new ProfesorDAO();
		Profesor profesor = profeDAO.seleccionarProfesor(c.get(0).toString());
		
		CentroDAO centroDAO = new CentroDAO();
		Centro centro = centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString()));
		
		ProfesorUCLM profe1 = new ProfesorUCLM();
		
		profe1.setCentroAdscripcion(centro);
		profe1.setDni(c.get(0).toString());
		profe1.setApellidos(profesor.getApellidos());
		profe1.setPassword(profesor.getPassword());
		profe1.setDoctor(profesor.isDoctor());
		profe1.setCategoria(profe1.getCategoria());
		profe1.setNombre(profesor.getNombre());
		gestor.desconectarBD();
		
		return profe1;
	}

	/**
	 * 
	 * @param profesorUCLM
	 * @throws SQLException 
	 */
	public int editarProfesorUCLM(ProfesorUCLM profesorUCLM) throws SQLException {
		int resultado = -1;
		GestorBD agente = new GestorBD();
	
		resultado = agente.update("update profesoresUCLM "
				+ "set( dni = '"+ profesorUCLM.getDni()+"',centroAdscripcion='"+profesorUCLM.getCentroAdscripcion().getIdCentro()
				+ "',categoria ="+profesorUCLM.getCategoria()+")");
		
		return resultado;
	}
}