package presentacion;

import java.io.Serializable;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

import negocio.entities.Estudiante;
import negocio.entities.PersonalVicerrectorado;
import negocio.entities.ProfesorUCLM;
import persistencia.EstudianteDAO;
import persistencia.PersonalVicerrectoradoDAO;
import persistencia.ProfesorUCLMDAO;

public class PantallaLogin extends JFrame implements Serializable {
	
	private static Logger logJava = Logger.getLogger(PantallaLogin.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4463273531768871291L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField_userName;
	private JButton btn_access;
	private transient Estudiante estudianteLogeado;
	private transient ProfesorUCLM directorLogeado;
	private String nombreUsuario;
	private String contrasena;
	private PantallaLogin pantLogin;
	final String tipoLetra = "Arial";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLogin frame = new PantallaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaLogin() {

		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 33, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lbl_userName = new JLabel("Nombre de usuario");
		lbl_userName.setFont(new Font(tipoLetra, Font.BOLD, 12));
		GridBagConstraints gbc_lbl_userName = new GridBagConstraints();
		gbc_lbl_userName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_userName.gridx = 3;
		gbc_lbl_userName.gridy = 2;
		contentPane.add(lbl_userName, gbc_lbl_userName);

		textField_userName = new JTextField();
		textField_userName.setToolTipText("Insertar aqui el nombre de usuario");
		textField_userName.setFont(new Font("tipoLetra", Font.PLAIN, 11));
		GridBagConstraints gbc_textField_userName = new GridBagConstraints();
		gbc_textField_userName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_userName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_userName.gridx = 3;
		gbc_textField_userName.gridy = 3;
		contentPane.add(textField_userName, gbc_textField_userName);
		textField_userName.setColumns(10);
		nombreUsuario = textField_userName.getText();

		JLabel lbl_password = new JLabel("Contrasena");
		lbl_password.setFont(new Font(tipoLetra, Font.BOLD, 12));
		GridBagConstraints gbc_lbl_password = new GridBagConstraints();
		gbc_lbl_password.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_password.gridx = 3;
		gbc_lbl_password.gridy = 4;
		contentPane.add(lbl_password, gbc_lbl_password);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font(tipoLetra, Font.PLAIN, 11));
		passwordField.setToolTipText("Insertar aqui la contrasena");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		contrasena = new String(passwordField.getPassword());

		btn_access = new JButton("Acceso");
		btn_access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaMatriculacion pantallamatricula = new PantallaMatriculacion();
				PantallaDireccionCurso pantallaDireccionEdicion = new PantallaDireccionCurso();
				EstudianteDAO ed1 = new EstudianteDAO();
				ProfesorUCLMDAO pf1 = new ProfesorUCLMDAO();
				PersonalVicerrectoradoDAO pv1 = new PersonalVicerrectoradoDAO();

				// si es un alumno --> pantalla realizar matricula
				try {
					if (ed1.seleccionarEstudiante(nombreUsuario) != null) {
						estudianteSeleccionado(ed1);
						pantallamatricula.setVisible(true);
					} else {
						// si no es alumno
						// si es director curso --> pantalla realizar/editar propuesta curso o pantalla
						// visualizar propuesta curso
						// es profesor de la UCLM
						if (pf1.listarProfesorUCLM(nombreUsuario) != null) {
							ProfesorUCLM p1 = pf1.seleccionarProfesorUCLM(nombreUsuario);
							if (p1.getPassword().compareTo(contrasena) == 0) {
								// Si es un profesor de la UCLM --> es director
								pantallaDireccionEdicion.setVisible(true);
								directorLogeado = p1;
							}
						} else if (pv1.seleccionarProfesor(nombreUsuario) != null) {// personal vicerrectorado/jefe de
																					// gabinete
							// si es personal vicerrectorado --> pantalla evaluar propuesta curso
							profesorSeleccionado(pv1);
						}
					}
				} catch (Exception exception1) {
					System.out.println("El usuario no se encuentra en la base de datos.");
				}

			}

		});
		btn_access.setBackground(new Color(192, 192, 192));
		btn_access.setFont(new Font(tipoLetra, Font.BOLD, 12));
		GridBagConstraints gbc_btn_access = new GridBagConstraints();
		gbc_btn_access.insets = new Insets(0, 0, 5, 5);
		gbc_btn_access.gridx = 3;
		gbc_btn_access.gridy = 6;
		contentPane.add(btn_access, gbc_btn_access);
	}

	public void logout() {
		pantLogin = new PantallaLogin();
		pantLogin.setVisible(true);
		borrarEstudianteLogeado();
		borrarDirectorLogeado();
	}

	public Estudiante getEstudianteLogeado() {
		return estudianteLogeado;
	}

	public void borrarEstudianteLogeado() {
		estudianteLogeado = null;
	}

	public ProfesorUCLM getDirectorLogeado() {
		return directorLogeado;
	}

	public void borrarDirectorLogeado() {
		directorLogeado = null;
	}

	public void profesorSeleccionado(PersonalVicerrectoradoDAO pv1) throws SQLException {
		PersonalVicerrectorado personalVicerrectorado = pv1.seleccionarProfesor(nombreUsuario);
		if (personalVicerrectorado.getPassword().compareTo(contrasena) == 0) {
			if (personalVicerrectorado.isJefe() == true) {
				PantallaJefeVicerrectorado pjfGabineteVicerrectorado = new PantallaJefeVicerrectorado();
				pjfGabineteVicerrectorado.setVisible(true);
			} else {
				PantallaEvaluarPropuesta pfGabineteVicerrectorado = new PantallaEvaluarPropuesta();
				pfGabineteVicerrectorado.setVisible(true);
			}

		}
	}

	public void estudianteSeleccionado(EstudianteDAO ed1) throws SQLException {
		Estudiante e1 = ed1.seleccionarEstudiante(nombreUsuario);
		if (e1.getPassword().compareTo(contrasena) == 0) {
			estudianteLogeado = e1;
		}

	}
}
