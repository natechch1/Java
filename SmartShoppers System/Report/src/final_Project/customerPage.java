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
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class customerPage extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(1100, 1000, Image.SCALE_SMOOTH);
	static User customer;
	private int fx;
	private int fy;
	private JTable table;
	DefaultTableModel model;
	private JTextField newPS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerPage window = new customerPage(customer);
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
	public customerPage(User customer) {
		this.customer = customer;
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
		frame.setBounds(100, 100, 1100, 1000);
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
//					if (account.loginAccount.get(i).equals(customer.name)) 
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
				userProfile up = new userProfile(customer);
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
		
		JLabel lblPreferenceStore = new JLabel("Preference Store :");
		lblPreferenceStore.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblPreferenceStore.setBounds(78, 123, 198, 45);
		frame.getContentPane().add(lblPreferenceStore);
		
		JLabel preferenceStore = new JLabel("");
		preferenceStore.setForeground(new Color(102, 0, 102));
		preferenceStore.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		preferenceStore.setBounds(298, 123, 446, 45);
		preferenceStore.setText(customer.preferenceStore);
		frame.getContentPane().add(preferenceStore);
		
		JButton btnChange = new JButton("Change");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				MaintainUser maintain = new MaintainUser();
				String storeAdress = (String) model.getValueAt(i, 0);
				
				try {
					maintain.load();
					for(User u : maintain.users) {
						if (u.name.equals(customer.name)) {
							u.preferenceStore = storeAdress;
							preferenceStore.setText(storeAdress);
							break;
						}	
					}
					maintain.update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnChange.setForeground(Color.WHITE);
		btnChange.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnChange.setBackground(new Color(0, 51, 51));
		btnChange.setBounds(820, 206, 180, 37);
		frame.getContentPane().add(btnChange);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 280, 922, 450);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) 
					newPS.setText((String) model.getValueAt(i, 0));
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		Object[] column = {"Store Address", "Open Time", "Close Time"};
		Object[] row = new Object[3];
		model.setColumnIdentifiers(column);
		table.setBackground(new Color(204, 255, 255));
		
		//show previous manager info
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			for(Store s: maintain.stores){
				row[0] = s.address;
				row[1] = s.openTime;
				row[2] = s.closeTime;
				model.addRow(row);
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		table.setModel(model);
		
		JButton btnGo = new JButton("Go Store");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				MaintainStore maintain = new MaintainStore();
				
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String addressString =  (String) model.getValueAt(i, 0);
					
					try {
						maintain.load();
						for(Store s : maintain.stores){
							if (s.address.equals(addressString)) {
								ShoppingListPage slp = new ShoppingListPage(s, customer);
								JOptionPane.showMessageDialog(null, "Going to Shopping!");
								slp.frame.setVisible(true);
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
		btnGo.setForeground(Color.WHITE);
		btnGo.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		btnGo.setBackground(new Color(0, 51, 51));
		btnGo.setBounds(428, 788, 226, 63);
		frame.getContentPane().add(btnGo);
		
		newPS = new JTextField();
		newPS.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		newPS.setColumns(10);
		newPS.setBounds(286, 206, 458, 37);
		frame.getContentPane().add(newPS);
		
		JLabel lblNewPreferenceStore = new JLabel("New Preference Store :");
		lblNewPreferenceStore.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblNewPreferenceStore.setBounds(30, 198, 246, 45);
		frame.getContentPane().add(lblNewPreferenceStore);
		
		
		
		JLabel backLabel = new JLabel("");
		backLabel.setForeground(new Color(51, 153, 102));
		backLabel.setBounds(0, 0, 1100, 1000);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
		
		
	}
}
