package persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import negocio.entities.TipoCurso;

public class MatriculaDAO extends AbstractEntityDAO{

	/**
	 * 
	 * @param matricula
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearMatricula(Matricula matricula) {
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
	 * @param matricula
	 */
	public Matricula seleccionarMatricula(int matricula) {
		GestorBD agente = GestorBD.getAgente();
		List<Object>  resultado = new ArrayList<Object>();
				
		GestorBD gestor = GestorBD.getAgente();
		List<Object> matriculaListado = gestor.select("select * from matriculas where idmatricula = "+matricula);
		List<Object> c = (List<Object>) matriculaListado.get(0);
		
		Matricula mat1 = new Matricula();
		
		mat1.setIdMatricula(Integer.parseInt(c.get(0).toString()));
		
		EstudianteDAO estudianteDAO = new EstudianteDAO();
		Estudiante estudiante = estudianteDAO.seleccionarEstudiante(c.get(1).toString());
		
		mat1.setEstudiante(estudiante);
		//como hacer esto
		mat1.setFecha(c.get(2).toString());
		
		mat1.setPagado(Boolean.parseBoolean(c.get(3).toString()));
		mat1.setTitulacion(c.get(4).toString());
		mat1.setCualificacion(c.get(5).toString());
		gestor.desconectarBD();
		
		return mat1;
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

