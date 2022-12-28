package presentacion;

import java.io.Serializable;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import persistencia.CursoPropioDAO;
import persistencia.ProfesorDAO;

public class PantallaEditarPropuestaCurso extends JFrame implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -185482206037375426L;
	private JPanel contentPane;
	private JTextField FeeField;
	private JTextField EditionField;
	private JTextField ETCSField;
	private JTextField resultadoField;
	private transient CursoPropioDAO cursoPropioDAO;
	private JComboBox NameField;
	private JDateChooser StartDateField;
	private JDateChooser EndDateField;
	private JComboBox centerBox;
	private JComboBox secretaryBox;
	private JComboBox typeBox;
	private transient List<Centro> centros;
	private transient List<CursoPropio> cursos;
	private transient List<Profesor> profesores;
	private PantallaLogin pantLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaEditarPropuestaCurso frame = new PantallaEditarPropuestaCurso();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PantallaEditarPropuestaCurso() throws SQLException {
		pantLogin = new PantallaLogin();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Title = new JLabel("Editar una propuesta de un curso");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(60, 11, 431, 39);
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
		ETCStxt.setBounds(60, 120, 35, 14);
		contentPane.add(ETCStxt);

		JLabel StarDateTxt = new JLabel("Fecha Inicio");
		StarDateTxt.setBounds(60, 145, 57, 14);
		contentPane.add(StarDateTxt);

		JLabel EndDateTxt = new JLabel("Fecha fin");
		EndDateTxt.setBounds(60, 182, 57, 14);
		contentPane.add(EndDateTxt);

		JLabel FeeTxt = new JLabel("Tasa Maricula");
		FeeTxt.setBounds(60, 207, 80, 14);
		contentPane.add(FeeTxt);

		JLabel EditionTxt = new JLabel("Edicion");
		EditionTxt.setBounds(60, 232, 46, 14);
		contentPane.add(EditionTxt);

		JLabel UCLM_Image = new JLabel(new ImageIcon("./images/uclm.png"));
		UCLM_Image.setBackground(SystemColor.activeCaptionText);
		UCLM_Image.setBounds(502, 95, 123, 70);
		contentPane.add(UCLM_Image);

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
		CenterTxt.setBounds(60, 257, 46, 22);
		contentPane.add(CenterTxt);

		resultadoField = new JTextField();
		resultadoField.setEnabled(false);
		resultadoField.setBounds(356, 205, 303, 23);
		contentPane.add(resultadoField);
		resultadoField.setColumns(10);

		centerBox = new JComboBox();
		centerBox.setBounds(170, 257, 409, 21);
		contentPane.add(centerBox);
		centerBox.removeAllItems();
		CentroDAO centroDAO = new CentroDAO();
		centros = centroDAO.listarCentros();
		for (Centro c : centros) {
			centerBox.addItem(c.getNombre() + " - " + c.getLocalizacion());
		}

		JLabel secretaryTxt = new JLabel("Secretario");
		secretaryTxt.setBounds(60, 287, 80, 22);
		contentPane.add(secretaryTxt);

		secretaryBox = new JComboBox();
		secretaryBox.setBounds(170, 288, 214, 21);
		contentPane.add(secretaryBox);
		secretaryBox.removeAllItems();
		ProfesorDAO profesorDAO = new ProfesorDAO();
		profesores = profesorDAO.listarProfesores();
		for (Profesor p : profesores) {
			secretaryBox.addItem(p.getNombre() + " " + p.getApellidos());
		}

		JLabel typeTxt = new JLabel("Tipo");
		typeTxt.setBounds(60, 316, 46, 22);
		contentPane.add(typeTxt);

		typeBox = new JComboBox();
		typeBox.setBounds(170, 319, 214, 21);
		contentPane.add(typeBox);
		typeBox.removeAllItems();
		for (TipoCurso t : TipoCurso.values()) {
			typeBox.addItem(t);
		}

		cursoPropioDAO = new CursoPropioDAO();
		NameField = new JComboBox();
		NameField.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					CursoPropio cursoSeleccionado = cursoPropioDAO
							.seleccionarCurso(NameField.getSelectedItem().toString().charAt(0));

					if (cursoSeleccionado.getId() == -1) {
						resultadoField.setText("Ha ocurrido un error al obtener los datos");
					} else {
						ETCSField.setText(cursoSeleccionado.getECTS() + "");
						FeeField.setText(cursoSeleccionado.getTasaMatricula() + "");
						StartDateField.setDate(cursoSeleccionado.getFechaInicio());
						EndDateField.setDate(cursoSeleccionado.getFechaFin());
						EditionField.setText(cursoSeleccionado.getEdicion() + "");
						centerBox.addItem(cursoSeleccionado.getCentro() + "");
						secretaryBox.addItem(cursoSeleccionado.getSecretario());
						typeBox.addItem(cursoSeleccionado.getTipo());
					}
				} catch (SQLException e1) {
					System.out.println(e1.toString());
				}
			}
		});
		NameField.setBounds(170, 90, 252, 21);
		contentPane.add(NameField);
		NameField.removeAllItems();
		EstadoCurso estado = EstadoCurso.PROPUESTO;
		cursos = cursoPropioDAO.listarCursosPorEstado(estado);
		if (cursos.size() == 0) {
			resultadoField.setText("No hay cursos propuestos");
		} else {
			for (CursoPropio c : cursos) {
				NameField.addItem(c.getId() + " - " + c.getNombre());
				ETCSField.setText(c.getECTS() + "");
				FeeField.setText(c.getTasaMatricula() + "");
				StartDateField.setDate(c.getFechaInicio());
				EndDateField.setDate(c.getFechaFin());
				EditionField.setText(c.getEdicion() + "");
				centerBox.addItem(c.getCentro() + "");
				secretaryBox.addItem(c.getSecretario());
				typeBox.addItem(c.getTipo());
			}
		}

		JButton EditBtn = new JButton("Editar propuesta");
		EditBtn.setBackground(new Color(50, 205, 50));
		EditBtn.setBounds(412, 308, 247, 39);
		EditBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					CursoPropio curso = new CursoPropio();
					curso.setCentro(centros.get(centerBox.getSelectedIndex()));
					// Anadir el director, que es el usuario que estara logueado
					ProfesorUCLM profesorUCLM = pantLogin.getDirectorLogeado();
					curso.setDirector(profesorUCLM);
					curso.setSecretario(profesores.get(secretaryBox.getSelectedIndex()));
					curso.setEstado(EstadoCurso.PROPUESTO); // Al crear el curso, el estado es PROPUESTO
					curso.setTipo(TipoCurso.valueOf(typeBox.getSelectedItem().toString()));
					curso.setNombre(NameField.getSelectedItem().toString());
					curso.setECTS(Integer.parseInt(ETCSField.getText()));
					curso.setFechaInicio(StartDateField.getDate());
					curso.setFechaFin(EndDateField.getDate());
					curso.setTasaMatricula(Double.parseDouble(FeeField.getText()));
					curso.setEdicion(Integer.parseInt(EditionField.getText()));
					GestorPropuestasCursos gestorPropuestasCursos = new GestorPropuestasCursos();
					int resultado = gestorPropuestasCursos.editarPropuestaCurso(curso);
					if (resultado == 0) {
						resultadoField.setText("Curso propuesto correctamente");
					}
				} catch (Exception e) {
					resultadoField.setText("Ha ocurrido un error, vuelva a intentarlo");
				}

			}
		});

		contentPane.add(EditBtn);
		ETCSField = new JTextField();
		ETCSField.setBounds(170, 116, 159, 20);
		contentPane.add(ETCSField);
		ETCSField.setColumns(10);

	}
}