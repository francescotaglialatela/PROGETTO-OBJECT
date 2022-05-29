package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import Model.Aperto;
import Model.Insegnante;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAggiungiQuizAperto extends JPanel {
	private JTextField textPunteggioMinimo;
	private JTextField textPunteggioMassimo;

	/**
	 * Create the panel.
	 */
	public PanelAggiungiQuizAperto(PanelSceltaQuiz panelScelta, String nomeT, String username) {
		setVisible(true);
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(806,678);
		
		Controller controller = new Controller();
		
		JLabel lblTitolo = new JLabel("CREAZIONE QUIZ APERTO");
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(0, 4, 806, 73);
		add(lblTitolo);
		
		JLabel lblDomanda = new JLabel("Domanda");
		lblDomanda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDomanda.setBounds(193, 140, 144, 22);
		add(lblDomanda);
		
		JPanel panelDomanda = new JPanel();
		panelDomanda.setLayout(null);
		panelDomanda.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelDomanda.setBounds(193, 170, 405, 142);
		add(panelDomanda);
		
		JTextArea domandaText = new JTextArea();
		domandaText.setWrapStyleWord(true);
		domandaText.setLineWrap(true);
		domandaText.setBounds(2, 2, 401, 138);
		panelDomanda.add(domandaText);
		
		JLabel lblPunteggioMinimo = new JLabel("Punteggio Minimo:");
		lblPunteggioMinimo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPunteggioMinimo.setBounds(193, 349, 124, 22);
		add(lblPunteggioMinimo);
		
		textPunteggioMinimo = new JTextField();
		textPunteggioMinimo.setHorizontalAlignment(SwingConstants.CENTER);
		textPunteggioMinimo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPunteggioMinimo.setColumns(10);
		textPunteggioMinimo.setBounds(193, 381, 38, 17);
		add(textPunteggioMinimo);
		
		JLabel lblPunteggioMassimo = new JLabel("Punteggio Massimo:");
		lblPunteggioMassimo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPunteggioMassimo.setBounds(358, 349, 173, 22);
		add(lblPunteggioMassimo);
		
		textPunteggioMassimo = new JTextField();
		textPunteggioMassimo.setHorizontalAlignment(SwingConstants.CENTER);
		textPunteggioMassimo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPunteggioMassimo.setColumns(10);
		textPunteggioMassimo.setBounds(358, 382, 38, 17);
		add(textPunteggioMassimo);
		
		JPanel panelAggiungiQuiz = new JPanel();
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
				
				controller.addQuizApertoDB(domandaText.getText(), Double.parseDouble(textPunteggioMinimo.getText()), Double.parseDouble(textPunteggioMassimo.getText()), nomeT);
				
				domandaText.setText("");
				textPunteggioMinimo.setText("");
				textPunteggioMassimo.setText("");
				
				
				JOptionPane.showMessageDialog(null, "Quiz aperto aggiunto!","My Learn",JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				panelScelta.panelMain.setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelAggiungiQuiz.setBackground(new Color(51,204,204));
			}
		});
		panelAggiungiQuiz.setLayout(null);
		panelAggiungiQuiz.setBackground(new Color(51, 102, 255));
		panelAggiungiQuiz.setBounds(244, 579, 287, 36);
		add(panelAggiungiQuiz);
		
		JLabel lblAggiungiQuiz = new JLabel("AGGIUNGI QUIZ");
		lblAggiungiQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblAggiungiQuiz.setForeground(Color.WHITE);
		lblAggiungiQuiz.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAggiungiQuiz.setBounds(0, 0, 287, 36);
		panelAggiungiQuiz.add(lblAggiungiQuiz);
		
		
	}
}
