package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Proj.Utils;
import net.miginfocom.swing.MigLayout;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.stream.Collectors;

public class URLs extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private  JList	taskList; // TASK LIST S

	private final int nr_rows = 11;
	private final int nr_cols = 5;

	private static  Map<String, String[]> dropdownOptions;



	private ColorPanel[] colorPanels;

	private static class ColorPanel extends JPanel {

		public ColorPanel() {
			setBackground(Color.ORANGE);
			setBorder(new EmptyBorder(0, 5, 0, 10));
			setLayout(new MigLayout("hidemode 3",
					// columns
					"[fill]" +
							"[fill]",
					// rows
					"[fill]" +
							"[fill]" +
							"[fill]"));
		}

	}

	public void initOptionList(){

		dropdownOptions = Collections.synchronizedMap(new LinkedHashMap<String, String[]>());
		dropdownOptions.put("Page", new String[]{"Normal", "Infinite"});
		dropdownOptions.put("Domain", new String[]{"Single Page", "Full Domain"});
		dropdownOptions.put("Number", new String[]{"Singular", "Multiple"});
		dropdownOptions.put("Account", new String[]{"None", "Yes"});
		dropdownOptions.put("Rating", new String[]{"None", "Safe", "Average", "High Risk", "BAD"});
		System.out.println(dropdownOptions);

	}


	public void getTaskInfo(String s) {
		// Label names
		String[] labelNames = {"Name", "Page", "Domain", "Number", "Account", "Rating", "Urls"};

		// Retrieve attribute values from the Hashtable
		Hashtable<String, String[]> tasks = Utils.JsonToHashtableUtility.ReadTasks();
		String[] attributeValues = tasks.get(s);
		String[] urls = tasks.get(s + "_Urls");


		if (attributeValues != null && attributeValues.length == labelNames.length) {
			// Dropdown options
			String[] dropdownOptions = {"Option 1", "Option 2", "Option 3"}; //change this shit

			Component[] components = contentPane.getComponents();

			for (int i = 0; i < components.length; i++) {
				Component component = components[i];



				// Update text Area

				if (component instanceof JTextArea textArea) {
						for (String url : urls) {
							textArea.setText(url);
						}
				}



				// Update dropdowns
				if (component instanceof JComboBox) {
					JComboBox<String> comboBox = (JComboBox<String>) component;
					int dropdownIndex = i - labelNames.length - 1;
					if (dropdownIndex >= 0 && dropdownIndex < dropdownOptions.length) {
						comboBox.setSelectedItem(attributeValues[i]);
					}
				}
			}



//
		} else {
			System.out.println("Invalid attribute values for task: " + s);
		}
	}


	private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
		//set text on right here
		String s = (String) taskList.getSelectedValue();
		getTaskInfo(s);
	}

	private static class NonEditableTableModel extends DefaultTableModel {

		NonEditableTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}
		@Override
		public boolean isCellEditable(int row, int column) {
			return false; // Make all cells non-editable
		}
	}

	private static class CustomListModel extends AbstractListModel {

		ArrayList<String> Elements;

		CustomListModel(ArrayList<String> Elements) {
			super();
		}


		@Override
		public int getSize() {
			return 0;
		}

		@Override
		public Object getElementAt(int index) {
			return null;
		}
	}


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
		contentPane.setLayout(new MigLayout("", "[20px:20px:600px,grow 10,right]" +
				"[10px:10px:20px,grow 10,right,fill]"   +  // separator column
				"[10px:10px:20px,grow 10,right,fill]" +                // separator for separator
				"[80px:80px:1200px,grow 40,center]" +
				"[20px:20px:600px,grow 10,left, fill]",
				"[][20px:20px:40px,grow 10]" +
						"[][][grow][][][20px:20px:30px,grow 10][20px:20px:40px,grow 10][20px:20px:40px,grow 10][10px:10px:300px,grow 10]"));


		// Init the OPTION LIST

		initOptionList();

		JLabel TITLE = new JLabel("TASKS");
		TITLE.setForeground(new Color(255, 153, 51));
		TITLE.setFont(new Font("Arial Narrow", Font.BOLD, 32));
		contentPane.add(TITLE, "cell 3 0, center");

		JLabel lblNewLabel = new JLabel("TASK LIST");
		lblNewLabel.setForeground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		contentPane.add(lblNewLabel, "cell 0 2, center");
		
		JLabel lblNewLabel_1 = new JLabel("TASK TABLE");
		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1, "cell 3 2");


		colorPanels = new ColorPanel[nr_rows];
		for ( int i = 0 ;i< nr_rows; i++) {
			colorPanels[i] = new ColorPanel();
			contentPane.add(colorPanels[i], "cell 1 " + i + ", growy");
		}

		colorPanels = new ColorPanel[nr_cols];
		for ( int i = 0 ;i< nr_cols; i++) {
			colorPanels[i] = new ColorPanel();
			contentPane.add(colorPanels[i], "cell " + i +  " 10, growx");
		}


		colorPanels = new ColorPanel[nr_cols];
		for ( int i = 0 ;i< nr_cols; i++) {
			colorPanels[i] = new ColorPanel();
			contentPane.add(colorPanels[i], "cell " + i +  " 1, growx");
		}

		
