package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.*;
import GUI.Home;
import java.awt.Font;

public class PanelHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelHome() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setSize(838,468);
		setVisible(true);
		
		JLabel lblNewLabel_1 = new JLabel("Bentornato in MyLearn");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Wide Latin", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(331, 54, 507, 66);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Controlla come sono andati i tuoi test svolti oppure svolgine uno nuovo!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(250, 82, 588, 109);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PanelHome.class.getResource("/res/iconprova.jpg")));
		lblNewLabel_2.setBounds(0, 0, 838, 479);
		add(lblNewLabel_2);
		
	}
}
