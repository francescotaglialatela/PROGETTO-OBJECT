package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.InsegnanteDAO;
import DAO.StudenteDAO;
import ImplementazionePostgresDAO.InsegnanteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;

public class PanelTestCreati extends JPanel {
	static String username;
	private JTable nuova_tabella;
	private DefaultTableModel modelTabella;
	/**
	 * Create the panel.
	 */
	public PanelTestCreati(String user,Account_Insegnante frame) {
		
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
				"Nome Test"
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
		
		JButton btnCreaTest = new JButton("Crea Test");
		btnCreaTest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreaTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrameCreaTest creaTest = new FrameCreaTest(username, frame);
				frame.secondFrame.setVisible(false);
				creaTest.setVisible(true);
			}
		});
		btnCreaTest.setBackground(Color.WHITE);
		btnCreaTest.setBounds(681, 390, 133, 43);
		panel.add(btnCreaTest);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelTestCreati.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel.setBounds(0, 0, 838, 449);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(PanelTestCreati.class.getResource("/res/sfondo_account.jpg")));
		lblNewLabel_1.setBounds(0, 0, 838, 468);
		panel.add(lblNewLabel_1);
		
	
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		Object[] tmp;
		for(int i=0; i<controller.VisualizzaTestCreati(user).size();i++) {
			tmp = new Object[] {controller.VisualizzaTestCreati(user).get(i)};
			modelTabella.addRow(tmp);
		}
		
	
		
	}
}
