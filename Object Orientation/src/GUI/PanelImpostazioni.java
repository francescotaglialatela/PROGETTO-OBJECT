package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import Controller.Controller;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PanelImpostazioni extends JPanel {
	
	String username;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	/**
	 * Create the panel.
	 */
	public PanelImpostazioni(String user,Account_Studente frame) {
		
		username=user;
		setVisible(true);
		setBackground(new Color(153, 102, 255));
		setSize(838,475);
		setLayout(null);
		
		
		Controller controller = new Controller();
		
		ArrayList<String> datiStudente;
		
		datiStudente = controller.datiStudenteDB(username);
		
		
		
		JPanel panelMain = new JPanel();
		panelMain.setBounds(0, 0, 838, 479);
		add(panelMain);
		panelMain.setLayout(null);
		
		JPanel panelSecond = new JPanel();
		panelSecond.setBackground(Color.WHITE);
		panelSecond.setBounds(79, 11, 674, 457);
		panelMain.add(panelSecond);
		panelSecond.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(8, 175, 50, 13);
		panelSecond.add(lblNome);
	
		
		nomeField = new JTextField();
		nomeField.setEditable(false);
		nomeField.setBounds(8, 196, 150, 17);
		nomeField.setText(datiStudente.get(0));
		panelSecond.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome: ");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCognome.setBounds(8, 221, 100, 15);
		panelSecond.add(lblCognome);
		
		cognomeField = new JTextField();
		cognomeField.setEditable(false);
		cognomeField.setColumns(10);
		cognomeField.setBounds(8, 244, 150, 17);
		cognomeField.setText(datiStudente.get(1));
		panelSecond.add(cognomeField);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(8, 318, 100, 15);
		panelSecond.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setEditable(false);
		emailField.setColumns(10);
		emailField.setBounds(8, 341, 150, 17);
		emailField.setText(datiStudente.get(2));
		panelSecond.add(emailField);
		
		JLabel lblPassword = new JLabel("Username");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(8, 269, 100, 15);
		panelSecond.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setColumns(10);
		usernameField.setBounds(8, 293, 150, 17);
		usernameField.setText(datiStudente.get(3));
		panelSecond.add(usernameField);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword_2.setBounds(8, 376, 150, 15);
		panelSecond.add(lblPassword_2);
		
		JLabel sfondoUser = new JLabel("");
		sfondoUser.setIcon(new ImageIcon(PanelImpostazioni.class.getResource("/res/user (10).png")));
		sfondoUser.setBounds(300, 21, 64, 64);
		panelSecond.add(sfondoUser);
		
		JButton btnModificaPassword = new JButton("Modifica");
		btnModificaPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificaAccount Modifica = new ModificaAccount(username);
				Modifica.setVisible(true);
				
			}
		});
		btnModificaPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModificaPassword.setBounds(551, 416, 77, 19);
		panelSecond.add(btnModificaPassword);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(8, 399, 150, 17);
		passwordField.setText(datiStudente.get(4));
		panelSecond.add(passwordField);
		
		JLabel sfondo = new JLabel("");
		sfondo.setIcon(new ImageIcon(PanelImpostazioni.class.getResource("/res/sfondo_account.jpg")));
		sfondo.setBounds(0, 0, 838, 479);
		panelMain.add(sfondo);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(PanelImpostazioni.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel_3.setBounds(0, 464, 838, 11);
		add(lblNewLabel_3);
		
	}
}
