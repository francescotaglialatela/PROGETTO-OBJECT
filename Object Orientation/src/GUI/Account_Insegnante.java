package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Account_Insegnante {

	 JFrame secondFrame;
	private PanelHome home;
	private PanelTestCreati testCreati;
	private PanelImpostazioniInsegnante impostazioni;
	private TestConsegnati testConsegnati;
	private JPanel contentPane;
	static String Username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account_Insegnante window = new Account_Insegnante(Username);
					window.secondFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Account_Insegnante(String username) {
		Username=username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		secondFrame = new JFrame();
		secondFrame.getContentPane().setBackground(new Color(0, 128, 128));
		secondFrame.getContentPane().setLayout(null);
		
		secondFrame.setTitle("Home Page ~ Insegnante");
		secondFrame.setResizable(false);
		secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		secondFrame.setBounds(100, 100, 1013, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		secondFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		home = new PanelHome();
		home.setSize(840, 475);
		home.setLocation(3, 4);
		
		testCreati = new PanelTestCreati(Username, this);
		testCreati.setSize(840, 475);
		testCreati.setLocation(3,4);
		
		testConsegnati = new TestConsegnati(Username, this);
		testConsegnati.setSize(840, 475);
		testConsegnati.setLocation(3,4);
		
		impostazioni = new PanelImpostazioniInsegnante(Username,this);
		impostazioni.setSize(840,475);
		impostazioni.setLocation(3,4);
			
		JPanel panelTop = new JPanel();
		panelTop.setBounds(0, 0, 999, 55);
		contentPane.add(panelTop);
		panelTop.setLayout(null);
		
		JLabel lblSfondoTop = new JLabel("");
		lblSfondoTop.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/sfondo app rid.jpg")));
		lblSfondoTop.setBounds(0, 0, 999, 150);
		panelTop.add(lblSfondoTop);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 530, 999, 55);
		contentPane.add(panelBottom);
		panelBottom.setLayout(null);
		
		JLabel lblSfondoBottom = new JLabel("");
		lblSfondoBottom.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/sfondo app rid.jpg")));
		lblSfondoBottom.setBounds(0, -117, 999, 172);
		panelBottom.add(lblSfondoBottom);
		
		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setBounds(0, 53, 999, 479);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel PanelLeft = new JPanel();
		PanelLeft.setBackground(new Color(147, 112, 219));
		PanelLeft.setBounds(0, 0, 163, 477);
		Main.add(PanelLeft);
		PanelLeft.setLayout(null);
		
		JPanel lblHome = new JPanel();
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHome.setBackground(new Color(153,153,204));
				
			}
			
		});
		
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblHome.setBackground(new Color(147,112,219));
			}
		});
		
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblHome.setBackground(new Color(204,153,255));
				home.setVisible(true);
				impostazioni.setVisible(false);
				testConsegnati.setVisible(false);
				testCreati.setVisible(false);
			}
		});
		
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblHome.setBackground(new Color(147,112,219));
			}
		});
		
		
		
		lblHome.setBorder(null);
		lblHome.setBounds(0, 10, 164, 62);
		PanelLeft.add(lblHome);
		lblHome.setBackground(new Color(147, 112, 219));
		lblHome.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Home");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(25, 10, 72, 42);
		lblHome.add(lblNewLabel_2);
		
		JLabel lblIconHome = new JLabel("");
		lblIconHome.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/home (3).png")));
		lblIconHome.setBounds(5, 20, 24, 24);
		lblHome.add(lblIconHome);
		
		JPanel panelTestCreati = new JPanel();
		
		panelTestCreati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelTestCreati.setBackground(new Color(153,153,204));
			}
			
		});
		
		panelTestCreati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelTestCreati.setBackground(new Color(147,112,219));
			}
		});
		
		panelTestCreati.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelTestCreati.setBackground(new Color(204,153,255));
				home.setVisible(false);
				impostazioni.setVisible(false);
				testConsegnati.setVisible(false);
				testCreati.setVisible(true);
				
				
			}
		});
		
		panelTestCreati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelTestCreati.setBackground(new Color(147,112,219));
			}
		});
		
		panelTestCreati.setBorder(null);
		panelTestCreati.setBounds(0, 90, 163, 62);
		PanelLeft.add(panelTestCreati);
		panelTestCreati.setLayout(null);
		panelTestCreati.setBackground(new Color(147, 112, 219));
		
		JLabel lblTestCreati = new JLabel("Test Creati");
		lblTestCreati.setForeground(Color.WHITE);
		lblTestCreati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCreati.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTestCreati.setBounds(27, 10, 118, 42);
		panelTestCreati.add(lblTestCreati);
		
		JLabel IconTestCreati = new JLabel("");
		IconTestCreati.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/test (2).png")));
		IconTestCreati.setBounds(5, 21, 24, 24);
		panelTestCreati.add(IconTestCreati);
		
		JPanel panelTestConsegnati = new JPanel();
		
		panelTestConsegnati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelTestConsegnati.setBackground(new Color(153,153,204));
			}
			
		});
		
		panelTestConsegnati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelTestConsegnati.setBackground(new Color(147,112,219));
			}
		});
		
		panelTestConsegnati.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelTestCreati.setBackground(new Color(204,153,255));
				home.setVisible(false);
				testCreati.setVisible(false);
				impostazioni.setVisible(false);
				testConsegnati.setVisible(true);
				
				
				
			}
		});
		
		panelTestConsegnati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelTestCreati.setBackground(new Color(147,112,219));
			}
		});
		
		panelTestConsegnati.setLayout(null);
		panelTestConsegnati.setBackground(new Color(147, 112, 219));
		panelTestConsegnati.setBounds(0, 170, 163, 62);
		PanelLeft.add(panelTestConsegnati);
		
		JLabel lblTestConsegnati = new JLabel("Test Ricevuti");
		lblTestConsegnati.setForeground(Color.WHITE);
		lblTestConsegnati.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestConsegnati.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTestConsegnati.setBounds(27, 10, 136, 42);
		panelTestConsegnati.add(lblTestConsegnati);
		
		JLabel IconTestConsegnati = new JLabel("");
		IconTestConsegnati.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/test.png")));
		IconTestConsegnati.setBounds(5, 19, 32, 32);
		panelTestConsegnati.add(IconTestConsegnati);
		
		JPanel panelImpostazioni = new JPanel();
		
		panelImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelImpostazioni.setBackground(new Color(153,153,204));
			}
			
		});
		
		panelImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelImpostazioni.setBackground(new Color(147,112,219));
			}
		});
		
		panelImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelImpostazioni.setBackground(new Color(204,153,255));
				home.setVisible(false);
				testCreati.setVisible(false);
				testConsegnati.setVisible(false);
				impostazioni.setVisible(true);
			}
		});
		
		panelImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelImpostazioni.setBackground(new Color(147,112,219));
			}
		});
		
		panelImpostazioni.setLayout(null);
		panelImpostazioni.setBackground(new Color(147, 112, 219));
		panelImpostazioni.setBounds(0, 260, 163, 62);
		PanelLeft.add(panelImpostazioni);
		
		JLabel lblImpostazioni = new JLabel("Impostazioni");
		lblImpostazioni.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpostazioni.setForeground(Color.WHITE);
		lblImpostazioni.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblImpostazioni.setBounds(28, 10, 131, 42);
		panelImpostazioni.add(lblImpostazioni);
		
		JLabel IconImpostazioni = new JLabel("");
		IconImpostazioni.setFont(new Font("Tahoma", Font.PLAIN, 8));
		IconImpostazioni.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/list (1).png")));
		IconImpostazioni.setBounds(5, 17, 32, 32);
		panelImpostazioni.add(IconImpostazioni);
		
		JPanel panelLogout = new JPanel();
		
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLogout.setBackground(new Color(153,153,204));
			}
			
		});
		
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelLogout.setBackground(new Color(147,112,219));
			}
		});
		
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelLogout.setBackground(new Color(204,153,255));
				secondFrame.setVisible(false);
				Home login = new Home();
				JOptionPane.showMessageDialog(null, "Log out avvenuto con Successo!");
				login.frmMylearn.setVisible(true);
			}
		});
		
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelLogout.setBackground(new Color(147,112,219));
			}
		});
		
		panelLogout.setLayout(null);
		panelLogout.setBackground(new Color(147, 112, 219));
		panelLogout.setBounds(0, 405, 163, 62);
		PanelLeft.add(panelLogout);
		
		JLabel lblLogOut = new JLabel("Log out");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setForeground(Color.WHITE);
		lblLogOut.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogOut.setBounds(7, 10, 131, 42);
		panelLogout.add(lblLogOut);
		
		JLabel IconLogOut = new JLabel("");
		IconLogOut.setIcon(new ImageIcon(Account_Insegnante.class.getResource("/res/log-out.png")));
		IconLogOut.setFont(new Font("Tahoma", Font.PLAIN, 8));
		IconLogOut.setBounds(5, 17, 32, 32);
		panelLogout.add(IconLogOut);
		
		JPanel panelV = new JPanel();
		panelV.setBackground(new Color(153, 102, 255));
		panelV.setBounds(161, -2, 838, 479);
		Main.add(panelV);
		panelV.setLayout(null);
		
		panelV.add(home);
		home.setVisible(true);
		
		panelV.add(testCreati);
		testCreati.setVisible(false);
		
		panelV.add(testConsegnati);
		testConsegnati.setVisible(false);
		
		panelV.add(impostazioni);
		impostazioni.setVisible(false);
		
	}
		
	}