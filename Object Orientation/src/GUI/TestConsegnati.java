package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestConsegnati extends JPanel {
	static String username;
	private JTable nuova_tabella;
	private DefaultTableModel modelTabella;
	/**
	 * Create the panel.
	 */
	public TestConsegnati(String user,Account_Insegnante frame) {
		username=user;
		
		Controller controller = new Controller();
		
		
		
		setVisible(true);
		setBackground(Color.WHITE);
		setSize(838,468);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 838, 468);
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
		nuova_tabella.setModel(modelTabella =new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome" , "Matricola" , "Test Consegnato"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnCorreggiTest = new JButton("Correggi Test");
		btnCorreggiTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int [] tmp;
				tmp = new int[] {nuova_tabella.getSelectedRow()};
				
				Object idStudente = nuova_tabella.getValueAt(tmp[0],1);
				Object nomeTest = nuova_tabella.getValueAt(tmp[0], 2);
				
				if(controller.verificaTestCorretto( (int)idStudente, controller.getIdTest((String)nomeTest))==true )
						{
							JOptionPane.showMessageDialog(null, "IL TEST SELEZIONATO E' GIA STATO CORRETTO!","MyLearn",JOptionPane.INFORMATION_MESSAGE);
						}
				else {
				
				FrameCorreggiTest correggiTest = new FrameCorreggiTest((int) idStudente, nomeTest.toString(),frame);
				frame.secondFrame.setVisible(false);
				correggiTest.setVisible(true);
				}
				
			}
		});
		btnCorreggiTest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCorreggiTest.setBounds(687, 398, 120, 25);
		panel.add(btnCorreggiTest);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(TestConsegnati.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel_1.setBounds(0, 0, 830, 475);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TestConsegnati.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel.setBounds(0, 0, 838, 475);
		add(lblNewLabel);
		nuova_tabella.getColumnModel().getColumn(0).setResizable(false);
		
		
		ArrayList testConsegnati = new ArrayList();
		testConsegnati = controller.VisualizzaTestConsegnati(username);
		
		
		if(testConsegnati.size()==0)
		{
			//NIENTE
		}
		
		
		if(testConsegnati.size()>=2)
		{
			Object[] tmp;
			int j=0;
			for(int i=0; i<testConsegnati.size()/2;i++)
			{
				
				tmp = new Object[] {controller.trovaDatiDB((int) testConsegnati.get(j)).get(0),testConsegnati.get(j),controller.getNomeTestDB((int) testConsegnati.get(j+1))};
				
				if(testConsegnati.size()>2)
				{
					j=j+2;
				}
				
				modelTabella.addRow(tmp);
			}
	
		}
		
	}
}
