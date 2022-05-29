package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class PanelQuizAperto extends JPanel {
	private JTextField DomandaField;
	ArrayList<String> Domande = new ArrayList();
	ArrayList<String> RisposteAperte = new ArrayList();
	ArrayList idQuiz = new ArrayList();
	private JTextField RispostaStudente;
	
	String nometest;
	String username;
	int num=0;

	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblDomanda;
	private JLabel lblNewLabel_1;
	private JLabel lblAvanti;
	
	
	/**
	 * Create the panel.
	 */
	public PanelQuizAperto(ArrayList<String> domandeAperte, int n, ArrayList<Character> Risposte, String nomet, String us, ArrayList idQuizM, Account_Studente frame,JFrame framet, ArrayList idQuizA) {

		
		
		
		setBackground(new Color(0,0,0,0));
		setSize(1086, 626);
		setLayout(null);
		setBounds(0, 0, 1086, 626);
		
		Controller controller = new Controller();
		
		
		
		idQuiz = idQuizA;
		nometest = nomet;
		username= us;
		Domande = domandeAperte;
		
		
		/*
		TestDAO test = new TestImplementazionePostgresDAO();
		idQuizM = test.getElencoQuizMultiploDB(nometest);
		idQuiz = test.getElencoQuizApertoDB(nometest);
		Domande = test.VisualizzaQuizMultiploDB(nometest, n);
		*/
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(271, 76, 543, 542);
		add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Test: "+nometest);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(16, 8, 527, 40);
		panel.add(lblNewLabel);
		
		lblDomanda = new JLabel(Domande.get(num));
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDomanda.setBounds(16, 98, 527, 65);
		panel.add(lblDomanda);
		
		RispostaStudente = new JTextField();
		RispostaStudente.setBounds(8, 281, 527, 85);
		panel.add(RispostaStudente);
		RispostaStudente.setHorizontalAlignment(SwingConstants.LEFT);
		RispostaStudente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		RispostaStudente.setText("");
		RispostaStudente.setColumns(10);
		
		lblAvanti = new JLabel("Avanti");
		
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
				
				RisposteAperte.add(RispostaStudente.getText());
				RispostaStudente.setText("");
				num = num+1;
				
				
				if(num>=idQuiz.size()) {
					lblAvanti.setVisible(false);
					JLabel lblConsegna = new JLabel("Consegna");
					lblConsegna.setFont(new Font("Tahoma", Font.PLAIN, 12));
					lblConsegna.setHorizontalAlignment(SwingConstants.CENTER);
					lblConsegna.setBounds(404, 474, 110, 42);
					panel.add(lblConsegna);
					
					lblConsegna.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							lblConsegna.setFont(new Font("Tahoma", Font.BOLD, 12));
						}
						
					});
					
					lblConsegna.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseExited(MouseEvent e) {
							lblConsegna.setFont(new Font("Tahoma", Font.PLAIN, 12));
						}
					});
					
					lblConsegna.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							lblConsegna.setFont(new Font("Tahoma", Font.PLAIN, 12));
						}
					});
					
					lblConsegna.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							
							if(Risposte.size()==RisposteAperte.size()) {
							for(int i=0; i<Risposte.size(); i++) {
								controller.aggiungiRispostaDB(Risposte.get(i), nometest,(int) idQuizM.get(i) , username, RisposteAperte.get(i),(int) idQuiz.get(i));
								
							}
							}
							
							int j=0;
							if(Risposte.size()>RisposteAperte.size()) {
								for(int i=0; i<Risposte.size(); i++) {
									controller.aggiungiRispostaDB(Risposte.get(i), nometest,(int) idQuizM.get(i) , username, RisposteAperte.get(j),(int) idQuiz.get(j));
									
									
									if(j<RisposteAperte.size()-1)
									{
										j++;
									}
								}
							}
							
							if(Risposte.size()<RisposteAperte.size() && idQuizM.size()<idQuiz.size()) {
								for(int i=0; i<RisposteAperte.size(); i++) {
								controller.aggiungiRispostaDB(Risposte.get(i), nometest,(int) idQuizM.get(i) , username, RisposteAperte.get(j),(int) idQuiz.get(j));
							
								if(j<Risposte.size()-1)
								{
									j++;
								}
							}
							}
							
					
							JOptionPane.showMessageDialog(null, "Il tuo test è stato consegnato con successo!");
							framet.setVisible(false);
							frame.secondFrame.setVisible(true);
							}
						
						
					});
						
					}
					else
					{
						Domande.clear();
						Domande = controller.VisualizzaQuizApertoDB(nometest, 1);
						lblDomanda.setText(Domande.get(0));
					}
				
					
				}
						
					});
		
		
					
					
				
		
		lblAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		});
		
		lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvanti.setBounds(404, 474, 110, 42);
		panel.add(lblAvanti);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PanelQuizAperto.class.getResource("/res/sfondoquiz.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1086, 626);
		add(lblNewLabel_1);
		
		
		
		
		
	

	}
	}
	
	

		
		
		
		
	

