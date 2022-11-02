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

public class PantallaEmpleadosVicerrectorado extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaEmpleadosVicerrectorado frame = new PantallaEmpleadosVicerrectorado();
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
	public PantallaEmpleadosVicerrectorado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ChooseAnOption = new JLabel("Choose an option");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(32, 11, 270, 39);
		contentPane.add(ChooseAnOption);
		
		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
		
		JButton IncomesBttn = new JButton("Check incomes");
		IncomesBttn.setForeground(Color.BLACK);
		IncomesBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaCheckIncomes M1 = new PantallaCheckIncomes();
				M1.setVisible(true);
			}
		});
		IncomesBttn.setBackground(Color.LIGHT_GRAY);
		IncomesBttn.setBounds(32, 88, 200, 53);
		contentPane.add(IncomesBttn);
		
		JButton CourseBtn = new JButton("List course editions");
		CourseBtn.setForeground(Color.BLACK);
		CourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaListEditions P1 = new PantallaListEditions();
				P1.setVisible(true);
			}
		});
		CourseBtn.setBackground(Color.LIGHT_GRAY);
		CourseBtn.setBounds(32, 162, 200, 53);
		contentPane.add(CourseBtn);
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(387, 77, 241, 213);
		contentPane.add(UCLM_letters);
		
		JButton StatusBtn = new JButton("Check the status of a course");
		StatusBtn.setForeground(Color.BLACK);
		StatusBtn.setBackground(Color.LIGHT_GRAY);
		StatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaStatusCourse P1 = new PantallaStatusCourse();
				P1.setVisible(true);
			}
		});
		StatusBtn.setBounds(32, 237, 200, 53);
		contentPane.add(StatusBtn);
	}

	public void evaluarCurso() {
		// TODO - implement PantallaEmpleadosVicerrectorado.evaluarCurso
		throw new UnsupportedOperationException();
	}
}
