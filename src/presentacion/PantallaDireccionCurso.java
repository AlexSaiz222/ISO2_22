package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.WindowConstants;

public class PantallaDireccionCurso extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private PantallaLogin pantLogin;

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
					System.out.println(e.toString());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaDireccionCurso() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton EditBtn = new JButton("Editar una propuesta de un curso");
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantallaEditarPropuestaCurso E1;
				try {
					E1 = new PantallaEditarPropuestaCurso();
					E1.setVisible(true);
				} catch (SQLException e) {
					System.out.println(e.toString());
				}

			}
		});

		EditBtn.setBackground(Color.LIGHT_GRAY);
		EditBtn.setForeground(Color.BLACK);
		EditBtn.setBounds(36, 126, 270, 53);
		contentPane.add(EditBtn);

		JLabel ChooseAnOption = new JLabel("Escoja una opcion");
		ChooseAnOption.setFont(new Font("Tahoma", Font.BOLD, 23));
		ChooseAnOption.setBounds(234, 21, 270, 39);
		contentPane.add(ChooseAnOption);

		JLabel EditInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		EditInfo.setToolTipText("Editar un curso existente");
		EditInfo.setBounds(257, 142, 23, 20);
		contentPane.add(EditInfo);

		JButton RegisterCourse = new JButton("Registrar un curso");
		RegisterCourse.setForeground(Color.BLACK);
		RegisterCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					PantallaRegistrarCurso R1 = new PantallaRegistrarCurso();
					R1.setVisible(true);
				} catch (SQLException e) {
					System.out.println(e.toString());
				}

			}
		});
		RegisterCourse.setBackground(Color.LIGHT_GRAY);
		RegisterCourse.setBounds(36, 242, 270, 53);
		contentPane.add(RegisterCourse);

		JLabel RegisterInfo = new JLabel(new ImageIcon("./images/ayuda.png"));
		RegisterInfo.setToolTipText("Registrar un curso aprobado");
		RegisterInfo.setBounds(257, 255, 23, 20);
		contentPane.add(RegisterInfo);

		JLabel UCLM_letters = new JLabel(new ImageIcon("./images/lettersUCLM.png"));
		UCLM_letters.setBounds(389, 96, 241, 213);
		contentPane.add(UCLM_letters);

		JButton LogOutBttn = new JButton("Log out");
		LogOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantLogin = new PantallaLogin();
				pantLogin.logout();
			}
		});
		LogOutBttn.setBounds(570, 11, 89, 23);
		contentPane.add(LogOutBttn);
	}

	public void altaCurso() {
		throw new UnsupportedOperationException();
	}

	public void edicionCurso() {
		throw new UnsupportedOperationException();
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
		throw new UnsupportedOperationException(); 
			
		}

	}
}