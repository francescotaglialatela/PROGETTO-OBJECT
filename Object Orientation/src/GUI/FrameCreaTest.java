package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;

public class FrameCreaTest extends JFrame {

	private JPanel contentPane;
	static String username;
	static Account_Insegnante accountFrame;
	private JTextField lblNomeTestField;
	 String nomeTest;
	FrameCreaTest frameCreaT;
	
	private PanelSceltaQuiz secondaPagina;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCreaTest frame = new FrameCreaTest(username, accountFrame);
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
	public FrameCreaTest(String user, Account_Insegnante frame) {
		setResizable(false);
		setTitle("MyLearn ~ Crea Test");
		
		Controller controller = new Controller();
		
		username = user;
		accountFrame = frame;
		
		frameCreaT = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 713);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		panelMain.setBounds(90, 15, 806, 678);
		panel.add(panelMain);
		panelMain.setLayout(null);
		
		JPanel primaPagina = new JPanel();
		primaPagina.setBounds(8, 8, 790, 662);
		panelMain.add(primaPagina);
		primaPagina.setLayout(null);
		
		JLabel lblCreazioneTest = new JLabel("CREAZIONE TEST");
		lblCreazioneTest.setBounds(0, 18, 790, 73);
		primaPagina.add(lblCreazioneTest);
		lblCreazioneTest.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCreazioneTest.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblText = new JLabel("Scegli il nome del tuo test");
		lblText.setBounds(0, 140, 790, 51);
		primaPagina.add(lblText);
		lblText.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNomeTestField = new JTextField();
		lblNomeTestField.setBounds(290, 199, 208, 25);
		primaPagina.add(lblNomeTestField);
		lblNomeTestField.setColumns(10);
		
		JPanel panelCreaTest = new JPanel();
		panelCreaTest.setBounds(323, 579, 132, 25);
		primaPagina.add(panelCreaTest);
		panelCreaTest.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCreaTest.setBackground(new Color(51, 102, 255));
		panelCreaTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelCreaTest.setBackground(new Color(51,204,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelCreaTest.setBackground(new Color(51,102,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelCreaTest.setBackground(new Color(51,102,255));
			
				nomeTest = lblNomeTestField.getText();
				
				
				
				controller.CreazioneTestDB(nomeTest, username);
				
				secondaPagina = new PanelSceltaQuiz(nomeTest, username, frame, frameCreaT);
				secondaPagina.setLocation(8,8);
				panelMain.add(secondaPagina);
				
				if(nomeTest.equals("")) {
					JOptionPane.showMessageDialog(null, "Errore! Non hai inserito il nome del test!","MyLearn",JOptionPane.ERROR_MESSAGE);
				}
				else
				{	
				primaPagina.setVisible(false);
				secondaPagina.setVisible(true);
				
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelCreaTest.setBackground(new Color(51,204,204));
			}
		});
		panelCreaTest.setLayout(null);
		
		JLabel lblCreaTest = new JLabel("CREA TEST");
		lblCreaTest.setBounds(0, 0, 129, 25);
		panelCreaTest.add(lblCreaTest);
		lblCreaTest.setForeground(Color.WHITE);
		lblCreaTest.setBackground(new Color(51, 102, 255));
		lblCreaTest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCreaTest.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSfondo = new JLabel("");
		lblSfondo.setIcon(new ImageIcon(FrameCreaTest.class.getResource("/res/sfondo_account.jpg")));
		lblSfondo.setBounds(0, 0, 986, 713);
		panel.add(lblSfondo);
		
		
		
	}
}
