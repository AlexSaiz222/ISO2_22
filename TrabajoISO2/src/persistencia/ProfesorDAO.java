package persistencia;

import java.util.ArrayList;
import java.util.List;

import negocio.entities.CategoriaProfesor;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.Profesor;

public class ProfesorDAO {
	
	public List<Profesor> listarProfesores() {
		List<Profesor> profesores = new ArrayList<Profesor>();
		GestorBD gestor = GestorBD.getAgente();
		
		List<Object> profesoresListados = gestor.select("select * from profesores");
		
		for(int i=0; i<profesoresListados.size(); i++) {
			Profesor profesor = new Profesor();
			List<Object> c = (List<Object>) profesoresListados.get(i);
			profesor.setDni(c.get(0).toString());
			profesor.setNombre(c.get(1).toString());
			profesor.setApellidos(c.get(2).toString());
			profesor.setDoctor(Boolean.getBoolean(c.get(3).toString()));
			
			profesores.add(profesor);
		}
		
		gestor.desconectarBD();
		return profesores;
	}
	
	public Profesor seleccionarProfesor(String dni) {
		GestorBD gestor = GestorBD.getAgente();
		List<Object> profesorListado = gestor.select("select * from profesores where dni='"+dni+"'");
		List<Object> c = (List<Object>) profesorListado.get(0);
		Profesor profesor = new Profesor(
				c.get(0).toString(),
				c.get(1).toString(),
				c.get(2).toString(),
				Boolean.getBoolean(c.get(3).toString()));
		
		gestor.desconectarBD();
		return profesor;
	}

	/**
	 * 
	 * @param profesor
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearProfesor(Profesor profesor) {
		int resultado = -1;
		GestorBD agente = GestorBD.getAgente();
		
		resultado = agente.insert("insert into profesores (dni,nombre,apellidos,doctor) "
				+ "values ('"+profesor.getDni()+"','"+profesor.getNombre()+"',"
				+ "'"+profesor.getApellidos()+"',"+profesor.isDoctor()+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param profesor
	 */
	public int editarProfesor(Profesor profesor) {
		int resultado = -1;
	GestorBD agente = GestorBD.getAgente();

	resultado = agente.update("update profesores "
			+ "set( dni = '"+ profesor.getDni()+"',"
					+ "nombre='"+profesor.getNombre()
			+ "',apellidos ='"+profesor.getApellidos()+"', "
					+ "doctor = "+profesor.isDoctor()+")");
	
	agente.desconectarBD();
	return resultado;
	}
}