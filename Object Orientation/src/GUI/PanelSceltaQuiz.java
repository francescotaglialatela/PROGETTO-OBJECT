package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSceltaQuiz extends JPanel {
	private JTextField nomeTestField;
	String nomeTest;
	PanelAggiungiQuizMultiplo aggiungiQuizMultiplo;
	PanelAggiungiQuizAperto aggiungiQuizAperto;
	JPanel panelMain = new JPanel();
	int CountQuizM=0;
	int CountQuizA=0;
	/**
	 * Create the panel.
	 */
	public PanelSceltaQuiz(String nomeT, String username, Account_Insegnante frame, FrameCreaTest frameCreaTest) {
		setVisible(true);
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(806,678);
		
		nomeTest = nomeT;
		aggiungiQuizMultiplo = new PanelAggiungiQuizMultiplo(this, nomeT, username);
		aggiungiQuizMultiplo.setLocation(0,0);
		aggiungiQuizMultiplo.setVisible(false);
		add(aggiungiQuizMultiplo);
		
		aggiungiQuizAperto = new PanelAggiungiQuizAperto(this,nomeT,username);
		aggiungiQuizAperto.setLocation(0,0);
		aggiungiQuizAperto.setVisible(false);
		add(aggiungiQuizAperto);
		
		panelMain.setBounds(0, 0, 806, 678);
		add(panelMain);
		panelMain.setLayout(null);
		
		JLabel lblText = new JLabel("CREAZIONE TEST");
		lblText.setBounds(0, 24, 806, 73);
		panelMain.add(lblText);
		lblText.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblText.setForeground(Color.BLACK);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		
		nomeTestField = new JTextField();
		nomeTestField.setBounds(297, 207, 208, 25);
		panelMain.add(nomeTestField);
		nomeTestField.setEditable(false);
		nomeTestField.setHorizontalAlignment(SwingConstants.CENTER);
		nomeTestField.setFont(new Font("Tahoma", Font.BOLD, 12));
		nomeTestField.setText(nomeTest);
		nomeTestField.setColumns(10);
		
		JPanel panelQuizMultiplo = new JPanel();
		panelQuizMultiplo.setBounds(218, 300, 150, 25);
		panelMain.add(panelQuizMultiplo);
		panelQuizMultiplo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelQuizMultiplo.setBackground(new Color(51,204,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQuizMultiplo.setBackground(new Color(51,102,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelQuizMultiplo.setBackground(new Color(51,204,204));
				CountQuizM++;
				aggiungiQuizMultiplo.setVisible(true);
				panelMain.setVisible(false);
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelQuizMultiplo.setBackground(new Color(51,102,255));
			}
		});
		panelQuizMultiplo.setBackground(new Color(51, 102, 255));
		panelQuizMultiplo.setLayout(null);
		
		JLabel lblQuizMultiplo = new JLabel("AGGIUNGI QUIZ MULTIPLO");
		lblQuizMultiplo.setForeground(Color.WHITE);
		lblQuizMultiplo.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblQuizMultiplo.setBounds(3, 0, 150, 25);
		panelQuizMultiplo.add(lblQuizMultiplo);
		
		JPanel panelQuizAperto = new JPanel();
		panelQuizAperto.setBounds(456, 300, 150, 25);
		panelMain.add(panelQuizAperto);
		panelQuizAperto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelQuizAperto.setBackground(new Color(51,204,204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelQuizAperto.setBackground(new Color(51,102,255));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelQuizAperto.setBackground(new Color(51,204,204));
				CountQuizA++;
				aggiungiQuizAperto.setVisible(true);
				panelMain.setVisible(false);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelQuizAperto.setBackground(new Color(51,102,255));
			}
		});
		panelQuizAperto.setBackground(new Color(51, 102, 255));
		panelQuizAperto.setLayout(null);
		
		JLabel lblQuizAperto = new JLabel("AGGIUNGI QUIZ APERTO");
		lblQuizAperto.setForeground(Color.WHITE);
		lblQuizAperto.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblQuizAperto.setBounds(10, 0, 150, 25);
		panelQuizAperto.add(lblQuizAperto);
		
		JPanel panelTermina = new JPanel();
		panelTermina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelTermina.setBackground(new Color(51,204,204));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				panelTermina.setBackground(new Color(51,102,255));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				panelTermina.setBackground(new Color(51,102,255));
				if(CountQuizA>0 && CountQuizM>0) {
				frameCreaTest.setVisible(false);
				frame.secondFrame.setVisible(true);
				JOptionPane.showMessageDialog(null, "Test creato.","MyLearn",JOptionPane.INFORMATION_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null,"Un test deve contenere almeno un quiz multiplo e un quiz aperto.","MyLearn",JOptionPane.ERROR_MESSAGE);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				panelTermina.setBackground(new Color(51,204,204));
				
			}
		});
		panelTermina.setLayout(null);
		panelTermina.setBackground(new Color(51, 102, 255));
		panelTermina.setBounds(327, 601, 150, 25);
		panelMain.add(panelTermina);
		
		JLabel lblTermina = new JLabel("TERMINA");
		lblTermina.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermina.setForeground(Color.WHITE);
		lblTermina.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblTermina.setBounds(0, 0, 150, 25);
		panelTermina.add(lblTermina);
		
		
		
	}
}
