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
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import java.awt.Color;

public class PantallaListarEdiciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaListarEdiciones frame = new PantallaListarEdiciones();
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
	public PantallaListarEdiciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ChooseAnOption = new JLabel("Listar Ediciones de un Curso");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(23, 11, 270, 39);
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
		
		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaJefeVicerrectorado v1 = new PantallaJefeVicerrectorado();
				v1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JLabel lblNewLabel = new JLabel("Fecha Inicio");
		lblNewLabel.setBounds(23, 83, 77, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Fin");
		lblNewLabel_1.setBounds(23, 108, 56, 14);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser StartDateField = new JDateChooser();
		StartDateField.setBounds(121, 83, 153, 20);
		contentPane.add(StartDateField);
		
		JDateChooser EndDate = new JDateChooser();
		EndDate.setBounds(121, 108, 153, 20);
		contentPane.add(EndDate);
		
		JTextArea Plot = new JTextArea();
		Plot.setBounds(377, 81, 228, 192);
		contentPane.add(Plot);
		
		JButton ListBttn = new JButton("Ver ediciones");
		ListBttn.setForeground(Color.BLACK);
		ListBttn.setBackground(Color.LIGHT_GRAY);
		ListBttn.setBounds(23, 148, 251, 57);
		contentPane.add(ListBttn);
	}
}