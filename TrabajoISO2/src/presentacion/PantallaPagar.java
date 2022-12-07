package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class PantallaPagar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPagar frame = new PantallaPagar();
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
	public PantallaPagar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaMatriculacion M1 = new PantallaMatriculacion();
				M1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
		
		JLabel lblPayment = new JLabel("Pago");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPayment.setBounds(10, 15, 270, 39);
		contentPane.add(lblPayment);
		
		JLabel lblPleaseSelectAn = new JLabel("Por favor, escoja una opcion");
		lblPleaseSelectAn.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPleaseSelectAn.setBounds(43, 54, 391, 39);
		contentPane.add(lblPleaseSelectAn);
		
		JLabel Payment = new JLabel(new ImageIcon("./images/metodosPago.png"));
		Payment.setBounds(150, 162, 379, 168);
		contentPane.add(Payment);
		
		JButton CardBtn = new JButton("Pago con tarjeta");
		CardBtn.setForeground(Color.BLACK);
		CardBtn.setBackground(Color.LIGHT_GRAY);
		CardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPagoTarjeta PT1 = new PantallaPagoTarjeta();
				PT1.setVisible(true);
			}
		});
		CardBtn.setBounds(104, 104, 131, 47);
		contentPane.add(CardBtn);
		
		JButton TransferBtn = new JButton("Pago por transferencia");
		TransferBtn.setForeground(Color.BLACK);
		TransferBtn.setBackground(Color.LIGHT_GRAY);
		TransferBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaTransferencia T1 = new PantallaTransferencia();
				T1.setVisible(true);
			}
		});
		TransferBtn.setBounds(419, 104, 167, 47);
		contentPane.add(TransferBtn);
		
		JLabel orTxt = new JLabel("o");
		orTxt.setBounds(316, 120, 46, 14);
		contentPane.add(orTxt);
	}
}