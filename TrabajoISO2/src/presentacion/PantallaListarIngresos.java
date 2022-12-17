package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import negocio.entities.TipoCurso;

public class PantallaListarIngresos extends JFrame {

	private JPanel contentPane;

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaListarIngresos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
		
		JButton GoBackBttn = new JButton("Go back");
		GoBackBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaEmpleadosVicerrectorado v1 = new PantallaEmpleadosVicerrectorado();
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
		for(TipoCurso t: TipoCurso.values()) {
			typeBox.addItem(t);
		}
		
	}
}