package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameCorreggiTest extends JFrame {

	private JPanel contentPane;
	static String nometest;
	private String username;
	private static int idStudente;
	static Account_Insegnante frameI;
	int idTest;
	ArrayList<String> Domande = new ArrayList();
	ArrayList<String> Risposte = new ArrayList();
	ArrayList<Double> Punteggi = new ArrayList();
	private JTextField PunteggioAssegnato;
	ArrayList<Double> punteggioFinale = new ArrayList();
	int n=0;
	ArrayList idQuizA = new ArrayList();
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCorreggiTest frame = new FrameCorreggiTest(idStudente,nometest,frameI);
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
	public FrameCorreggiTest(int idStu, String nomeTest, Account_Insegnante frameIns) {
		setResizable(false);
		
		idStudente = idStu;
		nometest = nomeTest;
		frameI = frameIns;
		
		
		Controller controller = new Controller();
		
		StudenteDAO s = new StudenteImplementazionePostgresDAO();
		TestDAO t = new TestImplementazionePostgresDAO();
		
		idTest = controller.getIdTest(nomeTest);
		Domande = controller.trovaDomandaQuizApertoDB(idStudente, idTest);
		Risposte = controller.trovaRisposteAperteDB(idStudente, idTest);
		Punteggi = controller.trovaPunteggiDaAssegnareDB(idStudente, idTest);
		idQuizA = controller.getElencoQuizApertoDB(nomeTest);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1086, 626);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelQuiz = new JPanel();
		panelQuiz.setBackground(Color.WHITE);
		panelQuiz.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelQuiz.setBounds(271, 76, 543, 542);
		panel.add(panelQuiz);
		panelQuiz.setLayout(null);
		
		JLabel lblDatiStudente = new JLabel("STUDENTE: ");
		lblDatiStudente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDatiStudente.setBounds(25, 25, 120, 20);
		panelQuiz.add(lblDatiStudente);
		
		JLabel lblMatricola = new JLabel("MATRICOLA:");
		lblMatricola.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatricola.setBounds(25, 55, 120, 20);
		panelQuiz.add(lblMatricola);
		
		JLabel lblTextDatiStudente = new JLabel(controller.trovaDatiDB(idStudente).get(0)+" "+controller.trovaDatiDB(idStudente).get(1));
		lblTextDatiStudente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTextDatiStudente.setBounds(126, 25, 407, 20);
		panelQuiz.add(lblTextDatiStudente);
		
		JLabel lbltextMatricola = new JLabel(String.valueOf(idStudente));
		lbltextMatricola.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltextMatricola.setBounds(126, 55, 318, 20);
		panelQuiz.add(lbltextMatricola);
		
		JLabel lblNomeTest = new JLabel("TEST:");
		lblNomeTest.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomeTest.setBounds(25, 85, 92, 20);
		panelQuiz.add(lblNomeTest);
		
		JLabel lblTextNomeTest = new JLabel(nometest);
		lblTextNomeTest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTextNomeTest.setBounds(126, 85, 120, 20);
		panelQuiz.add(lblTextNomeTest);
		
		JLabel lblDomanda = new JLabel("DOMANDA");
		lblDomanda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDomanda.setBounds(25, 179, 90, 20);
		panelQuiz.add(lblDomanda);
		
		JLabel lblRisposta = new JLabel("RISPOSTA");
		lblRisposta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRisposta.setBounds(25, 292, 90, 20);
		panelQuiz.add(lblRisposta);
		
		JLabel lblPunteggio = new JLabel("PUNTEGGIO DA ASSEGNARE");
		lblPunteggio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPunteggio.setBounds(25, 405, 200, 20);
		panelQuiz.add(lblPunteggio);
		
		JPanel panelDomanda = new JPanel();
		panelDomanda.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelDomanda.setBounds(25, 209, 508, 73);
		panelQuiz.add(panelDomanda);
		panelDomanda.setLayout(null);
		
		JTextArea domandaTxt = new JTextArea(Domande.get(0));
		domandaTxt.setBounds(0, 0, 508, 73);
		panelDomanda.add(domandaTxt);
		domandaTxt.setEditable(false);
		domandaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		domandaTxt.setLineWrap(true);
		
		JPanel panelRisposta = new JPanel();
		panelRisposta.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelRisposta.setLayout(null);
		panelRisposta.setBounds(25, 322, 508, 73);
		panelQuiz.add(panelRisposta);
		
		JTextArea rispostaTxt = new JTextArea(Risposte.get(0));
		rispostaTxt.setBounds(0, 0, 508, 73);
		panelRisposta.add(rispostaTxt);
		rispostaTxt.setEditable(false);
		rispostaTxt.setLineWrap(true);
		rispostaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		PunteggioAssegnato = new JTextField();
		PunteggioAssegnato.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PunteggioAssegnato.setBounds(25, 435, 96, 19);
		panelQuiz.add(PunteggioAssegnato);
		PunteggioAssegnato.setColumns(3);
		
		
		JLabel lblPunteggi = new JLabel("[ MIN "+Punteggi.get(0)+"- MAX "+Punteggi.get(1)+" ]");
		lblPunteggi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPunteggi.setBounds(139, 435, 394, 20);
		panelQuiz.add(lblPunteggi);
		
		JPanel panelTerminaCorrezione = new JPanel();
		panelTerminaCorrezione.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelTerminaCorrezione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelTerminaCorrezione.setBackground(new Color(153,153,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelTerminaCorrezione.setBackground(new Color(147,112,219));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelTerminaCorrezione.setBackground(new Color(204,153,255));
				
				
				
				
				
				for(int i=0; i<Risposte.size();i++)
				{
					
					controller.correggiTest(idStudente, (int)idQuizA.get(i), punteggioFinale.get(i));
				}
				
				controller.spuntaTest(idStudente,idTest);
				
				JOptionPane.showMessageDialog(null, "TEST CORRETTO CON SUCCESSO","MyLearn",JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				frameIns.secondFrame.setVisible(true);
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelTerminaCorrezione.setBackground(new Color(153,153,204));
			}
		});
		panelTerminaCorrezione.setBackground(new Color(153, 102, 255));
		panelTerminaCorrezione.setBounds(333, 485, 185, 27);
		panelQuiz.add(panelTerminaCorrezione);
		panelTerminaCorrezione.setLayout(null);
		panelTerminaCorrezione.setVisible(false);
		
		
		
		JLabel lblTerminaCorrezione = new JLabel("TERMINA CORREZIONE");
		lblTerminaCorrezione.setForeground(Color.WHITE);
		lblTerminaCorrezione.setBackground(Color.WHITE);
		lblTerminaCorrezione.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTerminaCorrezione.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminaCorrezione.setBounds(0, 0, 185, 27);
		panelTerminaCorrezione.add(lblTerminaCorrezione);
		
		JPanel panelAvanti = new JPanel();
		panelAvanti.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAvanti.setBackground(new Color(153,153,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAvanti.setBackground(new Color(147,112,219));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelAvanti.setBackground(new Color(204,153,255));
				int j=0;
				
				if(n>=Domande.size()-1)
				{
					if(Double.parseDouble(PunteggioAssegnato.getText())<Punteggi.get(j) || Double.parseDouble(PunteggioAssegnato.getText())>Punteggi.get(j+1))
					{
						JOptionPane.showMessageDialog(null, "ERRORE! IL PUNTEGGIO INSERITO NON RIENTRA NEL RANGE DEI VALORI.");
					}
					else
					{
					punteggioFinale.add(Double.parseDouble(PunteggioAssegnato.getText()));
					panelAvanti.setVisible(false);
					panelTerminaCorrezione.setVisible(true);
					}
				}
				else
				{
					if(Double.parseDouble(PunteggioAssegnato.getText())<Punteggi.get(j) || Double.parseDouble(PunteggioAssegnato.getText())>Punteggi.get(j+1))
					{
						JOptionPane.showMessageDialog(null, "ERRORE! IL PUNTEGGIO INSERITO NON RIENTRA NEL RANGE DEI VALORI.");
					}
					else
					{
						n=n+1;
						j=j+2;
				punteggioFinale.add(Double.parseDouble(PunteggioAssegnato.getText()));
				domandaTxt.setText(Domande.get(n));
				rispostaTxt.setText(Risposte.get(n));
				lblPunteggi.setText("[MIN "+Punteggi.get(n+1)+"- MAX "+Punteggi.get(n+2)+" ]");
				PunteggioAssegnato.setText("");
					}
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelAvanti.setBackground(new Color(153,153,204));
			}
		});
		panelAvanti.setLayout(null);
		panelAvanti.setBackground(new Color(153, 102, 255));
		panelAvanti.setBounds(333, 485, 185, 27);
		panelQuiz.add(panelAvanti);
		
		
		JLabel lblAvanti = new JLabel("AVANTI");
		lblAvanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvanti.setForeground(Color.WHITE);
		lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvanti.setBackground(Color.WHITE);
		lblAvanti.setBounds(0, 0, 185, 27);
		panelAvanti.add(lblAvanti);
		
		
		
		JLabel lblIcona = new JLabel("");
		lblIcona.setIcon(new ImageIcon(FrameCorreggiTest.class.getResource("/res/sfondoquiz.jpg")));
		lblIcona.setBounds(0, 0, 1086, 626);
		panel.add(lblIcona);
		
		
	}
}
