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
import javax.swing.SwingConstants;

public class ManagerPage extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
	private Image logoImage = new ImageIcon(LoginPage.class.getResource("/res/login.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	static User manager;
	private int fx;
	private int fy;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPage window = new ManagerPage(manager);
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
	public ManagerPage(User manager) {
		this.manager = manager;
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
		frame.setBounds(100, 100, 1100, 650);
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
		exit.setBounds(1056, 10, 34, 58);
		frame.getContentPane().add(exit);
		
		JLabel goBack = new JLabel("Log out");
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Account account = new Account();
//				
//				//remove this user from login list
//				for (int i = 0; i < account.loginAccount.size(); i++) {
//					if (account.loginAccount.get(i).equals(manager.name)) 
//						account.loginAccount.remove(i);
//				}
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
		goBack.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		goBack.setBounds(901, 27, 99, 51);
		frame.getContentPane().add(goBack);
		
		JLabel lblNewLabel = new JLabel("User Profile");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userProfile up = new userProfile(manager);
				up.frame.setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(Color.MAGENTA);
			}
		});
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblNewLabel.setBounds(699, 30, 152, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your Store:");
		lblNewLabel_1.setForeground(new Color(102, 0, 153));
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 24));
		lblNewLabel_1.setBounds(21, 281, 152, 73);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel storeName = new JLabel("");
		storeName.setForeground(new Color(102, 102, 255));
		storeName.setFont(new Font("Sitka Subheading", Font.BOLD, 24));
		storeName.setBounds(178, 288, 443, 58);
		storeName.setText(Manager.getStoreAdress(manager.name));
		frame.getContentPane().add(storeName);
		
		JButton btnCheckItem = new JButton("Check Item");
		btnCheckItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MaintainStore maintain = new MaintainStore();
				String storeAddress = Manager.getStoreAdress(manager.name);
				
				if(storeAddress.equals("Sorry you can't manage any store.")) {
					JOptionPane.showMessageDialog(null, "Sorry you has not permission to manage any store! Please connect to your Administrator.");
				}
				else {
					
					try {
						maintain.load();
						for(Store s: maintain.stores){
							if (s.address.equals(storeAddress)) {
								JOptionPane.showMessageDialog(null, "Going to Item Page!");
								ItemPage item = new ItemPage(s, manager);
								item.frame.setVisible(true);
								frame.dispose();
							}
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCheckItem.setForeground(Color.WHITE);
		btnCheckItem.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		btnCheckItem.setBackground(new Color(0, 51, 51));
		btnCheckItem.setBounds(433, 430, 253, 101);
		frame.getContentPane().add(btnCheckItem);
		
		JLabel loginLogo = new JLabel("");
		loginLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		loginLogo.setHorizontalAlignment(SwingConstants.CENTER);
		loginLogo.setBounds(433, 61, 227, 165);
		loginLogo.setIcon(new ImageIcon(logoImage));
		frame.getContentPane().add(loginLogo);
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 1100, 650);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
		
		
	}
}