//		JTextArea textArea = new JTextArea();
//		textArea.setFont(new Font("Arial Black", Font.BOLD, 15));
//		textArea.setBackground(new Color(255, 153, 0));
//		textArea.setForeground(Color.WHITE);
//		contentPane.add(textArea, "cell 1 4,grow");
//		//textArea.setEditable(false);
		String[] columnNames = {"Name", "Page", "Domain", "Number", "Account", "Rating"};

		DefaultTableModel model = new NonEditableTableModel(null,columnNames);


		// Create JTable
//		JPanel textArea = new JPanel();


		taskList = new JList();

		// Just Java things
		Hashtable<String,String[]> Tasks = Utils.JsonToHashtableUtility.ReadTasks();   // Don 't do it twice FIX LATER
		ArrayList<String> elements = Collections.list(Tasks.keys()).stream().filter(key -> !key.endsWith("_Urls"))
				.collect(Collectors.toCollection(ArrayList::new));

		taskList.setModel(new CustomListModel(elements) {



			@Override
			public int getSize() {
				return elements.size();
			}

			@Override
			public Object getElementAt(int i) {
				return elements.get(i);
			}
		});
		taskList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent evt) {
				jList1ValueChanged(evt);
			}
		});





		contentPane.add(taskList,"cell 0 4,grow" );




		JPanel contentPane2 = new JPanel(new MigLayout("wrap 3, insets 10", "[150!][grow][grow]",
				"[grow][grow][grow][grow][grow][grow][grow]"));

		// Label names
		String[] labelNames = {"Name", "Page", "Domain", "Number", "Account", "Rating", "Urls"};
		// Attribute values
		String[] attributeValues = {"someExample", "Normal", "Full Domain", "Multiple", "None", "None","adsd"};


		// Add labels, text fields, and dropdowns
		for (int i = 0; i < labelNames.length; i++) {
			String labelName = labelNames[i];

			// Label
			JLabel label = new JLabel(labelName);
			contentPane2.add(label, "cell 0 " + i);


			if ( i <= labelNames.length - 2 ) {

			// Dropdown


				if (labelName.equals("Name"))
				{
					JTextField textField = new JTextField(elements.get(0));
					textField.setEditable(true);
					contentPane2.add(textField, "cell 1 " + i + ",grow");

				}
				else {
					Collection<String[]> Values = dropdownOptions.values();

					JComboBox<String> comboBox = new JComboBox<String>(Values.stream().toList().get(i-1));
					System.out.println(Arrays.toString(Values.stream().toList().get(i-1)));
					contentPane2.add(comboBox, "cell 1 " + i  + ",grow");


				}
			}
		}

