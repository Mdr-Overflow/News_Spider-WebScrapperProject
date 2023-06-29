package GUI.Proper;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Proj.Utils;
import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;
import org.json.JSONObject;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class URLProper extends JPanel {


    private JFrame parentFrame;
    private JPanel contentPane;
    private JTextField textField;

    private  JList taskList; // TASK LIST S

    private  JList urlList;

    private JPanel contentPane2;

    private  JTextField textFieldTaskList;
    private  JTextField textFieldUrlList;

    private  JButton accountGenerateButton;
    private  JButton selectPathButton;

    private  JTextField NameField;

    private final int nr_rows = 11;
    private final int nr_cols = 5;

    private static  Map<String, String[]> dropdownOptions;


    private GUI.Proper.URLProper.ColorPanel[] colorPanels;

    private static class ColorPanel extends JPanel {

        public ColorPanel() {
            setBackground(new Color(0x0448d2));
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
        String[] labelNames = {"Name", "Page", "Domain", "Number", "Account", "Rating"};

        // Retrieve attribute values from the Hashtable
        Hashtable<String, String[]> tasks = Utils.JsonToHashtableUtility.ReadTasks();
        String[] attributeValues = tasks.get(s);
        String[] urls = tasks.get(s + "_Urls");


        if (attributeValues != null && urls != null) {
            // Dropdown options


            Component[] components = contentPane2.getComponents();
            int j = 0;
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];




                // Update dropdowns
                if (component instanceof JComboBox) {
                    JComboBox comboBox = (JComboBox) component;
                    comboBox.setEditable(true);
                    System.out.println(comboBox.getSelectedItem() + " SELECTED BEFORE ");

                    if (j < attributeValues.length) {
                        comboBox.setSelectedItem(attributeValues[j]);
                        System.out.println(attributeValues[j] + " SELECTED NOW");
                    }
                    j++;
                    comboBox.setEditable(false);
                }

                if (component instanceof JTextField) {

                    JTextField name = (JTextField) component;

                    name.setText(s);


                }


            }

            List<String> list =  Collections.list(tasks.keys()).stream().filter(key -> key.endsWith("_Urls"))
                    .collect(Collectors.toCollection(ArrayList::new));


            // UPDATE URL-LIST

            String KEY = Collections.list(tasks.keys()).stream().filter(key -> key.endsWith("_Urls"))
                    .collect(Collectors.toCollection(ArrayList::new)).stream().filter(key -> key.contains(s))
                    .collect(Collectors.toCollection((ArrayList::new))).get(0); // Should only be one !! !!!!!!!!!!!!

            ArrayList<String> urlsL = new ArrayList<>(Arrays.asList(tasks.get(KEY)));

            urlList.setModel(new GUI.Proper.URLProper.CustomListModel(urlsL) {


                @Override
                public int getSize() {
                    return urlsL.size();
                }

                @Override
                public Object getElementAt(int i) {
                    return urlsL.get(i);
                }
            });





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
     * Create the frame.
     */
    public URLProper(JFrame parentFrame) {
        this.parentFrame = parentFrame;


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.add(contentPane);
        this.parentFrame.add(this);


        contentPane.setLayout(new MigLayout("", "[200px:200px:600px,grow 10,right]" +
                "[50px:50px:200px,grow 10,right,fill]"   +  // separator column
                "[50px:50px:200px,grow 10,right,fill]" +                // separator for separator
                "[400px:200px:12000px,grow 40,center]" +
                "[100px:100px:6000px,grow 10,left, fill]",
                "[][20px:20px:400px,grow 10]" +
                        "[][][grow][50px:50px:300px,grow 10][][40px:40px:300px,grow 10][20px:40px:400px,grow 10][20px:40px:400px,grow 10][20px:40px:3000px,grow 10]"));


        // Init the OPTION LIST

        initOptionList();

        JLabel TITLE = new JLabel("TASKS");
        TITLE.setForeground(new Color(0x0448d2));
        TITLE.setFont(new Font("Arial Narrow", Font.BOLD, 32));
        contentPane.add(TITLE, "cell 3 0, center");

        JLabel lblNewLabel = new JLabel("TASK LIST");
        lblNewLabel.setForeground(new Color(0x0448d2));
        lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        contentPane.add(lblNewLabel, "cell 0 2, center");

        JLabel lblNewLabel_1 = new JLabel("TASK TABLE");
        lblNewLabel_1.setForeground(new Color(0x0448d2));
        lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        contentPane.add(lblNewLabel_1, "cell 3 2");

        JLabel URLlabel = new JLabel("URL LIST");
        URLlabel.setForeground(new Color(0x0448d2));
        URLlabel.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        contentPane.add(URLlabel, "cell 4 2, center");



        colorPanels = new GUI.Proper.URLProper.ColorPanel[nr_rows];
        for ( int i = 0 ;i< nr_rows; i++) {
            colorPanels[i] = new GUI.Proper.URLProper.ColorPanel();
            contentPane.add(colorPanels[i], "cell 1 " + i + ", growy");
        }

        colorPanels = new GUI.Proper.URLProper.ColorPanel[nr_cols];
        for ( int i = 0 ;i< nr_cols; i++) {
            colorPanels[i] = new GUI.Proper.URLProper.ColorPanel();
            contentPane.add(colorPanels[i], "cell " + i +  " 10, growx");
        }


        colorPanels = new GUI.Proper.URLProper.ColorPanel[nr_cols];
        for ( int i = 0 ;i< nr_cols; i++) {
            colorPanels[i] = new GUI.Proper.URLProper.ColorPanel();
            contentPane.add(colorPanels[i], "cell " + i +  " 1, growx");
        }



        String[] columnNames = {"Name", "Page", "Domain", "Number", "Account", "Rating"};

        DefaultTableModel model = new GUI.Proper.URLProper.NonEditableTableModel(null,columnNames);


        // Create JTable
//		JPanel textArea = new JPanel();


        taskList = new JList();
        urlList = new JList();

        // Just Java things
        Hashtable<String,String[]> Tasks = Utils.JsonToHashtableUtility.ReadTasks();   // Don 't do it twice FIX LATER
        ArrayList<String> elements = Collections.list(Tasks.keys()).stream().filter(key -> !key.endsWith("_Urls"))
                .collect(Collectors.toCollection(ArrayList::new));

        String KEY = Collections.list(Tasks.keys()).stream().filter(key -> key.endsWith("_Urls"))
                .collect(Collectors.toCollection(ArrayList::new)).get(0);

        ArrayList<String> urlsL = new ArrayList<>(Arrays.asList(Tasks.get(KEY)));

        taskList.setModel(new GUI.Proper.URLProper.CustomListModel(elements) {


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


        urlList.setModel(new GUI.Proper.URLProper.CustomListModel(urlsL) {


            @Override
            public int getSize() {
                return urlsL.size();
            }

            @Override
            public Object getElementAt(int i) {
                return urlsL.get(i);
            }
        });
        urlList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {


            }
        });



        contentPane.add(taskList,"cell 0 4,grow" );
        contentPane.add(urlList,"cell 4 4,grow" );


        contentPane2 = new JPanel(new MigLayout("wrap 3, insets 10", "[150!][grow][grow]",
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
                Collection<String[]> Values = dropdownOptions.values();

                if (labelName.equals("Name")) {
                    NameField = new JTextField(elements.get(0));
                    NameField.setEditable(true);
                    contentPane2.add(NameField, "cell 1 " + i + ",grow");

                } else if (labelName.equals("Account")) {


                    JComboBox<String> comboBox = new JComboBox<String>(Values.stream().toList().get(i-1));
                    System.out.println(Arrays.toString(Values.stream().toList().get(i-1)));

                    comboBox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                String selectedItem = (String) comboBox.getSelectedItem();

                                // Disable the Account Path text field and Generate File button
                                if (selectedItem.equals("None")) {
                                    selectPathButton.setEnabled(false);
                                    accountGenerateButton.setEnabled(false);
                                } else {
                                    selectPathButton.setEnabled(true);
                                    accountGenerateButton.setEnabled(true);
                                }
                            }
                        }
                    });


                    contentPane2.add(comboBox, "cell 1 " + i  + ",grow");



                }

                else if (labelName.equals("Rating")) {



                    JComboBox<String> comboBox = new JComboBox<String>(Values.stream().toList().get(i-1));
                    System.out.println(Arrays.toString(Values.stream().toList().get(i-1)));

                    comboBox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                String selectedItem = (String) comboBox.getSelectedItem();

                                // Disable the Account Path text field and Generate File button
                                if (selectedItem.equals("High Risk") || selectedItem.equals("BAD")) {

                                    String warningMessage = "Warning: High Risk Pages/Domains might result in unpredictable behavior" +
                                            " such as IP bans, or the application not getting data correctly!\n Tread at your own risk";

                                    JLabel warningLabel = new JLabel(warningMessage);
                                    warningLabel.setFont(new Font("Arial", Font.BOLD, 15));
                                    warningLabel.setForeground(new Color(255, 0, 0));
                                    warningLabel.setHorizontalAlignment(SwingConstants.CENTER);

                                    JOptionPane.showMessageDialog(contentPane2, warningLabel, "Warning", JOptionPane.WARNING_MESSAGE);

                                } else {

                                }
                            }
                        }
                    });


                    contentPane2.add(comboBox, "cell 1 " + i  + ",grow");







                }

                else {


                    JComboBox<String> comboBox = new JComboBox<String>(Values.stream().toList().get(i-1));
                    System.out.println(Arrays.toString(Values.stream().toList().get(i-1)));
                    contentPane2.add(comboBox, "cell 1 " + i  + ",grow");


                }
            }
        }



        // Account Settings section
        JLabel accountSettingsLabel = new JLabel("Account Settings");
        accountSettingsLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
        contentPane2.add(accountSettingsLabel, "cell 2 0,center");




        // Select Path option
        selectPathButton = new JButton("Select Account File Path");
        JTextField filePathTextField = new JTextField();
        filePathTextField.setEditable(false);

        selectPathButton.addActionListener(actionEvent -> {


            // Create a new JFrame for the pop-up
            JFrame popupFrame = new JFrame("Account Settings");
            popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(popupFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedPath = fileChooser.getSelectedFile();
                // Update the file path text field
                filePathTextField.setText(selectedPath.getAbsolutePath());

                try {
                    // Read the JSON data from the account file
                    String jsonData = new String(Files.readAllBytes(Paths.get(selectedPath.getAbsolutePath())));
                    //String json = new String(Files.readAllBytes(Paths.get(filePath)));

                    // Convert JSON data to a Hashtable
                    Hashtable<String, String[]> hashtable = new Hashtable<>();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    String taskName = jsonObject.getString("Task name");
                    String name = jsonObject.getString("Account name");
                    String password = jsonObject.getString("Password");
                    hashtable.put(taskName, new String[]{name,password});

                    System.out.println(hashtable);

                    // Display success message
                    JOptionPane.showMessageDialog(contentPane2, "Account Details Successfully Read", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    // Display error message
                    JOptionPane.showMessageDialog(contentPane2, "Error: Account Details Not Successfully Read", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }


            }
        });

        contentPane2.add(selectPathButton, "cell 2 2, grow");


        accountGenerateButton = new JButton("Generate Account File");
        contentPane2.add(accountGenerateButton, "cell 2 4,grow");


//        pack();
//        setLocationRelativeTo(null);


        contentPane2.setFont(new Font("Arial Black", Font.BOLD, 15));
        contentPane2.setBackground(new Color(0x0448d2));
        contentPane2.setForeground(Color.WHITE);

        contentPane.add(contentPane2, "cell 3 4,grow");
        // Here it ends


        // text area = list
        JLabel lblNewLabel_2 = new JLabel("Save or Delete Task");
        contentPane.add(lblNewLabel_2, "cell 3 6");


        JButton btnNewButton = new JButton("SAVE");
        contentPane.add(btnNewButton, "cell 3 8,grow");


        JLabel taskListLabel = new JLabel("Insert Task Name");
        contentPane.add(taskListLabel, "cell 0 6 ,center");

        textFieldTaskList = new JTextField();
        contentPane.add(textFieldTaskList, "cell 0 7,grow");
        //textField.setColumns(10);

        JButton saveTaskButton = new JButton("SAVE");
        contentPane.add(saveTaskButton, "cell 0 8,grow");



        lblNewLabel_2.setForeground(new Color(0x0448d2));
        lblNewLabel_2.setFont(new Font("Arial Narrow", Font.BOLD, 20));

        btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
        btnNewButton.setForeground(new Color(0x0448d2));


        taskListLabel.setForeground(new Color(0x0448d2));
        taskListLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));

        textFieldTaskList.setForeground(new Color(0x0448d2));
        textFieldTaskList.setFont(new Font("Arial Narrow", Font.BOLD, 12));

        saveTaskButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
        saveTaskButton.setForeground(new Color(0x0448d2));

        JLabel urlListLabel = new JLabel("Insert URL Name");
        contentPane.add(urlListLabel, "cell 4 6 , center");

        textFieldUrlList = new JTextField();
        contentPane.add(textFieldUrlList, "cell 4 7,grow");
        //textField.setColumns(10);

        JButton saveURLButton = new JButton("SAVE");
        contentPane.add(saveURLButton, "cell 4 8,grow");

        JButton delURLButton = new JButton("DELETE");
        contentPane.add(delURLButton, "cell 4 9,grow");

        urlListLabel.setForeground(new Color(0x0448d2));
        urlListLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));

        textFieldUrlList.setForeground(new Color(0x0448d2));
        textFieldUrlList.setFont(new Font("Arial Narrow", Font.BOLD, 16));

        saveURLButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
        saveURLButton.setForeground(new Color(0x0448d2));

        delURLButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
        delURLButton.setForeground(new Color(0x0448d2));




        textFieldTaskList.setFont(new Font("Arial", Font.BOLD, 16));
        textFieldTaskList.setForeground(new Color(204, 51, 0));

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDelete.setForeground(new Color(0x0448d2));
        btnDelete.setFont(new Font("Arial Black", Font.PLAIN, 12));
        contentPane.add(btnDelete, "cell 3 9,grow");




        accountGenerateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//
                // Create a new JFrame for the pop-up
                JFrame popupFrame = new JFrame("Account Settings");
                popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel popupPanel = new JPanel(new MigLayout("wrap 2, insets 10", "[grow][grow]"));

                // Add components to the pop-up panel
                JLabel accountSettingsLabel = new JLabel("Account Settings");
                accountSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                accountSettingsLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
                accountSettingsLabel.setForeground(new Color(0x0448d2));

                popupPanel.add(accountSettingsLabel, "cell 0 0 2 1, grow");

                JLabel infoLabel = new JLabel("Info Blah Blah");
                infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                infoLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
                infoLabel.setForeground(new Color(0x0448d2));
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
                    String taskName = NameField.getText();
                    if (!accountName.isEmpty() && !password.isEmpty() && !selectedPath.isEmpty()) {
                        try {
                            JSONObject jsonObject = new JSONObject();

                            jsonObject.put("Task name", taskName);
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
                btnDelete.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDelete.setForeground(new Color(0x0448d2));
            }



        });


    }

}