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
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registerManagerPage extends JFrame{

	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(803, 767, Image.SCALE_SMOOTH);
	private JTextField userName;
	private JTextField pwd;
	private JTextField address;
	private JTextField email;
	private int fx;
	private int fy;
	private JTable table;
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerManagerPage window = new registerManagerPage();
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
	public registerManagerPage() {
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
		frame.setBounds(100, 100, 803, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel exit = new JLabel("x");
		exit.setBounds(756, 10, 34, 58);
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
		frame.getContentPane().setLayout(null);
		exit.setForeground(Color.BLUE);
		exit.setFont(new Font("SimHei", Font.BOLD, 54));
		frame.getContentPane().add(exit);
		
		userName = new JTextField();
		userName.setBounds(143, 129, 181, 37);
		userName.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(32, 126, 87, 45);
		userNameLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(userNameLabel);
		
		pwd = new JTextField();
		pwd.setBounds(572, 129, 181, 37);
		pwd.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		pwd.setColumns(10);
		frame.getContentPane().add(pwd);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setBounds(461, 121, 87, 45);
		lblNewLabel_1_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Address");
		lblNewLabel_1_1_2.setBounds(32, 188, 87, 45);
		lblNewLabel_1_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Email Address");
		lblNewLabel_1_1_3.setBounds(419, 188, 129, 45);
		lblNewLabel_1_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		address = new JTextField();
		address.setBounds(143, 191, 181, 37);
		address.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		address.setColumns(10);
		frame.getContentPane().add(address);
		
		email = new JTextField();
		email.setBounds(572, 192, 181, 37);
		email.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		email.setColumns(10);
		frame.getContentPane().add(email);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 430, 721, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) {
					userName.setText((String) model.getValueAt(i, 0));
					pwd.setText((String) model.getValueAt(i, 1));
					address.setText((String) model.getValueAt(i, 2));
					email.setText((String) model.getValueAt(i, 3));
				}
			}
		});
		table.setBackground(new Color(204, 255, 255));
		model = new DefaultTableModel();
		Object[] column = {"Manager name", "Password", "Address", "Email"};
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
		//show previous manager info
		MaintainUser maintain = new MaintainUser();
		
		try {
			maintain.load();
			for(User u: maintain.users){
				if (u.type.equals("Manager")) {
					row[0] = u.name;
					row[1] = u.password;
					row[2] = u.address;
					row[3] = u.email;
					model.addRow(row);
				}
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userName.getText();
				String password = pwd.getText();
				String addString = address.getText();
				String emailString = email.getText();
				try {
					if (!Account.checkIsNewUser(user)) {
						JOptionPane.showMessageDialog(null, "Already Registered!");
					}
					else if(user.equals("") || password.equals("") || addString.equals("") || emailString.equals("")) {
						JOptionPane.showMessageDialog(null, "Please don't enter empty infomation!");
					}
					else {
						row[0] = user;
						row[1] = password;
						row[2] = addString;
						row[3] = emailString;
						model.addRow(row);
						
					}	
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRegister.setBounds(97, 279, 227, 56);
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
						
							User newUser = new User("Manager", user, password, addString, emailString, "");
							try {
								Account.createAccount(newUser);
								
								userName.setText("");
								pwd.setText("");
								address.setText("");
								email.setText("");
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "Register Successful!");
						
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
		frame.getContentPane().add(btnRegister);
		
		JLabel goBack = new JLabel("Back");
		goBack.setBounds(32, 41, 75, 58);
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdministratorPage a = new AdministratorPage(AdministratorPage.administrator);
				a.frame.setVisible(true);
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
		frame.getContentPane().add(goBack);
		
	
		
		
		
		
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				MaintainUser maintain = new MaintainUser();
				MaintainStore storeMaintain = new MaintainStore();
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String managerName =  (String) model.getValueAt(i, 0);
					
					try {
						maintain.load();
						for(User u: maintain.users){
							if (u.name.equals(managerName)) {
								// set it null in the store info 
								storeMaintain.load();
								for (Store s : storeMaintain.stores) {
									if(s.managerName.equals(u.name))
										s.setManagerName("");
								}
								storeMaintain.update();
								Account.deleteAccount(u);
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
		deleteButton.setBounds(97, 362, 227, 56);
		frame.getContentPane().add(deleteButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				MaintainUser maintain = new MaintainUser();
				
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String managerName =  (String) model.getValueAt(i, 0);
					
					try {
						maintain.load();
						for(User u: maintain.users){
							if (u.name.equals(managerName)) {
								String name = userName.getText();
								String password = pwd.getText();
								String addString = address.getText();
								String emailString = email.getText();
								User oldUser = new User("Manager", name, password, addString, emailString, "");
								
								Account.changeInfo(u, oldUser);
								model.setValueAt(name, i, 0);
								model.setValueAt(password, i, 1);
								model.setValueAt(addString, i, 2);
								model.setValueAt(emailString, i, 3);
								
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
		btnUpdate.setBounds(497, 279, 227, 56);
		frame.getContentPane().add(btnUpdate);
		
		JButton clearButton = new JButton("CLEAR TEXT");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userName.setText("");
				pwd.setText("");
				address.setText("");
				email.setText("");
			}
		});
		clearButton.setForeground(Color.WHITE);
		clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		clearButton.setBackground(new Color(0, 51, 51));
		clearButton.setBounds(497, 362, 227, 56);
		frame.getContentPane().add(clearButton);
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 802, 767);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
	}
}
