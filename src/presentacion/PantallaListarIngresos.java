package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;

import negocio.entities.TipoCurso;

public class PantallaListarIngresos extends JFrame {
	
	private static Logger logJava = Logger.getLogger(PantallaListarIngresos.class);
	private final static String logFatal = "LOG FATAL: ";

	private JPanel contentPane;
	private PantallaLogin pantLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaListarIngresos frame = new PantallaListarIngresos();
					frame.setVisible(true);
				} catch (Exception e) {
					logJava.fatal(logFatal+e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaListarIngresos() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ChooseAnOption = new JLabel("Listar ingresos");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(23, 11, 270, 39);
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

		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaJefeVicerrectorado v1 = new PantallaJefeVicerrectorado();
				v1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);

		JLabel TypeTxt = new JLabel("Tipo de curso");
		TypeTxt.setBounds(23, 89, 89, 14);
		contentPane.add(TypeTxt);

		JLabel StartTxt = new JLabel("Fecha Inicio");
		StartTxt.setBounds(23, 250, 72, 14);
		contentPane.add(StartTxt);

		JLabel EndTxt = new JLabel("Fecha Fin");
		EndTxt.setBounds(23, 276, 72, 14);
		contentPane.add(EndTxt);

		JButton IncomesBtn = new JButton("Listar Ingresos");
		IncomesBtn.setForeground(Color.BLACK);
		IncomesBtn.setBackground(Color.LIGHT_GRAY);
		IncomesBtn.setBounds(23, 301, 245, 64);
		contentPane.add(IncomesBtn);

		JTextArea PlotIncomes = new JTextArea();
		PlotIncomes.setBounds(403, 89, 205, 201);
		contentPane.add(PlotIncomes);

		JDateChooser StartDateField = new JDateChooser();
		StartDateField.setBounds(134, 250, 134, 20);
		contentPane.add(StartDateField);

		JDateChooser EndDateField = new JDateChooser();
		EndDateField.setBounds(134, 276, 134, 20);
		contentPane.add(EndDateField);

		JComboBox typeBox = new JComboBox();
		typeBox.setBounds(122, 89, 146, 21);
		contentPane.add(typeBox);
		typeBox.removeAllItems();
		for (TipoCurso t : TipoCurso.values()) {
			typeBox.addItem(t);
		}

	}
}