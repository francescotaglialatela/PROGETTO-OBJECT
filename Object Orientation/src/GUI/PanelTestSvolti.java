package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class PanelTestSvolti extends JPanel {
	
	String username;
	private JTable nuova_tabella;
	private DefaultTableModel modeltabella;
	
	
	
	/**
	 * Create the panel.
	 */
	public PanelTestSvolti(String user, Account_Studente frame) {
		username=user;
		setVisible(true);
		setBackground(new Color(153, 102, 255));
		setSize(838,475);
		setLayout(null);
		
		Controller controller = new Controller();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 102, 255));
		panel.setBounds(8, 8, 838, 468);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 10, 612, 421);
		panel.add(scrollPane);
		
		nuova_tabella = new JTable();
		nuova_tabella.setCellSelectionEnabled(true);
		nuova_tabella.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(nuova_tabella);
		nuova_tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nuova_tabella.setModel(modeltabella =new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Test", "Cognome","Punteggio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(PanelTestSvolti.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel_1.setBounds(0, 0, 830, 475);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelTestSvolti.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel.setBounds(0, 0, 838, 475);
		add(lblNewLabel);
		nuova_tabella.getColumnModel().getColumn(0).setResizable(false);
		
		
		
		ArrayList testSvolti = new ArrayList();
		testSvolti = controller.VisualizzaTestSvoltiDB(username);
		
		
		if(testSvolti.size()==0) {
			//NIENTE
		}
		
		if(testSvolti.size()>=2) {
		Object[] tmp;
		int j=0;
		
		for(int i=0; i<testSvolti.size()/2;i++) {
			
			if(controller.valutazioneDB((int ) controller.VisualizzaTestSvoltiDB(username).get(j), controller.TrovaIdStudenteDB(username))==true) {
			 
			  tmp= new Object[] {controller.getNomeTestDB((int ) testSvolti.get(j)),controller.TrovaCognomeStudenteDB(controller.TrovaIdStudenteDB(username)),controller.visionaPunteggioDB((int) controller.VisualizzaTestSvoltiDB(username).get(j),(int) controller.VisualizzaTestSvoltiDB(username).get(j+1))};
			modeltabella.addRow(tmp);
			}
			
			else
			{
				tmp= new Object[] {controller.getNomeTestDB((int ) testSvolti.get(j)),controller.TrovaCognomeStudenteDB(controller.TrovaIdStudenteDB(username)),"In fase di valutazione"};
				
				modeltabella.addRow(tmp);
			}
			
			if(testSvolti.size()>2)
				j=j+2;
			
		}
		
		
	}
	
	}	
}

