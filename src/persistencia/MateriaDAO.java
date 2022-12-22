package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import negocio.entities.Materia;
import negocio.entities.Profesor;


public class MateriaDAO extends AbstractEntityDAO {

	/**
	 * 
	 * @param materia
	 * @return resultado. 0 si correcto. -1 si incorrecto.
	 */

	public int crearMatricula(Materia materia) {
		int resultado = -1;
		GestorBD agente = new GestorBD();
		
		// Formateo de las fechas para la inserción en la BD
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date fechaInicio = Date.valueOf(simpleDateFormat.format(materia.getFechaInicio()));
		Date fechaFin = Date.valueOf(simpleDateFormat.format(materia.getFechaFin()));
		
		PreparedStatement pstmt;
		try {
			pstmt = agente.mBD.prepareStatement("insert into materias (responsable, nombre , horas , fechaInicio , fechaFin) "
					+ "values (?,?,?,?,?)");
			pstmt.setString(2, materia.getResponsable().getNombre());
			pstmt.setString(3, materia.getNombre());
			pstmt.setDouble(4, materia.getHoras());
			pstmt.setDate(8, fechaInicio);
			pstmt.setDate(9, fechaFin);
			
			resultado = agente.insert(pstmt);
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("MateriaDAO: "+e.getMessage());
		}
		
		return resultado;
	}

	/**
	 * 
	 * @param materia
	 * @throws ParseException 
	 */
	public Materia seleccionarMatricula(int materia) throws ParseException {
		GestorBD agente = new GestorBD();
		List<Object>  resultado = new ArrayList<Object>();
				
		GestorBD gestor = new GestorBD();
		List<Object> materiaListado = gestor.select("select * from materias where idMateria = "+materia);
		List<Object> c = (List<Object>) materiaListado.get(0);
		
		Materia mat1 = new Materia();
		
		ProfesorDAO responsableDAO = new ProfesorDAO();
		Profesor responsable = responsableDAO.seleccionarProfesor(c.get(1).toString());
		
		SimpleDateFormat formato = new SimpleDateFormat("d/MMM/y");
		Date fechaFin = (Date) formato.parse(c.get(5).toString());
		Date fechaInicio = (Date) formato.parse(c.get(4).toString());
		
		mat1.setFechaFin(fechaFin);
		mat1.setFechaInicio(fechaInicio);
		mat1.setHoras(Double.parseDouble(c.get(3).toString()));
		mat1.setIdMateria(materia);
		mat1.setNombre(c.get(2).toString());
		mat1.setResponsable(responsable);
		
		gestor.desconectarBD();
		
		return mat1;
	}

	/**
	 * 
	 * @param curso
	 */
	public int editarMateria(Materia materia) {
		int resultado = -1;
	GestorBD agente = new GestorBD();

	resultado = agente.update("update materias "
			+ "set( idMateria = "+ materia.getIdMateria()+","
			+ "responsable='"+materia.getResponsable()
			+ "',nombre ='"+materia.getNombre()
			+"', horas = "+materia.getHoras()
			+ ", fechaInicio ="+materia.getFechaInicio()
			+ ", fechaFin ="+materia.getFechaFin()+")");
	
	agente.desconectarBD();
	return resultado;
	}

}