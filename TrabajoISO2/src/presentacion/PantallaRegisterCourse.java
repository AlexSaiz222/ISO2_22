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
import com.toedter.calendar.JDateChooser;

public class PantallaRegisterCourse extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField ETCSField;
	private JTextField FeeField;
	private JTextField EditionField;

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
		Title.setBounds(192, 11, 315, 39);
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
		
		JLabel ETCStxt = new JLabel("ETCS");
		ETCStxt.setBounds(82, 119, 35, 14);
		contentPane.add(ETCStxt);
		
		JLabel StarDateTxt = new JLabel("Start Date");
		StarDateTxt.setBounds(82, 145, 57, 14);
		contentPane.add(StarDateTxt);
		
		JLabel EndDateTxt = new JLabel("End Date");
		EndDateTxt.setBounds(82, 176, 57, 14);
		contentPane.add(EndDateTxt);
		
		JButton EditBtn = new JButton("Register course proposal");
		EditBtn.setBackground(new Color(50, 205, 50));
		EditBtn.setBounds(82, 279, 247, 39);
		contentPane.add(EditBtn);
		
		JLabel FeeTxt = new JLabel("Tuition fee");
		FeeTxt.setBounds(82, 207, 80, 14);
		contentPane.add(FeeTxt);
		
		JLabel EditionTxt = new JLabel("Edition");
		EditionTxt.setBounds(82, 232, 46, 14);
		contentPane.add(EditionTxt);
		
		JLabel Photo = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		Photo.setBounds(432, 94, 196, 184);
		contentPane.add(Photo);
		
		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(170, 91, 159, 20);
		contentPane.add(NameField);
		
		ETCSField = new JTextField();
		ETCSField.setColumns(10);
		ETCSField.setBounds(170, 116, 159, 20);
		contentPane.add(ETCSField);
		
		FeeField = new JTextField();
		FeeField.setColumns(10);
		FeeField.setBounds(170, 204, 159, 20);
		contentPane.add(FeeField);
		
		EditionField = new JTextField();
		EditionField.setColumns(10);
		EditionField.setBounds(170, 229, 159, 20);
		contentPane.add(EditionField);
		
		JDateChooser StartDate = new JDateChooser();
		StartDate.setBounds(170, 145, 159, 20);
		contentPane.add(StartDate);
		
		JDateChooser EndDate = new JDateChooser();
		EndDate.setBounds(170, 176, 159, 20);
		contentPane.add(EndDate);
		
	}
}
