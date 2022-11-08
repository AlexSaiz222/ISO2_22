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
		resultado = agente.insert("insert into matriculas (idmatriculas,isestudiante,idcursopropio,tipopago,fecha,pagado) "
				+ "values ("+matricula.getIdMatricula()+",'"+matricula.getEstudiante().getIdEstudiante()+",'"+matricula.getTitulo().getId()
				+",'"+matricula.getTipoPago()+",'"+matricula.getFecha()+",'"+matricula.isPagado()+")");
		
		agente.desconectarBD();
		return resultado;
	}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.seleccionarCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public CursoPropio editarCurso(CursoPropio curso) {
		// TODO - implement CursoPropioDAO.editarCurso
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param estado
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarCursosPorEstado
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public double listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarIngresos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public void listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement CursoPropioDAO.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}
	
	public static List<CursoPropio> listarCursosPropiosPorPago(EstadoCurso estado) {
        // TODO Auto-generated method stub
        List<CursoPropio> cursos = new ArrayList<CursoPropio>();
        GestorBD gestor = GestorBD.getAgente();

        List<Object> cursosListados = gestor.select("select * from cursosPropios where estado='"+estado+"'");

        for(int i=0; i<cursosListados.size(); i++) {
            CursoPropio cursoPropio = new CursoPropio();
            List<Object> t = (List<Object>) cursosListados.get(i);
            cursoPropio.setNombre(t.get(1).toString());

            cursos.add(cursoPropio);
        }

        gestor.desconectarBD();
        return cursos;
    }

}

