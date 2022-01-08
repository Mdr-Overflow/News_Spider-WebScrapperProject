package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.DB_CONNECT;
import net.miginfocom.swing.MigLayout;

public class DB_BADURL extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	String search_ = " ";
	String order_ = "";
	String disregard = " ";
	JScrollPane pane;
	JScrollPane pane_search;
	JScrollPane pane_ordered;
	int click_number_on_order_button = 0;
	String ORDER_STATE = "DESC";
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DB_BADURL frame = new DB_BADURL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DB_BADURL() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[118px][200px:400px:1000px,grow][118px,right]", "[22px,grow][:280px:800px,grow][22px][50px,grow][][22px]"));
		
		JLabel Table_N = new JLabel("New label");
		contentPane.add(Table_N, "cell 1 0,alignx center,aligny center");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, "cell 1 1,grow");
		//panel.setLayout(null);
		
		DB.DB_BADURL bd = new DB.DB_BADURL();
		
		
		
		 pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) bd,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
		
		 
	      //pane.setBounds(0, 332, 592, -332);
	      panel.add(pane);
	      
	      try {
				bd.END_CONNECTION();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      
	      //table.addMouseListener(mous);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 3,grow");
		panel_1.setLayout(new MigLayout("", "[][][grow][grow][grow]", "[][][][][]"));
		
		
		JTextField textField_1;
		
		
		JButton btnNewButton = new JButton("Search");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton, "cell 1 1");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 2 1 3 1,growx");
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Clear Pane");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		panel_1.add(btnNewButton_2, "cell 1 2");
		
		JButton btnNewButton_1 = new JButton("Order by column selection");
		panel_1.add(btnNewButton_1, "cell 2 2 2 1");
		
		JLabel lblNewLabel = new JLabel("First click is DESCENDING , next is ASCENDIND and so forth");
		panel_1.add(lblNewLabel, "cell 2 3 2 1,alignx center,aligny center");
		
		JCheckBox Order_id = new JCheckBox("Order by id");
		Order_id.setName("Order_id");
		panel_1.add(Order_id, "flowx,cell 2 4 2 1");
		
		JCheckBox Order_time = new JCheckBox("Order by Time");
		panel_1.add(Order_time, "cell 4 4");
		Order_time.setName("Order_time");
		
		JCheckBox Search_url = new JCheckBox("Search by Url");
		panel_1.add(Search_url, "cell 3 0,alignx center,aligny center");
		Search_url.setName("Search_url");
		
		JCheckBox Order_url = new JCheckBox("Order by Url");
		panel_1.add(Order_url, "cell 3 4,alignx center,aligny center");
		Order_url.setName("Order_url");
		
		JCheckBox Search_time = new JCheckBox("Search by Time");
		Search_time.setName("Search_time");
		
		JCheckBox Search_id = new JCheckBox("Search by id");
		Search_id.setName("Search_id");
		
		panel_1.add(Search_id, "flowx,cell 2 0 2 1");
	
		panel_1.add(Search_time, "cell 4 0");
		
		
		
		// EVENTS
		
		// ALL CHECKBOXES
		
		ArrayList<JCheckBox> search_boxes = new ArrayList<JCheckBox>();
		ArrayList<JCheckBox> order_boxes = new ArrayList<JCheckBox>();
		
		 for (Component comp : panel_1.getComponents()) {

                if (comp instanceof JCheckBox) {
                	System.out.println(comp.getName());
                	
                	if(comp.getName().contains("Search"))
                	{
                		System.out.println("-----------");
                		System.out.println(comp.getName());
                		System.out.println("-----------");
                		search_boxes.add((JCheckBox) comp);
                		
                	}
                	else if (comp.getName().contains("Order"))
                	{
                		System.out.println("0000000000");
                		System.out.println(comp.getName());
                		System.out.println("0000000000");
                		order_boxes.add((JCheckBox) comp);
                		
                	}
                
                
                }
            }
		 
		 for (JCheckBox box : search_boxes) {
			 
			 
			 
			 box.addMouseListener(new MouseAdapter() {
		 			@Override
		 			public void mouseClicked(MouseEvent e) {
		 				
		 				
		 				
		 				//disable all others
		 				box.setEnabled(true);
		 				box.setSelected(true);
		 				for (JCheckBox box_other : search_boxes) {
		 					if (box_other != box)
		 					{
		 				
		 				box_other.setSelected(false);
		 				box_other.setEnabled(false);
		 					}
		 					}
		 				//Get search_
		 				search_=box.getName().substring(box.getName().lastIndexOf("_")+1);
		 				System.out.println(search_);
		 			}
		 		});
			 
			 
		 }
		 
		 
		 for (JCheckBox box : order_boxes) {
			 
			 
			 
			 box.addMouseListener(new MouseAdapter() {
		 			@Override
		 			public void mouseClicked(MouseEvent e) {
		 				
		 				
		 				
		 				//disable all others
		 				box.setEnabled(true);
		 				box.setSelected(true);
		 				for (JCheckBox box_other : order_boxes) {
		 					if (box_other != box)
		 					{
		 				
		 				box_other.setSelected(false);
		 				box_other.setEnabled(false);
		 					}
		 					}
		 				//Get search_
		 				order_=box.getName().substring(box.getName().lastIndexOf("_")+1);
		 				System.out.println(order_);
		 			}
		 		});
			 
			 
		 }
        
		 
		 
	
		//////
		
		
		
		// SEARCH
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String to_search = textField_1.getText();
				System.out.println("SEARCHING IN COLUMN:" + search_);
				
				if (to_search.length() != 0 && search_ != " " ) {
					
					
					if(search_.contains("id") ) {
						if(to_search.replaceAll("\\D", "@") == to_search) { //|\\d+\\D+|\\D+\\d+|\\D+\\d+\\D+
							
							DB.DB_BADURL bd = new DB.DB_BADURL();
							
							
				pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)bd,panel, Table_N,"search", to_search, search_, disregard, disregard);
						
						panel.removeAll();
						panel.revalidate();
						panel.repaint(getBounds());
						panel.add(pane_search);
						}
						else {
							
							JOptionPane.showMessageDialog(contentPane,
				 				    "Write a number without spaces or other characters");
			 				
							
						}
					}
					else { 
						
						System.out.println("NOT ID  ###########");
						DB.DB_BADURL bd = new DB.DB_BADURL();
						pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)bd,panel, Table_N,"search", to_search, search_, disregard, disregard);
					
					panel.removeAll();
					panel.revalidate();
					panel.repaint(getBounds());
					panel.add(pane_search);
					}
				
				
				try {
					bd.END_CONNECTION();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				else {}
				
				
				
				
			}
		});
		
		
		//ORDER
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(click_number_on_order_button < 2)
				{ ORDER_STATE = "DESC";
				click_number_on_order_button++;	
				}
				
				if(click_number_on_order_button == 2)
				{ ORDER_STATE = "ASC";
				click_number_on_order_button = 0;	
				}
				
				if (order_ != "") {
					DB.DB_BADURL bd = new DB.DB_BADURL();
					
				pane_ordered = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)bd,panel, Table_N,"order", disregard, disregard, order_ , ORDER_STATE); //[ ASC | DESC ]
				
				panel.removeAll();
				panel.revalidate();
				panel.repaint(getBounds());
				panel.add(pane_ordered);
				
				try {
					bd.END_CONNECTION();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				else {}
				
			}
		});
		
		
		
		
		
		
		
		// GO BACK TO NORMAL PANE
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint(getBounds());
				Table_N.setText("AGENTS@VIEW");
				panel.add(pane);
				
				
			}
		});
	}


}
