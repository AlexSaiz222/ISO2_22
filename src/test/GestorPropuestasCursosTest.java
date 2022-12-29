package test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoConNombreVacio() throws SQLException {
		this.resultadoEsperado = 1;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP2
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoConLongitudNombreMayor() throws SQLException {
		this.curso.setNombre("Curso de Prueba perteneciente al centro de Talavera de la Reina");
		this.resultadoEsperado = 1;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP3
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoECTSNegativo() throws SQLException {
		this.curso.setNombre("Curso de Prueba 2 perteneciente centro de Talavera");
		this.curso.setECTS(-5);
		this.resultadoEsperado = 2;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP4
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoECTSCero() throws SQLException {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(0);
		this.resultadoEsperado = 2;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP5
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioNula() throws SQLException {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.resultadoEsperado = 3;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP6
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoFechaFinNula() throws SQLException {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(1000);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.resultadoEsperado = 4;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
	}
	
	/**
	 * Caso de Prueba CP7
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioMayorQueFechaFin() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoFechaInicioIgualQueFechaFin() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoTasaMatriculaNegativa() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoTasaMatriculaCero() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoEdicionNegativa() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoEdicionCero() throws SQLException {
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
	 * @throws SQLException 
	 */
	@Test
	public void realizarPropuestaCursoCorrecta() throws SQLException {
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(250);
		this.curso.setEdicion(3);
		this.resultadoEsperado = 0;
		
		int resultado = gestor.realizarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
		
	}
	
	/**
	 * Caso de Prueba CP14
	 * @throws SQLException 
	 */
	@Test
	public void editarPropuestaCursoCorrecta() throws SQLException {
		this.curso.setId(this.curso.getCursoPropioDao().obtenerUltimoCurso());
		this.curso.setNombre("Curso de Prueba");
		this.curso.setECTS(6);
		this.curso.setFechaInicio(new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime());
		this.curso.setFechaFin(new GregorianCalendar(2022, Calendar.DECEMBER, 24).getTime());
		this.curso.setTasaMatricula(250);
		this.curso.setEdicion(3);
		this.resultadoEsperado = 0;
		
		int resultado = gestor.editarPropuestaCurso(curso);
		Assert.assertTrue(resultado == this.resultadoEsperado);
		this.curso.getCursoPropioDao().eliminarUltimoCurso();
	}

}
