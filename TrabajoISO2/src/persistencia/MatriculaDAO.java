package persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Matricula;
import negocio.entities.TipoCurso;

public class MatriculaDAO extends AbstractEntityDAO{

	/**
	 * 
	 * @param curso
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearNuevaMatricula(Matricula matricula) {
		int resultado = -1;
		GestorBD agente = GestorBD.getAgente();
		
		// TODO 
		resultado = agente.insert("insert into matriculas (idmatriculas,idestudiante,idcursopropio,tipopago,fecha,pagado) "
				+ "values ("+matricula.getIdMatricula()+",'"+matricula.getEstudiante().getIdEstudiante()+",'"+matricula.getTitulo().getId()
				+",'"+matricula.getTipoPago()+"',"+matricula.getFecha()+","+matricula.isPagado()+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 */
	public List<Object> seleccionarMatricula(Matricula matricula) {
		GestorBD agente = GestorBD.getAgente();
		List<Object>  resultado = new ArrayList<Object>();
		
		resultado = agente.select("select * from matriculas where idmatricula = "+matricula.getIdMatricula());
		agente.desconectarBD();
		
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 */
	public int editarMatricula(Matricula matricula) {
		int resultado = -1;
	GestorBD agente = GestorBD.getAgente();

	resultado = agente.update("update matriculas "
			+ "set( idestudiante = "+ matricula.getEstudiante().getIdEstudiante()+",idcursopropio="+matricula.getTitulo().getId()
			+ ",tipopago ='"+matricula.getTipoPago()+"', fecha = "+matricula.getFecha()
			+ ", pagado ="+matricula.getTipoPago()+")");
	
	agente.desconectarBD();
	return resultado;
	}

}