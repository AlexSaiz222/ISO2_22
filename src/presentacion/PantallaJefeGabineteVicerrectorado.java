package presentacion;

import java.io.Serializable;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import persistencia.CursoPropioDAO;

public class PantallaJefeGabineteVicerrectorado extends JFrame implements Serializable {
	
	private static Logger logJava = Logger.getLogger(PantallaJefeGabineteVicerrectorado.class);
	private final static String LOGFATAL = "LOG FATAL: ";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932249418747084027L;
	private JPanel contentPane;
	private transient CursoPropioDAO cursoDAO = new CursoPropioDAO();
	private JComboBox<String> NameField;
	private transient GestorPropuestasCursos g = new GestorPropuestasCursos();
	private PantallaLogin pantLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJefeGabineteVicerrectorado frame = new PantallaJefeGabineteVicerrectorado();
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
	public PantallaJefeGabineteVicerrectorado() throws SQLException {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Title = new JLabel("Evaluar propuesta de un curso");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(170, 11, 315, 39);
		contentPane.add(Title);

		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantLogin = new PantallaLogin();
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
		Name.setBounds(43, 94, 58, 14);
		contentPane.add(Name);

		NameField = new JComboBox<String>();
		NameField.setBounds(102, 90, 256, 23);
		contentPane.add(NameField);
		NameField.removeAllItems();

		EstadoCurso estado = EstadoCurso.PROPUESTO;
		List<CursoPropio> cursos = cursoDAO.listarCursosPorEstado(estado);

		for (CursoPropio c : cursos) {
			NameField.addItem(c.getNombre());
		}

		JLabel Photo = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		Photo.setBounds(422, 94, 196, 184);
		contentPane.add(Photo);

		JButton RejectBtn = new JButton("Rechazar propuesta");
		RejectBtn.setBackground(Color.RED);
		RejectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idCurso = Integer.parseInt(NameField.getSelectedItem().toString());
				try {
					CursoPropio curso = cursoDAO.seleccionarCurso(idCurso);
					// g.rechazarCurso(curso);
					EstadoCurso rechazadoCurso = EstadoCurso.PROPUESTA_RECHAZADA;
					curso.setEstado(rechazadoCurso);
				} catch (Exception e) {
					logJava.fatal(LOGFATAL+e.toString());
				}
			}
		});
		RejectBtn.setBounds(207, 239, 151, 39);
		contentPane.add(RejectBtn);

		JButton ApproveBtn = new JButton("Aprobar propuesta");
		ApproveBtn.setBackground(new Color(50, 205, 50));
		ApproveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CursoPropioDAO cursoDAO = new CursoPropioDAO();
				int idCurso = Integer.parseInt(NameField.getSelectedItem().toString());
				try {
					CursoPropio curso = cursoDAO.seleccionarCurso(idCurso);
					//g.altaCursoAprobado(curso);
				} catch (Exception e) {
					logJava.fatal(LOGFATAL+e.toString());
				}
			}
		});
		ApproveBtn.setBounds(30, 239, 151, 39);
		contentPane.add(ApproveBtn);
	}
}