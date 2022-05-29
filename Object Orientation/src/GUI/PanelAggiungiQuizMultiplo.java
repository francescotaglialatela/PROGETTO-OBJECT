package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import Model.Multiplo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAggiungiQuizMultiplo extends JPanel {
	private JTextField textOpzione1;
	private JTextField textOpzione2;
	private JTextField textOpzione3;
	private JTextField textOpzione4;
	private JTextField punteggioEsattoField;
	private JTextField punteggioSbagliatoField;
	ButtonGroup rispostaCorretta = new ButtonGroup();
	char rispostaC;
	int idQuizM=0;
	ArrayList<String> Opzioni = new ArrayList();
	
	/**
	 * Create the panel.
	 */
	public PanelAggiungiQuizMultiplo(PanelSceltaQuiz panelScelta, String nomeT, String username) {
		setVisible(true);
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(806,678);
		
		Controller controller = new Controller();
		
		JLabel lblText = new JLabel("CREAZIONE QUIZ MULTIPLO");
		lblText.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setBounds(0, 4, 806, 73);
		add(lblText);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDomanda.setBounds(193, 85, 144, 22);
		add(lblDomanda);
		
		JLabel lblOpzione1 = new JLabel("Opzione A");
		lblOpzione1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpzione1.setBounds(193, 269, 152, 22);
		add(lblOpzione1);
		
		textOpzione1 = new JTextField();
		textOpzione1.setBounds(193, 299, 405, 45);
		add(textOpzione1);
		textOpzione1.setColumns(10);
		
		JLabel lblOpzione2 = new JLabel("Opzione B");
		lblOpzione2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpzione2.setBounds(193, 352, 152, 22);
		add(lblOpzione2);
		
		JLabel lblOpzione3 = new JLabel("Opzione C");
		lblOpzione3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpzione3.setBounds(193, 431, 152, 22);
		add(lblOpzione3);
		
		textOpzione2 = new JTextField();
		textOpzione2.setColumns(10);
		textOpzione2.setBounds(193, 382, 405, 45);
		add(textOpzione2);
		
		textOpzione3 = new JTextField();
		textOpzione3.setColumns(10);
		textOpzione3.setBounds(193, 461, 405, 45);
		add(textOpzione3);
		
		JLabel lblOpzione4 = new JLabel("Opzione D");
		lblOpzione4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOpzione4.setBounds(193, 514, 152, 22);
		add(lblOpzione4);
		
		textOpzione4 = new JTextField();
		textOpzione4.setColumns(10);
		textOpzione4.setBounds(193, 544, 405, 45);
		add(textOpzione4);
		
		JLabel lblRispostaCorretta = new JLabel("Risposta Corretta");
		lblRispostaCorretta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRispostaCorretta.setBounds(48, 269, 124, 22);
		add(lblRispostaCorretta);
		
		JRadioButton btnA = new JRadioButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rispostaC = 'a';
			}
		});
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnA.setBackground(Color.WHITE);
		btnA.setBounds(48, 309, 91, 21);
		add(btnA);
		rispostaCorretta.add(btnA);
		
		JRadioButton btnB = new JRadioButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rispostaC = 'b';
			}
		});
		btnB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnB.setBackground(Color.WHITE);
		btnB.setBounds(48, 392, 91, 21);
		add(btnB);
		rispostaCorretta.add(btnB);
		
		JRadioButton btnC = new JRadioButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rispostaC = 'c';
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnC.setBackground(Color.WHITE);
		btnC.setBounds(48, 471, 91, 21);
		add(btnC);
		rispostaCorretta.add(btnC);
		
		JRadioButton btnD = new JRadioButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rispostaC = 'd';
			}
		});
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnD.setBackground(Color.WHITE);
		btnD.setBounds(48, 554, 91, 21);
		add(btnD);
		rispostaCorretta.add(btnD);
		
		JLabel lblPunteggio = new JLabel("Punteggio Esatto:");
		lblPunteggio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPunteggio.setBounds(606, 135, 124, 22);
		add(lblPunteggio);
		
		punteggioEsattoField = new JTextField();
		punteggioEsattoField.setHorizontalAlignment(SwingConstants.CENTER);
		punteggioEsattoField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		punteggioEsattoField.setBounds(748, 139, 38, 17);
		add(punteggioEsattoField);
		punteggioEsattoField.setColumns(10);
		
		JLabel lblPunteggioSbagliato = new JLabel("Punteggio Sbagliato:");
		lblPunteggioSbagliato.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPunteggioSbagliato.setBounds(604, 181, 144, 22);
		add(lblPunteggioSbagliato);
		
		punteggioSbagliatoField = new JTextField();
		punteggioSbagliatoField.setHorizontalAlignment(SwingConstants.CENTER);
		punteggioSbagliatoField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		punteggioSbagliatoField.setColumns(10);
		punteggioSbagliatoField.setBounds(748, 185, 38, 17);
		add(punteggioSbagliatoField);
		
		JPanel panelDomanda = new JPanel();
		panelDomanda.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelDomanda.setBounds(193, 115, 405, 142);
		add(panelDomanda);
		panelDomanda.setLayout(null);
		
		JTextArea domandaText = new JTextArea();
		domandaText.setBounds(2, 2, 401, 138);
		panelDomanda.add(domandaText);
		domandaText.setWrapStyleWord(true);
		domandaText.setLineWrap(true);
		
		JPanel panelAggiungiQuiz = new JPanel();
		panelAggiungiQuiz.setBackground(new Color(51, 102, 255));
		panelAggiungiQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAggiungiQuiz.setBackground(new Color(51,204,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAggiungiQuiz.setBackground(new Color(51,102,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelAggiungiQuiz.setBackground(new Color(51,102,255));
				
				Opzioni.add(textOpzione1.getText());
				Opzioni.add(textOpzione2.getText());
				Opzioni.add(textOpzione3.getText());
				Opzioni.add(textOpzione4.getText());
				char op ='a';
				
				
				
				
				idQuizM = controller.aggiungiQuizMultiplo(domandaText.getText(), Double.parseDouble(punteggioEsattoField.getText()),Double.parseDouble(punteggioSbagliatoField.getText()),nomeT,rispostaC);
				for(int i =0; i<4; i++) {
				controller.addOpzione(Opzioni.get(i), idQuizM, op);
				op++;
				}
				
				domandaText.setText("");
				textOpzione1.setText("");
				textOpzione2.setText("");
				textOpzione3.setText("");
				textOpzione4.setText("");
				punteggioEsattoField.setText("");
				punteggioSbagliatoField.setText("");
				
				Opzioni.clear();
				
				JOptionPane.showMessageDialog(null, "Quiz multiplo aggiunto!","My Learn",JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				panelScelta.panelMain.setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelAggiungiQuiz.setBackground(new Color(51,204,204));
			}
		});
		panelAggiungiQuiz.setBounds(256, 614, 287, 36);
		add(panelAggiungiQuiz);
		panelAggiungiQuiz.setLayout(null);
		
		JLabel lblAggiungiQuiz = new JLabel("AGGIUNGI QUIZ");
		lblAggiungiQuiz.setForeground(Color.WHITE);
		lblAggiungiQuiz.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAggiungiQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblAggiungiQuiz.setBounds(0, 0, 287, 36);
		panelAggiungiQuiz.add(lblAggiungiQuiz);
		
		
		
		
	}
}