//		// Add a bigger text area at the last row
//		JTextArea textArea = new JTextArea();
//		textArea.setEditable(true);
//		JScrollPane textAreaScrollPane = new JScrollPane(textArea);
//		contentPane2.add(textAreaScrollPane, "cell 1 " + labelNames.length + 1 + ", span 3, grow");
//
//		contentPane2.setBorder(new EmptyBorder(10, 10, 10, 10));



		// Account Settings section
		JLabel accountSettingsLabel = new JLabel("Account Settings");
		accountSettingsLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		contentPane2.add(accountSettingsLabel, "cell 2 0,center");

		JLabel accountNameLabel = new JLabel("Account File Path");
		contentPane2.add(accountNameLabel, "cell 2 1,center");

		JTextField accountNameTextField = new JTextField();
		contentPane2.add(accountNameTextField, "cell 2 2,span 1, grow");


		JButton accountGenerateButton = new JButton("Generate Account File");
		contentPane2.add(accountGenerateButton, "cell 2 4,grow");



		pack();
		setLocationRelativeTo(null);



		contentPane2.setFont(new Font("Arial Black", Font.BOLD, 15));
		contentPane2.setBackground(new Color(255, 153, 0));
		contentPane2.setForeground(Color.WHITE);

		contentPane.add(contentPane2, "cell 3 4,grow");
		// Here it ends

		
		// text area = list
		JLabel lblNewLabel_2 = new JLabel("Insert or Delete Valid Task Name");
		contentPane.add(lblNewLabel_2, "cell 3 6");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 7,grow");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("SAVE");
		contentPane.add(btnNewButton, "cell 3 8,grow");




		
		lblNewLabel_2.setForeground(new Color(255, 153, 51));
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		 btnNewButton.setForeground(new Color(255, 153, 0));

		 
		 
		 // GET URLS
		 
		 //Proj.Utils.WriteToFile("Urls.txt",textField.getText());
			ArrayList <String> URLS = new ArrayList <String>();
			
			if ( Proj.Utils.ReadFromURLs() != null) {
				System.out.println("ENTERED NON-NULL STATEMENT");
				URLS.addAll(Proj.Utils.ReadFromURLs());
				int i =0;
				for (String url : URLS) {
					System.out.println(url);
					model.addRow(new Object[]{url,"1","1","1","1","1"});
				}
			}




		setBounds(100, 100, 800, 800);
		 
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
			contentPane.add(btnDelete, "cell 3 9,grow");

			accountGenerateButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					try {
						UIManager.setLookAndFeel(new NimbusLookAndFeel());
					} catch (UnsupportedLookAndFeelException ex) {
						throw new RuntimeException(ex);
					}
					// Create a new JFrame for the pop-up
					JFrame popupFrame = new JFrame("Account Settings");
					popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					JPanel popupPanel = new JPanel(new MigLayout("wrap 2, insets 10", "[grow][grow]"));

					// Add components to the pop-up panel
					JLabel accountSettingsLabel = new JLabel("Account Settings");
					accountSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					accountSettingsLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
					accountSettingsLabel.setForeground(new Color(255, 153, 0));

					popupPanel.add(accountSettingsLabel, "cell 0 0 2 1, grow");

					JLabel infoLabel = new JLabel("Info Blah Blah");
					infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
					infoLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
					infoLabel.setForeground(new Color(255, 153, 0));
					popupPanel.add(infoLabel, "cell 0 1 2 1, grow");

					JLabel accountNameLabel = new JLabel("Account name / Email");
					JTextField accountNameTextField = new JTextField();
					popupPanel.add(accountNameLabel, "cell 0 2, grow");
					popupPanel.add(accountNameTextField, "cell 1 2, grow");

					JLabel passwordLabel = new JLabel("Password");
					JTextField passwordTextField = new JTextField();
					popupPanel.add(passwordLabel, "cell 0 3, grow");
					popupPanel.add(passwordTextField, "cell 1 3, grow");

					// Select Path option
					JButton selectPathButton = new JButton("Select Path");
					JTextField filePathTextField = new JTextField();
					filePathTextField.setEditable(false);
					selectPathButton.addActionListener(actionEvent -> {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int result = fileChooser.showOpenDialog(popupFrame);
						if (result == JFileChooser.APPROVE_OPTION) {
							File selectedPath = fileChooser.getSelectedFile();
							// Update the file path text field
							filePathTextField.setText(selectedPath.getAbsolutePath());
						}
					});
					popupPanel.add(selectPathButton, "cell 0 4, grow");
					popupPanel.add(filePathTextField, "cell 1 4, grow");


					JButton generateButton = new JButton("Generate");
					generateButton.addActionListener(actionEvent -> {
						String accountName = accountNameTextField.getText();
						String password = passwordTextField.getText();
						String selectedPath = filePathTextField.getText();
					if (!accountName.isEmpty() && !password.isEmpty() && !selectedPath.isEmpty()) {
						try {
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("Account name", accountName);
							jsonObject.put("Password", password);

							String jsonContent = jsonObject.toString();
							String fileName = "account.json";

							// Create the file with the JSON content
							File file = new File(selectedPath, fileName);
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(jsonContent);
							fileWriter.close();

							JOptionPane.showMessageDialog(popupFrame, "File generated successfully!");
						} catch (IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(popupFrame, "Error generating the file.");
						}
					} else {
						JOptionPane.showMessageDialog(popupFrame, "Please fill in all fields and select a path.");
					}});



					popupPanel.add(generateButton, "cell 0 5 2 1, center, grow");


					// Add the pop-up panel to the pop-up frame
					popupFrame.getContentPane().add(popupPanel);
					popupFrame.pack();
					popupFrame.setMinimumSize(new Dimension(600, 200));
					popupFrame.setLocationRelativeTo(null);
					popupFrame.setVisible(true);
				}
			});


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
			
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					int to_del = 0;
//					if(textField.getText().contains("https://") && !textField.getText().contains(" ") && !textField.getText().contains("\n")) {
//
//						String[] Conts;
//
//						getModel().getValueAt(rowIndex, columnIndex)
//						Conts = textArea.getText().split("\n");
//						ArrayList <String> content = new ArrayList();
//						System.out.println("FIRST STEP OF DEL");
//						System.out.println(textField.getText());
//						for (String cont : Conts) {
//							System.out.println(cont);
//							if( textField.getText().contains(cont)) {
//							to_del = 1;
//							}
//							else {
//								content.add(cont);
//							}
//						}
//
//						if(to_del == 1) {
//							Proj.Utils.WriteToFileNOAPPEND("Urls.txt","IGNORE");
//							System.out.println("LAST STEP OF DEL");
//							textArea.setText("");
//							for (String  to_append : content) {
//							System.out.println(to_append);
//							textArea.append(to_append+"\n");
//							Proj.Utils.WriteToFile("Urls.txt",to_append);
//							}
//						}
//
//						else {
//							JOptionPane.showMessageDialog(contentPane,
//				 				    "Write a valid url to delete in the textbox");
//						}
//
//					}
//							//content.add(cont);
//
//					else {
//			 			JOptionPane.showMessageDialog(contentPane,
//			 				    "Write a valid url to delete in the textbox");
//			 		}
//				}
//			});
//









//
//			// ADD URL
//
//		 btnNewButton.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					 btnNewButton.setForeground(Color.GREEN);
//				}
//				@Override
//				public void mouseExited(MouseEvent e) {
//					 btnNewButton.setForeground(new Color(255, 153, 51));
//				}
//
//		 	@Override
//		 	public void mouseClicked(MouseEvent e) {
//
//		 		if(textField.getText().contains("https://") && !textField.getText().contains(" ") && !textField.getText().contains("\n"))
//		 		{
//
//		 			textArea.setText("");
//		 			Proj.Utils.WriteToFile("Urls.txt",textField.getText());
//		 			ArrayList <String> URLS = new ArrayList <String>();
//
//		 			if ( Proj.Utils.ReadFromURLs() != null) {
//		 				System.out.println("ENTERED NON-NULL STATEMENT");
//						URLS.addAll(Proj.Utils.ReadFromURLs());
//						for (String url : URLS) {
//							System.out.println(url);
//							textArea.append(url + "\n");
//						}
//
//		 		}
//		 		}
//		 		else {
//		 			JOptionPane.showMessageDialog(contentPane,
//		 				    "Write a valid url in the textbox");
//		 		}
//		 	}



		 });

		 
		 
		 
	}

}
