package presentacion;

import java.io.Serializable;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

public class PantallaMatriculacion extends JFrame implements Serializable{
	
	private static Logger logJava = Logger.getLogger(PantallaMatriculacion.class);
	private final static String LOGFATAL = "LOG FATAL: ";

	/**
	 * 
	 */
	private static final long serialVersionUID = 8107811786715723766L;
	private JPanel contentPane;
	private PantallaLogin pantLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaMatriculacion frame = new PantallaMatriculacion();
					frame.setVisible(true);
				} catch (Exception e) {
					logJava.fatal(LOGFATAL+e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaMatriculacion() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ChooseAnOption = new JLabel("Escoja una opcion");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(225, 11, 270, 39);
		contentPane.add(ChooseAnOption);

		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantLogin = new PantallaLogin();
				pantLogin.logout();
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);

		JButton MatriculateBtn = new JButton("Matricularse");
		MatriculateBtn.setForeground(Color.BLACK);
		MatriculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					PantallaProcesoMatriculacion M1 = new PantallaProcesoMatriculacion();
					M1.setVisible(true);
				} catch (SQLException e) {
					logJava.fatal(LOGFATAL+e.toString());
				}

			}
		});

		MatriculateBtn.setBackground(Color.LIGHT_GRAY);
		MatriculateBtn.setBounds(32, 118, 200, 53);
		contentPane.add(MatriculateBtn);

		JLabel EditInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		EditInfo.setToolTipText("Matricularse de un curso existente");
		EditInfo.setBounds(253, 134, 23, 20);
		contentPane.add(EditInfo);

		JButton btnPay = new JButton("Pagar una matricula");
		btnPay.setForeground(Color.BLACK);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPagar P1 = new PantallaPagar();
				P1.setVisible(true);
			}
		});
		btnPay.setBackground(Color.LIGHT_GRAY);
		btnPay.setBounds(32, 234, 200, 53);
		contentPane.add(btnPay);

		JLabel RegisterInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		RegisterInfo.setToolTipText("Tarjeta o transferencia");
		RegisterInfo.setBounds(253, 247, 23, 20);
		contentPane.add(RegisterInfo);

		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(385, 88, 241, 213);
		contentPane.add(UCLM_letters);
	}
}