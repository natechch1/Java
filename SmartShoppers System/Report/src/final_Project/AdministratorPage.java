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
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdministratorPage extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(1100, 1000, Image.SCALE_SMOOTH);
	static User administrator;
	private int fx;
	private int fy;
	private JTable table;
	private JTextField address;
	private JTextField managerName;
	private JTextField openTime;
	private JTextField closeTime;
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorPage window = new AdministratorPage(administrator);
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
	public AdministratorPage(User administrator) {
		this.administrator = administrator;
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
//					if (account.loginAccount.get(i).equals(administrator.name)) 
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 628, 924, 290);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) {
					address.setText((String) model.getValueAt(i, 0));
					managerName.setText((String) model.getValueAt(i, 1));
					openTime.setText((String) model.getValueAt(i, 2));
					closeTime.setText((String) model.getValueAt(i, 3));
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(new Color(204, 255, 255));
		model = new DefaultTableModel();
		Object[] column = {"Store Address", "Manager Name", "Open Time", "Close Time"};
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		
		//show previous manager info
		MaintainStore maintain = new MaintainStore();
		
		try {
			maintain.load();
			for(Store s: maintain.stores){
				row[0] = s.address;
				row[1] = s.managerName;
				row[2] = s.openTime;
				row[3] = s.closeTime;
				model.addRow(row);
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		table.setModel(model);
		
		JLabel lblNewLabel = new JLabel("User Profile");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userProfile up = new userProfile(administrator);
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
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
		lblNewLabel.setBounds(699, 30, 152, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnRegister = new JButton("Modify Manager");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerManagerPage rm = new registerManagerPage();
				rm.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnRegister.setBackground(new Color(0, 51, 51));
		btnRegister.setBounds(57, 49, 227, 56);
		frame.getContentPane().add(btnRegister);
		
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		addressLabel.setBounds(153, 154, 87, 45);
		frame.getContentPane().add(addressLabel);
		
		address = new JTextField();
		address.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		address.setColumns(10);
		address.setBounds(264, 157, 181, 37);
		frame.getContentPane().add(address);
		
		JLabel managerNameLabel = new JLabel("Manager Name");
		managerNameLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		managerNameLabel.setBounds(532, 149, 137, 45);
		frame.getContentPane().add(managerNameLabel);
		
		managerName = new JTextField();
		managerName.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		managerName.setColumns(10);
		managerName.setBounds(693, 157, 181, 37);
		frame.getContentPane().add(managerName);
		
		JLabel openLabel = new JLabel("Open Time");
		openLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		openLabel.setBounds(141, 216, 99, 45);
		frame.getContentPane().add(openLabel);
		
		openTime = new JTextField();
		openTime.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		openTime.setColumns(10);
		openTime.setBounds(264, 219, 181, 37);
		frame.getContentPane().add(openTime);
		
		JLabel closeLabel = new JLabel("Close Time");
		closeLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		closeLabel.setBounds(557, 216, 112, 45);
		frame.getContentPane().add(closeLabel);
		
		closeTime = new JTextField();
		closeTime.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		closeTime.setColumns(10);
		closeTime.setBounds(693, 220, 181, 37);
		frame.getContentPane().add(closeTime);
		
		JButton btnadd = new JButton("ADD");
		btnadd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String add = address.getText();
				String manager = managerName.getText();
				String open = openTime.getText();
				String close = closeTime.getText();
				MaintainStore maintain = new MaintainStore();
				
				try {
					if(add.equals("") || open.equals("") || close.equals("")) {
						JOptionPane.showMessageDialog(null, "Please don't enter empty infomation!");
					}
					else if (!Administrator.checkIsNewStore(add)) {
						JOptionPane.showMessageDialog(null, "Already has this store!");
					}
					else if (Account.checkIsNewUser(manager)) {
						JOptionPane.showMessageDialog(null, "This Manager is not exist!");
					}
					else if (!Administrator.checkIsNewManager(manager)) {
						JOptionPane.showMessageDialog(null, "This Manager already has one store!");
					}
					else {
						row[0] = add;
						row[1] = manager;
						row[2] = open;
						row[3] = close;
						model.addRow(row);
						
						Store store = new Store(add, manager, open, close);
						
						Administrator.addStore(store);
						address.setText("");
						managerName.setText("");
						openTime.setText("");
						closeTime.setText("");
						
					}	
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// print all Account in console, just for test check
				try {
					maintain.load();
					System.out.println();
					for(Store s: maintain.stores){
						System.out.println(s.toString());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnadd.setForeground(Color.WHITE);
		btnadd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnadd.setBackground(new Color(0, 51, 51));
		btnadd.setBounds(224, 319, 227, 56);
		frame.getContentPane().add(btnadd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addMouseListener(new MouseAdapter() {
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
						for(Store s: maintain.stores){
							if (s.address.equals(addressString)) {
								String add = address.getText();
								String manager = managerName.getText();
								String open = openTime.getText();
								String close = closeTime.getText();
								Store changedStore = new Store(add, manager, open, close);
								
								Administrator.updateStore(s, changedStore);
								model.setValueAt(add, i, 0);
								model.setValueAt(manager, i, 1);
								model.setValueAt(open, i, 2);
								model.setValueAt(close, i, 3);
								
								JOptionPane.showMessageDialog(null, "Update success!");
								break;
							}
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnUpdate.setBackground(new Color(0, 51, 51));
		btnUpdate.setBounds(624, 319, 227, 56);
		frame.getContentPane().add(btnUpdate);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addMouseListener(new MouseAdapter() {
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
						for(Store s: maintain.stores){
							if (s.address.equals(addressString)) {
								Administrator.deleteStore(addressString);
								model.removeRow(i);
								JOptionPane.showMessageDialog(null, "Delete success!");
							}
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		deleteButton.setBackground(new Color(0, 51, 51));
		deleteButton.setBounds(224, 402, 227, 56);
		frame.getContentPane().add(deleteButton);
		
		JButton clearButton = new JButton("CLEAR TEXT");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				address.setText("");
				managerName.setText("");
				openTime.setText("");
				closeTime.setText("");
			}
		});
		clearButton.setForeground(Color.WHITE);
		clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		clearButton.setBackground(new Color(0, 51, 51));
		clearButton.setBounds(624, 402, 227, 56);
		frame.getContentPane().add(clearButton);
		
		JButton btnCheckItem = new JButton("Check Item");
		btnCheckItem.addMouseListener(new MouseAdapter() {
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
						for(Store s: maintain.stores){
							if (s.address.equals(addressString)) {
								JOptionPane.showMessageDialog(null, "Going to Item Page!");
								ItemPage item = new ItemPage(s, administrator);
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
		btnCheckItem.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnCheckItem.setBackground(new Color(0, 51, 51));
		btnCheckItem.setBounds(426, 512, 227, 56);
		frame.getContentPane().add(btnCheckItem);
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 1100, 1000);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
		
		
	}
}
