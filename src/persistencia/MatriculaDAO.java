package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import negocio.entities.ModoPago;
import negocio.entities.TipoCurso;

public class MatriculaDAO {

	/**
	 * 
	 * @param matricula
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearMatricula(Matricula matricula) {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		// Formateo de la fecha para la insercion en la BD
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date fecha = Date.valueOf(simpleDateFormat.format(matricula.getFecha()));
		
		PreparedStatement pstmt;
		try {
			pstmt = agente.mBD.prepareStatement("insert into matriculas (idestudiante, idcursopropio, tipopago, fecha, pagado) "
					+ "values (?,?,?,?,?,?)");
			pstmt.setString(1, matricula.getEstudiante().getDni());
			pstmt.setInt(2, matricula.getTitulo().getId());
			pstmt.setString(3, matricula.getTipoPago().name());
			pstmt.setDate(4, fecha);
			pstmt.setBoolean(5, matricula.isPagado());
			
			resultado = agente.insert(pstmt);
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("MatriculaDAO: "+e.getMessage());
		}
		
		return resultado;
	}

	/**
	 * 
	 * @param matricula
	 * @throws ParseException 
	 */
	public Matricula seleccionarMatricula(int matricula) throws ParseException {
		GestorBD agente = new GestorBD();
		List<Object> matriculaListado = agente.select("select * from matriculas where idmatricula = "+matricula);
		List<Object> c = (List<Object>) matriculaListado.get(0);
		
		Matricula mat1 = new Matricula();
		
		EstudianteDAO estudianteDAO = new EstudianteDAO();
		Estudiante estudiante = estudianteDAO.seleccionarEstudiante(c.get(1).toString());
		
		SimpleDateFormat formato = new SimpleDateFormat("d/MMM/y");
		Date fecha = (Date) formato.parse(c.get(4).toString());
		
		CursoPropioDAO cursoDAO = new CursoPropioDAO();
		CursoPropio curso = cursoDAO.seleccionarCurso(Integer.parseInt(c.get(2).toString()));
		
		ModoPago tipopago = null;
		tipopago.valueOf(c.get(3).toString());
		
		mat1.setIdMatricula(Integer.parseInt(c.get(0).toString()));
		mat1.setTitulo(curso);
		mat1.setEstudiante(estudiante);
		mat1.setFecha(fecha);
		mat1.setTipoPago(tipopago);
		mat1.setPagado(Boolean.parseBoolean(c.get(5).toString()));
		
		return mat1;
	}

	/**
	 * 
	 * @param curso
	 */
	public int editarMatricula(Matricula matricula) {
		int resultado = -1;
		GestorBD agente = new GestorBD();
	
		resultado = agente.update("update matriculas "
				+ "set( idestudiante = "+ matricula.getEstudiante().getIdEstudiante()+",idcursopropio="+matricula.getTitulo().getId()
				+ ",tipopago ='"+matricula.getTipoPago()+"', fecha = "+matricula.getFecha()
				+ ", pagado ="+matricula.getTipoPago()+")");
		
		return resultado;
	}

}
