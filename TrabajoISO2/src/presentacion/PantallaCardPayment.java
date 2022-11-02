package presentacion;

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
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Color;

public class PantallaCardPayment extends JFrame {

	private JPanel contentPane;
	private JTextField NumberField;
	private JTextField OwnerField;
	private JTextField SecretNumberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCardPayment frame = new PantallaCardPayment();
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
	public PantallaCardPayment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPagar M1 = new PantallaPagar();
				M1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaLogin P1 = new PantallaLogin();
				P1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
		
		JLabel lblPayment = new JLabel("Payment by credit card");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPayment.setBounds(34, 11, 270, 39);
		contentPane.add(lblPayment);
		
		NumberField = new JTextField();
		NumberField.setBounds(112, 152, 148, 20);
		contentPane.add(NumberField);
		NumberField.setColumns(10);
		
		JLabel NumberTxt = new JLabel("Number");
		NumberTxt.setBounds(25, 155, 77, 14);
		contentPane.add(NumberTxt);
		
		JLabel OwnerTxt = new JLabel("Owner");
		OwnerTxt.setBounds(25, 180, 46, 14);
		contentPane.add(OwnerTxt);
		
		OwnerField = new JTextField();
		OwnerField.setColumns(10);
		OwnerField.setBounds(112, 177, 148, 20);
		contentPane.add(OwnerField);
		
		JLabel SecretTxt = new JLabel("Secret number");
		SecretTxt.setBounds(24, 205, 89, 14);
		contentPane.add(SecretTxt);
		
		SecretNumberField = new JTextField();
		SecretNumberField.setBounds(112, 202, 148, 20);
		contentPane.add(SecretNumberField);
		SecretNumberField.setColumns(10);
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(358, 79, 241, 213);
		contentPane.add(UCLM_letters);
		
		JLabel CourseTxt = new JLabel("Tuition");
		CourseTxt.setBounds(25, 103, 46, 14);
		contentPane.add(CourseTxt);
		
		JList TuitionList = new JList();
		TuitionList.setBounds(112, 102, 148, 39);
		contentPane.add(TuitionList);
		
		JButton PayBtn = new JButton("Pay now!");
		PayBtn.setForeground(Color.BLACK);
		PayBtn.setBackground(Color.LIGHT_GRAY);
		PayBtn.setBounds(25, 233, 235, 59);
		contentPane.add(PayBtn);
	}
}
