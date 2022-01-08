package SDA;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vizualiser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKEY;
	private JTextField textFieldVALUE;
	private JTextField textField_VALUE_SEARCH;
	Double ZOOM = 1.;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vizualiser frame = new Vizualiser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Vizualiser() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[118px][200px:400px:1000px,grow][118px,right]", "[50.00px][:280px:800px,grow][22px][50px,grow][][22px]"));
		
		
		
		
		 CobM cobM = new CobM();
		
		JLabel Table_N = new JLabel("Vizualizator");
		Table_N.setFont(new Font("Arial Black", Font.PLAIN, 16));
		Table_N.setForeground(new Color(255, 153, 0));
		
		
		
		
		
		
		contentPane.add(Table_N, "cell 1 0,alignx center,aligny center");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(null);
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
					// pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) hd,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}
				   
			};
			
			DB.DB_HEADERS hd = new DB.DB_HEADERS();
			
			
			
			// pane = TABLE_MAKER.Make_UA_TABLE((DB_CONNECT) hd,panel,Table_N,"normal",disregard,disregard,disregard,disregard);
			contentPane.addComponentListener(Listener);
			ComponentEvent e = new ComponentEvent(contentPane, 102); // INT VALUE FOR SHOWN COMP EVENT
		    Listener.componentShown(e);
		    
			
		
		
		 
	      //pane.setBounds(0, 332, 592, -332);
	     // panel.add(pane);
	      
	      try {
				hd.END_CONNECTION();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		JButton btnNewButton_3 = new JButton("ZOOM IN");
		
		contentPane.add(btnNewButton_3, "cell 0 3,alignx center,aligny center");
	      
	      //table.addMouseListener(mous);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 1 3,grow");
		panel_1.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][]"));
		JTextField textField_KEY_SEARCH;
		
		JButton btnNewButton = new JButton("Search");
		
		
		
		
		JLabel lblNewLabel = new JLabel("KEY");
		panel_1.add(lblNewLabel, "cell 2 1");
		
		JLabel lblNewLabel_1 = new JLabel("VALUE");
		panel_1.add(lblNewLabel_1, "cell 3 1");
		
		JButton btnNewButton_1 = new JButton("Insert");
		
		panel_1.add(btnNewButton_1, "cell 1 2");
		
		textFieldKEY = new JTextField();
		panel_1.add(textFieldKEY, "cell 2 2,growx");
		textFieldKEY.setColumns(10);
		
		textFieldVALUE = new JTextField();
		panel_1.add(textFieldVALUE, "cell 3 2,growx");
		textFieldVALUE.setColumns(10);
		panel_1.add(btnNewButton, "cell 1 3");
		
		textField_KEY_SEARCH = new JTextField();
		panel_1.add(textField_KEY_SEARCH, "cell 2 3,growx");
		textField_KEY_SEARCH.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Clear Pane");
	
		
		textField_VALUE_SEARCH = new JTextField();
		panel_1.add(textField_VALUE_SEARCH, "cell 3 3,growx");
		textField_VALUE_SEARCH.setColumns(10);
		
		panel_1.add(btnNewButton_2, "cell 1 4");
		
		JButton btnNewButton_4 = new JButton("ZOOM OUT");
		
		contentPane.add(btnNewButton_4, "cell 2 3,alignx center,aligny center");
		
		JButton btnNewButton_5 = new JButton("RESET ZOOM");
		
		contentPane.add(btnNewButton_5, "cell 1 4,alignx center,aligny center");
		
		
		//
		
		Map<String,Integer>map=new Map<>();
		map.add("this",1 );
		System.out.println(map.remove("this"));
		System.out.println(map.remove("this"));
		
		
		for(int i=0 ;i<=10 ; i++) {
			System.out.print(i + "    ");
			map.add("this" + Integer.toString(i),i );
		
		
	}
		//map.add("this" + Integer.toString(2),3 );
		
		
		for (HashNode node : map.getAllNodes())
			System.out.println(node);
		
		
			for (Component jc : contentPane.getComponents()) {
			
		    if ( jc instanceof JLabel) {
		    	((JLabel) jc).setFont(new Font("Arial Black", Font.PLAIN, 16));
		    	((JLabel) jc).setForeground(new Color(255, 153, 0));
		    
		    }
		    
		    if ( jc instanceof JButton) {
		    	((JButton) jc).setFont(new Font("Arial Black", Font.PLAIN, 12));
		    	((JButton) jc).setForeground(new Color(255, 153, 0));
		    
		    }
			
		    
		    if ( jc instanceof JTextField) {
		    	((JTextField) jc).setFont(new Font("Arial Black", Font.PLAIN, 12));
		    	((JTextField) jc).setForeground(new Color(255, 153, 0));
		    
		    }
			
			
			};
			
			for (Component jc : panel_1.getComponents()) {
				
			    if ( jc instanceof JLabel) {
			    	((JLabel) jc).setFont(new Font("Arial Black", Font.PLAIN, 16));
			    	((JLabel) jc).setForeground(new Color(255, 153, 0));
			    
			    }
			    
			    if ( jc instanceof JButton) {
			    	((JButton) jc).setFont(new Font("Arial Black", Font.PLAIN, 12));
			    	((JButton) jc).setForeground(new Color(255, 153, 0));
			    
			    }
				
			    
			    if ( jc instanceof JTextField) {
			    	((JTextField) jc).setFont(new Font("Arial Black", Font.PLAIN, 12));
			    	((JTextField) jc).setForeground(new Color(255, 153, 0));
			    
			    }
				
				
				};
		
		
		
		//EVENTS
		
		//SEARCH
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldKEY.getText() != "" && textFieldVALUE.getText() != "" && textFieldVALUE.getText().matches("\\d")) {
					
					String Key = textFieldKEY.getText();
					String Value = textFieldVALUE.getText();
					
					clearpanel(panel);
					cobM.StartDrw(Key,Value,panel);
					
					
				}
				else {
					UIManager.put("OptionPane.minimumSize",new Dimension(200,200)); 
					UIManager.put("OptionPane.messageFont", new Font("Arial Black", Font.BOLD, 12));
					UIManager.put("OptionPane.buttonFont", new Font("Arial Black", Font.BOLD, 12));
				
					UIManager.put("OptionPane.messageForeground",new Color(255, 153, 0));
					UIManager.put("OptionPane.messageDialogTitle","Resuts");
					
				
					
					
					JOptionPane.showMessageDialog(btnNewButton_1.getTopLevelAncestor(),"Enter an integer in the value box, anything in the key box or valid input");
					
					UIManager.put("OptionPane.minimumSize",new Dimension(50,50));
					
				}
				
				
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				clearpanel(panel);
				ZOOM = 1.;
				cobM.KILL();
				
			}
		});
		
			
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(textField_VALUE_SEARCH.getText() != "" || textField_KEY_SEARCH.getText() != "")
				{
					
					
					String value = "*";
					String key  ="*";
					
					if (textField_VALUE_SEARCH.getText() != "") value = textField_VALUE_SEARCH.getText();
					if (textField_KEY_SEARCH.getText() != "")   key = textField_KEY_SEARCH.getText() ;
					  
					
					clearpanel(panel);
					cobM.Redraw(panel);
					
					
					Graphics g = panel.getGraphics();
					System.out.println("Value:" + value + "} <------REGEX ");
					System.out.println("Key:" + key + "} <------REGEX ");
				    
				    Graphics2D g2d = (Graphics2D) g;
			        g2d.setPaintMode();
					
					for (Component jc : panel.getComponents()) {
						
					    if ( jc instanceof JLabel) {
					    	if(((JLabel) jc).getText().contains("Value: " + value) && ((JLabel) jc).getText().contains("Key: " + key) ) {
					        //System.out.println("FOUND");
					    	
					    	
					    		Rectangle r = jc.getBounds();
					    		//r = SwingUtilities.convertRectangle(jc.getParent(), r, panel.getParent());
					    		
					    		g2d.setColor(Color.RED);
					    		Stroke stroke3 = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
						 	    g2d.setStroke(stroke3);
					    		g2d.drawOval(r.x, r.y, r.width , r.height);
							    
					        
					    }
					
					
				}}}
				
			}
		});
		
		//ZOOM IN 
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ZOOM += 0.1;
				
				clearpanel(panel);
				cobM.RedrawZOOM(panel,ZOOM);
			}
		});
		
		//ZOOM OUT
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				ZOOM -= 0.1;
				
				clearpanel(panel);
				cobM.RedrawZOOM(panel,ZOOM);
			}
		});
		

		
		//RESET ZOOM
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ZOOM = 1.;
				
				clearpanel(panel);
				cobM.RedrawZOOM(panel,ZOOM);
			}
		});
}
	


public void clearpanel(JPanel p) {    
	   
		
		p.removeAll();
		p.revalidate();
		
		Graphics g = p.getGraphics();
		int W = p.getWidth();
	    int H = p.getHeight();
	    
	    Graphics2D g2d = (Graphics2D) g;
        g2d.setPaintMode();
        
	    g2d.setStroke(new BasicStroke(1));
	    g2d.setColor(Color.ORANGE);
	    
	   // AffineTransform tx1 = new AffineTransform();
	  
	    	

       	
            //tx1.scale(0.65, 0.65);
            //g2d.setTransform(tx1);
            g2d.fillRect(0,0,W,H);
	    
        
	}  



}
