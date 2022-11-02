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
import javax.swing.JList;

public class PantallaEditCourseProposal extends JFrame {

	private JPanel contentPane;
	private JTextField EtcsField;
	private JTextField StartDateField;
	private JTextField EndDateField;
	private JTextField FeeField;
	private JTextField EditionField;
	private JTextField NameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaEditCourseProposal frame = new PantallaEditCourseProposal();
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
	public PantallaEditCourseProposal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Edit a course proposal");
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
		
		EtcsField = new JTextField();
		EtcsField.setBounds(170, 116, 159, 20);
		contentPane.add(EtcsField);
		EtcsField.setColumns(10);
		
		JLabel StarDateTxt = new JLabel("Start Date");
		StarDateTxt.setBounds(82, 145, 57, 14);
		contentPane.add(StarDateTxt);
		
		JLabel EndDateTxt = new JLabel("End Date");
		EndDateTxt.setBounds(82, 176, 57, 14);
		contentPane.add(EndDateTxt);
		
		JButton EditBtn = new JButton("Edit course proposal");
		EditBtn.setBackground(new Color(50, 205, 50));
		EditBtn.setBounds(82, 279, 247, 39);
		contentPane.add(EditBtn);
		
		EtcsField = new JTextField();
		EtcsField.setColumns(10);
		EtcsField.setBounds(172, 116, 157, 20);
		contentPane.add(EtcsField);
		
		StartDateField = new JTextField();
		StartDateField.setColumns(10);
		StartDateField.setBounds(170, 142, 157, 20);
		contentPane.add(StartDateField);
		
		EndDateField = new JTextField();
		EndDateField.setColumns(10);
		EndDateField.setBounds(170, 173, 157, 20);
		contentPane.add(EndDateField);
		
		FeeField = new JTextField();
		FeeField.setColumns(10);
		FeeField.setBounds(172, 204, 157, 20);
		contentPane.add(FeeField);
		
		EditionField = new JTextField();
		EditionField.setColumns(10);
		EditionField.setBounds(170, 229, 157, 20);
		contentPane.add(EditionField);
		
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
		NameField.setBounds(170, 91, 159, 20);
		contentPane.add(NameField);
		NameField.setColumns(10);
	}
}
