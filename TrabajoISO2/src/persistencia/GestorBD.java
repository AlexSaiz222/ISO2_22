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
	
	protected static GestorBD instancia = null;
	protected static Connection mBD;
	private final static String DRIVER ="jdbc:derby";
	private final static String CONNECTION_STRING ="jdbc:derby:db_proyecto_iso2;create=true";
	private final static String DBNAME ="db_proyecto_iso2";
	private final static String DBUSER ="admin";
	private final static String DBPASS ="admin";
	
	private GestorBD() {
		conectarBD();
	}
	
	public static GestorBD getAgente() {
		if (instancia == null) {
            instancia = new GestorBD();
        }
		return instancia;
	}

	public static void conectarBD() {
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		try {
			DriverManager.registerDriver(derbyEmbeddedDriver);
			mBD = DriverManager.getConnection(DRIVER+":"+DBNAME, DBUSER, DBPASS);
			System.out.println("BBDD existente. Conectado");
		} catch (SQLException e) {
			// Si no esta creada la base de datos, la crea y se conecta
			if (((e.getErrorCode() == 40000) && ("XJ004".equals(e.getSQLState())))) {
				System.out.println("BBDD no creada");
				crearBaseDatosSinoExiste();
				conectarBD();
			} else {
				System.out.println("Mensaje error: "+e.getMessage());
				System.out.println("Codigo error: "+e.getErrorCode());
				System.out.println("Estado SQL: "+e.getSQLState());
			}
		}
	}

	public void desconectarBD() {
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
			mBD.close();
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
	 */
	public ResultSet select(String sql) {
		List<String> resultado = new ArrayList<String>();
		String v = "";
		conectarBD();
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = mBD.createStatement();
			rs = stmt.executeQuery(sql);
			/*
			while (rs.next()) {
				String [] datos = new String[rs.getMetaData().getColumnCount()];
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					v = String.valueOf(rs.getObject(i));
					System.out.println(String.valueOf(rs.getObject(i)));
					if(rs.getMetaData().getColumnType(i) == 4) {
						v.add(rs.getInt(i));
					} else if(rs.getMetaData().getColumnType(i) == 12) {
						v.add(rs.getString(i));
					}
					
					datos[i] = v;
				}
				resultado.add(datos);
			}
			*/
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desconectarBD();
		return rs;
	}

	/**
	 * 
	 * @param sql
	 */
	public int insert(String sql) {
		conectarBD();
		PreparedStatement stmt;
		int res = -1;
		try {
			stmt = mBD.prepareStatement(sql);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == 1)
			res = 0;
		desconectarBD();
		return res;
	}

	/**
	 * 
	 * @param sql
	 */
	public int update(String sql) {
		// TODO - implement GestorBD.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 */
	public int delete(String sql) {
		// TODO - implement GestorBD.delete
		throw new UnsupportedOperationException();
	}

	public void operation() {
		// TODO - implement GestorBD.operation
		throw new UnsupportedOperationException();
	}
	
	private static void crearBaseDatosSinoExiste() {
		System.out.println("Creando base de datos...");
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs = null;
		String createSQL = "create table estudiantes (dni varchar(10) not null, "
				+ "nombre varchar(50) not null, apellidos varchar(50) not null, "
				+ "titulacion varchar(50), cualificacion varchar(50), primary key (dni))";

		try {
			// Crear la conexion y la BBDD
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			mBD = DriverManager.getConnection(CONNECTION_STRING, DBUSER, DBPASS);
			mBD.setAutoCommit(false);
			stmt = mBD.createStatement();
			
			// Crear la tabla estudiantes
			stmt.execute(createSQL);

			// Datos iniciales de estudiantes
			pstmt = mBD.prepareStatement("insert into ESTUDIANTES (DNI, NOMBRE , APELLIDOS , TITULACION , CUALIFICACION) VALUES (?,?,?,?,?)");
			pstmt.setString(1, "11111111A");
			pstmt.setString(2, "Pepe");
			pstmt.setString(3, "Pérez");
			pstmt.setString(4, "Ingeniería Informática");
			pstmt.setString(5, "Ingeniero SW");
			pstmt.executeUpdate();
						
			// Crear la tabla centros
			createSQL = "create table centros (idCentro int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), nombre varchar(50) not null, "
					+ "localizacion varchar(50) not null, attribute int, primary key (idCentro))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesores
			createSQL = "create table profesores (dni varchar(10) not null, nombre varchar(50) not null, "
					+ "apellidos varchar(50) not null, doctor boolean, primary key (dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesoresExternos
			createSQL = "create table profesoresExternos (dni varchar(10) not null, nombre varchar(50) not null, "
					+ "apellidos varchar(50) not null, doctor boolean, titulacion varchar(50) not null, primary key (dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesoresUCLM
			createSQL = "create table profesoresUCLM (dni varchar(10) not null, centroAdscripcion int not null, "
					+ "categoria varchar(30) not null, nombre varchar(50) not null, apellidos varchar(50) not null, "
					+ "attribute int, primary key (dni), foreign key (centroAdscripcion) references centros(idCentro))";
			stmt.execute(createSQL);
			
			// Crear la tabla cursosPropios
			createSQL = "create table cursosPropios (idCursoPropio int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), idCentro int not null, "
					+ "idDirector varchar(10) not null, idSecretario varchar(10) not null, "
					+ "estado varchar(30) not null, tipo varchar(30) not null, "
					+ "nombre varchar(50) not null, ECTS int not null, fechaInicio date, fechaFin date, tasaMatricula double, edicion int, "
					+ "primary key (idCursoPropio), foreign key (idCentro) references centros(idCentro), "
					+ "foreign key (idDirector) references profesoresUCLM(dni), "
					+ "foreign key (idSecretario) references profesoresUCLM(dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla matriculas
			createSQL = "create table matriculas (idMatricula int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), idEstudiante varchar(10) not null, "
					+ "idCursoPropio int not null, tipoPago varchar(30) not null, fecha date, pagado boolean, "
					+ "attribute int, primary key (idMatricula), foreign key (idEstudiante) references estudiantes(dni), "
					+ "foreign key (idCursoPropio) references cursosPropios(idCursoPropio))";
			stmt.execute(createSQL);
			
			// Crear la tabla materias
			createSQL = "create table materias (idMateria int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), responsable varchar(10) not null, "
					+ "nombre varchar(50) not null, horas double not null, fechaInicio date, fechaFin date, "
					+ "primary key (idMateria), foreign key (responsable) references profesores(dni))";
			stmt.execute(createSQL);

			// Guardar cambios en la BD
			mBD.commit();

		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}

		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException ex) {
			if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
				System.out.println("Derby shut down normally");
			} else {
				System.err.println("Derby did not shut down normally");
				System.err.println(ex.getMessage());
			}
		}
		
	}

}