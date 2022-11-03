package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;

import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;
import persistencia.CentroDAO;
import persistencia.CursoPropioDAO;
import persistencia.ProfesorDAO;
import persistencia.ProfesorUCLMDAO;

import javax.swing.JComboBox;

public class PantallaRegisterCourse extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField ETCSField;
	private JTextField FeeField;
	private JTextField EditionField;
	private JTextField resultadoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegisterCourse frame = new PantallaRegisterCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaRegisterCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Register a course proposal");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(192, 11, 315, 39);
		contentPane.add(Title);
		
		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
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
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(82, 94, 35, 14);
		contentPane.add(Name);
		
		JLabel ETCStxt = new JLabel("ETCS");
		ETCStxt.setBounds(82, 119, 35, 14);
		contentPane.add(ETCStxt);
		
		JLabel StarDateTxt = new JLabel("Start Date");
		StarDateTxt.setBounds(82, 145, 57, 14);
		contentPane.add(StarDateTxt);
		
		JLabel EndDateTxt = new JLabel("End Date");
		EndDateTxt.setBounds(82, 176, 57, 14);
		contentPane.add(EndDateTxt);
		
		JLabel FeeTxt = new JLabel("Tuition fee");
		FeeTxt.setBounds(82, 207, 80, 14);
		contentPane.add(FeeTxt);
		
		JLabel EditionTxt = new JLabel("Edition");
		EditionTxt.setBounds(82, 232, 46, 14);
		contentPane.add(EditionTxt);
		
		JLabel Photo = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		Photo.setBounds(432, 94, 89, 77);
		contentPane.add(Photo);
		
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
		
		JDateChooser StartDateField = new JDateChooser();
		StartDateField.setBounds(170, 145, 159, 20);
		contentPane.add(StartDateField);
		
		JDateChooser EndDateField = new JDateChooser();
		EndDateField.setBounds(170, 176, 159, 20);
		contentPane.add(EndDateField);
		
		JLabel CenterTxt = new JLabel("Center");
		CenterTxt.setBounds(82, 256, 46, 22);
		contentPane.add(CenterTxt);
		
		JComboBox centerBox = new JComboBox();
		centerBox.setBounds(170, 257, 409, 21);
		contentPane.add(centerBox);
		centerBox.removeAllItems();
		CentroDAO centroDAO = new CentroDAO();
		List<Centro> centros = centroDAO.listarCentros();
		for(Centro c: centros) {
			centerBox.addItem(c.getNombre()+" - "+c.getLocalizacion());
		}
		
		JLabel secretaryTxt = new JLabel("Secretary");
		secretaryTxt.setBounds(82, 287, 80, 22);
		contentPane.add(secretaryTxt);
		
		JComboBox secretaryBox = new JComboBox();
		secretaryBox.setBounds(170, 288, 214, 21);
		contentPane.add(secretaryBox);
		secretaryBox.removeAllItems();
		ProfesorDAO profesorDAO = new ProfesorDAO();
		List<Profesor> profesores = profesorDAO.listarProfesores();
		for(Profesor p: profesores) {
			secretaryBox.addItem(p.getNombre()+" "+p.getApellidos());
		}
		
		JLabel typeTxt = new JLabel("Type");
		typeTxt.setBounds(82, 321, 46, 22);
		contentPane.add(typeTxt);
		
		JComboBox typeBox = new JComboBox();
		typeBox.setBounds(170, 319, 214, 21);
		contentPane.add(typeBox);
		typeBox.removeAllItems();
		for(TipoCurso t: TipoCurso.values()) {
			typeBox.addItem(t);
		}
		
		resultadoField = new JTextField();
		resultadoField.setEnabled(false);
		resultadoField.setBounds(356, 205, 303, 23);
		contentPane.add(resultadoField);
		resultadoField.setColumns(10);
		
		JButton EditBtn = new JButton("Register course proposal");
		EditBtn.setBackground(new Color(50, 205, 50));
		EditBtn.setBounds(412, 308, 247, 39);
		EditBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					CursoPropio curso = new CursoPropio();
					curso.setCentro(centros.get(centerBox.getSelectedIndex()));
					// Añadir el director, que es el usuario que estará logueado
					// Por defecto, pondremos el primero de la lista de profesoresUCLM hasta que se implemente el login
					ProfesorUCLM profesorUCLM = new ProfesorUCLM();
					profesorUCLM.setDni("11111111B");
					curso.setDirector(profesorUCLM);
					curso.setSecretario(profesores.get(secretaryBox.getSelectedIndex()));
					curso.setEstado(EstadoCurso.PROPUESTO); // Al crear el curso, el estado es PROPUESTO
					curso.setTipo(TipoCurso.valueOf(typeBox.getSelectedItem().toString()));
					curso.setNombre(NameField.getText());
					curso.setECTS(Integer.parseInt(ETCSField.getText()));
					curso.setFechaInicio(StartDateField.getDate());
					curso.setFechaFin(EndDateField.getDate());
					curso.setTasaMatricula(Double.parseDouble(FeeField.getText()));
					curso.setEdicion(Integer.parseInt(EditionField.getText()));
					CursoPropioDAO cursoDAO = new CursoPropioDAO();
					int resultado = cursoDAO.crearNuevoCurso(curso);
					if(resultado == 0) {
						resultadoField.setText("Curso propuesto correctamente");
					}
				} catch (Exception e) {
					resultadoField.setText("Ha ocurrido un error, vuelva a intentarlo");
				}

			}
		});
		contentPane.add(EditBtn);
		
		
		
	}
}