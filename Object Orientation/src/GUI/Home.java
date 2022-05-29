package GUI;

import java.awt.EventQueue;

import java.awt.Image;

import Controller.Controller;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet.FontAttribute;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;

import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.*;

public class Home {

	 JFrame frmMylearn;
	private JTextField Username;
	private JPasswordField Password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmMylearn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public Home() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMylearn = new JFrame();
		frmMylearn.setTitle("MyLearn");
		frmMylearn.setResizable(false);
		frmMylearn.getContentPane().setBackground(new Color(0, 128, 128));
		frmMylearn.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1013, 619);
		frmMylearn.getContentPane().add(panel);
		panel.setLayout(null);
		
				
				
		JPanel panelMain = new JPanel();
		panelMain.setBounds(252, 10, 507, 599);
		panel.add(panelMain);
		panelMain.setBackground(Color.WHITE);
		panelMain.setLayout(null);
		
		JLabel lblWelcome = new JLabel("     Ti diamo il benvenuto in MyLearn");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWelcome.setBounds(10, 10, 487, 61);
		panelMain.add(lblWelcome);
		
		JLabel lblIntrodution = new JLabel("                       Accedi per svolgere le tue attivit\u00E0!");
		lblIntrodution.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblIntrodution.setBounds(20, 55, 410, 20);
		panelMain.add(lblIntrodution);
		
		JLabel lblIcona = new JLabel("");
		lblIcona.setIcon(new ImageIcon(Home.class.getResource("/res/elearning.png")));
		lblIcona.setBounds(190, 100, 185, 128);
		panelMain.add(lblIcona);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(121, 223, 128, 30);
		panelMain.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username *");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsername.setBounds(121, 263, 128, 30);
		panelMain.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password *");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(121, 315, 128, 30);
		panelMain.add(lblPassword);
		
		JButton btnNewButton = new JButton("Accedi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = Username.getText();
				char[] password = Password.getPassword(); 
				String passwordCast = new String(password);
				Controller controller = new Controller();
				
				
				controller.loginStudente(username, passwordCast);
				
				
				
				if(controller.loginStudente(username, passwordCast)==true ) {
					Account_Studente page = new Account_Studente(username);
					JOptionPane.showMessageDialog(null, "Login avvenuto con successo!", "MyLearn", JOptionPane.INFORMATION_MESSAGE);
					frmMylearn.setVisible(false);
					page.secondFrame.setVisible(true);
					
				}
				
				
				if(controller.loginInsegnante(username, passwordCast)==true) {
					  Account_Insegnante pageInsegnante = new Account_Insegnante(username);
					  JOptionPane.showMessageDialog(null, "Login avvenuto con successo!");
					  frmMylearn.setVisible(false);
					  pageInsegnante.secondFrame.setVisible(true);
					 
				}
					
				if((controller.loginInsegnante(username, passwordCast)== false &&
						(controller.loginStudente(username, passwordCast)==false ))) {
					JOptionPane.showMessageDialog(null, "Errore! Username o Password non corretto, riprova.", "MyLearn", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		
		
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(197, 432, 94, 20);
		panelMain.add(btnNewButton);
		
		JLabel lblRegistrazione = new JLabel("Sei un nuovo utente di MyLearn? Clicca qui!");
		lblRegistrazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistrazione.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistrazione.setFont(new Font("Tahoma", Font.ITALIC,11));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblRegistrazione.setFont(new Font("Tahome", Font.BOLD | Font.ITALIC, 10));
				Registrazione panelRegistrazione = new Registrazione();
				frmMylearn.setVisible(false);
				panelRegistrazione.setVisible(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblRegistrazione.setFont(new Font("Tahoma", Font.ITALIC,11));
			}
		});
		lblRegistrazione.setForeground(new Color(0, 0, 255));
		lblRegistrazione.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRegistrazione.setBounds(259, 391, 238, 20);
		panelMain.add(lblRegistrazione);
		
		JCheckBox mostraPassword = new JCheckBox("Mostra password");
		mostraPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mostraPassword.isSelected())
					Password.setEchoChar((char)0);
				else
					Password.setEchoChar('•');
			}
		});
		mostraPassword.setBackground(Color.WHITE);
		mostraPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		mostraPassword.setBounds(100, 391, 134, 21);
		panelMain.add(mostraPassword);
		
		Username = new JTextField();
		Username.setBounds(121, 300, 237, 19);
		panelMain.add(Username);
		Username.setColumns(10);
		
		Password = new JPasswordField();
		Password.setBounds(121, 348, 237, 19);
		panelMain.add(Password);
		
		JLabel lblSfondo = new JLabel("New label");
		lblSfondo.setIcon(new ImageIcon(Home.class.getResource("/res/sfondo app rid.jpg")));
		lblSfondo.setBounds(0, 0, 1013, 619);
		panel.add(lblSfondo);
		frmMylearn.setBounds(100, 100, 1027, 656);
		frmMylearn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
