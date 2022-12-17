package presentacion;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.entities.Estudiante;
import persistencia.EstudianteDAO;
import persistencia.GestorBD;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class PantallaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField UsrField;
	private JPasswordField PsswdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GestorBD gestor = new GestorBD();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLogin frame = new PantallaLogin();
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
	public PantallaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel UserText = new JLabel("DNI:");
		UserText.setBounds(229, 110, 70, 39);
		contentPane.add(UserText);
		
		JLabel UCLM_Image = new JLabel(new ImageIcon("./images/uclm.png"));
		UCLM_Image.setBackground(SystemColor.activeCaptionText);
		UCLM_Image.setBounds(544, 304, 123, 70);
		contentPane.add(UCLM_Image);
		
		JLabel PsswdText = new JLabel("Contrase�a:");
		PsswdText.setBounds(229, 150, 70, 39);
		contentPane.add(PsswdText);
		
		UsrField = new JTextField();
		UsrField.setBackground(Color.LIGHT_GRAY);
		UsrField.setForeground(SystemColor.infoText);
		UsrField.setBounds(309, 119, 133, 20);
		contentPane.add(UsrField);
		UsrField.setColumns(10);
		String nombreUsuario = UsrField.getName();
		
		JLabel lblNewLabel = new JLabel("Bienvenido a la Universidad De Castilla La Mancha");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(59, 22, 566, 31);
		contentPane.add(lblNewLabel);
		
		JLabel dni_info = new JLabel(new ImageIcon("./images/ayuda.png"));
		dni_info.setToolTipText("p.e 03435754P");
		dni_info.setBounds(452, 119, 23, 20);
		contentPane.add(dni_info);
		
		PsswdField = new JPasswordField();
		PsswdField.setEchoChar('*');
		PsswdField.setBackground(Color.LIGHT_GRAY);
		PsswdField.setBounds(309, 159, 133, 20);
		contentPane.add(PsswdField);
		String psswdString = PsswdField.getName();
		
		//Aqui hay que poner un controlador para que, sabiendo el tipo de inicio de sesion, se abra una pantalla u otra
				JButton loginBtn = new JButton("Iniciar sesion");
				loginBtn.setBackground(new Color(0, 0, 0));
				loginBtn.setForeground(new Color(255, 255, 255));
				loginBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						PantallaMatriculacion pantallamatricula = new PantallaMatriculacion();
						EstudianteDAO ed1 = new EstudianteDAO();
						
						//ACCI�N DEL BOT�N DE ACCESO - COMPLETAR
						//1� comprobar si el usuario es correcto
						//si no es correcto --> lanzar excepci�n o mensaje de error
						
						//�es un alumno? --> pantalla realizar matr�cula
						if(ed1.seleccionarEstudiante(nombreUsuario) != null) {
							Estudiante e1 = ed1.seleccionarEstudiante(nombreUsuario);
							if(e1.getPsswd().compareTo(psswdString)==0) {
								pantallamatricula.setVisible(true);
							}
						}
						//�es personal vicerrectorado? --> pantalla evaluar propuesta curso
						
						//�es Jefe Gabinete vicerrectorado? --> pantalla Realizar consulta cursos
						
						//�es director curso? --> pantalla realizar/editar propuesta curso o pantalla visualizar propuesta curso
						
					}
				});
				loginBtn.setBackground(new Color(192, 192, 192));
				loginBtn.setFont(new Font("Arial", Font.BOLD, 12));
				GridBagConstraints gbc_btn_access = new GridBagConstraints();
				gbc_btn_access.insets = new Insets(0, 0, 5, 5);
				gbc_btn_access.gridx = 3;
				gbc_btn_access.gridy = 6;
				contentPane.add(loginBtn, gbc_btn_access);
				loginBtn.setBounds(212, 199, 225, 61);
				contentPane.add(loginBtn);
		
	}
	
	public void login() {
		// TODO - implement PantallaLogin.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement PantallaLogin.logout
		throw new UnsupportedOperationException();
	}
}