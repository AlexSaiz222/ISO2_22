package persistencia;

import java.util.ArrayList;
import java.util.List;

import negocio.entities.CategoriaProfesor;
import negocio.entities.Centro;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;

public class ProfesorUCLMDAO {
	
	public List<ProfesorUCLM> listarProfesoresUCLM() {
		List<ProfesorUCLM> profesoresUCLM = new ArrayList<ProfesorUCLM>();
		GestorBD gestor = GestorBD.getAgente();
		
		List<Object> profesoresUCLMListados = gestor.select("select * from profesoresUCLM");
		CentroDAO centroDAO = new CentroDAO();
		ProfesorDAO profesorDAO = new ProfesorDAO();
		
		for(int i=0; i<profesoresUCLMListados.size(); i++) {
			ProfesorUCLM profesorUCLM = new ProfesorUCLM();
			List<Object> c = (List<Object>) profesoresUCLMListados.get(i);
			profesorUCLM.setDni(c.get(0).toString());
			profesorUCLM.setCentroAdscripcion(centroDAO.seleccionarCentro(Integer.parseInt(c.get(1).toString())));
			profesorUCLM.setCategoria(CategoriaProfesor.valueOf(c.get(2).toString()));
			
			Profesor profesor = profesorDAO.listarProfesor(profesorUCLM.getDni());
			profesorUCLM.setNombre(profesor.getNombre());
			profesorUCLM.setApellidos(profesor.getApellidos());
			profesorUCLM.setDoctor(profesor.isDoctor());
			
			profesoresUCLM.add(profesorUCLM);
		}
		
		gestor.desconectarBD();
		return profesoresUCLM;
	}

}
