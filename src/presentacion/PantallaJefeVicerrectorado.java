package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

public class PantallaJefeVicerrectorado extends JFrame {
	
	private static Logger logJava = Logger.getLogger(PantallaJefeVicerrectorado.class);
	private final static String LOGFATAL = "LOG FATAL: ";

	private JPanel contentPane;
	private PantallaLogin pantLogin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaJefeVicerrectorado frame = new PantallaJefeVicerrectorado();
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
	public PantallaJefeVicerrectorado() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ChooseAnOption = new JLabel("Escoja una opcion");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(32, 21, 270, 39);
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

		JButton IncomesBttn = new JButton("Ver ingresos");
		IncomesBttn.setForeground(Color.BLACK);
		IncomesBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaListarIngresos M1 = new PantallaListarIngresos();
				M1.setVisible(true);
			}
		});
		IncomesBttn.setBackground(Color.LIGHT_GRAY);
		IncomesBttn.setBounds(32, 88, 230, 53);
		contentPane.add(IncomesBttn);

		JButton CourseBtn = new JButton("Listar ediciones");
		CourseBtn.setForeground(Color.BLACK);
		CourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaListarEdiciones P1 = new PantallaListarEdiciones();
				P1.setVisible(true);
			}
		});
		CourseBtn.setBackground(Color.LIGHT_GRAY);
		CourseBtn.setBounds(32, 162, 230, 53);
		contentPane.add(CourseBtn);

		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(387, 77, 241, 213);
		contentPane.add(UCLM_letters);

		JButton StatusBtn = new JButton("Ver el estado de un curso");
		StatusBtn.setForeground(Color.BLACK);
		StatusBtn.setBackground(Color.LIGHT_GRAY);
		StatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaEstadosCurso P1 = new PantallaEstadosCurso();
				P1.setVisible(true);
			}
		});
		StatusBtn.setBounds(32, 237, 230, 53);
		contentPane.add(StatusBtn);
	}
}