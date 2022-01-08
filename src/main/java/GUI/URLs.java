package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class URLs extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URLs frame = new URLs();
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
	public URLs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[20px:20px:600px,grow 10,right][80px:80px:1200px,grow 40,center][20px:20px:600px,grow 10,left]", "[][20px:20px:40px,grow 10][][][grow][][][20px:20px:30px,grow 10][20px:20px:40px,grow 10][20px:20px:40px,grow 10][10px:10px:300px,grow 10]"));
		
		JLabel lblNewLabel = new JLabel("URL LIST");
		contentPane.add(lblNewLabel, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("URLs in list");
		contentPane.add(lblNewLabel_1, "cell 1 3");
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
		textArea.setBackground(new Color(255, 153, 0));
		textArea.setForeground(Color.WHITE);
		contentPane.add(textArea, "cell 1 4,grow");
		//textArea.setEditable(false);
		
		
		JLabel lblNewLabel_2 = new JLabel("Insert or Delete Valid URL");
		contentPane.add(lblNewLabel_2, "cell 1 6");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 7,grow");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		contentPane.add(btnNewButton, "cell 1 8,grow");
		
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
			contentPane.add(btnDelete, "cell 1 9,grow");
		 
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
							JOptionPane.showMessageDialog(contentPane,
				 				    "Write a valid url to delete in the textbox");
						}
					
					}
							//content.add(cont);
					
					else {
			 			JOptionPane.showMessageDialog(contentPane,
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
		 			JOptionPane.showMessageDialog(contentPane,
		 				    "Write a valid url in the textbox");
		 		}
		 	}
		 });
		 
		 
		 
		 
	}

}
