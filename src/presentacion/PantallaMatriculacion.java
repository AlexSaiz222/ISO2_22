package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PantallaMatriculacion extends JFrame {

	private JPanel contentPane;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaMatriculacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
		
		JButton MatriculateBtn = new JButton("Matricularse");
		MatriculateBtn.setForeground(Color.BLACK);
		MatriculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaProcesoMatriculacion M1 = null;
				try {
					M1 = new PantallaProcesoMatriculacion();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				M1.setVisible(true);
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