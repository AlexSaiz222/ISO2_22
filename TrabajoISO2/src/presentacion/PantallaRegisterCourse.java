package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PantallaRegisterCourse extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegisterCourse frame = new PantallaRegisterCourse();
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
	public PantallaRegisterCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Register a course proposal");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(170, 11, 315, 39);
		contentPane.add(Title);
		
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
				PantallaDireccionCurso D1 = new PantallaDireccionCurso();
				D1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JLabel Name = new JLabel("Name");
		Name.setBounds(82, 94, 35, 14);
		contentPane.add(Name);
		
		JButton RegisterBtn = new JButton("Register course proposal");
		RegisterBtn.setBackground(new Color(50, 205, 50));
		RegisterBtn.setBounds(82, 224, 247, 39);
		contentPane.add(RegisterBtn);
		
		JList list = new JList();
		list.setBounds(159, 93, 170, 120);
		contentPane.add(list);
		
		JLabel Photo = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		Photo.setBounds(422, 94, 196, 184);
		contentPane.add(Photo);
	}
}
