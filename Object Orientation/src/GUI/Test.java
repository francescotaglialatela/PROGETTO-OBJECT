package GUI;

import java.awt.BorderLayout;

import Controller.Controller;

import java.awt.EventQueue;

import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.*;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class Test extends JFrame{

	static Account_Studente secondFrame;
	private JPanel contentPane;
	static String nometest;
	static String username;
	static int IdStudente;
	int n =0;
	int y=227;
	char op =0;
	int a;
	Integer in;
	JFrame framet;
	private PanelQuizAperto panelQa;
	
	ArrayList<Character> Risposte = new ArrayList();
	ArrayList<String> Domande = new ArrayList();
	ArrayList<String> DomandeAperte = new ArrayList();
	ArrayList idQuizM = new ArrayList();
	ArrayList idQuizA = new ArrayList();
	
	ButtonGroup sceltaquiz = new ButtonGroup();
	JRadioButton rdbtnOpzioniQuiz;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test(nometest,username,secondFrame);
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
	public Test(String nm, String us, Account_Studente frame) {
		
		framet = new JFrame();
		framet.setResizable(false);
		
		framet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framet.setBounds(100, 100, 1100, 663);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		framet.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		nometest=nm;
		username=us;
		
		
		Controller controller = new Controller();
		
		idQuizM = controller.getElencoQuizMultiploDB(nometest);
		idQuizA = controller.getElencoQuizApertoDB(nometest);
		Domande = controller.VisualizzaQuizMultiploDB(nometest, n);
		DomandeAperte = controller.VisualizzaQuizApertoDB(nometest, n);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1086, 626);
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelQuiz = new JPanel();
		panelQuiz.setBackground(Color.WHITE);
		panelQuiz.setBorder(new LineBorder(Color.BLACK, 2));
		panelQuiz.setBounds(271, 76, 543, 542);
		panel.add(panelQuiz);
		
		JLabel lblSfondoQuiz = new JLabel("");
		lblSfondoQuiz.setIcon(new ImageIcon(Test.class.getResource("/res/sfondoquiz.jpg")));
		lblSfondoQuiz.setBounds(0, 0, 1086, 626);
		panel.add(lblSfondoQuiz);
		panelQuiz.setLayout(null);
		
		
		JRadioButton Button1 = new JRadioButton(Domande.get(1));
		Button1.setBounds(6, 220, 524, 21);
		panelQuiz.add(Button1);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				op = (char) Button1.getMnemonic();
				op = Character.toLowerCase(op);
				Risposte.add(n, op);
				}
				
		});
		Button1.setMnemonic('a');
		Button1.setBackground(Color.WHITE);
		sceltaquiz.add(Button1);
		
		JRadioButton Button2 = new JRadioButton(Domande.get(2));
		Button2.setBounds(6, 275, 524, 21);
		panelQuiz.add(Button2);
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				op = (char) Button2.getMnemonic();
				op = Character.toLowerCase(op);
				Risposte.add(n,op);
				
			}
		});
		Button2.setMnemonic('b');
		Button2.setBackground(Color.WHITE);
		sceltaquiz.add(Button2);
		
		JRadioButton Button3 = new JRadioButton(Domande.get(3));
		Button3.setBounds(6, 330, 524, 21);
		panelQuiz.add(Button3);
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op = (char) Button3.getMnemonic();
				op = Character.toLowerCase(op);
				Risposte.add(n,op);
			
			}});
		Button3.setMnemonic('c');
		Button3.setBackground(Color.WHITE);
		sceltaquiz.add(Button3);
		
		JRadioButton Button4 = new JRadioButton(Domande.get(4));
		Button4.setBounds(6, 385, 524, 21);
		panelQuiz.add(Button4);
		Button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op = (char) Button4.getMnemonic();
				op = Character.toLowerCase(op);
				Risposte.add(n,op);
			}
		});
		Button4.setMnemonic('d');
		Button4.setBackground(Color.WHITE);
		sceltaquiz.add(Button4);
		
		
		JLabel lblDomanda = new JLabel(Domande.get(0));
		lblDomanda.setBounds(16, 98, 514, 84);
		panelQuiz.add(lblDomanda);
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDomanda.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblNomeCognome = new JLabel("Test: "+nometest);
		lblNomeCognome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeCognome.setBounds(16, 8, 519, 42);
		panelQuiz.add(lblNomeCognome);
		
		JLabel lblIndietro = new JLabel("Indietro");
		
		JLabel lblAvanti = new JLabel("Avanti");
		lblAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			
		});
		
		lblAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		});
		
		lblAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.BOLD, 10));
				n=n+1; 
				
				if(n>=idQuizM.size()) {
					n=0;
					
					panelQa= new PanelQuizAperto(DomandeAperte, n, Risposte, nometest, username, idQuizM, frame,framet, idQuizA);

					panel.setVisible(false);
					contentPane.add(panelQa);
					panelQa.setVisible(true);
					
					n++;
					
					
				}
				else {
					Domande.clear();
					Domande = controller.VisualizzaQuizMultiploDB(nometest, n);
					
					
					
					lblDomanda.setText("Domanda: "+Domande.get(0));
					Button1.setText(Domande.get(1));
					Button2.setText(Domande.get(2));
					Button3.setText(Domande.get(3));
					Button4.setText(Domande.get(4));
					
					
					lblIndietro.setVisible(true);
					
					}
			}
		});
		
		lblAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		});
		
		lblAvanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAvanti.setBounds(404, 472, 110, 42);
		panelQuiz.add(lblAvanti);
		
		
		lblIndietro.setVisible(false);
		
		lblIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblIndietro.setFont(new Font("Tahoma", Font.BOLD, 12));
				
			}
			
		});
		
		lblIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblIndietro.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		});
		
		lblIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Risposte.remove(n-1);
				Domande.clear();
				n=n-1;
				if(n==0) {
					lblIndietro.setVisible(false);
				}
				
				
				Domande = controller.VisualizzaQuizMultiploDB(nometest, n);
				
				
				lblDomanda.setText("Domanda: "+Domande.get(0));
				Button1.setText(Domande.get(1));
				Button2.setText(Domande.get(2));
				Button3.setText(Domande.get(3));
				Button4.setText(Domande.get(4));
				
			}
		});
		
		lblIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		});
		
		lblIndietro.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndietro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIndietro.setBounds(16, 472, 110, 42);
		panelQuiz.add(lblIndietro);
		

		
	}
}
