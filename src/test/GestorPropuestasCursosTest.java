package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;

public class GestorPropuestasCursosTest {
	
	private GestorPropuestasCursos gestor;
	private CursoPropio curso;
	private Centro centro;
	private ProfesorUCLM director;
	private Profesor secretario;
	private EstadoCurso estado;
	private TipoCurso tipo;
	private int idCurso;
	private int resultadoEsperado;

	@Before
	public void setUp() throws Exception {
		this.gestor = new GestorPropuestasCursos();
		this.centro = new Centro();					this.centro.setIdCentro(1);
		this.director = new ProfesorUCLM();			this.director.setDni("11111111B");
		this.secretario = new Profesor();			this.secretario.setDni("11111111B");
		this.estado = EstadoCurso.PROPUESTO;
		this.tipo = TipoCurso.MASTER;
		this.idCurso = 0;
		curso = new CursoPropio(this.centro, this.director, this.secretario, this.estado, this.tipo, this.idCurso, 
				"", 0, null, null, 0.00, 0);
	}
	
	/**
	 * Caso de Prueba CP1
	 */
	@Test
	public void realizarPropuestaCursoConNombreVacio() {
		this.resultadoEsperado = 1;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP2
	 */
	@Test
	public void realizarPropuestaCursoConLongitudNombreMayor() {
		this.curso.setNombre("Curso de Prueba perteneciente al centro de Talavera de la Reina");
		this.resultadoEsperado = 1;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP3
	 */
	@Test
	public void realizarPropuestaCursoECTSNegativo() {
		this.curso.setNombre("Curso de Prueba 2 perteneciente centro de Talavera");
		this.curso.setECTS(-5);
		this.resultadoEsperado = 2;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP4
	 */
	@Test
	public void realizarPropuestaCursoECTSCero() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(0);
		this.resultadoEsperado = 2;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP5
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioNula() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.resultadoEsperado = 3;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP6
	 */
	@Test
	public void realizarPropuestaCursoFechaFinNula() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(1000);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.resultadoEsperado = 4;
		
		System.out.println(curso.toString());
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP7
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioMayorQueFechaFin() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 22).getTime());
		this.resultadoEsperado = 4;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP8
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioIgualQueFechaFin() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.resultadoEsperado = 4;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP9
	 */
	@Test
	public void realizarPropuestaCursoTasaMatriculaNegativa() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(-27.5);
		this.resultadoEsperado = 5;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP10
	 */
	@Test
	public void realizarPropuestaCursoTasaMatriculaCero() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(0);
		this.resultadoEsperado = 5;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP11
	 */
	@Test
	public void realizarPropuestaCursoEdicionNegativa() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(250);
		this.curso.setEdicion(-4);
		this.resultadoEsperado = 6;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP12
	 */
	@Test
	public void realizarPropuestaCursoEdicionCero() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(250);
		this.curso.setEdicion(0);
		this.resultadoEsperado = 6;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP13
	 */
	@Test
	public void realizarPropuestaCursoCorrecta() {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(250);
		this.curso.setEdicion(3);
		this.resultadoEsperado = 0;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
		// TODO Eliminar el curso insertado
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

}