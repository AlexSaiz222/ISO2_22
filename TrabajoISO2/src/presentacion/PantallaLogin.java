package presentacion;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.GestorBD;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PantallaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField UsrField;
	private JTextField PsswdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GestorBD gestor = GestorBD.getAgente();
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
		setBounds(100, 100, 693, 424);
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
		
		JLabel PsswdText = new JLabel("Password:");
		PsswdText.setBounds(229, 150, 70, 39);
		contentPane.add(PsswdText);
		
		UsrField = new JTextField();
		UsrField.setBackground(new Color(192, 192, 192));
		UsrField.setForeground(SystemColor.infoText);
		UsrField.setBounds(309, 119, 133, 20);
		contentPane.add(UsrField);
		UsrField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Universidad De Castilla La Mancha");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(59, 22, 566, 31);
		contentPane.add(lblNewLabel);
		
		PsswdField = new JTextField();
		PsswdField.setBackground(new Color(192, 192, 192));
		PsswdField.setForeground(Color.BLACK);
		PsswdField.setColumns(10);
		PsswdField.setBounds(309, 159, 133, 20);
		contentPane.add(PsswdField);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(212, 199, 225, 61);
		contentPane.add(btnNewButton);
		
		JLabel dni_info = new JLabel(new ImageIcon("./images/ayuda.png"));
		dni_info.setToolTipText("f.e 03435754P");
		dni_info.setText("");
		dni_info.setBounds(452, 119, 23, 20);
		contentPane.add(dni_info);
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
