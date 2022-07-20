package final_Project;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class LoginPage extends JFrame{
	
	// set image icon
	private Image logoImage = new ImageIcon(LoginPage.class.getResource("/res/login.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	private Image keyImage = new ImageIcon(LoginPage.class.getResource("/res/key.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image userImage = new ImageIcon(LoginPage.class.getResource("/res/user.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
	
	
	public JFrame frame;
	private JTextField userName;
	private JPasswordField pwd;
	private int fx;
	private int fy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fx = e.getXOnScreen() - frame.getX();
                fy = e.getYOnScreen() - frame.getY();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - fx, e.getYOnScreen() - fy);
            }
        });
        
		frame.getContentPane().setBackground(new Color(47, 79, 79));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Forte", Font.PLAIN, 12));
		panel.setBackground(new Color(51, 153, 153));
		panel.setBorder(new LineBorder(new Color(123, 104, 238), 2));
		panel.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel goRegister = new JLabel("If you don't have acount, click here to register!");
		goRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goRegister.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goRegister.setForeground(Color.DARK_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				registerPage rg = new registerPage();
				rg.frame.setVisible(true);
				frame.dispose();
			}
		});
		goRegister.setForeground(Color.DARK_GRAY);
		goRegister.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		goRegister.setBounds(224, 476, 351, 46);
		panel.add(goRegister);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(102, 153, 255)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(224, 225, 332, 46);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		userName = new JTextField();
		userName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(userName.getText().equals("Username")) {
					userName.setText("");
				}
				else {
					userName.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (userName.getText().equals("")) 
					userName.setText("Username");
			}
		});
		userName.setText("Username");
		userName.setBackground(new Color(255, 255, 255));
		userName.setBorder(null);
		userName.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		userName.setBounds(10, 10, 263, 26);
		panel_1.add(userName);
		userName.setColumns(10);
		
		JLabel userLabel = new JLabel("");
		userLabel.setBounds(283, 0, 49, 46);
		panel_1.add(userLabel);
		userLabel.setIcon(new ImageIcon(userImage));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(102, 153, 255)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(224, 298, 332, 46);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		pwd = new JPasswordField();
		pwd.setFont(new Font("Arial", Font.BOLD, 14));
		
		pwd.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(pwd.getText().equals("Password")) {
					pwd.setEchoChar('●');
					pwd.setText("");
				}
				else {
					pwd.selectAll();
				}
			
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (pwd.getText().equals("")) {
					pwd.setText("Password");
					pwd.setEchoChar((char)0);
				}
			}
		});
		
		pwd.setBorder(null);
		pwd.setBounds(10, 10, 264, 26);
		pwd.setText("Password");
		panel_1_1.add(pwd);
		
		JLabel keyLabel = new JLabel("");
		keyLabel.setBounds(283, 0, 39, 46);
		panel_1_1.add(keyLabel);
		keyLabel.setIcon(new ImageIcon(keyImage));
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = userName.getText();
				@SuppressWarnings("deprecation")
				String pass = pwd.getText();
				MaintainUser maintain = new MaintainUser();
				Boolean success = false;
				
				
				try {
					maintain.load();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for (int i = 0; i < maintain.users.size(); i++) {
					if (user.equals(maintain.users.get(i).name) && pass.equals(maintain.users.get(i).password)) {
						success = true;
						
						
						JOptionPane.showMessageDialog(null, "Login Successful!");
						
						// set this user name to each own page
						if(maintain.users.get(i).type.equals("Customer")) {
							customerPage c = new customerPage(maintain.users.get(i));
//							customerPage.customer = u;
							c.frame.setVisible(true);
							frame.dispose();
						}
						else if (maintain.users.get(i).type.equals("Manager")) {
							ManagerPage m = new ManagerPage(maintain.users.get(i));
							m.frame.setVisible(true);
							frame.dispose();
						}
						else {
							AdministratorPage a = new AdministratorPage(maintain.users.get(i));
							a.frame.setVisible(true);
							frame.dispose();
						}
					}
						
				}
				
				if (success == false) {
//					Account account = new Account();
//					account.loginAccount.add(user);
					JOptionPane.showMessageDialog(null, "Invalid User name or Password!");
					
				}
				
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 51, 51));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNewButton.setBounds(280, 380, 227, 56);
		panel.add(btnNewButton);
		
		JLabel exit = new JLabel("x");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure close?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					frame.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.blue);
			}
		});
		exit.setFont(new Font("SimHei", Font.BOLD, 54));
		exit.setForeground(Color.BLUE);
		exit.setBounds(755, 10, 45, 84);
		panel.add(exit);
		
		JLabel loginLogo = new JLabel("");
		loginLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		loginLogo.setHorizontalAlignment(SwingConstants.CENTER);
		loginLogo.setBounds(280, 31, 227, 165);
		loginLogo.setIcon(new ImageIcon(logoImage));
		panel.add(loginLogo);
		
		JLabel backLabel = new JLabel("✖");
		backLabel.setBounds(0, 0, 800, 600);
		backLabel.setIcon(new ImageIcon(backIcon));
		panel.add(backLabel);
		
		frame.setLocationRelativeTo(null);
	}
}
