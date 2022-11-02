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
import javax.swing.JList;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class PantallaMatriculationProcess extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaMatriculationProcess frame = new PantallaMatriculationProcess();
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
	public PantallaMatriculationProcess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				PantallaMatriculacion M1 = new PantallaMatriculacion();
				M1.setVisible(true);
			}
		});
		GoBackBttn.setBounds(570, 45, 89, 23);
		contentPane.add(GoBackBttn);
		
		JLabel Title = new JLabel("Matriculate");
		Title.setFont(new Font("Tahoma", Font.BOLD, 23));
		Title.setBounds(10, 15, 270, 39);
		contentPane.add(Title);
		
		JLabel SelectInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		SelectInfo.setText("");
		SelectInfo.setToolTipText("Select an existing course");
		SelectInfo.setBounds(253, 80, 23, 20);
		contentPane.add(SelectInfo);
		
		JLabel NameTxt = new JLabel("Name");
		NameTxt.setBounds(34, 86, 43, 14);
		contentPane.add(NameTxt);
		
		JList NameList = new JList();
		NameList.setBounds(87, 83, 156, 160);
		contentPane.add(NameList);
		
		JButton MatriculationBttn = new JButton("Pass to payment");
		MatriculationBttn.setForeground(Color.BLACK);
		MatriculationBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaPagar PP1 = new PantallaPagar();
				PP1.setVisible(true);
			}
		});
		MatriculationBttn.setBackground(Color.LIGHT_GRAY);
		MatriculationBttn.setBounds(34, 282, 209, 67);
		contentPane.add(MatriculationBttn);
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(358, 79, 241, 213);
		contentPane.add(UCLM_letters);
		
		JLabel DateTxt = new JLabel("Date");
		DateTxt.setBounds(31, 257, 46, 14);
		contentPane.add(DateTxt);
		
		JDateChooser DateField = new JDateChooser();
		DateField.setBounds(87, 251, 156, 20);
		contentPane.add(DateField);
	}
}
