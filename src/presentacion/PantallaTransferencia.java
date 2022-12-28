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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.WindowConstants;

public class PantallaTransferencia extends JFrame {

	private JPanel contentPane;
	private JTextField OwnerField;
	private JTextField IBANField;
	private JTextField BankField;
	private PantallaLogin pantLogin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaTransferencia frame = new PantallaTransferencia();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaTransferencia() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
				pantLogin = new PantallaLogin();
				pantLogin.logout();
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);

		JLabel lblPayment = new JLabel("Pago por transferencia");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPayment.setBounds(34, 11, 270, 39);
		contentPane.add(lblPayment);

		JLabel lblNewLabel = new JLabel("Nombre Porpietario");
		lblNewLabel.setBounds(35, 89, 119, 14);
		contentPane.add(lblNewLabel);

		OwnerField = new JTextField();
		OwnerField.setBounds(164, 86, 192, 20);
		contentPane.add(OwnerField);
		OwnerField.setColumns(10);

		IBANField = new JTextField();
		IBANField.setBounds(164, 117, 192, 20);
		contentPane.add(IBANField);
		IBANField.setColumns(10);
		// meter un label de fecha de compra

		JLabel IBANtxt = new JLabel("IBAN");
		IBANtxt.setBounds(34, 120, 46, 14);
		contentPane.add(IBANtxt);

		JLabel BnkTxt = new JLabel("Direccion bancaria");
		BnkTxt.setBounds(34, 145, 127, 14);
		contentPane.add(BnkTxt);

		BankField = new JTextField();
		BankField.setBounds(164, 148, 192, 20);
		contentPane.add(BankField);
		BankField.setColumns(10);

		JButton PayBtn = new JButton("Pagar!");
		PayBtn.setForeground(Color.BLACK);
		PayBtn.setBackground(Color.LIGHT_GRAY);
		PayBtn.setBounds(34, 188, 320, 46);
		contentPane.add(PayBtn);

		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(405, 79, 241, 213);
		contentPane.add(UCLM_letters);
	}

}