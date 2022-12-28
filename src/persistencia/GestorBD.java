package persistencia;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.jdbc.EmbeddedDriver;

public class GestorBD {

	protected Connection mBD;
	private final static String DRIVER = "jdbc:derby";
	private final static String CONNECTION_STRING = "jdbc:derby:db_proyecto_iso2;create=true";
	private final static String DBNAME = "db_proyecto_iso2";
	private final static String DBUSER = "admin";
	private final static String DBPASS = "admin";

	public GestorBD() throws SQLException {
		conectarBD();
	}

	public void conectarBD() throws SQLException {
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		try {
			DriverManager.registerDriver(derbyEmbeddedDriver);
			this.mBD = DriverManager.getConnection(DRIVER + ":" + DBNAME, DBUSER, DBPASS);
		} catch (SQLException e) {
			// Si no esta creada la base de datos, la crea y se conecta
			if (((e.getErrorCode() == 40000) && ("XJ004".equals(e.getSQLState())))) {
				crearBaseDatosSinoExiste();
				conectarBD();
			} else {
				System.out.println("Mensaje error: " + e.getMessage());
				System.out.println("Codigo error: " + e.getErrorCode());
				System.out.println("Estado SQL: " + e.getSQLState());
			}
		}
	}

	public void desconectarBD() {
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
			if (!this.mBD.isClosed())
				this.mBD.close();
		} catch (SQLException ex) {
			if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
				System.out.println("Derby shut down normally");
			} else {
				System.err.println("Derby did not shut down normally");
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<Object> select(String sql) throws SQLException {
		List<Object> resultado = new ArrayList<Object>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.mBD.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				List<Object> v = new ArrayList<Object>();
				int i = 1;
				boolean j = true;
				while (j) {
					try {
						v.add(rs.getObject(i));
						i++;
					} catch (SQLException e) {
						j = false;
					}
				}
				resultado.add(v);
			}

			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
		}

