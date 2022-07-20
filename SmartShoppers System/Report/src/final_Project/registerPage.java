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
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class registerPage extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
	private JTextField userName;
	private JTextField pwd;
	private JTextField address;
	private JTextField email;
	
	private int fx;
	private int fy;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerPage window = new registerPage();
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
	public registerPage() {
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
		
		String[] userType = {"Customer", "Administrator"}; 
		JComboBox comboBox = new JComboBox(userType);
		comboBox.setFont(new Font("SimSun", Font.PLAIN, 18));
		comboBox.setBackground(new Color(204, 255, 255));
		comboBox.setBounds(427, 75, 181, 35);
		frame.getContentPane().add(comboBox);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("User type");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1.setBounds(316, 71, 87, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		userName = new JTextField();
		userName.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		userName.setBounds(427, 123, 181, 37);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel.setBounds(316, 120, 87, 45);
		frame.getContentPane().add(userNameLabel);
		
		pwd = new JTextField();
		pwd.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		pwd.setColumns(10);
		pwd.setBounds(427, 181, 181, 37);
		frame.getContentPane().add(pwd);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(316, 173, 87, 45);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Address");
		lblNewLabel_1_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(316, 239, 87, 45);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Email Address");
		lblNewLabel_1_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(274, 298, 129, 45);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		address = new JTextField();
		address.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		address.setColumns(10);
		address.setBounds(427, 242, 181, 37);
		frame.getContentPane().add(address);
		
		email = new JTextField();
		email.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		email.setColumns(10);
		email.setBounds(427, 302, 181, 37);
		frame.getContentPane().add(email);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// get user Account type
				int userType = comboBox.getSelectedIndex();
				String user = userName.getText();
				String password = pwd.getText();
				String addString = address.getText();
				String emailString = email.getText();
				MaintainUser maintain = new MaintainUser();
				
				try {
					if (!Account.checkIsNewUser(user)) {
						JOptionPane.showMessageDialog(null, "Already Registered!");
					}
					else if(user.equals("") || password.equals("") || addString.equals("") || emailString.equals("")) {
						JOptionPane.showMessageDialog(null, "Please don't enter empty infomation!");
					}
					else {
						// its Customer Account
						if (userType == 0) {
							User newUser = new User("Customer", user, password, addString, emailString, "");
							
							try {
								Account.createAccount(newUser);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Register Successful!");
							
							// go to login page
							LoginPage lg = new LoginPage();
							lg.frame.setVisible(true);
							frame.dispose();
						}
						// its Administrator Account
						else if (userType == 1) {
								User newUser = new User("Administrator", user, password, addString, emailString, "");
								
								try {
									Account.createAccount(newUser);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								JOptionPane.showMessageDialog(null, "Register Successful!");
								// go to login page
								LoginPage lg = new LoginPage();
								lg.frame.setVisible(true);
								frame.dispose();
							
						}
					}
				} catch (HeadlessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// print all Account in console, just for test check
				try {
					maintain.load();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(User u: maintain.users){
					System.out.println(u.toString());
				}
				
				
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRegister.setBackground(new Color(0, 51, 51));
		btnRegister.setBounds(320, 417, 227, 56);
		frame.getContentPane().add(btnRegister);
		
		JLabel goBack = new JLabel("Back");
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage lg = new LoginPage();
				lg.frame.setVisible(true);
				frame.dispose();
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
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 800, 600);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
	}
}
