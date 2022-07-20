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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MapPage extends JFrame{
	static String storeAddress;
	// set image icon
	private Image logoImage = new ImageIcon(MapPage.class.getResource("/res/login.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	private Image backIcon = new ImageIcon(MapPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
	static User user;
	public JFrame frame;
	private int fx;
	private int fy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapPage window = new MapPage(user, storeAddress);
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
	public MapPage(User user, String storeAddress) {
		this.user = user;
		this.storeAddress = storeAddress;
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
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Forte", Font.PLAIN, 12));
		panel.setBackground(new Color(51, 153, 153));
		panel.setBorder(new LineBorder(new Color(123, 104, 238), 2));
		panel.setBounds(0, 0, 800, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
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
		goBack.setBounds(22, 31, 75, 58);
		panel.add(goBack);
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel storeInfoLabel = new JLabel("Current Store:");
		storeInfoLabel.setForeground(new Color(153, 0, 102));
		storeInfoLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		storeInfoLabel.setBounds(230, 201, 146, 45);
		panel.add(storeInfoLabel);
		
		JLabel storeInfo = new JLabel((String) null);
		storeInfo.setForeground(new Color(102, 0, 0));
		storeInfo.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		storeInfo.setBounds(410, 201, 129, 45);
		storeInfo.setText(storeAddress);
		panel.add(storeInfo);
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel tip = new JLabel("All store are in a 50*50 matrix. ");
		tip.setForeground(new Color(102, 0, 0));
		tip.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		tip.setBounds(54, 244, 634, 45);
		panel.add(tip);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 299, 743, 466);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(null);
		textArea.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		textArea.setBackground(new Color(255, 153, 153));
		try {
			textArea.setText(Account.getMap(storeAddress));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrollPane.setViewportView(textArea);
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 800, 800);
		backLabel.setIcon(new ImageIcon(backIcon));
		panel.add(backLabel);
		
		frame.setLocationRelativeTo(null);
	}
}