		desconectarBD();
		return resultado;
	}

	/**
	 * @param pstmt
	 * @return res * 0 si se ha insertado correctamente * -1 si se produce un error
	 */
	public int insert(PreparedStatement pstmt) {
		int res = -1;
		try {
			res = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Insert: " + e.getMessage());
		}

		if (res == 0) // Si devuelve 0, es que no se ha insertado ninguna fila --> Incorrecto
			res = -1;

		if (res == 1) // Se ha insertado 1 fila --> Correcto
			res = 0;

		desconectarBD();
		return res;
	}

	/**
	 * 
	 * @param sql
	 * @return res * 0 si se ha actualizado correctamente * -1 si se produce un
	 *         error
	 * @throws SQLException
	 */
	public int update(String sql) throws SQLException {
		conectarBD();
		PreparedStatement stmt = null;
		int res = -1;
		try {
			stmt = this.mBD.prepareStatement(sql);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
		}

		if (res == 0) // Si devuelve 0, es que no se ha actualizado ninguna fila --> Incorrecto
			res = -1;

		if (res == 1) // Se ha actualizado 1 fila --> Correcto
			res = 0;

		desconectarBD();
		return res;
	}

	/**
	 * 
	 * @param sql
	 * @return res * * 0 si se ha eliminado correctamente * -1 si se produce un
	 *         error
	 * @throws SQLException
	 */
	public int delete(String sql) throws SQLException {
		conectarBD();
		PreparedStatement stmt = null;
		int res = -1;
		try {
			stmt = this.mBD.prepareStatement(sql);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
		}

		if (res == 0) // Si devuelve 0, es que no se ha eliminado ninguna fila --> Incorrecto
			res = -1;

		if (res == 1) // Se ha eliminado 1 fila --> Correcto
			res = 0;

		desconectarBD();
		return res;
	}

	private void crearBaseDatosSinoExiste() throws SQLException {
		System.out.println("Creando base de datos...");
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String createSQL = "create table estudiantes (dni varchar(10) not null, "
				+ "nombre varchar(50) not null, apellidos varchar(50) not null, "
				+ "password varchar(50), titulacion varchar(50), cualificacion varchar(50), primary key (dni))";

		try {
			// Crear la conexion y la BBDD
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			this.mBD = DriverManager.getConnection(CONNECTION_STRING, DBUSER, DBPASS);
			this.mBD.setAutoCommit(false);
			stmt = this.mBD.createStatement();

			// Crear la tabla estudiantes
			stmt.execute(createSQL);

			try {
				// Datos iniciales de estudiantes
				pstmt = this.mBD.prepareStatement(
						"insert into ESTUDIANTES (DNI, NOMBRE, APELLIDOS, PASSWORD, TITULACION, CUALIFICACION) VALUES (?,?,?,?,?,?)");
				pstmt.setString(1, "00000000A");
				pstmt.setString(2, "Pepe");
				pstmt.setString(3, "Perez");
				pstmt.setString(4, "PepePerez");
				pstmt.setString(5, "Ingenieria Informatica");
				pstmt.setString(6, "Ingeniero SW");
				pstmt.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}

			// Crear la tabla centros
			createSQL = "create table centros (idCentro int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), nombre varchar(50) not null, "
					+ "localizacion varchar(50) not null, primary key (idCentro))";
			stmt.execute(createSQL);

			try {
				// Datos iniciales de centros
				pstmt = this.mBD.prepareStatement("insert into centros (nombre, localizacion) VALUES (?,?)");
				pstmt.setString(1, "Facultad de Ciencias Sociales");
				pstmt.setString(2, "Talavera de la Reina");
				pstmt.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}

			// Crear la tabla profesores
			createSQL = "create table profesores (dni varchar(10) not null, nombre varchar(50) not null, "
					+ "apellidos varchar(50) not null, password varchar(50), doctor boolean, primary key (dni))";
			stmt.execute(createSQL);

			try {
				// Datos iniciales de profesores
				pstmt = this.mBD.prepareStatement(
						"insert into profesores (dni, nombre, apellidos, password, doctor) VALUES (?,?,?,?,?)");
				pstmt.setString(1, "11111111B");
				pstmt.setString(2, "Jaime");
				pstmt.setString(3, "Garcia");
				pstmt.setString(4, "JaimeGarcia");
				pstmt.setBoolean(5, true);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}
			try {
				pstmt = this.mBD.prepareStatement(
						"insert into profesores (dni, nombre, apellidos, password, doctor) VALUES (?,?,?,?,?)");
				pstmt.setString(1, "22222222C");
				pstmt.setString(2, "Alberto");
				pstmt.setString(3, "Sanchez");
				pstmt.setString(4, "AlbertoSanchez");
				pstmt.setBoolean(5, false);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}

			// Crear la tabla profesoresExternos
			createSQL = "create table profesoresExternos (dni varchar(10) not null, "
					+ "titulacion varchar(50) not null, primary key (dni), foreign key (dni) references profesores(dni))";
			stmt.execute(createSQL);

			// Crear la tabla profesoresUCLM
			createSQL = "create table profesoresUCLM (dni varchar(10) not null, centroAdscripcion int not null, "
					+ "categoria varchar(30) not null, primary key (dni), foreign key (centroAdscripcion) references centros(idCentro), "
					+ "foreign key (dni) references profesores(dni))";
			stmt.execute(createSQL);
			try {
				// Datos iniciales de profesoresUCLM
				pstmt = this.mBD.prepareStatement(
						"insert into profesoresUCLM (dni, centroAdscripcion, categoria) VALUES (?,?,?)");
				pstmt.setString(1, "11111111B");
				pstmt.setInt(2, 1);
				pstmt.setString(3, "CATEDRATICO");
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}
			try {
				pstmt = this.mBD.prepareStatement(
						"insert into profesoresUCLM (dni, centroAdscripcion, categoria) VALUES (?,?,?)");
				pstmt.setString(1, "22222222C");
				pstmt.setInt(2, 1);
				pstmt.setString(3, "TITULAR_UNIVERSIDAD");
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
				System.out.println(e.getMessage());
			} finally {
				if (pstmt != null)
					pstmt.close();
			}

			// Crear la tabla cursosPropios
			createSQL = "create table cursosPropios (idCursoPropio int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "idCentro int not null, " + "idDirector varchar(10) not null, "
					+ "idSecretario varchar(10) not null, " + "estado varchar(30) not null, "
					+ "tipo varchar(30) not null, " + "nombre varchar(50) not null, " + "ECTS int not null, "
					+ "fechaInicio date, " + "fechaFin date, " + "tasaMatricula double, " + "edicion int, "
					+ "primary key (idCursoPropio), foreign key (idCentro) references centros(idCentro), "
					+ "foreign key (idDirector) references profesoresUCLM(dni), "
					+ "foreign key (idSecretario) references profesoresUCLM(dni))";
			stmt.execute(createSQL);

			// Crear la tabla matriculas
			createSQL = "create table matriculas (idMatricula int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), idEstudiante varchar(10) not null, "
					+ "idCursoPropio int not null, tipoPago varchar(30) not null, fecha date, pagado boolean, "
					+ "primary key (idMatricula), foreign key (idEstudiante) references estudiantes(dni), "
					+ "foreign key (idCursoPropio) references cursosPropios(idCursoPropio))";
			stmt.execute(createSQL);

			// Crear la tabla materias
			createSQL = "create table materias (idMateria int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), responsable varchar(10) not null, "
					+ "nombre varchar(50) not null, horas double not null, fechaInicio date, fechaFin date, "
					+ "primary key (idMateria), foreign key (responsable) references profesores(dni))";
			stmt.execute(createSQL);

			// Crear la tabla Pers.Vic
			createSQL = "create table vicerrectorado (dni varchar(50) not null, "
					+ "nombre varchar(50) not null, apellidos varchar(50) not null, password varchar(50) not null, jefe boolean, "
					+ "primary key (dni))";
			stmt.execute(createSQL);

			// Guardar cambios en la BD
			this.mBD.commit();

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
		}

		desconectarBD();

	}

}