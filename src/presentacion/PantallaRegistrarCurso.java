package presentacion;

import java.io.Serializable;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;
import persistencia.CentroDAO;
import persistencia.ProfesorDAO;

public class PantallaRegistrarCurso extends JFrame implements Serializable {
	
	private static Logger logJava = Logger.getLogger(PantallaRegistrarCurso.class);
	private final static String LOGFATAL = "LOG FATAL: ";

	/**
	 * 
	 */
	private static final long serialVersionUID = -5906974317605751792L;
	private JPanel contentPane;
	private JTextField NameField;
	private JTextField ETCSField;
	private JTextField FeeField;
	private JTextField EditionField;
	private JTextField resultadoField;
	private JComboBox<TipoCurso> typeBox;
	private JComboBox secretaryBox;
	private JDateChooser StartDateField;
	private JDateChooser EndDateField;
	private transient CentroDAO centroDAO;
	private transient List<Centro> centros;
	private transient ProfesorDAO profesorDAO;
	private transient List<Profesor> profesores;
	private transient CursoPropio curso;
	private transient ProfesorUCLM profesorUCLM;
	private JComboBox centerBox;
	private PantallaLogin pantLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegistrarCurso frame = new PantallaRegistrarCurso();
					frame.setVisible(true);
				} catch (Exception e) {
					logJava.fatal(LOGFATAL+e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PantallaRegistrarCurso() throws SQLException {
		pantLogin = new PantallaLogin();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Title = new JLabel("Registrar una propuesta de un curso");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(63, 11, 497, 39);
		contentPane.add(Title);

		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantLogin.logout();
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);

		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaDireccionCurso D1 = new PantallaDireccionCurso();
				D1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);

		JLabel Name = new JLabel("Nombre");
		Name.setBounds(60, 94, 57, 14);
		contentPane.add(Name);

		JLabel ETCStxt = new JLabel("ETCS");
		ETCStxt.setBounds(60, 119, 57, 14);
		contentPane.add(ETCStxt);

		JLabel StarDateTxt = new JLabel("Fecha Inicio");
		StarDateTxt.setBounds(60, 145, 79, 14);
		contentPane.add(StarDateTxt);

		JLabel EndDateTxt = new JLabel("Fecha Fin");
		EndDateTxt.setBounds(60, 176, 79, 14);
		contentPane.add(EndDateTxt);

		JLabel FeeTxt = new JLabel("Tasa");
		FeeTxt.setBounds(60, 207, 102, 14);
		contentPane.add(FeeTxt);

		JLabel EditionTxt = new JLabel("Edicion");
		EditionTxt.setBounds(60, 232, 68, 14);
		contentPane.add(EditionTxt);

		JLabel UCLM_Image = new JLabel(new ImageIcon("./images/uclm.png"));
		UCLM_Image.setBackground(SystemColor.activeCaptionText);
		UCLM_Image.setBounds(439, 95, 123, 70);
		contentPane.add(UCLM_Image);

		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(170, 91, 159, 20);
		contentPane.add(NameField);

		ETCSField = new JTextField();
		ETCSField.setColumns(10);
		ETCSField.setBounds(170, 116, 159, 20);
		contentPane.add(ETCSField);

		FeeField = new JTextField();
		FeeField.setColumns(10);
		FeeField.setBounds(170, 204, 159, 20);
		contentPane.add(FeeField);

		EditionField = new JTextField();
		EditionField.setColumns(10);
		EditionField.setBounds(170, 229, 159, 20);
		contentPane.add(EditionField);

		StartDateField = new JDateChooser();
		StartDateField.setBounds(170, 145, 159, 20);
		contentPane.add(StartDateField);

		EndDateField = new JDateChooser();
		EndDateField.setBounds(170, 176, 159, 20);
		contentPane.add(EndDateField);

		JLabel CenterTxt = new JLabel("Centro");
		CenterTxt.setBounds(60, 256, 68, 22);
		contentPane.add(CenterTxt);

		centerBox = new JComboBox();
		centerBox.setBounds(170, 257, 409, 21);
		contentPane.add(centerBox);
		centerBox.removeAllItems();
		centroDAO = new CentroDAO();
		centros = centroDAO.listarCentros();
		for (Centro c : centros) {
			centerBox.addItem(c.getNombre() + " - " + c.getLocalizacion());
		}

		JLabel secretaryTxt = new JLabel("Secretario");
		secretaryTxt.setBounds(60, 287, 102, 22);
		contentPane.add(secretaryTxt);

		secretaryBox = new JComboBox();
		secretaryBox.setBounds(170, 288, 214, 21);
		contentPane.add(secretaryBox);
		secretaryBox.removeAllItems();
		profesorDAO = new ProfesorDAO();
		profesores = profesorDAO.listarProfesores();
		for (Profesor p : profesores) {
			secretaryBox.addItem(p.getNombre() + " " + p.getApellidos());
		}

		JLabel typeTxt = new JLabel("Tipo");
		typeTxt.setBounds(60, 321, 68, 22);
		contentPane.add(typeTxt);

		typeBox = new JComboBox();
		typeBox.setBounds(170, 319, 214, 21);
		contentPane.add(typeBox);
		typeBox.removeAllItems();
		for (TipoCurso t : TipoCurso.values()) {
			typeBox.addItem(t);
		}

		resultadoField = new JTextField();
		resultadoField.setEnabled(false);
		resultadoField.setBounds(356, 205, 303, 23);
		contentPane.add(resultadoField);
		resultadoField.setColumns(10);

		JButton EditBtn = new JButton("Registrar propuesta");
		EditBtn.setBackground(new Color(50, 205, 50));
		EditBtn.setBounds(412, 308, 247, 39);
		EditBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					int resultado = 0, ects = -1, edicion = -1;
					double tasaMatricula = -1;
					
					try {
						ects = Integer.parseInt(ETCSField.getText());
					} catch (NumberFormatException e) {
						resultado = 1;
					}
					
					try {
						tasaMatricula = Double.parseDouble(FeeField.getText());
					} catch (Exception e) {
						resultado = 5;
					}
					
					try {
						edicion = Integer.parseInt(EditionField.getText());
					} catch (NumberFormatException e) {
						resultado = 6;
					}
					
					curso = new CursoPropio();
					curso.setCentro(centros.get(centerBox.getSelectedIndex()));
					
					// Anadir el director, que es el usuario que este logueado
					profesorUCLM = pantLogin.getDirectorLogeado();
					if(profesorUCLM == null) { // Si no hay ning??n profesor logueado, se utiliza este por defecto
						profesorUCLM = new ProfesorUCLM();
						profesorUCLM.setDni("11111111B");
					}

					curso.setDirector(profesorUCLM);
					curso.setSecretario(profesores.get(secretaryBox.getSelectedIndex()));
					curso.setEstado(EstadoCurso.PROPUESTO); // Al crear el curso, el estado es PROPUESTO
					curso.setTipo(TipoCurso.valueOf(typeBox.getSelectedItem().toString()));
					curso.setNombre(NameField.getText());
					curso.setECTS(ects);
					curso.setFechaInicio(StartDateField.getDate());
					curso.setFechaFin(EndDateField.getDate());
					curso.setTasaMatricula(tasaMatricula);
					curso.setEdicion(edicion);
					
					GestorPropuestasCursos gestorPropuestasCursos = new GestorPropuestasCursos();

					resultado = gestorPropuestasCursos.realizarPropuestaCurso(curso);
					
					System.out.println("Resultado Interfaz: "+resultado);
					
					switch(resultado) {
						case -1:
							resultadoField.setText("No se ha podido crear el curso");
							break;
						case 0:
							resultadoField.setText("Curso propuesto correctamente");
							break;
						case 1:
							resultadoField.setText("Nombre requerido");
							break;
						case 2:
							resultadoField.setText("ECTS debe ser un n??mero entero positivo");
							break;
						case 3:
							resultadoField.setText("Fecha Inicio debe ser seleccionada");
							break;
						case 4:
							resultadoField.setText("Fecha Fin debe ser seleccionada y mayor a Fecha Inicio");
							break;
						case 5:
							resultadoField.setText("Tasa Matr??cula debe ser un n??mero positivo");
							break;
						case 6:
							resultadoField.setText("Edici??n debe ser un n??mero entero positivo");
							break;
						default:
							break;
					}
				} catch (Exception e) {
					logJava.fatal(LOGFATAL+e.toString());
				}

			}
		});
		contentPane.add(EditBtn);
	}
}