package persistencia;

import java.util.ArrayList;
import java.util.List;
import negocio.entities.Estudiante;

public class EstudianteDAO extends AbstractEntityDAO {
	
	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		GestorBD gestor = GestorBD.getAgente();
		
		List<Object> estudiantesListados = gestor.select("select * from estudiantes");
		
		for(int i=0; i<estudiantesListados.size(); i++) {
			Estudiante estudiante1 = new Estudiante();
			List<Object> c = (List<Object>) estudiantesListados.get(i);
			estudiante1.setDni(c.get(0).toString());
			
		}
		
		gestor.desconectarBD();
		return estudiantes;
	}
	
	public Estudiante seleccionarEstudiante(String dni) {
		
		GestorBD gestor = GestorBD.getAgente();
		List<Object> estudiantesListados = gestor.select("select * from estudiantes where dni='"+dni+"'");
		List<Object> c = (List<Object>) estudiantesListados.get(0);
		Estudiante estudiante1 = new Estudiante(
				c.get(0).toString(),
				c.get(1).toString(),
				c.get(2).toString(),
				c.get(3).toString(),
				c.get(4).toString());
		
		gestor.desconectarBD();
		return estudiante1;
	}


}
