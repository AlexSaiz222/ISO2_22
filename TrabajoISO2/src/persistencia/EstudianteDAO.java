package persistencia;

import java.util.ArrayList;
import java.util.List;

import negocio.entities.Estudiante;

public class EstudianteDAO extends AbstractEntityDAO {
	
	public int crearEstudiante(Estudiante estudiante) {
		int resultado = -1;
		GestorBD agente = GestorBD.getAgente();
		
		resultado = agente.insert("insert into ESTUDIANTES (DNI, NOMBRE, APELLIDOS, PASSWORD, TITULACION, CUALIFICACION) "
				+ "values ('"+estudiante.getDni()+"','"+estudiante.getNombre()+"',"
				+ "'"+estudiante.getApellidos()+"','"+estudiante.getPsswd()+"','"+estudiante.getTitulacion()+"','"+estudiante.getCualificacion()+"','"+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param estudiante
	 */
	public Estudiante seleccionarEstudiante(String estudiante) {
		GestorBD agente = GestorBD.getAgente();
		List<Object>  resultado = new ArrayList<Object>();
			
		GestorBD gestor = GestorBD.getAgente();
		List<Object> estudianteListado = gestor.select("select * from ESTUDIANTES where dni = "+estudiante);
		List<Object> c = (List<Object>) estudianteListado.get(0);
		
		Estudiante student1 = new Estudiante();
		
		student1.setDni(c.get(0).toString());
		student1.setNombre(c.get(1).toString());
		student1.setApellidos(c.get(2).toString());
		student1.setPsswd(c.get(3).toString());
		student1.setTitulacion(c.get(4).toString());
		student1.setCualificacion(c.get(5).toString());
		gestor.desconectarBD();
		
		return student1;
	}

	/**
	 * 
	 * @param estudiante
	 */
	public int editarEstudiante(Estudiante estudiante) {
		int resultado = -1;
	GestorBD agente = GestorBD.getAgente();

	resultado = agente.update("update estudiantes "
			+ "set( dni = '"+ estudiante.getDni()+"',nombre='"+estudiante.getNombre()
			+ "',apellidos = '"+estudiante.getApellidos()+"', password = '"+estudiante.getPsswd()
			+ "', titulacion ='"+estudiante.getTitulacion()+"', cualificacion ='"+estudiante.getCualificacion()+"')");
	
	agente.desconectarBD();
	return resultado;
	}
}