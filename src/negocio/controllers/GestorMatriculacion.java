package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import negocio.entities.CursoPropio;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import negocio.entities.ModoPago;
import persistencia.GestorBD;
import persistencia.MatriculaDAO;

public class GestorMatriculacion {

	private static Logger logJava = Logger.getLogger(GestorMatriculacion.class);
	/**
	 * 
	 * @param curso
	 * @param estudiante
	 * @throws SQLException
	 */
	public void realizarMatriculacion(CursoPropio curso, Estudiante estudiante) throws SQLException {

		GestorBD gestor = new GestorBD();
		List<Object> matriculaListado = gestor.select("select * from matriculas where " + "idEstudiante = '"
				+ estudiante.getDni() + "' and idCursoPropio = " + curso.getId());

		MatriculaDAO matriculaDAO = new MatriculaDAO();
		Matricula matricula = new Matricula();

		List<Object> t = (List<Object>) matriculaListado.get(0);
		try {
			matricula = matriculaDAO.seleccionarMatricula((int) t.get(0));
		} catch (ParseException e) {
			logJava.fatal("LOG FATAL: "+e.toString());
		}

		Collection<Matricula> mCollection = estudiante.getMatriculas();
		mCollection.add(matricula);
		estudiante.setMatriculas(mCollection);

	}

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 * @throws SQLException
	 */
	public void realizarPagoMatricula(CursoPropio curso, Estudiante estudiante) throws SQLException {

		GestorBD gestor = new GestorBD();
		List<Object> matriculaListado = gestor.select("select * from matriculas where " + "idEstudiante = '"
				+ estudiante.getDni() + "' and idCursoPropio = " + curso.getId());

		MatriculaDAO matriculaDAO = new MatriculaDAO();
		Matricula matricula = new Matricula();

		List<Object> t = (List<Object>) matriculaListado.get(0);
		try {
			matricula = matriculaDAO.seleccionarMatricula((int) t.get(0));
		} catch (ParseException e) {
			logJava.fatal("LOG FATAL: "+e.toString());
		}

		if (matricula.getTipoPago() == ModoPago.TARJETA_CREDITO) {
			realizarPagoTarjeta(matricula);
		} else if (matricula.getTipoPago() == ModoPago.TRANSFERENCIA) {
			realizarPagoTransferencia(matricula);
		}

	}

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 */
	private void realizarPagoTarjeta(Matricula matricula) {
		// TODO - implement GestorMatriculacion.realizarPagoTarjeta
		matricula.setPagado(true);

	}

	/**
	 * 
	 * @param curso
	 * @param estudiante
	 */
	private void realizarPagoTransferencia(Matricula matricula) {
		// TODO - implement GestorMatriculacion.realizarPagoTransferencia
		matricula.setPagado(true);
	}

}