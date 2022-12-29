package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import negocio.controllers.GestorConsultas;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;

public class GestorConsultasTest {
	private GestorConsultas gestorConsultas;
	private EstadoCurso estadoCurso;

	@Before
	public void setUp() throws Exception {
		gestorConsultas = new GestorConsultas();
	}

	@Test
	public void fechaInicioMayorQueFechaFin() throws SQLException {
		Date fechaInicio = new Date(122,11,28); 
		Date fechaFinDate = new Date(112,11,27); 

		List<CursoPropio> resultado = gestorConsultas.listarEdicionesCursos(fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void fechaInicioIgualQueFechaFin() throws SQLException {
		Date fechaInicio = new Date(122,11,29); 
		Date fechaFinDate = new Date(122,11,29); 

		List<CursoPropio> resultado = gestorConsultas.listarEdicionesCursos(fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void FechaInicioBienFechaFinNull() throws SQLException {
		Date fechaInicio = new Date(122,7,24); 
		Date fechaFinDate = null;

		List<CursoPropio> resultado = gestorConsultas.listarEdicionesCursos(fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void FechaInicioNullFechaFinBien() throws SQLException {
		Date fechaInicio = null; 
		Date fechaFinDate = new Date(123,8,29);

		List<CursoPropio> resultado = gestorConsultas.listarEdicionesCursos(fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void FechaInicioMenorFechaFin() {
		Date fechaInicio = new Date(101,8,4);
		Date fechaFinDate = new Date(104,0,16);

		boolean resultado = gestorConsultas.ComprobarFechas(fechaInicio, fechaFinDate);

		Assert.assertTrue(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioFechaFinNull() throws SQLException {
		estadoCurso = EstadoCurso.PROPUESTO; 
		Date fechaInicio = new Date(119,4,24);
		Date fechaFinDate = null;

		List<CursoPropio> resultado = gestorConsultas.consultarEstadoCursos(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioNullFechaFin() throws SQLException {
		estadoCurso = EstadoCurso.TERMINADO; 
		Date fechaInicio = null;
		Date fechaFinDate = new Date(120,4,5);

		List<CursoPropio> resultado = gestorConsultas.consultarEstadoCursos(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioMayorFechaFin() throws SQLException {
		estadoCurso = EstadoCurso.EN_MATRICULACION; 
		Date fechaInicio = new Date(122,11,28);
		Date fechaFinDate = new Date(122,11,27);

		List<CursoPropio> resultado = gestorConsultas.consultarEstadoCursos(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void EstadoCursoCORRECTOFechaInicioIgualFechaFin() throws SQLException {
		estadoCurso = EstadoCurso.EN_IMPARTICION; 
		Date fechaInicio = new Date(121,1,2);
		Date fechaFinDate = new Date(121,1,2);

		List<CursoPropio> resultado = gestorConsultas.consultarEstadoCursos(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
	}
	@Test
	public void EstadoCursoNULLFechaInicioBienFechaFinBien() throws SQLException {
		estadoCurso = null; 
		Date fechaInicio = new Date(120,0,21);
		Date fechaFinDate = new Date(120,1,22);

		List<CursoPropio> resultado = gestorConsultas.consultarEstadoCursos(estadoCurso,fechaInicio, fechaFinDate);

		Assert.assertNull(resultado);
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
	public void EstadoCursoNull() throws SQLException {
		estadoCurso = null;

		List<CursoPropio> resultado = gestorConsultas.listarCursosPropiosPorEstado(estadoCurso);

		Assert.assertNull(resultado);
	}
	@Test
	public void EstadoCursoPropuesto() throws SQLException {
		estadoCurso = EstadoCurso.PROPUESTO;

		List<CursoPropio> resultado = gestorConsultas.listarCursosPropiosPorEstado(estadoCurso);
		int numero_cursos = resultado.size();
		int numero_cursos_esperados = 0;
		
		Assert.assertTrue(numero_cursos == numero_cursos_esperados);
	}
}
