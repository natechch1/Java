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
import java.util.UUID;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemPage extends JFrame{
	
	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(950, 1200, Image.SCALE_SMOOTH);
	private JTextField itemName;
	private JTextField category;
	private JTextField quantity;
	private JTextField price;
	private int fx;
	private int fy;
	private JTable table;
	DefaultTableModel model;
	public static Store store;
	private JTextField mapX;
	private JTextField mapY;
	static User user;
	private JTextField description;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemPage window = new ItemPage(store, user);
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
	public ItemPage(Store store, User user) {
		this.store = store;
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
		frame.setBounds(100, 100, 950, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel exit = new JLabel("x");
		exit.setBounds(870, 29, 34, 58);
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
		
		itemName = new JTextField();
		itemName.setBounds(211, 176, 181, 37);
		itemName.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		frame.getContentPane().add(itemName);
		itemName.setColumns(10);
		
		JLabel itemNameLabel = new JLabel("Item Name");
		itemNameLabel.setBounds(64, 176, 129, 45);
		itemNameLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(itemNameLabel);
		
		category = new JTextField();
		category.setBounds(658, 176, 181, 37);
		category.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		category.setColumns(10);
		frame.getContentPane().add(category);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Category");
		lblNewLabel_1_1_1.setBounds(508, 176, 87, 45);
		lblNewLabel_1_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Quantity");
		lblNewLabel_1_1_2.setBounds(64, 238, 87, 45);
		lblNewLabel_1_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Price");
		lblNewLabel_1_1_3.setBounds(508, 243, 87, 45);
		lblNewLabel_1_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		quantity = new JTextField();
		quantity.setBounds(211, 238, 181, 37);
		quantity.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		quantity.setColumns(10);
		frame.getContentPane().add(quantity);
		
		price = new JTextField();
		price.setBounds(658, 239, 181, 37);
		price.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		price.setColumns(10);
		frame.getContentPane().add(price);
		
		mapX = new JTextField();
		mapX.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		mapX.setColumns(10);
		mapX.setBounds(246, 377, 100, 37);
		frame.getContentPane().add(mapX);
		
		mapY = new JTextField();
		mapY.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		mapY.setColumns(10);
		mapY.setBounds(442, 377, 100, 37);
		frame.getContentPane().add(mapY);
		
		description = new JTextField();
		description.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		description.setColumns(10);
		description.setBounds(211, 440, 628, 74);
		frame.getContentPane().add(description);
		
		String[] itemSize = {"Small", "Medium", "Large"}; 
		JComboBox sizeComboBox = new JComboBox(itemSize);
		sizeComboBox.setFont(new Font("SimSun", Font.PLAIN, 18));
		sizeComboBox.setBackground(new Color(204, 255, 255));
		sizeComboBox.setBounds(211, 310, 181, 35);
		frame.getContentPane().add(sizeComboBox);
		
		String[] dc = {"Yes", "No"};
		JComboBox discountComboBox = new JComboBox(dc);
		discountComboBox.setFont(new Font("SimSun", Font.PLAIN, 18));
		discountComboBox.setBackground(new Color(204, 255, 255));
		discountComboBox.setBounds(658, 310, 181, 35);
		frame.getContentPane().add(discountComboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 728, 884, 443);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i >= 0) {
					itemName.setText((String) model.getValueAt(i, 0));
					quantity.setText(String.valueOf(model.getValueAt(i, 1)) );
					price.setText(String.valueOf(model.getValueAt(i, 2)));
					category.setText((String) model.getValueAt(i, 4));
					// set item size in combobox
					if (model.getValueAt(i, 3).equals("Small")) {
						sizeComboBox.setSelectedIndex(0);
					}
					else if(model.getValueAt(i, 3).equals("Medium")) {
						sizeComboBox.setSelectedIndex(1);
					}
					else {
						sizeComboBox.setSelectedIndex(2);
					}
					
					// set item discount in combobox
					if (model.getValueAt(i, 5).equals("Yes")) {
						discountComboBox.setSelectedIndex(0);
					}
					else {
						discountComboBox.setSelectedIndex(1);
					}
					mapX.setText((String) model.getValueAt(i, 6));
					mapY.setText((String) model.getValueAt(i, 7));
					description.setText((String) model.getValueAt(i, 8));
				}
			}
		});
		table.setBackground(new Color(204, 255, 255));
		model = new DefaultTableModel();
		Object[] column = {"Item Name", "Quantity", "Price", "Size", "category", "Discount", "Coordinate X", "Coordinate Y", "Description"};
		Object[] row = new Object[9];
		model.setColumnIdentifiers(column);
		//show previous manager info
		MaintainItem maintain = new MaintainItem();
		
		try {
			maintain.load();
			for(Item i: maintain.items){
				if (i.storeAddress.equals(store.address)) {
					row[0] = i.itemName;
					row[1] = i.quantity;
					row[2] = i.price;
					row[3] = i.itemSize;
					row[4] = i.category;
					row[5] = i.discount;
					row[6] = i.mapX;
					row[7] = i.mapY;
					row[8] = i.description;
					model.addRow(row);
				}
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(139, 546, 227, 56);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String name = itemName.getText();
				String cate = category.getText();
				String x = mapX.getText();
				String y = mapY.getText();
				int quan = Integer.valueOf(quantity.getText());
				double pc = Double.valueOf(price.getText());
				int sizeIndex = sizeComboBox.getSelectedIndex();
				int disIndex = discountComboBox.getSelectedIndex();
				String des = description.getText(); 
				MaintainItem maintain = new MaintainItem();
				
				try {
					if (!Administrator.checkIsNewItem(name, store.address, itemSize[sizeIndex])) {
						JOptionPane.showMessageDialog(null, "Already Registered!");
					}
					else if(name.equals("") || cate.equals("") || quan == 0 || pc == 0 || x.equals("") || y.equals("") || des.equals("")) {
						JOptionPane.showMessageDialog(null, "Please don't enter empty infomation!");
					}
					else {
							UUID uuid = UUID.randomUUID();
							String itemID = String.valueOf(uuid);
							Item item = new Item(store.address, itemID, name, itemSize[sizeIndex], cate, quan, pc, dc[disIndex], x, y, des);
							try {
								Administrator.addItem(item);
								
								row[0] = name;
								row[1] = quan;
								row[2] = pc;
								row[3] = itemSize[sizeIndex];
								row[4] = cate;
								row[5] = dc[disIndex];
								row[6] = x;
								row[7] = y;
								row[8] = des;
								model.addRow(row);
								
								itemName.setText("");
								category.setText("");
								quantity.setText("");
								price.setText("");
								mapX.setText("");
								mapY.setText("");
								description.setText("");
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "Add Successful!");
						
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
				for(Item i: maintain.items){
					System.out.println(i.toString());
				}
				
				
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAdd.setBackground(new Color(0, 51, 51));
		frame.getContentPane().add(btnAdd);
		
		JLabel goBack = new JLabel("Back");
		goBack.setBounds(32, 41, 75, 58);
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (user.type.equals("Administrator")) {
					AdministratorPage a = new AdministratorPage(AdministratorPage.administrator);
					a.frame.setVisible(true);
					frame.dispose();
				}
				else {
					ManagerPage m = new ManagerPage(ManagerPage.manager);
					m.frame.setVisible(true);
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
		frame.getContentPane().add(goBack);
		
	
		
		
		
		
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				MaintainItem maintain = new MaintainItem();
			
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String iName =  (String) model.getValueAt(i, 0);
					String size =  (String) model.getValueAt(i, 3);
					try {
						maintain.load();
						for(Item it: maintain.items){
							if (it.itemName.equals(iName) && it.itemSize.equals(size) && it.storeAddress.equals(store.address)) {
								
								Administrator.deleteItem(iName, store.address, size);
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
		deleteButton.setBounds(139, 633, 227, 56);
		frame.getContentPane().add(deleteButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				MaintainItem maintain = new MaintainItem();
				
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String iName =  (String) model.getValueAt(i, 0);
					String size = (String) model.getValueAt(i, 3);
					try {
						maintain.load();
						for(Item it: maintain.items){
							if (it.itemName.equals(iName) && it.itemSize.equals(size) && it.storeAddress.equals(store.address)) {
								String name = itemName.getText();
								String cate = category.getText();
								String x = mapX.getText();
								String y = mapY.getText();
								int quan = Integer.valueOf(quantity.getText());
								double pc = Double.valueOf(price.getText());
								int sizeIndex = sizeComboBox.getSelectedIndex();
								int disIndex = discountComboBox.getSelectedIndex();
								String des = description.getText();
								
								Item item = new Item(store.address, it.itemID, name, itemSize[sizeIndex], cate, quan, pc, dc[disIndex], x, y, des);
								
								Administrator.updateItem(item);
								
								model.setValueAt(name, i, 0);
								model.setValueAt(quan, i, 1);
								model.setValueAt(pc, i, 2);
								model.setValueAt(itemSize[sizeIndex], i, 3);
								model.setValueAt(cate, i, 4);
								model.setValueAt(dc[disIndex], i, 5);
								model.setValueAt(x, i, 6);
								model.setValueAt(y, i, 7);
								model.setValueAt(des, i, 8);
								
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
		btnUpdate.setBounds(521, 546, 227, 56);
		frame.getContentPane().add(btnUpdate);
		
		JButton clearButton = new JButton("CLEAR TEXT");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				itemName.setText("");
				category.setText("");
				quantity.setText("");
				price.setText("");
				sizeComboBox.setSelectedIndex(0);
				discountComboBox.setSelectedIndex(0);
				mapX.setText("");
				mapY.setText("");
				description.setText("");
			}
		});
		clearButton.setForeground(Color.WHITE);
		clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		clearButton.setBackground(new Color(0, 51, 51));
		clearButton.setBounds(521, 633, 227, 56);
		frame.getContentPane().add(clearButton);
		
		
		JLabel userNameLabel_1 = new JLabel("Item Size");
		userNameLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel_1.setBounds(64, 303, 87, 45);
		frame.getContentPane().add(userNameLabel_1);
		
		JLabel userNameLabel_1_1 = new JLabel("Discount");
		userNameLabel_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel_1_1.setBounds(508, 308, 87, 45);
		frame.getContentPane().add(userNameLabel_1_1);
		
		JLabel storeInfoLabel = new JLabel("Current Store:");
		storeInfoLabel.setForeground(new Color(153, 0, 102));
		storeInfoLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		storeInfoLabel.setBounds(273, 101, 146, 45);
		frame.getContentPane().add(storeInfoLabel);
		
		JLabel storeInfo = new JLabel("");
		storeInfo.setForeground(new Color(102, 0, 0));
		storeInfo.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		storeInfo.setBounds(453, 101, 129, 45);
		storeInfo.setText(store.address);
		frame.getContentPane().add(storeInfo);
		
		JLabel mapLabel_1_2 = new JLabel("Map Coordinate");
		mapLabel_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		mapLabel_1_2.setBounds(42, 373, 135, 45);
		frame.getContentPane().add(mapLabel_1_2);
		
		JLabel userNameLabel_1_3 = new JLabel("X :");
		userNameLabel_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel_1_3.setBounds(205, 373, 47, 45);
		frame.getContentPane().add(userNameLabel_1_3);
		
		JLabel userNameLabel_1_4 = new JLabel("Y :");
		userNameLabel_1_4.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		userNameLabel_1_4.setBounds(382, 373, 34, 45);
		frame.getContentPane().add(userNameLabel_1_4);
		
		JLabel describeLabel = new JLabel("Description");
		describeLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		describeLabel.setBounds(64, 440, 113, 45);
		frame.getContentPane().add(describeLabel);
		
		JButton btnMap = new JButton("Check Map");
		btnMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MapPage m = new MapPage(user, store.address);
				m.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMap.setForeground(Color.WHITE);
		btnMap.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnMap.setBackground(new Color(0, 51, 51));
		btnMap.setBounds(612, 90, 227, 56);
		frame.getContentPane().add(btnMap);
		
		

		
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 950, 1200);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
		
		
	}
}
