package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import Model.Insegnante;
import Model.Studente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Registrazione extends JFrame {

	private JPanel contentPane;
	private Home homeFrame;
	ButtonGroup sceltaAccount = new ButtonGroup();
	String scelta;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confermaPasswordField;
	
	String nome;
	String cognome;
	String email;
	String username;
	String password;
	String confermaPassword;
	char sesso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrazione frame = new Registrazione();
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
	public Registrazione() {
		
		Controller controller = new Controller();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1019, 598);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		panelMain.setBounds(252, 10, 507, 578);
		panel.add(panelMain);
		panelMain.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Registrazione MyLearn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(85, 49, 372, 30);
		panelMain.add(lblNewLabel_1);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(85, 104, 150, 15);
		panelMain.add(lblNome);
		lblNome.setVisible(false);
		
		nomeField = new JTextField();
		nomeField.setBounds(85, 127, 150, 17);
		panelMain.add(nomeField);
		nomeField.setColumns(10);
		nomeField.setVisible(false);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCognome.setBounds(272, 103, 150, 15);
		panelMain.add(lblCognome);
		lblCognome.setVisible(false);
		
		cognomeField = new JTextField();
		cognomeField.setColumns(10);
		cognomeField.setBounds(272, 127, 150, 17);
		panelMain.add(cognomeField);
		cognomeField.setVisible(false);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(85, 163, 150, 15);
		panelMain.add(lblEmail);
		lblEmail.setVisible(false);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(85, 186, 150, 17);
		panelMain.add(emailField);
		emailField.setVisible(false);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(272, 163, 150, 15);
		panelMain.add(lblUsername);
		lblUsername.setVisible(false);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(272, 186, 150, 17);
		panelMain.add(usernameField);
		usernameField.setVisible(false);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(85, 224, 150, 15);
		panelMain.add(lblPassword);
		lblPassword.setVisible(false);
		
		JLabel lblConfermaPassword = new JLabel("Conferma password");
		lblConfermaPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfermaPassword.setBounds(272, 224, 150, 15);
		panelMain.add(lblConfermaPassword);
		lblConfermaPassword.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 247, 150, 17);
		panelMain.add(passwordField);
		passwordField.setVisible(false);
		
		confermaPasswordField = new JPasswordField();
		confermaPasswordField.setBounds(272, 247, 150, 17);
		panelMain.add(confermaPasswordField);
		confermaPasswordField.setVisible(false);
		
		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSesso.setBounds(85, 282, 150, 15);
		panelMain.add(lblSesso);
		lblSesso.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index==0) {
					sesso = 'M';
				}
				if(index==1) {
					sesso = 'F';
				}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(85, 305, 150, 15);
		panelMain.add(comboBox);
		comboBox.setVisible(false);
		
		
		
		JLabel Domanda1 = new JLabel("Che account vuoi creare?");
		Domanda1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Domanda1.setBounds(141, 172, 300, 25);
		panelMain.add(Domanda1);
		
		JRadioButton btnInsegnante = new JRadioButton("Insegnante");
		
		
		
		btnInsegnante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scelta = btnInsegnante.getText();
			}
		});
		btnInsegnante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInsegnante.setBackground(Color.WHITE);
		btnInsegnante.setBounds(85, 241, 150, 21);
		panelMain.add(btnInsegnante);
		sceltaAccount.add(btnInsegnante);
		
		JRadioButton btnStudente = new JRadioButton("Studente");
		btnStudente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scelta = btnStudente.getText();
			}
		});
		btnStudente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStudente.setBackground(Color.WHITE);
		btnStudente.setBounds(267, 241, 150, 21);
		panelMain.add(btnStudente);
		sceltaAccount.add(btnStudente);
		
		
		
		JPanel panelAvanti = new JPanel();
		
		
		JLabel lblAvanti = new JLabel("Avanti");
		JCheckBox mostraPassword = new JCheckBox("");
		JCheckBox mostraConfermaPassword = new JCheckBox("");
		
		lblAvanti.setForeground(Color.WHITE);
		lblAvanti.setBackground(new Color(153, 102, 255));
		lblAvanti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvanti.setBounds(0, 0, 75, 20);
		panelAvanti.add(lblAvanti);
		
		panelAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelAvanti.setBackground(new Color(153,153,204));
				
			}
			
		});
		
		panelAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelAvanti.setBackground(new Color(147,112,219));
			}
		});
		
		panelAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelAvanti.setBackground(new Color(204,153,255));
				btnStudente.setVisible(false);
				btnInsegnante.setVisible(false);
				Domanda1.setVisible(false);
				panelAvanti.setVisible(false);
				lblAvanti.setVisible(false);
				
				lblNome.setVisible(true);
				lblCognome.setVisible(true);
				lblEmail.setVisible(true);
				lblUsername.setVisible(true);
				lblPassword.setVisible(true);
				lblConfermaPassword.setVisible(true);
				lblSesso.setVisible(true);
				nomeField.setVisible(true);
				cognomeField.setVisible(true);
				emailField.setVisible(true);
				usernameField.setVisible(true);
				passwordField.setVisible(true);
				confermaPasswordField.setVisible(true);
				comboBox.setVisible(true);
				mostraPassword.setVisible(true);
				mostraConfermaPassword.setVisible(true);
				
				
				
				JPanel panelRegistrati = new JPanel();
				
				panelRegistrati.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						panelRegistrati.setBackground(new Color(153,153,204));
						
					}
					
				});
				
				panelRegistrati.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						panelRegistrati.setBackground(new Color(147,112,219));
					}
				});
				
				panelRegistrati.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						panelRegistrati.setBackground(new Color(204,153,255));
					
						
						nome = nomeField.getText();
						
						cognome = cognomeField.getText();
						
						email = emailField.getText();
						username = usernameField.getText();
						password = new String( passwordField.getPassword());
						confermaPassword = new String(confermaPasswordField.getPassword());
						
						if(!password.equals(confermaPassword))
							JOptionPane.showMessageDialog(null,"Hai inserito due password diverse, riprova!","MyLearn", JOptionPane.ERROR_MESSAGE);
						
						if(nome.equals("") || cognome.equals("") || email.equals("") || username.equals("") || password.equals(""))
							JOptionPane.showMessageDialog(null, "Hai lascaito un campo vuoto, controlla!","MyLearn",JOptionPane.ERROR_MESSAGE);
						
						if(scelta.equals("Studente") && password.equals(confermaPassword) && !nome.equals("") && !cognome.equals("") && !email.equals("") && !password.equals("") && !username.equals("")) {
						
						//Studente s = new Studente(username,password,email,nome,cognome,sesso,"0");
						//StudenteDAO studente = new StudenteImplementazionePostgresDAO();
						//studente.RegistrazioneStudenteDB(s);
						
						controller.registrazioneStudente(username, password, email, nome, cognome, sesso, "0");
						
						JOptionPane.showMessageDialog(null,"Registrazione avvenuta con successo!","MyLearn", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						Home paginaIniziale = new Home();
						paginaIniziale.frmMylearn.setVisible(true);
						
						}
						
							
						if(scelta.equals("Insegnante") && password.equals(confermaPassword) && !nome.equals("") && !cognome.equals("") && !email.equals("") && !password.equals("") && !username.equals("")) 
						{
							//Insegnante i = new Insegnante(username,password,email,nome,cognome,sesso,"0","0");
							//InsegnanteDAO insegnante= new InsegnanteImplementazionePostgresDAO();
							//insegnante.RegistrazioneInsegnanteDB(i);
							
							controller.registrazioneInsegnante(username, password, email, nome, cognome, sesso, "0","0");
							
							JOptionPane.showMessageDialog(null,"Registrazione avvenuta con successo!","MyLearn", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							Home paginaIniziale = new Home();
							paginaIniziale.frmMylearn.setVisible(true);
						}
					}
				});
				
				panelRegistrati.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						panelRegistrati.setBackground(new Color(147,112,219));
					}
				});
				
				panelRegistrati.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				panelRegistrati.setBackground(new Color(153, 102, 255));
				panelRegistrati.setBounds(198, 525, 75, 20);
				panelMain.add(panelRegistrati);
				panelRegistrati.setLayout(null);
				
				JLabel lblRegistrati = new JLabel("Registrati");
				
				lblRegistrati.setForeground(Color.WHITE);
				lblRegistrati.setBackground(new Color(153, 102, 255));
				lblRegistrati.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
				lblRegistrati.setBounds(0, 0, 75, 20);
				panelRegistrati.add(lblRegistrati);
				
			}
		});
		
		panelAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelAvanti.setBackground(new Color(147,112,219));
			}
		});
		
		panelAvanti.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelAvanti.setBackground(new Color(153, 102, 255));
		panelAvanti.setBounds(198, 525, 75, 20);
		panelMain.add(panelAvanti);
		panelAvanti.setLayout(null);
		
		
		mostraPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mostraPassword.isSelected())
				passwordField.setEchoChar((char)0);
				
				else
					passwordField.setEchoChar('•');
			}
				
		});
		mostraPassword.setBounds(239, 243, 20, 21);
		panelMain.add(mostraPassword);
		mostraPassword.setVisible(false);
		
		
		mostraConfermaPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mostraConfermaPassword.isSelected())
					confermaPasswordField.setEchoChar((char)0);
					
					else
						confermaPasswordField.setEchoChar('•');
			}
		});
		mostraConfermaPassword.setBounds(420, 243, 20, 21);
		panelMain.add(mostraConfermaPassword);
		mostraConfermaPassword.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Registrazione.class.getResource("/res/sfondo app rid.jpg")));
		lblNewLabel.setBounds(0, 0, 1019, 598);
		panel.add(lblNewLabel);
	}
}
