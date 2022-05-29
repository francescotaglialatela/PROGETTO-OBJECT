package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import DAO.StudenteDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificaAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField confermaPasswordField;
	static String username;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificaAccount dialog = new ModificaAccount(username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificaAccount(String username) {
		String nuovaPassword;
		
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
						
						
						if(controller.checkPasswordDB(confermaPassword, username)==false) {
							JOptionPane.showMessageDialog(null, "Errore! Hai inserito la password attuale, prova a sceglierne un'altra.","MyLearn", JOptionPane.ERROR_MESSAGE);
						}
						
						if(password.equals(confermaPassword) && controller.checkPasswordDB(confermaPassword, username)==true) {
							
							controller.modificaPasswordDB(username, confermaPassword);
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
