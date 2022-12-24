package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class PantallaEstadosCurso extends JFrame {

	private JPanel contentPane;
	private PantallaJefeVicerrectorado v1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaEstadosCurso frame = new PantallaEstadosCurso();
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
	public PantallaEstadosCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CheckTxt = new JLabel("Ver el estado de un curso");
		CheckTxt.setFont(new Font("Tahoma", Font.BOLD, 23));
		CheckTxt.setBounds(23, 11, 339, 39);
		contentPane.add(CheckTxt);
		
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
				v1 = new PantallaJefeVicerrectorado();
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
		
		JButton ListBttn = new JButton("Ver Estado");
		ListBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ListBttn.setForeground(Color.BLACK);
		ListBttn.setBackground(Color.LIGHT_GRAY);
		ListBttn.setBounds(23, 148, 251, 57);
		contentPane.add(ListBttn);
	}

}