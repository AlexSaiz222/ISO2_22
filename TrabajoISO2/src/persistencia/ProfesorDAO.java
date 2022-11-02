package persistencia;

import java.util.List;

import negocio.entities.Profesor;

public class ProfesorDAO {
	
	public Profesor listarProfesor(String dni) {
		GestorBD gestor = GestorBD.getAgente();
		List<Object> profesorListado = gestor.select("select * from profesores where dni="+dni);
		List<Object> c = (List<Object>) profesorListado.get(0);
		Profesor profesor = new Profesor(
				c.get(0).toString(),
				c.get(1).toString(),
				c.get(2).toString(),
				Boolean.getBoolean(c.get(3).toString()));
		
		return profesor;
	}

}
