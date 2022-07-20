package final_Project;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class userProfile extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
	private JTextField userName;
	private JTextField pwd;
	private JTextField address;
	private JTextField email;
	private int fx;
	private int fy;
	public static User user;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userProfile window = new userProfile(user);
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
	public userProfile(User user) {
		this.user = user;
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
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
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
		exit.setForeground(Color.BLUE);
		exit.setFont(new Font("SimHei", Font.BOLD, 54));
		exit.setBounds(756, 10, 34, 58);
		frame.getContentPane().add(exit);
		
		userName = new JTextField();
		userName.setText(user.name);
		userName.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		userName.setBounds(350, 153, 181, 37);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel.setBounds(239, 150, 87, 45);
		frame.getContentPane().add(userNameLabel);
		
		pwd = new JTextField();
		pwd.setText(user.password);
		pwd.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		pwd.setColumns(10);
		pwd.setBounds(350, 211, 181, 37);
		frame.getContentPane().add(pwd);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(239, 203, 87, 45);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Address");
		lblNewLabel_1_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(239, 269, 87, 45);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Email Address");
		lblNewLabel_1_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(197, 328, 129, 45);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		address = new JTextField();
		address.setText(user.address);
		address.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		address.setColumns(10);
		address.setBounds(350, 272, 181, 37);
		frame.getContentPane().add(address);
		
		email = new JTextField();
		email.setText(user.email);
		email.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		email.setColumns(10);
		email.setBounds(350, 332, 181, 37);
		frame.getContentPane().add(email);
		
		JButton btnRegister = new JButton("CHANGE");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = userName.getText();
				String pass = pwd.getText();
				String add = address.getText();
				String em = email.getText();
				User changedUser = new User(user.type, name, pass, add, em, user.preferenceStore);
				
				try {
					Account.changeInfo(user, changedUser);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Success change!");
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRegister.setBackground(new Color(0, 51, 51));
		btnRegister.setBounds(275, 416, 227, 56);
		frame.getContentPane().add(btnRegister);
		
		JLabel goBack = new JLabel("Back");
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// back to each user main page
				if (user.type.equals("Customer")) {
					customerPage c = new customerPage(user);
					c.frame.setVisible(true);
					frame.dispose();
				}
				else if (user.type.equals("Manager")) {
					ManagerPage m = new ManagerPage(user);
					m.frame.setVisible(true);
					frame.dispose();
				}
				else {
					AdministratorPage a = new AdministratorPage(user);
					a.frame.setVisible(true);
					frame.dispose();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				goBack.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goBack.setForeground(Color.blue);
			}
		});
		goBack.setForeground(Color.BLUE);
		goBack.setFont(new Font("Sitka Subheading", Font.BOLD, 30));
		goBack.setBounds(68, 46, 75, 58);
		frame.getContentPane().add(goBack);
		
		JButton btnDeleteAccount = new JButton("DELETE ACCOUNT");
		btnDeleteAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure delete this account?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					try {
						Account.deleteAccount(user);
						LoginPage lg = new LoginPage();
						lg.frame.setVisible(true);
						frame.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteAccount.setForeground(Color.WHITE);
		btnDeleteAccount.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnDeleteAccount.setBackground(new Color(0, 51, 51));
		btnDeleteAccount.setBounds(297, 503, 189, 45);
		frame.getContentPane().add(btnDeleteAccount);
		
		JLabel typeLabel = new JLabel("Account Type");
		typeLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		typeLabel.setBounds(213, 95, 113, 45);
		frame.getContentPane().add(typeLabel);
		
		JLabel accountType = new JLabel("");
		accountType.setForeground(new Color(102, 102, 0));
		accountType.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		accountType.setBounds(350, 95, 181, 45);
		accountType.setText(user.type);
		frame.getContentPane().add(accountType);
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 800, 600);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
	}
}
