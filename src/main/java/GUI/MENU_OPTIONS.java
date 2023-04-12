package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import DB.Create_Tables;
import DB.DB_UA;
import Proj.Headers;
import net.miginfocom.swing.MigLayout;

public class MENU_OPTIONS {

	static String resolution_ = "" ;

	public static void Create_Options_Meniu(JPanel current_panel) {
	
	current_panel.setLayout(new MigLayout("", "[1px:1px:300px,grow][1px:1px:300px,grow][][][][][][][][][][][][1px:1px:300px,grow][1px:1px:300px,grow]", "[][15px:15px:600px,grow][][][][][][][][][][][][][][][][][][][15px:15px:600px,grow]"));
	
	JLabel lblNewLabel = new JLabel("OPTIONS");
	current_panel.add(lblNewLabel, "cell 6 0");
	
	JLabel lblNewLabel_1 = new JLabel("Run Headless");
	current_panel.add(lblNewLabel_1, "cell 2 3");
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox, "cell 2 4");
	
	JLabel lblNewLabel_2 = new JLabel("Only Run Safe URLs");
	current_panel.add(lblNewLabel_2, "cell 2 6");
	
	JLabel lblNewLabel_7 = new JLabel("Maximum Number of Threads");
	current_panel.add(lblNewLabel_7, "cell 12 6");
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox_1, "cell 2 7");

	 JTextField textField = new JTextField();
	current_panel.add(textField, "cell 12 7,growx");
	textField.setColumns(10);
	
	JLabel lblNewLabel_3 = new JLabel("Save screencap of page");
	current_panel.add(lblNewLabel_3, "cell 2 9");
	
	JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox_2, "cell 2 10");
	
	JLabel lblNewLabel_4 = new JLabel("Delete Temporary URL file");
	current_panel.add(lblNewLabel_4, "cell 2 12");
	
	JLabel lblNewLabel_8 = new JLabel("Request Rate in milliseconds");
	current_panel.add(lblNewLabel_8, "cell 12 12");
	
	JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox_3, "cell 2 13");
	
	JTextField textField_1 = new JTextField();
	current_panel.add(textField_1, "cell 12 13,growx");
	textField_1.setColumns(10);
	
	JLabel lblNewLabel_5 = new JLabel("");
	current_panel.add(lblNewLabel_5, "flowx,cell 2 15");
	
	JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox_4, "cell 2 16");
	
	JLabel lblNewLabel_6 = new JLabel("Delete Temporary RESULTS file");
	current_panel.add(lblNewLabel_6, "cell 2 18");
	
	JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
	current_panel.add(chckbxNewCheckBox_5, "cell 2 19");
	
	JLabel lblNewLabel_9 = new JLabel("Delete Temporary LOG file");
	current_panel.add(lblNewLabel_9, "cell 2 15");
	
	lblNewLabel.setForeground(new Color(255, 153, 51));
	lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_1.setForeground(new Color(255, 153, 51));
	lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_2.setForeground(new Color(255, 153, 51));
	lblNewLabel_2.setFont(new Font("Arial Narrow", Font.BOLD, 16));

	lblNewLabel_3.setForeground(new Color(255, 153, 51));
	lblNewLabel_3.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_4.setForeground(new Color(255, 153, 51));
	lblNewLabel_4.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_6.setForeground(new Color(255, 153, 51));
	lblNewLabel_6.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_7.setForeground(new Color(255, 153, 51));
	lblNewLabel_7.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_8.setForeground(new Color(255, 153, 51));
	lblNewLabel_8.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	lblNewLabel_9.setForeground(new Color(255, 153, 51));
	lblNewLabel_9.setFont(new Font("Arial Narrow", Font.BOLD, 16));
	
	textField.setFont(new Font("Arial", Font.BOLD, 16));
	textField.setForeground(new Color(204, 51, 0));
	
	textField_1.setFont(new Font("Arial", Font.BOLD, 16));
	textField_1.setForeground(new Color(204, 51, 0));
	
	JButton btnNewButton = new JButton("Save Settings");
	current_panel.add(btnNewButton, "cell 12 19,grow");
	
	////////////////////////// GET OPTIONS
	Object[] op = {};
	op = Proj.Utils.ParseOptions();
	if ((int) op[0] != -1 ) {




		if ((int) op[0] == 1) {
		chckbxNewCheckBox.setSelected(true);
		}
		else chckbxNewCheckBox.setSelected(false);
	
		if ((int) op[1] == 1) {
			chckbxNewCheckBox_1.setSelected(true);
			}
			else chckbxNewCheckBox_1.setSelected(false);
		
		if ((int) op[2] == 1) {
			chckbxNewCheckBox_2.setSelected(true);
			}
			else chckbxNewCheckBox_2.setSelected(false);
		
		if ((int) op[3] == 1) {
			chckbxNewCheckBox_3.setSelected(true);
			}
			else chckbxNewCheckBox_3.setSelected(false);
		
		if ((int) op[4] == 1) {
			chckbxNewCheckBox_4.setSelected(true);
			}
			else chckbxNewCheckBox_4.setSelected(false);
		
		if ((int) op[5] == 1) {
			chckbxNewCheckBox_5.setSelected(true);
			}
			else chckbxNewCheckBox_5.setSelected(false);
		
			textField.setText(Integer.toString((int) op[6]));
			textField_1.setText(Integer.toString((int) op[7]));

			resolution_ = (String) op[8];

	}
	else System.out.println("FATAL ERROR");
	//////////////////////////GET OPTIONS
	
	
	
	 btnNewButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			 btnNewButton.setForeground(Color.GREEN);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			 btnNewButton.setForeground(new Color(255, 153, 51));
		}
	 	@Override
	 	public void mouseClicked(MouseEvent e) {
	 		int good = 1;
	 		String options ="Options: ";
	 		
	 		if (chckbxNewCheckBox.isSelected())     //COOKIE JAR
	 			options += "COOKIE JAR: YES,";
	 		else 
	 			options += "COOKIE JAR: NO,";
	 			
	 		if (chckbxNewCheckBox_1.isSelected())     //SAFE URLS
	 			options += "SAFE URLS: YES,";
	 		else 
	 			options += "SAFE URLS: NO,";
	 		
	 		if (chckbxNewCheckBox_2.isSelected())     //SCREENCAP
	 			options += "SCREENCAP: YES,";
	 		else 
	 			options += "SCREENCAP: NO,";
	 		
	 		if (chckbxNewCheckBox_3.isSelected())     //DEL URL FILE
	 			options += "DEL URL FILE: YES,";
	 		else 
	 			options += "DEL URL FILE: NO,";
	 		
	 		if (chckbxNewCheckBox_4.isSelected())     //DEL LOG FILE
	 			options += "DEL LOG FILE: YES,";
	 		else 
	 			options += "DEL LOG FILE: NO,";
	 		
	 		if (chckbxNewCheckBox_5.isSelected())     //DEL RESULTS FILE
	 			options += "DEL RESULTS FILE: YES,";
	 		else 
	 			options += "DEL RESULTS FILE: NO,";
	 		
	 		try {
	 			if (textField.getText().length() != 0 || textField_1.getText().length() != 0) {
	 		int res1 = Integer.parseInt(textField.getText());
	 		int res2 = Integer.parseInt(textField_1.getText()) ;
	 			
	 			if (res1 < 0 || res2 < 0) {
	 				good =0;
	 				JOptionPane.showMessageDialog(current_panel,
		 				    "Write a real positive number in the boxes");
	 				
	 			}
	 			}
	 			
	 		}
	 		
	 		catch (Exception ex2)
	 		{
	 			good = 0;
	 			ex2.printStackTrace();
	 			JOptionPane.showMessageDialog(current_panel,
	 				    "Write a real positive number in the boxes");

	 		}
	 		
	 		if (good == 1) {
	 		
	 		if (textField.getText().length() != 0) 
	 		options += textField.getText() + ",";
	 		else
	 		options += Proj.Utils.DEFAULTmaxthreads + ",";
	 			 
	 		
	 		
	 		if (textField_1.getText().length() != 0) 
		 		options += textField_1.getText();
	 		else	
		 	    options += Proj.Utils.DEFAULTrequestrate;
		 			
		 		
	 		
	 		
	 		
	 		Proj.Utils.WriteToFile("Options.txt", options);
	 		
	 		}
	 		
	 	}
	});
	 btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
	 btnNewButton.setForeground(new Color(255, 153, 0));
	
}
	
	
	public static void Create_Urls_Meniu(JPanel current_panel) {
		
		
		current_panel.setLayout(new MigLayout("", "[20px:20px:600px,grow 10,right][80px:80px:1200px,grow 40,center][20px:20px:600px,grow 10,left]", "[][20px:20px:40px,grow 10][][][grow][][][20px:20px:30px,grow 10][20px:20px:40px,grow 10][20px:20px:40px,grow 10][10px:10px:300px,grow 10]"));
		
		JLabel lblNewLabel = new JLabel("URL LIST");
		current_panel.add(lblNewLabel, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("URLs in list");
		current_panel.add(lblNewLabel_1, "cell 1 3");
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
		textArea.setBackground(new Color(255, 153, 0));
		textArea.setForeground(Color.WHITE);
		current_panel.add(textArea, "cell 1 4,grow");
		//textArea.setEditable(false);
		
		
		JLabel lblNewLabel_2 = new JLabel("Insert or Delete Valid URL");
		current_panel.add(lblNewLabel_2, "cell 1 6");
		
		JTextField textField = new JTextField();
		current_panel.add(textField, "cell 1 7,grow");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		current_panel.add(btnNewButton, "cell 1 8,grow");
		
		lblNewLabel.setForeground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		lblNewLabel_2.setForeground(new Color(255, 153, 51));
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		 btnNewButton.setForeground(new Color(255, 153, 0));
		 
		 
		 
		 // GET URLS
		 
		 //Proj.Utils.WriteToFile("Urls.txt",textField.getText());
			ArrayList <String> URLS = new ArrayList <String>();
			
			if ( Proj.Utils.ReadFromURLs() != null) {
				System.out.println("ENTERED NON-NULL STATEMENT");
				URLS.addAll(Proj.Utils.ReadFromURLs());
				for (String url : URLS) {
					System.out.println(url);
					textArea.append(url+ "\n");
				}
			}
		 
		 
			
		 
		 //
		 
		 textField.setFont(new Font("Arial", Font.BOLD, 16));
			textField.setForeground(new Color(204, 51, 0));
			
			JButton btnDelete = new JButton("DELETE");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnDelete.setForeground(new Color(255, 153, 0));
			btnDelete.setFont(new Font("Arial Black", Font.PLAIN, 12));
			current_panel.add(btnDelete, "cell 1 9,grow");
		 
			//DEL URL
			btnDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnDelete.setForeground(Color.GREEN);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnDelete.setForeground(new Color(255, 153, 51));
				}
			
				@Override
				public void mouseClicked(MouseEvent e) {
					int to_del = 0;
					if(textField.getText().contains("https://") && !textField.getText().contains(" ") && !textField.getText().contains("\n")) {
						
						String[] Conts;
						Conts = textArea.getText().split("\n");
						ArrayList <String> content = new ArrayList();
						System.out.println("FIRST STEP OF DEL");
						System.out.println(textField.getText());
						for (String cont : Conts) {
							System.out.println(cont);
							if( textField.getText().contains(cont)) {
							to_del = 1;
							}
							else {
								content.add(cont);
							}
						}
					
						if(to_del == 1) {
							Proj.Utils.WriteToFileNOAPPEND("Urls.txt","IGNORE");
							System.out.println("LAST STEP OF DEL");
							textArea.setText("");
							for (String  to_append : content) {
							System.out.println(to_append);
							textArea.append(to_append+"\n");
							Proj.Utils.WriteToFile("Urls.txt",to_append);
							}
							
						}
						
						else {
							JOptionPane.showMessageDialog(current_panel,
				 				    "Write a valid url to delete in the textbox");
						}
					
					}
							//content.add(cont);
					
					else {
			 			JOptionPane.showMessageDialog(current_panel,
			 				    "Write a valid url to delete in the textbox");
			 		}
				}
			});
			
			
			
			// ADD URL
			
		 btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					 btnNewButton.setForeground(Color.GREEN);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					 btnNewButton.setForeground(new Color(255, 153, 51));
				}
		 
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		
		 		if(textField.getText().contains("https://") && !textField.getText().contains(" ") && !textField.getText().contains("\n"))
		 		{
		 			
		 			textArea.setText("");
		 			Proj.Utils.WriteToFile("Urls.txt",textField.getText());
		 			ArrayList <String> URLS = new ArrayList <String>();
		 			
		 			if ( Proj.Utils.ReadFromURLs() != null) {
		 				System.out.println("ENTERED NON-NULL STATEMENT");
						URLS.addAll(Proj.Utils.ReadFromURLs());
						for (String url : URLS) {
							System.out.println(url);
							textArea.append(url + "\n");
						}
		 			
		 		}
		 		}
		 		else {
		 			JOptionPane.showMessageDialog(current_panel,
		 				    "Write a valid url in the textbox");
		 		}
		 	}
		 });
		 
		 
		 
		 
	}
		
		//END OF URLs Meniu
	
	
	public static void Remake_MainWindow(JPanel current_panel) {
		

		current_panel.setLocation(new Point(1, 1));
		current_panel.setBounds(new Rectangle(2, 2, 2, 2));
		current_panel.setBackground(Color.ORANGE);
		current_panel.setLayout(new MigLayout("", "[80px:80px:300px,grow 50,left][100px:457px:1400px,grow,center][80px:80px:300px,grow 50,right]", "[30px:30px:30px,top][100px:264px:800px,grow,center][30px:n:50px,grow 10][100px:80px:200px,grow 50,fill][]"));
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(100, 100));
		panel_1.setPreferredSize(new Dimension(1, 1));
		panel_1.setMaximumSize(new Dimension(32000, 32000));
		
		
		
		
		
		panel_1.addComponentListener(MainW.resizeListener);                     //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   IMPORTANT
		
		
		
		
		
		
		panel_1.setBounds(new Rectangle(2, 2, 2, 2));
		panel_1.setBackground(new Color(255, 255, 255));
		current_panel.add(panel_1, "cell 1 1,grow");
		panel_1.setLayout(null);
		
		
		
		
		Panel panel_2 = new Panel();
		panel_2.setMinimumSize(new Dimension(100, 100));
		panel_2.setMaximumSize(new Dimension(4000, 2000));
		panel_2.setBackground(new Color(255, 255, 255));
		current_panel.add(panel_2, "cell 1 3,alignx center,aligny center");
		panel_2.setLayout(new MigLayout("", "[100px:100px:100px,grow 10,left][100px:250px:250px,grow 50,fill][100px:100px:100px,grow 10,right]", "[22px:22px:66px,grow][30px:50px:50px,grow 10][30px:50px:50px,grow]"));
		
		JLabel label = new JLabel("Enter Words to Search");
		//Dimension rv;
		System.out.println(label.getWidth());
		label.setFont(new Font("Arial Black", Font.PLAIN, 16));
		
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(Label.CENTER);
			panel_2.add(label, "cell 1 0,growx,aligny center");
			label.setForeground(new Color(255, 153, 0));
			label.setName("SLabIGN");
			//textField = new JTextField();
			JTextField KeyWord_TextBox = new JTextField();
			KeyWord_TextBox.setMinimumSize(new Dimension(20, 20));
			KeyWord_TextBox.setFont(new Font("Arial", Font.BOLD, 16));
			KeyWord_TextBox.setForeground(new Color(204, 51, 0));
			panel_2.add(KeyWord_TextBox, "cell 1 1,grow");
			KeyWord_TextBox.setBounds(new Rectangle(1, 0, 0, 0));
			
			
			JButton button = new JButton("START");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setForeground(Color.GREEN);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					button.setForeground(new Color(255, 153, 51));
				}
			});
			button.setFont(new Font("Arial Black", Font.PLAIN, 12));
			button.setForeground(new Color(255, 153, 0));
			panel_2.add(button, "cell 1 2,grow");
			
			
	//////// RUN 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
				///  ESTABLISH CONN TO THE DATABASE AND CREATE TABLES IN MEM IF THEY DON'T EXIST ALREADY
				try {
					Create_Tables ct = new Create_Tables();
					
					
					// CREATE DEFAULTS FOR USERAGENTS AND HEADERS
					DB_UA ua_db = new DB_UA();
					DB.DB_HEADERS hd_db = new DB.DB_HEADERS();
					
					ua_db.Populate_UA_DEFAULT();
					hd_db.Populate_HEADERS_DEFAULT();
				
					
					 
					
					hd_db.Populate_HEADERS_DEFAULT();
					// CREATE AGENTS DB
					
					ua_db.END_CONNECTION();
					hd_db.END_CONNECTION();
					
					Headers h = new Headers();
					
					DB.DB_AGENTS ag_db = new DB.DB_AGENTS();
					ag_db.del_AGENTS();
					ag_db.Create_AGENTS(10);
					MainW.Agents = new ArrayList<HashMap<String,String>>(h.Generate_Agents(ag_db));
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(current_panel,
		 				    "COULD NOT ESTABLISH CONNECTION TO DATABASE OR NOT ENOGTH MEMORY FOR TABLES");
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							System.exit(-1);
				}
				
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			button.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				public void  actionPerformed(ActionEvent e) {
		//////// DEFAULTS 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					String[] Url_list_Test = {"https://www.theverge.com", "https://www.libertatea.ro/", "https://www.pcgarage.ro/",
	                     "https://www.theguardian.com/", "https://www.emag.ro/", "https://www.theguardian.com/",
	                     "https://www.dailymail.co.uk/", "https://www.reuters.com/","https://time.com/",
	                     "https://www.bloomberg.com/europe","https://www.digi24.ro/","https://www.heraldscotland.com/", "https://example.com"};
					
					button.setEnabled(false);
	   ArrayList<Future<Runnable>> futures =  new ArrayList<Future<Runnable>>();
	   String params = "All";
	   String KeyWord = KeyWord_TextBox.getText();
	    MainW.rate = -1;
	    MainW.threads = -1;
	   //String keyword = "tornado";
	   
	   ////////DEFAULTS 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
	   
	   
	    ArrayList <String> URLS = new ArrayList <String>();
		   
	   MainW.Real_URLS = Url_list_Test;
	   int i = 0 ;
		if ( Proj.Utils.ReadFromURLs() != null) {
			
			
			
			
			URLS.addAll(Proj.Utils.ReadFromURLs());
			MainW.Real_URLS = new String[URLS.size()];
			System.out.println("ENTERED URL GETTER");
			System.out.println(URLS.size());
			for (String url : URLS) {
				System.out.println("url of URLS");
				System.out.println(url);
				MainW.Real_URLS[i] = url; 
				i++;
						//textArea.append(url+ "\n");
			}
		}
	    
	   ////// GET OPTIONS
		
		Object[] op = {};
		op = Proj.Utils.ParseOptions();
		if ((int) op[0] != -1 ) {
			if ((int) op[0] == 1) {
			// Headless
				MainW.options[0] = "yes";
				
			}
			else MainW.options[0] = "no";
		
			if ((int) op[1] == 1) {
				// Safe
				
				MainW.options[1] = "yes";
				
				}
			else MainW.options[1] = "no";//
			
			if ((int) op[2] == 1) {

				MainW.options[2] = "yes";
				
				}
			else MainW.options[2] = "no";
			
			if ((int) op[3] == 1) {

				MainW.options[3] = "yes";
				
				}
			else MainW.options[3] = "no";
			
			if ((int) op[4] == 1) {

				MainW.options[4] = "yes";
				
				}
			else MainW.options[4] = "no";
			
			if ((int) op[5] == 1) {

				MainW.options[5] = "yes";
				
				}
			else MainW.options[5] = "no";
			
				MainW.rate = (int) op[7];
				MainW.threads = (int) op[6];
			
		
		}
		if (MainW.rate == -1 || MainW.threads == -1)
		{
			System.out.println("FAILED GETTING OPTIONS");
			MainW.rate = 200;
			MainW.threads = 12;
		}
	   //////
		System.out.println(MainW.rate);
		System.out.println(MainW.threads);
		MainW.clearpanel(panel_1);
		MainW.init_progressbar(panel_1);
		
		
		
		SwingWorker<Boolean, Integer> bar_worker = new SwingWorker<Boolean, Integer>() {
			@Override
			 protected Boolean doInBackground() throws Exception {
				futures.add((Future<Runnable>) MainW.fill())  ;{ return true; }
			   }
			 
			 
			 protected void done() {
				    
				    boolean status;
				  
				    try {
				   
				     status = get();
				
				     MainW.progress = 0;
				    
				    } catch (InterruptedException e) {
				    	
				     // This is thrown if the thread's interrupted.
				    } catch (ExecutionException e) {
				     
				    }
				   }
				   
			  
			 
	   	};
	
	   	SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
			   @Override
			   protected Boolean doInBackground() throws Exception {
				  futures.add((Future<Runnable>) Proj.driver2.RunInGui(params, KeyWord, MainW.Real_URLS, MainW.rate, MainW.threads,
						  MainW.Agents, MainW.options));{ return true; }
			   }
			
			   protected void done() {
				    
				    boolean status;
				   
				    try {
				   
				     status = get();
				     button.setEnabled(true);
				     MainW.progress = 0;
				     //kill_progressbar(b);
				     
				     MainW.clearpanel(panel_1);
					 MainW.paintComponents1(panel_1,MainW.Real_URLS,KeyWord);
					 futures.clear();
					 if (!bar_worker.isDone()) {
					 bar_worker.cancel(status);}
				    } catch (InterruptedException e) {
				    	
				 
				    } catch (ExecutionException e) {
				     
				    }
				   }
				   
	   	};
	   		bar_worker.execute();
			  worker.execute();
			 }
				
				
				
			});
		
	
		
		                                                                                 //     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
	

	
	/*
	 
	 
	  
	  
	  
	 */

	
	}
}

