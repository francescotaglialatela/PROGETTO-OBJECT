package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DAO.InsegnanteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;

public class ModificaAccountInsegnante extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField confermaPasswordField;
	static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificaAccountInsegnante dialog = new ModificaAccountInsegnante(username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificaAccountInsegnante(String user) {
		String nuovaPassword;

		username = user;
		
		System.out.println(username);
		
		Controller controller = new Controller();
		
		
		
		setBounds(100, 100, 520, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(120, 50, 150, 15);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 78, 170, 17);
		contentPanel.add(passwordField);
		
		JCheckBox showPassword = new JCheckBox("Mostra Password");
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPassword.isSelected())
				{
					passwordField.setEchoChar((char)0);
					confermaPasswordField.setEchoChar((char)0);
				}
				else
				{
					passwordField.setEchoChar('•');
					confermaPasswordField.setEchoChar('•');
				}
			}
		});
		showPassword.setBounds(120, 194, 150, 21);
		contentPanel.add(showPassword);
		
		JLabel lblConfermaPassword = new JLabel("Conferma Password");
		lblConfermaPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfermaPassword.setBounds(120, 123, 150, 15);
		contentPanel.add(lblConfermaPassword);
		
		confermaPasswordField = new JPasswordField();
		confermaPasswordField.setBounds(120, 150, 170, 17);
		contentPanel.add(confermaPasswordField);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String password;
						String confermaPassword;
						
						
						password = new String(passwordField.getPassword());	
						confermaPassword = new String(confermaPasswordField.getPassword());
						
						
						if(controller.checkPasswordInsegnanteDB(confermaPassword, username)==false) {
							JOptionPane.showMessageDialog(null, "Errore! Hai inserito la password attuale, prova a sceglierne un'altra.","MyLearn", JOptionPane.ERROR_MESSAGE);
						}
						
						if(password.equals(confermaPassword) && controller.checkPasswordInsegnanteDB(confermaPassword, username)==true) {
							
							controller.modificaPasswordInsegnanteDB(username, confermaPassword);
							
							JOptionPane.showMessageDialog(null, "Password cambiata!","MyLearn", JOptionPane.INFORMATION_MESSAGE); 
							setVisible(false);
						}
					
						if(!password.equals(confermaPassword))
							JOptionPane.showMessageDialog(null, "Errore! Le password da te inserita non combaciano, riprova!","MyLearn", JOptionPane.ERROR_MESSAGE);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
