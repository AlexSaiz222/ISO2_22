package test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import negocio.controllers.GestorConsultas;
import negocio.entities.EstadoCurso;

public class GestorConsultasTest {
	private GestorConsultas gestorConsultas;
	private EstadoCurso estadoCurso;

	@Before
	public void setUp() throws Exception {
		gestorConsultas = new GestorConsultas();
	}

	@Test
	public void fechaInicioMayorQueFechaFin() {
		Date fechaInicio = new Date(122,11,28); 
		Date fechaFinDate = new Date(112,11,27); 

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void fechaInicioIgualQueFechaFin() {
		Date fechaInicio = new Date(122,11,29); 
		Date fechaFinDate = new Date(122,11,29); 

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void FechaInicioBienFechaFinNull() {
		Date fechaInicio = new Date(122,7,24); 
		Date fechaFinDate = null;

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void FechaInicioNullFechaFinBien() {
		Date fechaInicio = null; 
		Date fechaFinDate = new Date(123,8,29);

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void FechaInicioMenorFechaFin() {
		Date fechaInicio = new Date(101,8,4);
		Date fechaFinDate = new Date(104,0,16);

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertTrue(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioFechaFinNull() {
		estadoCurso = EstadoCurso.PROPUESTO; 
		Date fechaInicio = new Date(119,4,24);
		Date fechaFinDate = null;

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioNullFechaFin() {
		estadoCurso = EstadoCurso.TERMINADO; 
		Date fechaInicio = null;
		Date fechaFinDate = new Date(120,4,5);

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioMayorFechaFin() {
		estadoCurso = EstadoCurso.EN_MATRICULACION; 
		Date fechaInicio = new Date(122,11,28);
		Date fechaFinDate = new Date(122,11,27);

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioIgualFechaFin() {
		estadoCurso = EstadoCurso.EN_IMPARTICION; 
		Date fechaInicio = new Date(121,1,2);
		Date fechaFinDate = new Date(121,1,2);

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoNULLFechaInicioBienFechaFinBien() {
		estadoCurso = null; 
		Date fechaInicio = new Date(120,0,21);
		Date fechaFinDate = new Date(120,1,22);

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoBienFechaInicioBienFechaFinBien() {
		estadoCurso = EstadoCurso.PROPUESTA_RECHAZADA; 
		Date fechaInicio = new Date(120,0,21);
		Date fechaFinDate = new Date(120,1,22);

		boolean resultado = gestorConsultas.ComprobarEstadoCursoConFecha(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertTrue(resultado);
	}
	@Test
	public void EstadoCursoNull() {
		estadoCurso = null;

		boolean resultado = gestorConsultas.ComprobarEstado(estadoCurso);

		Assert.assertFalse(resultado);
	}
	@Test
	public void EstadoCursoPropuesto() {
		estadoCurso = EstadoCurso.PROPUESTO;

		boolean resultado = gestorConsultas.ComprobarEstado(estadoCurso);

		Assert.assertTrue(resultado);
	}
	@After
	public void tearDown() throws Exception {

	}
}
