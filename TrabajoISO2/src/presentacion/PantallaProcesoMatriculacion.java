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
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import negocio.controllers.GestorMatriculacion;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Estudiante;
import negocio.entities.Matricula;
import persistencia.CursoPropioDAO;
import persistencia.MatriculaDAO;
import javax.swing.JComboBox;

public class PantallaProcesoMatriculacion extends JFrame {

	private JPanel contentPane;
	private JTextField resultadoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaProcesoMatriculacion frame = new PantallaProcesoMatriculacion();
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
	public PantallaProcesoMatriculacion() {
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
		
		JLabel Title = new JLabel("Matricularse de un curso");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(10, 15, 520, 39);
		contentPane.add(Title);
		
		JLabel NameTxt = new JLabel("Nombre del curso");
		NameTxt.setBounds(34, 86, 62, 14);
		contentPane.add(NameTxt);
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(359, 123, 241, 213);
		contentPane.add(UCLM_letters);
		
		JLabel DateTxt = new JLabel("Fecha");
		DateTxt.setBounds(34, 123, 46, 14);
		contentPane.add(DateTxt);
		
		JDateChooser DateField = new JDateChooser();
		DateField.setBounds(106, 117, 191, 20);
		contentPane.add(DateField);
		
		JComboBox<String> NameField = new JComboBox<String>();
		NameField.setBounds(106, 82, 191, 23);
		contentPane.add(NameField);
		NameField.removeAllItems();
		CursoPropioDAO cursoDAO = new CursoPropioDAO();
		EstadoCurso estado = EstadoCurso.VALIDADO;
		List<CursoPropio> cursos = cursoDAO.listarCursosPorEstado(estado);
		for(CursoPropio c: cursos) {
			NameField.addItem(c.getNombre());
		}
		
		JButton MatriculationBttn = new JButton("Pagar matricula");
		MatriculationBttn.setForeground(Color.BLACK);
		MatriculationBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// anyadir el estudiante, que es el usuario que estar�a logueado
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
						GestorMatriculacion gMatriculacion = new GestorMatriculacion();
						//aqui se meteria el m�todo para matricularse
						//gMatriculacion.realizarMatriculacion(null, est);
						resultadoField.setText("Matriculado en curso correctamente");
						PantallaPagar PP1 = new PantallaPagar();
						PP1.setVisible(true);
					}
				}catch(Exception e) {
					resultadoField.setText("Ha ocurrido un error, vuelva a intentarlo mas tarde");
				}
			}
		});
		MatriculationBttn.setBackground(Color.LIGHT_GRAY);
		MatriculationBttn.setBounds(34, 160, 263, 67);
		contentPane.add(MatriculationBttn);
		
		JButton btnPayLater = new JButton("Pagar mas tarde");
		btnPayLater.setForeground(Color.BLACK);
		btnPayLater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {try {
				// anyadir el estudiante, que es el usuario que estar�a logueado
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
					resultadoField.setText("Matriculado en curso correctamente");
					PantallaMatriculacion PM1 = new PantallaMatriculacion();
					PM1.setVisible(true);
				}
			}catch(Exception e) {
				resultadoField.setText("Ha ocurrido un error, vuelva a intentarlo mas tarde");
			}
			}
		});
		btnPayLater.setBackground(Color.LIGHT_GRAY);
		btnPayLater.setBounds(34, 244, 263, 67);
		contentPane.add(btnPayLater);
		
		resultadoField = new JTextField();
		resultadoField.setEnabled(false);
		resultadoField.setColumns(10);
		resultadoField.setBounds(316, 83, 303, 23);
		contentPane.add(resultadoField);
		
	}
}