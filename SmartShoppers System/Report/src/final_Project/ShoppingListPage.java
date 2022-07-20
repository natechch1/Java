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
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShoppingListPage extends JFrame{
	
	public JFrame frame;
	private Image backIcon = new ImageIcon(LoginPage.class.getResource("/res/background.jpg")).getImage().getScaledInstance(1200, 1200, Image.SCALE_SMOOTH);
	private JTextField searchItem;
	private int fx;
	private int fy;
	private JTable itemTable;
	DefaultTableModel model;
	DefaultTableModel modelList;
	public static Store store;
	static User user;
	private JTextField quantity;
	private JTable shoppingListTable;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingListPage window = new ShoppingListPage(store, user);
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
	public ShoppingListPage(Store store, User user) {
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
		frame.setBounds(100, 100, 1200, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel exit = new JLabel("x");
		exit.setBounds(1115, 29, 34, 58);
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
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setBounds(393, 181, 129, 45);
		searchLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 24));
		frame.getContentPane().add(searchLabel);
		
		JLabel test = new JLabel("");
		test.setForeground(new Color(153, 0, 102));
		test.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		test.setBounds(844, 582, 160, 45);
		frame.getContentPane().add(test);
		
		String[] itemSize = {"Small", "Medium", "Large"};
		
		String[] dc = {"Yes", "No"};

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 284, 1133, 255);
		frame.getContentPane().add(scrollPane);
		
		itemTable = new JTable();
		itemTable.setBackground(new Color(204, 255, 255));
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
		
		itemTable.setModel(model);
		scrollPane.setViewportView(itemTable);
		
		
		searchItem = new JTextField();
		searchItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model = (DefaultTableModel) itemTable.getModel();
				String search = searchItem.getText().trim();
				TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
				itemTable.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(search));
				
			}
		});
		searchItem.setBounds(540, 181, 255, 37);
		searchItem.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		frame.getContentPane().add(searchItem);
		searchItem.setColumns(10);
		
		
		JLabel goBack = new JLabel("Back");
		goBack.setBounds(32, 41, 75, 58);
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				customerPage c = new customerPage(user);
				c.frame.setVisible(true);
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
		
	
		JLabel storeInfoLabel = new JLabel("Current Store:");
		storeInfoLabel.setForeground(new Color(153, 0, 102));
		storeInfoLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		storeInfoLabel.setBounds(393, 114, 160, 45);
		frame.getContentPane().add(storeInfoLabel);
		
		JLabel storeInfo = new JLabel("");
		storeInfo.setForeground(new Color(102, 0, 0));
		storeInfo.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		storeInfo.setBounds(595, 114, 298, 45);
		storeInfo.setText(store.address);
		frame.getContentPane().add(storeInfo);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setForeground(new Color(51, 51, 0));
		lblQuantity.setFont(new Font("Sitka Subheading", Font.BOLD, 24));
		lblQuantity.setBounds(102, 1127, 129, 45);
		frame.getContentPane().add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		quantity.setColumns(10);
		quantity.setBounds(264, 1119, 116, 57);
		frame.getContentPane().add(quantity);
		
		JLabel totalPrice = new JLabel("");
		totalPrice.setForeground(new Color(153, 0, 102));
		totalPrice.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		totalPrice.setBounds(370, 1050, 160, 45);
		try {
			totalPrice.setText(String.valueOf(df.format(Customer.getTotalPrice(user.name, store.address))));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame.getContentPane().add(totalPrice);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 672, 1131, 353);
		frame.getContentPane().add(scrollPane_1);
		
		shoppingListTable = new JTable();
		shoppingListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = shoppingListTable.getSelectedRow();
				if(i >= 0) 
					quantity.setText(String.valueOf(modelList.getValueAt(i, 1)) );
			}
		});
		scrollPane_1.setViewportView(shoppingListTable);
		shoppingListTable.setBackground(new Color(204, 255, 255));
		
		modelList = new DefaultTableModel();
		Object[] col = {"Item Name", "Quantity", "Price", "Size", "Coordinate X", "Coordinate Y"};
		Object[] r = new Object[6];
		modelList.setColumnIdentifiers(col);
		//show previous manager info
		MaintainShoppingList maintain1 = new MaintainShoppingList();
		
		try {
			maintain1.load();
			for(ShoppingList i: maintain1.shoppingLists){
				if (i.storeAddress.equals(store.address) && i.customerName.equals(user.name)) {
					r[0] = i.itemName;
					r[1] = i.quantity;
					r[2] = i.price;
					r[3] = i.size;
					r[4] = i.mapX;
					r[5] = i.mapY;
					modelList.addRow(r);
				}
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		shoppingListTable.setModel(modelList);
		

		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(464, 559, 227, 56);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = itemTable.getSelectedRow();
				MaintainItem maintain = new MaintainItem();
			
				
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String name =  (String) model.getValueAt(i, 0);
					String size = (String) model.getValueAt(i, 3);
					
					try {
						maintain.load();
						for(Item it : maintain.items){
							if (it.itemName.equals(name) && it.itemSize.equals(size) && it.storeAddress.equals(store.address) ) {
								ShoppingList sl = new ShoppingList(user.name, store.address, name, 1, it.price , size, it.mapX, it.mapY);
								if (Customer.checkIsNewList(sl)) {
									Customer.addShoppingList(sl);
									
									r[0] = it.itemName;
									r[1] = 1;
									r[2] = it.price;
									r[3] = it.itemSize;
									r[4] = it.mapX;
									r[5] = it.mapY;
									modelList.addRow(r);
									JOptionPane.showMessageDialog(null, "Add Success!");
									totalPrice.setText(String.valueOf(df.format(Customer.getTotalPrice(user.name, store.address))));
								}
								else {
									JOptionPane.showMessageDialog(null, "Already Add This Item!");
								}
								
							}
						}
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAdd.setBackground(new Color(0, 51, 51));
		frame.getContentPane().add(btnAdd);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = shoppingListTable.getSelectedRow();
			
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String itemName =  (String) modelList.getValueAt(i, 0);
					String size = (String) modelList.getValueAt(i, 3);
					
					try {
						
						Customer.deleteShoppingList(itemName, size, store.address, user.name);
						System.out.println("  Count: " + shoppingListTable.getRowCount());
						System.out.println("  Now at index : " + i);
						modelList.removeRow(i);
						
						totalPrice.setText(String.valueOf(df.format(Customer.getTotalPrice(user.name, store.address))));
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
		deleteButton.setBounds(761, 1119, 227, 56);
		frame.getContentPane().add(deleteButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = shoppingListTable.getSelectedRow();
				MaintainItem maintain = new MaintainItem();
				int quan = Integer.valueOf(quantity.getText()) ;
				
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row first!");
				}
				else {
					
					String name =  (String) modelList.getValueAt(i, 0);
					String size = (String) modelList.getValueAt(i, 3);

					try {
						maintain.load();
						for(Item it : maintain.items){
							if (it.itemName.equals(name) && it.itemSize.equals(size) && it.storeAddress.equals(store.address)) {
								int itemQuantity = Integer.valueOf(it.quantity);
								
								if (quan <= itemQuantity) {
									ShoppingList sl = new ShoppingList(user.name, store.address, it.itemName, quan, it.price , it.itemSize, it.mapX, it.mapY);
									Customer.updateQuantity(sl);;
									modelList.setValueAt(quan, i, 1);
									JOptionPane.showMessageDialog(null, "Update Success!");
									totalPrice.setText(String.valueOf(df.format(Customer.getTotalPrice(user.name, store.address))));
								}
								else {
									JOptionPane.showMessageDialog(null, "The quantity required is more than what the supermarket has.!");
								}
								
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
		btnUpdate.setBounds(418, 1119, 227, 56);
		frame.getContentPane().add(btnUpdate);
		
		
		JLabel lblShoppingList = new JLabel("Shopping List : ");
		lblShoppingList.setForeground(new Color(153, 0, 102));
		lblShoppingList.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblShoppingList.setBounds(32, 626, 160, 45);
		frame.getContentPane().add(lblShoppingList);
		
		JLabel lblItem = new JLabel("Item :");
		lblItem.setForeground(new Color(153, 0, 102));
		lblItem.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblItem.setBounds(32, 229, 83, 45);
		frame.getContentPane().add(lblItem);
		
		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setForeground(new Color(153, 0, 102));
		lblTotalPrice.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblTotalPrice.setBounds(159, 1050, 160, 45);
		
		frame.getContentPane().add(lblTotalPrice);
		
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
		btnMap.setBounds(785, 103, 227, 56);
		frame.getContentPane().add(btnMap);
		
	
		
		
		
		

		
		
		JLabel backLabel = new JLabel("");
		backLabel.setBounds(0, 0, 1200, 1200);
		backLabel.setIcon(new ImageIcon(backIcon));
		frame.getContentPane().add(backLabel);
		
	}
}
