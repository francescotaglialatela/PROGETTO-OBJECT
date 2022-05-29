package GUI;

import java.awt.Color;

import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.*;

import javax.swing.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelElencoTest extends JPanel {

	private DefaultTableModel modelTabella;
	static String username;
	private JTable nuova_tabella;

	/**
	 * Create the panel.
	 */
	public PanelElencoTest(String user, Account_Studente frame) {
		
		Controller controller = new Controller();
		
		username=user;
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
				"Nome Test", "Insegnante"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		nuova_tabella.getColumnModel().getColumn(0).setResizable(false);
		
		JButton btnSvolgiTest = new JButton("Svolgi Test");
		btnSvolgiTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] tmp;
				tmp = new int[] {nuova_tabella.getSelectedColumn(),nuova_tabella.getSelectedRow()};
				Object a = nuova_tabella.getValueAt(tmp[1], tmp[0]);
				
				
				
				if(controller.verificaTestSvoltoDB(controller.TrovaIdStudenteDB(username), controller.getIdTestDB(a.toString()) )==true)
						{
							JOptionPane.showMessageDialog(null, "IL TEST SELEZIONATO E' GIA STATO SVOLTO!","MyLearn",JOptionPane.INFORMATION_MESSAGE);
						}
				else
				
				{
				
				Test nuovotest = new Test(a.toString(),username, frame);
				frame.secondFrame.setVisible(false);
				nuovotest.framet.setVisible(true);
				
				}
			}
		});
		btnSvolgiTest.setBackground(Color.WHITE);
		btnSvolgiTest.setBounds(681, 390, 133, 43);
		panel.add(btnSvolgiTest);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelElencoTest.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel.setBounds(0, 0, 838, 449);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(PanelElencoTest.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel_1.setBounds(0, 0, 838, 520);
		panel.add(lblNewLabel_1);
		
		
		
		
		
		
		
	
		Object[] tmp;
		int j=0;
		for(int i=0; i<controller.VisualizzaElencoTestDB().size()/2;i++) {
			
			tmp= new Object[] {controller.VisualizzaElencoTestDB().get(j),controller.VisualizzaElencoTestDB().get(j+1)};
			modelTabella.addRow(tmp);
			j=j+2;
		}
		
		
	}
}
		 
		
		
		
	
			
	

