package persistencia;

import java.util.ArrayList;
import java.util.List;

import negocio.entities.Centro;
import negocio.entities.Matricula;

public class CentroDAO extends AbstractEntityDAO{
	
	public List<Centro> listarCentros() {
		List<Centro> centros = new ArrayList<Centro>();
		GestorBD gestor = GestorBD.getAgente();
		
		List<Object> centrosListados = gestor.select("select * from centros");
		
		for(int i=0; i<centrosListados.size(); i++) {
			Centro centro = new Centro();
			List<Object> c = (List<Object>) centrosListados.get(i);
			centro.setIdCentro(Integer.parseInt(c.get(0).toString()));
			centro.setNombre(c.get(1).toString());
			centro.setLocalizacion(c.get(2).toString());
			centros.add(centro);
		}
		
		gestor.desconectarBD();
		return centros;
	}
	
	public Centro seleccionarCentro(int idCentro) {
		GestorBD gestor = GestorBD.getAgente();
		List<Object> centroListado = gestor.select("select * from centros where idcentro="+idCentro);
		List<Object> c = (List<Object>) centroListado.get(0);
		Centro centro = new Centro(
				Integer.parseInt(c.get(0).toString()),
				c.get(1).toString(),
				c.get(2).toString());
		
		gestor.desconectarBD();
		return centro;
	}
	
	/**
	 * 
	 * @param centro
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearCentro(Centro centro) {
		int resultado = -1;
		GestorBD agente = GestorBD.getAgente();
		
		// TODO 
		resultado = agente.insert("insert into centros (idcentro,nombre,localizacion) "
				+ "values ("+centro.getIdCentro()+",'"+centro.getNombre()+","+centro.getLocalizacion()+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param centro
	 */
	public int editarCentro(Centro centro) {
		int resultado = -1;
	GestorBD agente = GestorBD.getAgente();

	resultado = agente.update("update centros "
			+ "set( idcentro = "+ centro.getIdCentro()
			+ ",localizacion ='"+centro.getLocalizacion()
			+ ", nombre ="+centro.getNombre()+")");
	
	agente.desconectarBD();
	return resultado;
	}

}
