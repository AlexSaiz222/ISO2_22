package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import persistencia.CursoPropioDAO;
import persistencia.MatriculaDAO;

import javax.swing.JComboBox;

public class PantallaMatriculationProcess extends JFrame {

	private JPanel contentPane;
	private JTextField resultadoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaMatriculationProcess frame = new PantallaMatriculationProcess();
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
	public PantallaMatriculationProcess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				PantallaMatriculacion M1 = new PantallaMatriculacion();
				M1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JLabel Title = new JLabel("Matriculate");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(10, 15, 270, 39);
		contentPane.add(Title);
		
		JLabel SelectInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		SelectInfo.setText("");
		SelectInfo.setToolTipText("Select an existing course");
		SelectInfo.setBounds(274, 80, 23, 20);
		contentPane.add(SelectInfo);
		
		JLabel NameTxt = new JLabel("Name");
		NameTxt.setBounds(34, 86, 43, 14);
		contentPane.add(NameTxt);
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(359, 123, 241, 213);
		contentPane.add(UCLM_letters);
		
		JLabel DateTxt = new JLabel("Date");
		DateTxt.setBounds(34, 123, 46, 14);
		contentPane.add(DateTxt);
		
		JDateChooser DateField = new JDateChooser();
		DateField.setBounds(70, 117, 191, 20);
		contentPane.add(DateField);
		
		JComboBox<String> NameField = new JComboBox<String>();
		NameField.setBounds(70, 83, 191, 23);
		contentPane.add(NameField);
		NameField.removeAllItems();
		EstadoCurso Estado= EstadoCurso.VALIDADO;
		List<CursoPropio> cursos = CursoPropioDAO.listarCursosPropiosPorEstado(Estado);
		for(CursoPropio c: cursos) {
			NameField.addItem(c.getNombre());
		}
		
		JButton MatriculationBttn = new JButton("Pass to payment");
		MatriculationBttn.setForeground(Color.BLACK);
		MatriculationBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// anyadir el estudiante, que es el usuario que estaría logueado
					//Se ha puesto un ejemplo hasta que se implemente dicho login
					Estudiante est = new Estudiante();
					est.setIdEstudiante(4);
					
					Matricula mat = new Matricula();
					
					mat.setTitulo(cursos.get(NameField.getSelectedIndex()));
					mat.setPagado(false);
					mat.setEstudiante(est);
					mat.setFecha((Date) DateField.getDate());
					
					MatriculaDAO matDAO = new MatriculaDAO();
					
					int resultado = matDAO.crearMatricula(mat);
					
					if(resultado == 0) {
						resultadoField.setText("Successfully enroll in this course");
						PantallaPagar PP1 = new PantallaPagar();
						PP1.setVisible(true);
					}
				}catch(Exception e) {
					resultadoField.setText("An error has ocurred, please, try again");
				}
			}
		});
		MatriculationBttn.setBackground(Color.LIGHT_GRAY);
		MatriculationBttn.setBounds(34, 160, 227, 67);
		contentPane.add(MatriculationBttn);
		
		JButton btnPayLater = new JButton("Pay later");
		btnPayLater.setForeground(Color.BLACK);
		btnPayLater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {try {
				// anyadir el estudiante, que es el usuario que estaría logueado
				//Se ha puesto un ejemplo hasta que se implemente dicho login
				Estudiante est = new Estudiante();
				est.setIdEstudiante(4);
				
				Matricula mat = new Matricula();
				
				mat.setTitulo(cursos.get(NameField.getSelectedIndex()));
				mat.setPagado(false);
				mat.setEstudiante(est);
				mat.setFecha((Date) DateField.getDate());
				
				MatriculaDAO matDAO = new MatriculaDAO();
				
				int resultado = matDAO.crearMatricula(mat);
				
				if(resultado == 0) {
					resultadoField.setText("Successfully enroll in this course");
					PantallaMatriculacion PM1 = new PantallaMatriculacion();
					PM1.setVisible(true);
				}
			}catch(Exception e) {
				resultadoField.setText("An error has ocurred, please, try again");
			}
			}
		});
		btnPayLater.setBackground(Color.LIGHT_GRAY);
		btnPayLater.setBounds(34, 244, 227, 67);
		contentPane.add(btnPayLater);
		
		resultadoField = new JTextField();
		resultadoField.setEnabled(false);
		resultadoField.setColumns(10);
		resultadoField.setBounds(316, 83, 303, 23);
		contentPane.add(resultadoField);
		
	}
}
