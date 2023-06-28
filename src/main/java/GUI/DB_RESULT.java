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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import DB.DB_CONNECT;
import com.formdev.flatlaf.FlatLightLaf;
import com.raven.main.Main;
import net.miginfocom.swing.MigLayout;
import java.awt.Rectangle;

public class DB_RESULT extends JFrame {

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
	int height=0;
	int width=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {


			public void run() {
				try {
					DB_RESULT frame = new DB_RESULT();
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
	public DB_RESULT() {


		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				 UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 565);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(80, 22, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[80px,grow,right][800px:400px:1000px,grow][80px,grow,right]", "[22px,grow][280px:280px:800px,grow][22px][150px:50px,grow][][22px]"));
		
		JLabel Table_N = new JLabel("New label");
		contentPane.add(Table_N, "cell 1 0,alignx center,aligny center");
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(80, 22, 400, 280));
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, "cell 1 1,grow");
		//panel.setLayout(null);
		
		
	      
	      //table.addMouseListener(mous);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 3,grow");
		panel_1.setLayout(new MigLayout("", "[][][grow][grow][grow][128.00][grow]", "[][][][][]"));
		
		
		JTextField textField_1;
		
		
		JButton btnNewButton = new JButton("Search");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JCheckBox Search_id = new JCheckBox("Search by id");
		Search_id.setName("Search_id");
		
		panel_1.add(Search_id, "cell 1 0");
		
		JCheckBox Search_url = new JCheckBox("Search by Url");
		panel_1.add(Search_url, "flowx,cell 2 0");
		Search_url.setName("Search_url");
		
		JCheckBox Search_time = new JCheckBox("Search by Time");
		Search_time.setName("Search_time");
		
			panel_1.add(Search_time, "cell 3 0");
		
		JCheckBox Search_useragent = new JCheckBox("Search by UserAgent");
		panel_1.add(Search_useragent, "cell 6 0");
		panel_1.add(btnNewButton, "cell 1 1");
		Search_useragent.setName("Search_useragent");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 2 1 5 1,growx");
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Clear Pane");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		panel_1.add(btnNewButton_2, "cell 1 2");
		
		JButton btnNewButton_1 = new JButton("Order by column selection");
		panel_1.add(btnNewButton_1, "cell 2 2 4 1");
		
		JLabel lblNewLabel = new JLabel("First click is DESCENDING , next is ASCENDIND and so forth");
		panel_1.add(lblNewLabel, "cell 2 3 4 1,alignx center,aligny center");
		
		JCheckBox Order_id = new JCheckBox("Order by id");
		Order_id.setName("Order_id");
		panel_1.add(Order_id, "flowx,cell 1 4");
		
		JCheckBox Search_result = new JCheckBox("Search by Result");
		panel_1.add(Search_result, "cell 5 0");
		Search_result.setName("Search_result");
		
		JCheckBox Order_url = new JCheckBox("Order by Url");
		panel_1.add(Order_url, "cell 2 4");
		Order_url.setName("Order_url");
		
		JCheckBox Order_time = new JCheckBox("Order by Time");
		panel_1.add(Order_time, "cell 3 4");
		Order_time.setName("Order_time");
		
		JCheckBox Order_result = new JCheckBox("Order by Result");
		panel_1.add(Order_result, "cell 5 4");
		Order_result.setName("Order_result");
		
		JCheckBox Order_useragent = new JCheckBox("Order by UserAgent");
		panel_1.add(Order_useragent, "cell 6 4");
		Order_useragent.setName("Order_useragent");
		
		
		
		panel.addAncestorListener(new AncestorListener() {
	        @Override
	        public void ancestorAdded(AncestorEvent event) {
	                height = getHeight();
	                width = getWidth();
	                //Modifications to the components here.
	        }

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		
		
		DB.DB_RESULT res = new DB.DB_RESULT();
		
		
		
		 pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) res,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
		
		 
	      //pane.setBounds(0, 332, 592, -332);
	      panel.add(pane);
	      
	      try {
				res.END_CONNECTION();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	      //pack();
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
							
							DB.DB_RESULT res = new DB.DB_RESULT();
							
							
				pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)res,panel, Table_N,"search", to_search, search_, disregard, disregard);
						
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
						DB.DB_RESULT res = new DB.DB_RESULT();
						pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)res,panel, Table_N,"search", to_search, search_, disregard, disregard);
					
					panel.removeAll();
					panel.revalidate();
					panel.repaint(getBounds());
					panel.add(pane_search);
					}
				
				
				try {
					res.END_CONNECTION();
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
					DB.DB_RESULT res = new DB.DB_RESULT();
					
				pane_ordered = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)res,panel, Table_N,"order", disregard, disregard, order_ , ORDER_STATE); //[ ASC | DESC ]
				
				panel.removeAll();
				panel.revalidate();
				panel.repaint(getBounds());
				panel.add(pane_ordered);
				
				try {
					res.END_CONNECTION();
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
				Table_N.setText("RESULTS@VIEW");
				panel.add(pane);
				
				
			}
		});
	}
}
