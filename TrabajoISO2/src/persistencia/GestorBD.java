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
	private final static String CONNECTION_STRING ="jdbc:derby:proyecto_iso;create=true";
	private final static String DBNAME ="proyecto_iso";
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
		} catch (SQLException e) {
			// Si no esta creada la base de datos, la crea y se conecta
			if (((e.getErrorCode() == 40000) && ("XJ004".equals(e.getSQLState())))) {
				crearBaseDatosSinoExiste();
				conectarBD();
			} else {
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
				System.out.println(e.getSQLState());
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
		
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs = null;
		String createSQL = "create table estudiantes (dni varchar(10) not null,"
				+ " nombre varchar(50) not null, apellidos varchar(50) not null,"
				+ " titulacion varchar(50), cualificacion varchar(50), primary key (dni))";

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
			pstmt = mBD.prepareStatement("insert into estudiantes (dni, nombre, apellidos, titulacion, cualificacion) values(?,?,?,?,?)");
			pstmt.setString(1, "11111111A");
			pstmt.setString(2, "Pepe");
			pstmt.setString(3, "Pérez");
			pstmt.setString(4, "Ingeniería Informática");
			pstmt.setString(4, "Ingeniero SW");
			pstmt.executeUpdate();
			
			// Crear la tabla centros
			createSQL = "create table centros (idCentro int not null auto_increment, nombre varchar(50) not null, "
					+ "localizacion varchar(50) not null, attribute int, primary key (idCentro))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesores
			createSQL = "create table profesores (dni varchar(10) not null, nombre varchar(50) not null, "
					+ "apellidos varchar(50) not null, doctor bool, primary key (dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesoresExternos
			createSQL = "create table profesoresExternos (dni varchar(10) not null, nombre varchar(50) not null, "
					+ "apellidos varchar(50) not null, doctor bool, titulacion varchar(50) not null, primary key (dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla profesoresUCLM
			createSQL = "create table profesoresUCLM (dni varchar(10) not null, centroAdscripcion int not null, "
					+ "categoria enum('CATEDRATICO', 'TITULAR_UNIVERSIDAD', 'CONTRATADO_DOCTOR', 'AYUDANTE_DOCTOR', 'AYUDANTE', 'ASOCIADO') not null, "
					+ "nombre varchar(50) not null, apellidos varchar(50) not null, attribute int, primary key (idCentro), "
					+ "foreign key (centroAdscripcion) references centros(idCentro))";
			stmt.execute(createSQL);
			
			// Crear la tabla cursosPropios
			createSQL = "create table cursosPropios (idCursoPropio int not null auto_increment, idCentro int not null, "
					+ "idDirector varchar(10) not null, idSecretario varchar(10) not null,"
					+ "estado enum('PROPUESTO', 'VALIDADO', 'PROPUESTA_RECHAZADA', 'EN_MATRICULACION', 'EN_IMPARTICION', 'TERMINADO') not null, "
					+ "tipo enum('MASTER', 'EXPERTO', 'ESPECIALISTA', 'FORMACION_AVANZADA', 'FORMACION_CONTINUA', 'MICROCREDENCIALES', 'CORTA_DURACION') not null, "
					+ "nombre varchar(50) not null, ECTS int not null, fechaInicio date, fechaFin date, tasaMatricula double, edicion int, "
					+ "primary key (idCursoPropio), foreign key (idCentro) references centros(idCentro), "
					+ "foreign key (idDirector) references profesoresUCLM(dni), "
					+ "foreign key (idSecretario) references profesoresUCLM(dni))";
			stmt.execute(createSQL);
			
			// Crear la tabla matriculas
			createSQL = "create table matriculas (idMatricula int not null auto_increment, idEstudiante int not null, "
					+ "idCursoPropio int not null, tipoPago enum('TARJETA_CREDITO', 'TRANSFERENCIA') not null, fecha date, pagado bool, "
					+ "attribute int, primary key (idMatricula), foreign key (idEstudiante) references estudiantes(dni),"
					+ "foreign key (idCursoPropio) references cursosPropios(idCursoPropio))";
			stmt.execute(createSQL);
			
			// Crear la tabla materias
			createSQL = "create table materias (idMateria int not null auto_increment, responsable varchar(10) not null, "
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