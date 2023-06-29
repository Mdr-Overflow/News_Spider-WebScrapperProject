package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
import DB.DB_UA;
import net.miginfocom.swing.MigLayout;

public class DB_HEADERS extends JFrame {

	 JPanel contentPane;
	private JTable table;
	String search_ = " ";
	String order_ = "";
	static String disregard = " ";
	private  JScrollPane pane;
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
					DB_HEADERS frame = new DB_HEADERS();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	    
	    public void processEvent(ComponentEvent e) {
	    	super.processEvent(e);
	    	    }
	
	
	/**
	 * Create the frame.
	 */
	public DB_HEADERS() {
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
		panel.setBackground(Color.GREEN);
		contentPane.add(panel, "cell 1 1,grow");
		//panel.setLayout(null);
		//pack();
	
		
		
		 ComponentListener Listener = new ComponentListener(){

				@Override
				public void componentResized(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentShown(ComponentEvent e) {
					
					DB.DB_HEADERS hd = new DB.DB_HEADERS();
					
					
					System.out.println("FIRED EVENT >>>>>>>>>>>>>>>>>");
					 pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) hd,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}
				   
			};
			
			DB.DB_HEADERS hd = new DB.DB_HEADERS();
			
			
			
			 pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) hd,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
			contentPane.addComponentListener(Listener);
			ComponentEvent e = new ComponentEvent(contentPane, 102); // INT VALUE FOR SHOWN COMP EVENT
		    Listener.componentShown(e);
		    
			
		
		
		 
	      //pane.setBounds(0, 332, 592, -332);
	      panel.add(pane);
	      
	      try {
				hd.END_CONNECTION();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      
	      //table.addMouseListener(mous);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 3,grow");
		panel_1.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][]"));
		JTextField textField_1;
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Search by Header");
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Search by id");
		
		panel_1.add(chckbxNewCheckBox, "cell 2 0");
		
		
		
		
		panel_1.add(chckbxNewCheckBox_1, "cell 3 0");
		
		JButton btnNewButton = new JButton("Search");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton, "cell 1 1");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 2 1 2 1,growx");
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Clear Pane");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		panel_1.add(btnNewButton_2, "cell 1 2");
		
		JButton btnNewButton_1 = new JButton("Order by column selection");
		panel_1.add(btnNewButton_1, "cell 2 2");
		
		JLabel lblNewLabel = new JLabel("First click is DESCENDING , next is ASCENDIND and so forth");
		panel_1.add(lblNewLabel, "cell 2 3,alignx center,aligny center");
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Order by id");
		panel_1.add(chckbxNewCheckBox_2, "cell 2 4");
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Order by Header");
		panel_1.add(chckbxNewCheckBox_3, "cell 3 4");
		
		
		
		// EVENTS
		
		
		/// CHECKBOXES
		
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxNewCheckBox_1.setEnabled(true);
				chckbxNewCheckBox_1.setSelected(true);
				chckbxNewCheckBox.setSelected(false);
				chckbxNewCheckBox.setEnabled(false);
				search_="header";
				
			}
		});
		
		
		
		
		
		
		
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxNewCheckBox.setEnabled(true);
				chckbxNewCheckBox.setSelected(true);
				chckbxNewCheckBox_1.setSelected(false);
				chckbxNewCheckBox_1.setEnabled(false);
				search_="id";
				
				
			}
		});
		
		
		
		chckbxNewCheckBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxNewCheckBox_2.setEnabled(true);
				chckbxNewCheckBox_2.setSelected(true);
				chckbxNewCheckBox_3.setSelected(false);
				chckbxNewCheckBox_3.setEnabled(false);
				order_="id";
				
				
			}
		});
		
		
		
		
		chckbxNewCheckBox_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chckbxNewCheckBox_3.setEnabled(true);
				chckbxNewCheckBox_3.setSelected(true);
				chckbxNewCheckBox_2.setSelected(false);
				chckbxNewCheckBox_2.setEnabled(false);
				order_="header";
				
				
			}
		});
		
		
		
		
		
		
		
		
		// SEARCH
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String to_search = textField_1.getText();
				
				
				if (to_search.length() != 0 && search_ != " " ) {
					
					
					if(search_ == "id" ) {
						if(to_search.replaceAll("\\D", "@") == to_search) { //|\\d+\\D+|\\D+\\d+|\\D+\\d+\\D+
							
							DB.DB_HEADERS hd = new DB.DB_HEADERS();
							
				pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)hd,panel, Table_N,"search", to_search, search_, disregard, disregard);
						
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
						DB.DB_HEADERS hd = new DB.DB_HEADERS();
						pane_search = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)hd,panel, Table_N,"search", to_search, search_, disregard, disregard);
					
					panel.removeAll();
					panel.revalidate();
					panel.repaint(getBounds());
					panel.add(pane_search);
					}
				
				
				try {
					hd.END_CONNECTION();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				else {}
				
				
				
				
			}
		});
		
		
		
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
					DB.DB_HEADERS hd = new DB.DB_HEADERS();
				pane_ordered = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT)hd,panel, Table_N,"order", disregard, disregard, order_ , ORDER_STATE); //[ ASC | DESC ]
				
				panel.removeAll();
				panel.revalidate();
				panel.repaint(getBounds());
				panel.add(pane_ordered);
				
				try {
					hd.END_CONNECTION();
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
				Table_N.setText("HEADERS@VIEW");
				panel.add(pane);
				
				
			}
		});
	}

	
	

}
