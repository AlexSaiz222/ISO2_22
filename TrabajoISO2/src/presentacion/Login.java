package presentacion;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.entities.Estudiante;
import persistencia.EstudianteDAO;
import persistencia.GestorBD;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.Window.Type;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField_userName;
	private JButton btn_access;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {50, 50, 50, 50, 50, 50, 50, 50};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 33, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbl_userName = new JLabel("Nombre de usuario");
		lbl_userName.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_lbl_userName = new GridBagConstraints();
		gbc_lbl_userName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_userName.gridx = 3;
		gbc_lbl_userName.gridy = 2;
		contentPane.add(lbl_userName, gbc_lbl_userName);
		
		textField_userName = new JTextField();
		textField_userName.setToolTipText("Insertar aqui el nombre de usuario");
		textField_userName.setFont(new Font("Arial", Font.PLAIN, 11));
		GridBagConstraints gbc_textField_userName = new GridBagConstraints();
		gbc_textField_userName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_userName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_userName.gridx = 3;
		gbc_textField_userName.gridy = 3;
		contentPane.add(textField_userName, gbc_textField_userName);
		textField_userName.setColumns(10);
		String nombreUsuario = textField_userName.getText();
		
		JLabel lbl_password = new JLabel("Contraseña");
		lbl_password.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_lbl_password = new GridBagConstraints();
		gbc_lbl_password.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_password.gridx = 3;
		gbc_lbl_password.gridy = 4;
		contentPane.add(lbl_password, gbc_lbl_password);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 11));
		passwordField.setToolTipText("Insertar aqui la contraseña");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		String contraseña = passwordField.getText();
		
		btn_access = new JButton("Acceso");
		btn_access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaMatriculacion pantallamatricula = new PantallaMatriculacion();
				EstudianteDAO ed1 = new EstudianteDAO();
				
				//ACCIÓN DEL BOTÓN DE ACCESO - COMPLETAR
				//1º comprobar si el usuario es correcto
				//si no es correcto --> lanzar excepción o mensaje de error
				
				//¿es un alumno? --> pantalla realizar matrícula
				if(ed1.listarEstudiante(nombreUsuario) != null) {
					Estudiante e1 = ed1.listarEstudiante(nombreUsuario);
					if(e1.getPassword().compareTo(contraseña)==0) {
						pantallamatricula.setVisible(true);
					}
				}
				//¿es personal vicerrectorado? --> pantalla evaluar propuesta curso
				
				//¿es Jefe Gabinete vicerrectorado? --> pantalla Realizar consulta cursos
				
				//¿es director curso? --> pantalla realizar/editar propuesta curso o pantalla visualizar propuesta curso
				
				
			}
		});
		btn_access.setBackground(new Color(192, 192, 192));
		btn_access.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btn_access = new GridBagConstraints();
		gbc_btn_access.insets = new Insets(0, 0, 5, 5);
		gbc_btn_access.gridx = 3;
		gbc_btn_access.gridy = 6;
		contentPane.add(btn_access, gbc_btn_access);
	}

}
