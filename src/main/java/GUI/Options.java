package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Options extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
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
	public Options() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1px:1px:300px,grow][1px:1px:300px,grow][][][][][][][][][][][][1px:1px:300px,grow][1px:1px:300px,grow]", "[][15px:15px:600px,grow][][][][][][][][][][][][][][][][][][][15px:15px:600px,grow]"));
		
		JLabel lblNewLabel = new JLabel("OPTIONS");
		contentPane.add(lblNewLabel, "cell 6 0");
		
		JLabel lblNewLabel_1 = new JLabel("Use Cookie Jar");
		contentPane.add(lblNewLabel_1, "cell 2 3");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox, "cell 2 4");
		
		JLabel lblNewLabel_2 = new JLabel("Only Run Safe URLs");
		contentPane.add(lblNewLabel_2, "cell 2 6");
		
		JLabel lblNewLabel_7 = new JLabel("Maximum Number of Threads");
		contentPane.add(lblNewLabel_7, "cell 12 6");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_1, "cell 2 7");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 12 7,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Save screencap of page");
		contentPane.add(lblNewLabel_3, "cell 2 9");
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_2, "cell 2 10");
		
		JLabel lblNewLabel_4 = new JLabel("Delete Temporary URL file");
		contentPane.add(lblNewLabel_4, "cell 2 12");
		
		JLabel lblNewLabel_8 = new JLabel("Request Rate in milliseconds");
		contentPane.add(lblNewLabel_8, "cell 12 12");
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_3, "cell 2 13");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 12 13,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		contentPane.add(lblNewLabel_5, "flowx,cell 2 15");
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_4, "cell 2 16");
		
		JLabel lblNewLabel_6 = new JLabel("Delete Temporary RESULTS file");
		contentPane.add(lblNewLabel_6, "cell 2 18");
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
		contentPane.add(chckbxNewCheckBox_5, "cell 2 19");
		
		JLabel lblNewLabel_9 = new JLabel("Delete Temporary LOG file");
		contentPane.add(lblNewLabel_9, "cell 2 15");
		
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
		contentPane.add(btnNewButton, "cell 12 19,grow");
		
		////////////////////////// GET OPTIONS
		int[] op = {};
		op = Proj.Utils.ParseOptions();
		if (op[0] != -1 ) {
			if (op[0] == 1) {
			chckbxNewCheckBox.setSelected(true);
			}
			else chckbxNewCheckBox.setSelected(false);
		
			if (op[1] == 1) {
				chckbxNewCheckBox_1.setSelected(true);
				}
				else chckbxNewCheckBox_1.setSelected(false);
			
			if (op[2] == 1) {
				chckbxNewCheckBox_2.setSelected(true);
				}
				else chckbxNewCheckBox_2.setSelected(false);
			
			if (op[3] == 1) {
				chckbxNewCheckBox_3.setSelected(true);
				}
				else chckbxNewCheckBox_3.setSelected(false);
			
			if (op[4] == 1) {
				chckbxNewCheckBox_4.setSelected(true);
				}
				else chckbxNewCheckBox_4.setSelected(false);
			
			if (op[5] == 1) {
				chckbxNewCheckBox_5.setSelected(true);
				}
				else chckbxNewCheckBox_5.setSelected(false);
			
				textField.setText(Integer.toString(op[6]));
				textField_1.setText(Integer.toString(op[7]));
		
		
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
		 				JOptionPane.showMessageDialog(contentPane,
			 				    "Write a real positive number in the boxes");
		 				
		 			}
		 			}
		 			
		 		}
		 		
		 		catch (Exception ex2)
		 		{
		 			good = 0;
		 			ex2.printStackTrace();
		 			JOptionPane.showMessageDialog(contentPane,
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

}
