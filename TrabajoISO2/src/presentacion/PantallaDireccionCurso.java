package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.Icon;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class PantallaDireccionCurso extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaDireccionCurso frame = new PantallaDireccionCurso();
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
	public PantallaDireccionCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton EditBtn = new JButton("Edit a course proposal");
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditCourseProposal E1 = new EditCourseProposal();
				E1.setVisible(true);
			}
		});
		EditBtn.setBackground(Color.LIGHT_GRAY);
		EditBtn.setForeground(Color.BLACK);
		EditBtn.setBounds(36, 126, 200, 53);
		contentPane.add(EditBtn);
		
		JLabel ChooseAnOption = new JLabel("Choose an option");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(234, 21, 270, 39);
		contentPane.add(ChooseAnOption);
		
		JLabel EditInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		EditInfo.setToolTipText("Edit a existing course");
		EditInfo.setBounds(257, 142, 23, 20);
		contentPane.add(EditInfo);
		
		JButton RegisterCourse = new JButton("Register a course");
		RegisterCourse.setForeground(Color.BLACK);
		RegisterCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterCourse R1 = new RegisterCourse();
				R1.setVisible(true);
			}
		});
		RegisterCourse.setBackground(Color.LIGHT_GRAY);
		RegisterCourse.setBounds(36, 242, 200, 53);
		contentPane.add(RegisterCourse);
		
		JLabel RegisterInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		RegisterInfo.setToolTipText("Register an approved course");
		RegisterInfo.setBounds(257, 255, 23, 20);
		contentPane.add(RegisterInfo);
		
		
		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(389, 96, 241, 213);
		contentPane.add(UCLM_letters);
		
		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaLogin L1 = new PantallaLogin();
				L1.setVisible(true);
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
	}

	public void altaCurso() {
		// TODO - implement PantallaDireccionCursos.altaCurso
		throw new UnsupportedOperationException();
	}

	public void edicionCurso() {
		// TODO - implement PantallaDireccionCursos.edicionCurso
		throw new UnsupportedOperationException();
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
