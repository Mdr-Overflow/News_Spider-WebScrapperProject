package com.raven.form;

//import com.intellij.uiDesigner.core.*;

import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import com.raven.component.*;
import com.raven.main.Main;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import net.miginfocom.swing.MigLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
//import org.jdesktop.swingx.*;

public class Form_Tasks extends JPanel {


    private JFrame frmNewsSpiderTest;
    private JTextField textField;
    private final JPanel PANELMANAGER = new JPanel();
    private CardLayout cardl = new CardLayout(0, 0);
    private final JPanel panel_3 = new JPanel();

    private JPanel current_panel;

    /**
     * @wbp.nonvisual location=20,589
     */
    static int rate = -1;
    static int threads = -1;
    static String[] Real_URLS;
    private final JPanel panel_OPTIONS = new JPanel();
    private final JPanel panel_Urls = new JPanel();
    public static JProgressBar bar = null;

    private JLabel gifLabel;
    static int progress = 0;

    public static String[] options = {" ", " ", " ", " ", " ", " "};
    public static ArrayList<HashMap<String, String>> Agents;


    public String[] done = {"?"};

    private static double SCALE_FACTOR = 1.5;

    private static final String INSECURE_URLS_FILE = "insecure.txt";
    public static void main(String[] args) {



        // ZOOM IN OPTION
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

//        FlatLightLaf.setup();


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form_Tasks frame = new Form_Tasks();
                    //frame.setBackground(Color.yellow);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }







    public Form_Tasks() {
        initComponents();
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        //setOpaque(false);


        // Check if the insecure file already exists before downloading

        File insecureFile = new File(INSECURE_URLS_FILE);
        if (!insecureFile.exists()) {
            downloadInsecureUrls();
        }


        initData();



//        //---- button5 ----
//        button5.setText("Back");
//        button5.setForeground(new Color(0x0448d2));
//        button5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
//        button5.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Back");
//            }
//        });
//        add(button5, "cell 0 3,aligny center,grow 100 0");

    }

    private void initData() {

        try {
            setComponentValuesFromTasks();
        }
        catch (Exception e){
            System.out.println(e.toString());

            // have to create it

//            saveTasks();


        }

//        initTableData();
    }

    // WORKS WELL
    public void removeUrlFromTask(String taskName, String url) {
        try {
            File file = new File("Tasks.json");
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONObject jsonContent = new JSONObject(content);
            JSONArray tasks = jsonContent.getJSONArray("Tasks");

            for (int i = 0; i < tasks.length(); i++) {
                JSONObject task = tasks.getJSONObject(i);
                if (task.getString("Name").equals(taskName)) {
                    JSONArray urls = task.getJSONArray("Urls");
                    for (int j = 0; j < urls.length(); j++) {
                        if (urls.getString(j).equals(url)) {
                            urls.remove(j);
                            break;
                        }
                    }
                    task.put("Urls", urls);
                    break;
                }
            }
            Files.write(file.toPath(), jsonContent.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // WORKS WELL
    public void downloadInsecureUrls() {
        String url = "https://raw.githubusercontent.com/mitchellkrogza/The-Big-List-of-Hacked-Malware-Web-Sites/master/.dev-tools/_strip_domains/domains.txt";
        String outputFileName = "insecure.txt";

        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(outputFileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // WORKS WELL
    public boolean isInsecure(String url) {
        try {
            URI uri = new URI(url);
            if (!"https".equals(uri.getScheme())) {
                JOptionPane.showMessageDialog(null, "Web page does not use secure HTTP, abort");
                return true;
            }
            String domain = uri.getHost();
            BufferedReader reader = new BufferedReader(new FileReader("insecure.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(domain)) {
                    JOptionPane.showMessageDialog(null, "URL is detected to be a threat to security, aborted");
                    return true;
                }
            }
            reader.close();
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(null, "URL is not valid, abort");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

        // PROBLEMS SEEMS TO COME FROM HOW DATA IS SAVED IN JSON AND HOW MODELS ARE HANDLED
    // PROBLEM HERE

    // PROBLEM ON WRITTING NEW FILE INTESEAT OF ADDING TO IT


    // OK WORKS NOW , ALL THE STUFF WORKS NOW FROM FRESH STATE
    // PROBLEM ON NUMBER ON SAVE

    // NOW ALL WORK FROM CLEAN STATE

    public void saveTasks() {
        try {
            // Check if textField1 value is valid
            String taskName = textField1.getText();
            if (taskName == null || taskName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Task name is not valid. Please enter a valid task name.");
                return;
            }

            // Load existing tasks from file
            String content = new String(Files.readAllBytes(Paths.get("Tasks.json")));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray tasks = jsonObject.getJSONArray("Tasks");

            // Create new task object
            JSONObject newTask = new JSONObject();
            newTask.put("Name", taskName);
            newTask.put("Page", comboBox7.getSelectedItem().toString());
            newTask.put("Domain", comboBox6.getSelectedItem().toString());
            newTask.put("Number", comboBox5.getSelectedItem().toString());
            newTask.put("Rating", comboBox4.getSelectedItem().toString());

            // Get URLs from list
            JSONArray urls = new JSONArray();
            DefaultListModel model;
            if (list2.getModel() instanceof DefaultListModel) {
                model = (DefaultListModel) list2.getModel();
            } else {
                model = new DefaultListModel();
                for (int i = 0; i < list2.getModel().getSize(); i++) {
                    model.addElement(list2.getModel().getElementAt(i));
                }
                list2.setModel(model);
            }
            for (int i = 0; i < model.getSize(); i++) {
                urls.put(model.getElementAt(i));
            }
            newTask.put("Urls", urls);

            // Search for existing task and replace it, or append the new task if it doesn't exist
            boolean found = false;
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject task = tasks.getJSONObject(i);
                if (task.getString("Name").equals(taskName)) {
                    // Replace existing task
                    tasks.put(i, newTask);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Append new task
                tasks.put(newTask);
            }

            // Save updated tasks to file
            try (FileWriter file = new FileWriter("Tasks.json")) {
                file.write(jsonObject.toString(4));  // Using toString(4) for pretty-printing
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // THIS IS THE ONE
    private void setComponentValuesFromTasks() {
        // Read JSON file
        JSONObject jsonData;
        try {
            jsonData = new JSONObject(new JSONTokener(new FileReader("Tasks.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Get the tasks array
        JSONArray tasksArray = jsonData.getJSONArray("Tasks");

        // Get DefaultListModel for list1 and list2
        DefaultListModel<String> list1Model = getListModel(list1);
        DefaultListModel<String> list2Model =  getListModel(list2);

        // Traverse the tasks array
        for (int i = 0; i < tasksArray.length(); i++) {
            JSONObject task = tasksArray.getJSONObject(i);

            // Set the values of the components GOOD NOW
            textField1.setText(task.getString("Name"));
            comboBox7.setSelectedItem(task.getString("Page"));
            comboBox6.setSelectedItem(task.getString("Domain"));
            comboBox5.setSelectedItem(task.getString("Number"));
            comboBox4.setSelectedItem(task.getString("Rating"));


            if (!list1Model.contains(task.getString("Name"))) {
                list1Model.addElement(task.getString("Name"));
            }

            // Set the values of list1 and list2
            JSONArray urls = task.getJSONArray("Urls");
            for (int j = 0; j < urls.length(); j++) {
                String url = urls.getString(j);

                if (!list2Model.contains(url)) {
                    list2Model.addElement(url);
                }
            }
        }
    }
    private DefaultListModel getListModel(JList list) {
        DefaultListModel model;
        if (list.getModel() instanceof DefaultListModel) {
            model = (DefaultListModel) list.getModel();
        } else {
            model = new DefaultListModel();
            for (int i = 0; i < list.getModel().getSize(); i++) {
                model.addElement(list.getModel().getElementAt(i));
            }
            list.setModel(model);
        }
        return model;
    }

    // KINDA WEIRD
    private JSONObject loadJSON(String filename) {
        JSONObject jsonObject = null;
        try {
            File file = new File(filename);
            if (!file.exists()) return null;
            String content = new String(Files.readAllBytes(file.toPath()));
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public void deleteTask() {
        // Load tasks from file
        JSONObject jsonObject = loadJSON("Tasks.json");
        if (jsonObject != null) {
            JSONArray tasks = jsonObject.getJSONArray("Tasks");
            String currentTaskName = textField1.getText();
            int deletedTaskIndex = -1;

            // Find task with matching name and remove it
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject task = tasks.getJSONObject(i);
                if (task.getString("Name").equals(currentTaskName)) {
                    tasks.remove(i);
                    deletedTaskIndex = i;
                    break;
                }
            }

            // Save tasks back to file
            try (FileWriter file = new FileWriter("Tasks.json")) {
                file.write(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Remove item from list1
            DefaultListModel<String> model = getListModel(list1);
            model.removeElement(currentTaskName);

            // Set data in the ComboBoxes and TextField to a random task from the JSON file
            if(tasks.length() > 0) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(tasks.length());
                JSONObject randomTask = tasks.getJSONObject(randomIndex);

                textField1.setText(randomTask.getString("Name"));
                comboBox4.setSelectedItem(randomTask.getString("Rating"));
                comboBox5.setSelectedItem(randomTask.getString("Number"));
                comboBox6.setSelectedItem(randomTask.getString("Domain"));
                comboBox7.setSelectedItem(randomTask.getString("Page"));
            } else {
                textField1.setText("");
                Random rand = new Random();
                comboBox4.setSelectedIndex(rand.nextInt(comboBox4.getItemCount()));
                comboBox5.setSelectedIndex(rand.nextInt(comboBox5.getItemCount()));
                comboBox6.setSelectedIndex(rand.nextInt(comboBox6.getItemCount()));
                comboBox7.setSelectedIndex(rand.nextInt(comboBox7.getItemCount()));
            }
        }
    }


    // NUMBER AND RATING ARE SWITCHED SOMEWHERE

    public void createNewTask() {
        String taskName = textArea1.getText().trim();

        if (taskName.isEmpty() || !taskName.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(null, "No valid name given to task");
            return;
        }

        // check if taskName already exists

        DefaultListModel model;
        if (list1.getModel() instanceof DefaultListModel) {
            model = (DefaultListModel) list1.getModel();
        } else {
            model = new DefaultListModel();
            for (int i = 0; i < list1.getModel().getSize(); i++) {
                model.addElement(list1.getModel().getElementAt(i));
            }
            list1.setModel(model);
        }

            if (model.contains(taskName)) {
                JOptionPane.showMessageDialog(null, "Task already exists");
                return;
            }



        JSONObject jsonObject = loadJSON("Tasks.json");
        if (jsonObject != null) {
            JSONArray tasks = jsonObject.getJSONArray("Tasks");

            JSONObject newTask = new JSONObject();
            newTask.put("Name", taskName);
            newTask.put("Page", getRandomComboBoxValue(comboBox7)); // GOOD
            newTask.put("Domain", "Multiple Pages");                   // GOOD
            newTask.put("Number", getRandomComboBoxValue(comboBox5));  // HERE THEY ARE SWITCHED
            newTask.put("Rating", getRandomComboBoxValue(comboBox4));
            newTask.put("Urls", new JSONArray());

            tasks.put(newTask);

            try (FileWriter file = new FileWriter("Tasks.json")) {
                file.write(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add new task to list1

            model.addElement(taskName);
        }
    }
    // WORKS WELL BUT ALSO ADDS TO TASKS FOR SOME REASON
    public void addUrlToTask(String url) {
        String taskName = textField1.getText();

        // Check if taskName is empty or null
        if (taskName == null || taskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        taskName = taskName.trim();

        try {
            String content = new String(Files.readAllBytes(Paths.get("tasks.json")));
            JSONObject obj = new JSONObject(content);
            JSONArray taskList = obj.getJSONArray("Tasks");

            DefaultListModel<String> model = getListModel(list2);

            // Check if the URL already exists in list2
            if (model.contains(url)) {
                JOptionPane.showMessageDialog(null, "The URL already exists in the list.");
                return;
            }

            for (int i = 0; i < taskList.length(); i++) {
                JSONObject task = taskList.getJSONObject(i);
                if (task.get("Name").equals(taskName)) {
                    JSONArray urls = task.getJSONArray("Urls");
                    urls.put(url);
                    break;
                }
            }

            Files.write(Paths.get("tasks.json"), obj.toString().getBytes());

            // Add URL to list2
            model.addElement(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // culprrit is random gen
    public String getRandomComboBoxValue(JComboBox<String> comboBox) {
        Random random = new Random();
        int randomIndex = random.nextInt(comboBox.getItemCount());
        return comboBox.getItemAt(randomIndex);
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {


        panel32 = new JPanel();
        jLabel3 = new JLabel();
        panel45 = new JPanel();
        panel38 = new JPanel();
        panel34 = new JPanel();
        panel37 = new JPanel();
        panel44 = new JPanel();
        panel39 = new JPanel();
        jLabel1 = new JLabel();
        panel31 = new JPanel();
        jLabel4 = new JLabel();
        panel43 = new JPanel();
        jLabel2 = new JLabel();
        panel35 = new JPanel();
        panel33 = new JPanel();
        panel42 = new JPanel();
        panel40 = new JPanel();
        panel47 = new JPanel();
        scrollPane3 = new JScrollPane();
        list1 = new JList();
        panel2 = new JPanel();
        panel46 = new JPanel();
        panel1 = new JPanel();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label5 = new JLabel();
        comboBox7 = new JComboBox<>();
        label6 = new JLabel();
        comboBox6 = new JComboBox<>();
        label7 = new JLabel();
        comboBox5 = new JComboBox<>();
        label8 = new JLabel();
        comboBox4 = new JComboBox<>();
        panel48 = new JPanel();
        scrollPane4 = new JScrollPane();
        list2 = new JList();
        panel3 = new JPanel();
        panel49 = new JPanel();
        panel51 = new JPanel();
        panel50 = new JPanel();
        panel53 = new JPanel();
        panel52 = new JPanel();
        label1 = new JLabel();
        panel55 = new JPanel();
        label3 = new JLabel();
        panel56 = new JPanel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        panel57 = new JPanel();
        button5 = new JButton();
        panel58 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea2 = new JTextArea();
        button3 = new JButton();
        panel61 = new JPanel();
        button6 = new JButton();
        panel62 = new JPanel();
        button4 = new JButton();
        button7 = new JButton();
        panel65 = new JPanel();
        panel66 = new JPanel();
        button9 = new JButton();
        panel59 = new JPanel();
        panel67 = new JPanel();
        panel63 = new JPanel();
        panel70 = new JPanel();
        panel68 = new JPanel();
        panel69 = new JPanel();
        panel60 = new JPanel();
        panel64 = new JPanel();

// Initialize the DefaultListModel

       textField1.setEditable(false);

       ListModel<String> list1Model = new DefaultListModel<String>();
       ListModel<String> list2Model = new DefaultListModel<String>();

        // Set the model for list1 and list2
        list1.setModel(list1Model);
        list2.setModel(list2Model);

        // PROBLEMS HERE

            list1.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting() && list1.getSelectedValue() != null) {
                        String selectedTaskName = list1.getSelectedValue().toString();

                        // Read JSON file
                        JSONObject jsonData;
                        try {
                            jsonData = new JSONObject(new JSONTokener(new FileReader("Tasks.json")));
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                            return;
                        }

                        // Get the tasks array
                        JSONArray tasksArray = jsonData.getJSONArray("Tasks");

                        // Traverse the tasks array
                        for (int i = 0; i < tasksArray.length(); i++) {
                            JSONObject task = tasksArray.getJSONObject(i);

                            // Check if this is the selected task
                            if (task.getString("Name").equals(selectedTaskName)) {

                                // Set the values of the components
                                textField1.setText(task.getString("Name"));
                                comboBox7.setSelectedItem(task.getString("Page"));
                                comboBox6.setSelectedItem(task.getString("Domain"));
                                comboBox5.setSelectedItem(task.getString("Number"));
                                comboBox4.setSelectedItem(task.getString("Rating"));

                                // Set the values of list2
                                DefaultListModel<String> list2Model = getListModel(list2);
                                list2Model.clear();
                                JSONArray urls = task.getJSONArray("Urls");
                                for (int j = 0; j < urls.length(); j++) {
                                    list2Model.addElement(urls.getString(j));
                                }

                                // Stop iterating as we've found our task
                                break;
                            }
                        }
                    }
                }
            });


        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskName = textField1.getText();
                String url = textArea2.getText();
                if (!taskName.isEmpty() && !url.isEmpty()) {
                    removeUrlFromTask(taskName, url);
                    DefaultListModel<String> model2 = (DefaultListModel<String>) list2.getModel();
                    model2.removeElement(url);
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = textArea2.getText().trim();
                if (!isInsecure(url)) {
                    addUrlToTask(url);
                }
            }
        });

        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewTask();
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTasks();
            }
        });

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        // HERE
        // Load data from JSON file into components
//        JSONObject jsonObject = loadJSON("Tasks.json");
//        if (jsonObject != null) {
//            JSONArray tasks = jsonObject.getJSONArray("Tasks");
//            for (int i = 0; i < tasks.length(); i++) {
//                JSONObject task = tasks.getJSONObject(i);
//                textField1.setText(task.getString("Name"));
//                comboBox7.setSelectedItem(task.getString("Page"));
//                comboBox6.setSelectedItem(task.getString("Number"));
//                comboBox4.setSelectedItem(task.getString("Rating"));
//                JSONArray urls = task.getJSONArray("Urls");
//                DefaultListModel<String> model = new DefaultListModel<>();
//                for (int j = 0; j < urls.length(); j++) {
//                    model.addElement(urls.getString(j));
//                }
//                list1.setModel(model);
//                list2.setModel(model);
//            }
//        }








        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,alignx center",
            // columns
            "[191,grow 50,fill]0" +
            "[31,fill]0" +
            "[430,grow,fill]0" +
            "[31,fill]0" +
            "[191,grow 50,fill]",
            // rows
            "[51,grow,fill]0" +
            "[]0" +
            "[34,grow]0" +
            "[]0" +
            "[]0" +
            "[270]0" +
            "[]0" +
            "[]0" +
            "[6]0" +
            "[]0" +
            "[29]0" +
            "[]0" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[grow]" +
            "[]"));

        //======== panel32 ========
        {
            panel32.setForeground(new Color(0x0448d2));
            panel32.setBackground(new Color(0x0448d2));
            panel32.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel32.getComponentCount(); i++) {
                    Rectangle bounds = panel32.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel32.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel32.setMinimumSize(preferredSize);
                panel32.setPreferredSize(preferredSize);
            }
        }
        add(panel32, "cell 1 0");

        //---- jLabel3 ----
        jLabel3.setFont(new Font("sansserif", Font.BOLD, 24));
        jLabel3.setForeground(new Color(0x0448d2));
        jLabel3.setText("Tasks");
        jLabel3.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleTask.png"));
        add(jLabel3, "cell 2 0,align center center,grow 0 0");

        //======== panel45 ========
        {
            panel45.setForeground(new Color(0x0448d2));
            panel45.setBackground(new Color(0x0448d2));
            panel45.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel45.getComponentCount(); i++) {
                    Rectangle bounds = panel45.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel45.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel45.setMinimumSize(preferredSize);
                panel45.setPreferredSize(preferredSize);
            }
        }
        add(panel45, "cell 3 0,growy");

        //======== panel38 ========
        {
            panel38.setForeground(new Color(0x0448d2));
            panel38.setBackground(new Color(0x0448d2));
            panel38.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel38.getComponentCount(); i++) {
                    Rectangle bounds = panel38.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel38.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel38.setMinimumSize(preferredSize);
                panel38.setPreferredSize(preferredSize);
            }
        }
        add(panel38, "cell 0 1,grow");

        //======== panel34 ========
        {
            panel34.setForeground(new Color(0x0448d2));
            panel34.setBackground(new Color(0x0448d2));
            panel34.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel34.getComponentCount(); i++) {
                    Rectangle bounds = panel34.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel34.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel34.setMinimumSize(preferredSize);
                panel34.setPreferredSize(preferredSize);
            }
        }
        add(panel34, "cell 1 1,growy");

        //======== panel37 ========
        {
            panel37.setForeground(new Color(0x0448d2));
            panel37.setBackground(new Color(0x0448d2));
            panel37.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel37.getComponentCount(); i++) {
                    Rectangle bounds = panel37.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel37.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel37.setMinimumSize(preferredSize);
                panel37.setPreferredSize(preferredSize);
            }
        }
        add(panel37, "cell 2 1,grow");

        //======== panel44 ========
        {
            panel44.setForeground(new Color(0x0448d2));
            panel44.setBackground(new Color(0x0448d2));
            panel44.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel44.getComponentCount(); i++) {
                    Rectangle bounds = panel44.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel44.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel44.setMinimumSize(preferredSize);
                panel44.setPreferredSize(preferredSize);
            }
        }
        add(panel44, "cell 3 1,growy");

        //======== panel39 ========
        {
            panel39.setForeground(new Color(0x0448d2));
            panel39.setBackground(new Color(0x0448d2));
            panel39.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel39.getComponentCount(); i++) {
                    Rectangle bounds = panel39.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel39.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel39.setMinimumSize(preferredSize);
                panel39.setPreferredSize(preferredSize);
            }
        }
        add(panel39, "cell 4 1,grow");

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Task List");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleSplit.png"));
        add(jLabel1, "cell 0 2,align center center,grow 0 0");

        //======== panel31 ========
        {
            panel31.setForeground(new Color(0x0448d2));
            panel31.setBackground(new Color(0x0448d2));
            panel31.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel31.getComponentCount(); i++) {
                    Rectangle bounds = panel31.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel31.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel31.setMinimumSize(preferredSize);
                panel31.setPreferredSize(preferredSize);
            }
        }
        add(panel31, "cell 1 2,grow");

        //---- jLabel4 ----
        jLabel4.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel4.setForeground(new Color(0x0448d2));
        jLabel4.setText("Task Table");
        jLabel4.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleTable.png"));
        add(jLabel4, "cell 2 2,align center center,grow 0 0");

        //======== panel43 ========
        {
            panel43.setForeground(new Color(0x0448d2));
            panel43.setBackground(new Color(0x0448d2));
            panel43.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel43.getComponentCount(); i++) {
                    Rectangle bounds = panel43.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel43.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel43.setMinimumSize(preferredSize);
                panel43.setPreferredSize(preferredSize);
            }
        }
        add(panel43, "cell 3 2,growy");

        //---- jLabel2 ----
        jLabel2.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel2.setForeground(new Color(0x0448d2));
        jLabel2.setText("URL List");
        jLabel2.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleSplit2.png"));
        add(jLabel2, "cell 4 2,align center center,grow 0 0");

        //======== panel35 ========
        {
            panel35.setForeground(new Color(0x0448d2));
            panel35.setBackground(new Color(0x0448d2));
            panel35.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel35.getComponentCount(); i++) {
                    Rectangle bounds = panel35.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel35.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel35.setMinimumSize(preferredSize);
                panel35.setPreferredSize(preferredSize);
            }
        }
        add(panel35, "cell 0 3,growy");

        //======== panel33 ========
        {
            panel33.setForeground(new Color(0x0448d2));
            panel33.setBackground(new Color(0x0448d2));
            panel33.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel33.getComponentCount(); i++) {
                    Rectangle bounds = panel33.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel33.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel33.setMinimumSize(preferredSize);
                panel33.setPreferredSize(preferredSize);
            }
        }
        add(panel33, "cell 1 3,growy");

        //======== panel42 ========
        {
            panel42.setForeground(new Color(0x0448d2));
            panel42.setBackground(new Color(0x0448d2));
            panel42.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel42.getComponentCount(); i++) {
                    Rectangle bounds = panel42.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel42.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel42.setMinimumSize(preferredSize);
                panel42.setPreferredSize(preferredSize);
            }
        }
        add(panel42, "cell 3 3,growy");

        //======== panel40 ========
        {
            panel40.setForeground(new Color(0x0448d2));
            panel40.setBackground(new Color(0x0448d2));
            panel40.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel40.getComponentCount(); i++) {
                    Rectangle bounds = panel40.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel40.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel40.setMinimumSize(preferredSize);
                panel40.setPreferredSize(preferredSize);
            }
        }
        add(panel40, "cell 4 3,growy");

        //======== panel47 ========
        {
            panel47.setForeground(new Color(0x0448d2));
            panel47.setBackground(new Color(0x0448d2));
            panel47.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel47.getComponentCount(); i++) {
                    Rectangle bounds = panel47.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel47.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel47.setMinimumSize(preferredSize);
                panel47.setPreferredSize(preferredSize);
            }
        }
        add(panel47, "cell 2 4,grow");

        //======== scrollPane3 ========
        {
            scrollPane3.setBorder(new EtchedBorder());
            scrollPane3.setViewportView(list1);
        }
        add(scrollPane3, "cell 0 5,grow");

        //======== panel2 ========
        {
            panel2.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2, "cell 0 5");

        //======== panel46 ========
        {
            panel46.setForeground(new Color(0x0448d2));
            panel46.setBackground(new Color(0x0448d2));
            panel46.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel46.getComponentCount(); i++) {
                    Rectangle bounds = panel46.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel46.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel46.setMinimumSize(preferredSize);
                panel46.setPreferredSize(preferredSize);
            }
        }
        add(panel46, "cell 1 5,growy");

        //======== panel1 ========
        {
            panel1.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1, "cell 2 5");

        //======== panel4 ========
        {
            panel4.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[grow,fill]" +
                "[183,fill]" +
                "[182,fill]" +
                "[36,grow,fill]",
                // rows
                "[]" +
                "[]para" +
                "[]para" +
                "[]para" +
                "[]para" +
                "[]0" +
                "[]"));

            //---- label4 ----
            label4.setText("Name");
            label4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(label4, "cell 1 1,align center center,grow 0 0");

            //---- textField1 ----
            textField1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(textField1, "cell 2 1");

            //---- label5 ----
            label5.setText("Page");
            label5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(label5, "cell 1 2,align center center,grow 0 0");

            //---- comboBox7 ----
            comboBox7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            comboBox7.setModel(new DefaultComboBoxModel<>(new String[] {
                "Static",
                "Dinamic",
                "Infinite Scroll"
            }));
            panel4.add(comboBox7, "cell 2 2");

            //---- label6 ----
            label6.setText("Domain");
            label6.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(label6, "cell 1 3,align center center,grow 0 0");

            //---- comboBox6 ----
            comboBox6.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            comboBox6.setModel(new DefaultComboBoxModel<>(new String[] {
                "Single Page",
                "Multiple Pages"
            }));
            panel4.add(comboBox6, "cell 2 3");

            //---- label7 ----
            label7.setText("Number");
            label7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(label7, "cell 1 4,align center center,grow 0 0");

            //---- comboBox5 ----
            comboBox5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            comboBox5.setModel(new DefaultComboBoxModel<>(new String[] {
                "Singular",
                "Multiple"
            }));
            panel4.add(comboBox5, "cell 2 4");

            //---- label8 ----
            label8.setText("Rating");
            label8.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel4.add(label8, "cell 1 5,align center center,grow 0 0");

            //---- comboBox4 ----
            comboBox4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            comboBox4.setModel(new DefaultComboBoxModel<>(new String[] {
                "None",
                "Safe",
                "Average",
                "High Risk",
                "BAD"
            }));
            panel4.add(comboBox4, "cell 2 5");
        }
        add(panel4, "cell 2 5,grow");

        //======== panel48 ========
        {
            panel48.setForeground(new Color(0x0448d2));
            panel48.setBackground(new Color(0x0448d2));
            panel48.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel48.getComponentCount(); i++) {
                    Rectangle bounds = panel48.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel48.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel48.setMinimumSize(preferredSize);
                panel48.setPreferredSize(preferredSize);
            }
        }
        add(panel48, "cell 3 5,growy");

        //======== scrollPane4 ========
        {
            scrollPane4.setBorder(new EtchedBorder());
            scrollPane4.setViewportView(list2);
        }
        add(scrollPane4, "cell 4 5,grow");

        //======== panel3 ========
        {
            panel3.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        add(panel3, "cell 4 5");

        //======== panel49 ========
        {
            panel49.setForeground(new Color(0x0448d2));
            panel49.setBackground(new Color(0x0448d2));
            panel49.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel49.getComponentCount(); i++) {
                    Rectangle bounds = panel49.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel49.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel49.setMinimumSize(preferredSize);
                panel49.setPreferredSize(preferredSize);
            }
        }
        add(panel49, "cell 1 6,growy");

        //======== panel51 ========
        {
            panel51.setForeground(new Color(0x0448d2));
            panel51.setBackground(new Color(0x0448d2));
            panel51.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel51.getComponentCount(); i++) {
                    Rectangle bounds = panel51.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel51.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel51.setMinimumSize(preferredSize);
                panel51.setPreferredSize(preferredSize);
            }
        }
        add(panel51, "cell 3 6,growy");

        //======== panel50 ========
        {
            panel50.setForeground(new Color(0x0448d2));
            panel50.setBackground(new Color(0x0448d2));
            panel50.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel50.getComponentCount(); i++) {
                    Rectangle bounds = panel50.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel50.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel50.setMinimumSize(preferredSize);
                panel50.setPreferredSize(preferredSize);
            }
        }
        add(panel50, "cell 0 7,growy");

        //======== panel53 ========
        {
            panel53.setForeground(new Color(0x0448d2));
            panel53.setBackground(new Color(0x0448d2));
            panel53.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel53.getComponentCount(); i++) {
                    Rectangle bounds = panel53.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel53.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel53.setMinimumSize(preferredSize);
                panel53.setPreferredSize(preferredSize);
            }
        }
        add(panel53, "cell 2 7,growy");

        //======== panel52 ========
        {
            panel52.setForeground(new Color(0x0448d2));
            panel52.setBackground(new Color(0x0448d2));
            panel52.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel52.getComponentCount(); i++) {
                    Rectangle bounds = panel52.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel52.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel52.setMinimumSize(preferredSize);
                panel52.setPreferredSize(preferredSize);
            }
        }
        add(panel52, "cell 4 7,growy");

        //---- label1 ----
        label1.setText("Insert Task Name");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label1, "cell 0 9,align center center,grow 0 0");

        //======== panel55 ========
        {
            panel55.setForeground(new Color(0x0448d2));
            panel55.setBackground(new Color(0x0448d2));
            panel55.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel55.getComponentCount(); i++) {
                    Rectangle bounds = panel55.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel55.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel55.setMinimumSize(preferredSize);
                panel55.setPreferredSize(preferredSize);
            }
        }
        add(panel55, "cell 1 9,growy");

        //---- label3 ----
        label3.setText("Save or Delete Task");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label3, "cell 2 9,align center center,grow 0 0");

        //======== panel56 ========
        {
            panel56.setForeground(new Color(0x0448d2));
            panel56.setBackground(new Color(0x0448d2));
            panel56.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel56.getComponentCount(); i++) {
                    Rectangle bounds = panel56.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel56.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel56.setMinimumSize(preferredSize);
                panel56.setPreferredSize(preferredSize);
            }
        }
        add(panel56, "cell 3 9,growy");

        //---- label2 ----
        label2.setText("Insert URL");
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label2, "cell 4 9,align center center,grow 0 0");

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            textArea1.setForeground(new Color(0x0448d2));
            scrollPane1.setViewportView(textArea1);
        }
        add(scrollPane1, "cell 0 10,growx");

        //======== panel57 ========
        {
            panel57.setForeground(new Color(0x0448d2));
            panel57.setBackground(new Color(0x0448d2));
            panel57.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel57.getComponentCount(); i++) {
                    Rectangle bounds = panel57.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel57.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel57.setMinimumSize(preferredSize);
                panel57.setPreferredSize(preferredSize);
            }
        }
        add(panel57, "cell 1 10,growy");

        //---- button5 ----
        button5.setText("Save");
        button5.setForeground(new Color(0x0448d2));
        button5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button5, "cell 2 10");

        //======== panel58 ========
        {
            panel58.setForeground(new Color(0x0448d2));
            panel58.setBackground(new Color(0x0448d2));
            panel58.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel58.getComponentCount(); i++) {
                    Rectangle bounds = panel58.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel58.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel58.setMinimumSize(preferredSize);
                panel58.setPreferredSize(preferredSize);
            }
        }
        add(panel58, "cell 3 10,growy");

        //======== scrollPane2 ========
        {

            //---- textArea2 ----
            textArea2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            textArea2.setForeground(new Color(0x0448d2));
            scrollPane2.setViewportView(textArea2);
        }
        add(scrollPane2, "cell 4 10");

        //---- button3 ----
        button3.setText("Save");
        button3.setForeground(new Color(0x0448d2));
        button3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button3, "cell 0 11");

        //======== panel61 ========
        {
            panel61.setForeground(new Color(0x0448d2));
            panel61.setBackground(new Color(0x0448d2));
            panel61.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel61.getComponentCount(); i++) {
                    Rectangle bounds = panel61.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel61.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel61.setMinimumSize(preferredSize);
                panel61.setPreferredSize(preferredSize);
            }
        }
        add(panel61, "cell 1 11,growy");

        //---- button6 ----
        button6.setText("Delete");
        button6.setForeground(new Color(0x0448d2));
        button6.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button6, "cell 2 11");

        //======== panel62 ========
        {
            panel62.setForeground(new Color(0x0448d2));
            panel62.setBackground(new Color(0x0448d2));
            panel62.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel62.getComponentCount(); i++) {
                    Rectangle bounds = panel62.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel62.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel62.setMinimumSize(preferredSize);
                panel62.setPreferredSize(preferredSize);
            }
        }
        add(panel62, "cell 3 11,growy");

        //---- button4 ----
        button4.setText("Save");
        button4.setForeground(new Color(0x0448d2));
        button4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button4, "cell 4 11");

        //---- button7 ----
        button7.setText("Delete");
        button7.setForeground(new Color(0x0448d2));
        button7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button7, "cell 0 12");

        //======== panel65 ========
        {
            panel65.setForeground(new Color(0x0448d2));
            panel65.setBackground(new Color(0x0448d2));
            panel65.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel65.getComponentCount(); i++) {
                    Rectangle bounds = panel65.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel65.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel65.setMinimumSize(preferredSize);
                panel65.setPreferredSize(preferredSize);
            }
        }
        add(panel65, "cell 1 12,growy");

        //======== panel66 ========
        {
            panel66.setForeground(new Color(0x0448d2));
            panel66.setBackground(new Color(0x0448d2));
            panel66.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel66.getComponentCount(); i++) {
                    Rectangle bounds = panel66.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel66.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel66.setMinimumSize(preferredSize);
                panel66.setPreferredSize(preferredSize);
            }
        }
        add(panel66, "cell 3 12,growy");

        //---- button9 ----
        button9.setText("Delete");
        button9.setForeground(new Color(0x0448d2));
        button9.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(button9, "cell 4 12");

        //======== panel59 ========
        {
            panel59.setForeground(new Color(0x0448d2));
            panel59.setBackground(new Color(0x0448d2));
            panel59.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel59.getComponentCount(); i++) {
                    Rectangle bounds = panel59.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel59.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel59.setMinimumSize(preferredSize);
                panel59.setPreferredSize(preferredSize);
            }
        }
        add(panel59, "cell 0 13,growy");

        //======== panel67 ========
        {
            panel67.setForeground(new Color(0x0448d2));
            panel67.setBackground(new Color(0x0448d2));
            panel67.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel67.getComponentCount(); i++) {
                    Rectangle bounds = panel67.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel67.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel67.setMinimumSize(preferredSize);
                panel67.setPreferredSize(preferredSize);
            }
        }
        add(panel67, "cell 4 13,growy");

        //======== panel63 ========
        {
            panel63.setForeground(new Color(0x0448d2));
            panel63.setBackground(new Color(0x0448d2));
            panel63.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel63.getComponentCount(); i++) {
                    Rectangle bounds = panel63.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel63.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel63.setMinimumSize(preferredSize);
                panel63.setPreferredSize(preferredSize);
            }
        }
        add(panel63, "cell 1 14,growy");

        //======== panel70 ========
        {
            panel70.setForeground(new Color(0x0448d2));
            panel70.setBackground(new Color(0x0448d2));
            panel70.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel70.getComponentCount(); i++) {
                    Rectangle bounds = panel70.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel70.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel70.setMinimumSize(preferredSize);
                panel70.setPreferredSize(preferredSize);
            }
        }
        add(panel70, "cell 3 14,grow");

        //======== panel68 ========
        {
            panel68.setForeground(new Color(0x0448d2));
            panel68.setBackground(new Color(0x0448d2));
            panel68.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel68.getComponentCount(); i++) {
                    Rectangle bounds = panel68.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel68.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel68.setMinimumSize(preferredSize);
                panel68.setPreferredSize(preferredSize);
            }
        }
        add(panel68, "cell 0 15,growy");

        //======== panel69 ========
        {
            panel69.setForeground(new Color(0x0448d2));
            panel69.setBackground(new Color(0x0448d2));
            panel69.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel69.getComponentCount(); i++) {
                    Rectangle bounds = panel69.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel69.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel69.setMinimumSize(preferredSize);
                panel69.setPreferredSize(preferredSize);
            }
        }
        add(panel69, "cell 2 15,growy");

        //======== panel60 ========
        {
            panel60.setForeground(new Color(0x0448d2));
            panel60.setBackground(new Color(0x0448d2));
            panel60.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel60.getComponentCount(); i++) {
                    Rectangle bounds = panel60.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel60.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel60.setMinimumSize(preferredSize);
                panel60.setPreferredSize(preferredSize);
            }
        }
        add(panel60, "cell 4 15,growy");

        //======== panel64 ========
        {
            panel64.setForeground(new Color(0x0448d2));
            panel64.setBackground(new Color(0x0448d2));
            panel64.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel64.getComponentCount(); i++) {
                    Rectangle bounds = panel64.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel64.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel64.setMinimumSize(preferredSize);
                panel64.setPreferredSize(preferredSize);
            }
        }
        add(panel64, "cell 1 16");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JPanel panel32;
    private JLabel jLabel3;
    private JPanel panel45;
    private JPanel panel38;
    private JPanel panel34;
    private JPanel panel37;
    private JPanel panel44;
    private JPanel panel39;
    private JLabel jLabel1;
    private JPanel panel31;
    private JLabel jLabel4;
    private JPanel panel43;
    private JLabel jLabel2;
    private JPanel panel35;
    private JPanel panel33;
    private JPanel panel42;
    private JPanel panel40;
    private JPanel panel47;
    private JScrollPane scrollPane3;
    private JList list1;
    private JPanel panel2;
    private JPanel panel46;
    private JPanel panel1;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label5;
    private JComboBox<String> comboBox7;
    private JLabel label6;
    private JComboBox<String> comboBox6;
    private JLabel label7;
    private JComboBox<String> comboBox5;
    private JLabel label8;
    private JComboBox<String> comboBox4;
    private JPanel panel48;
    private JScrollPane scrollPane4;
    private JList list2;
    private JPanel panel3;
    private JPanel panel49;
    private JPanel panel51;
    private JPanel panel50;
    private JPanel panel53;
    private JPanel panel52;
    private JLabel label1;
    private JPanel panel55;
    private JLabel label3;
    private JPanel panel56;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel panel57;
    private JButton button5;
    private JPanel panel58;
    private JScrollPane scrollPane2;
    private JTextArea textArea2;
    private JButton button3;
    private JPanel panel61;
    private JButton button6;
    private JPanel panel62;
    private JButton button4;
    private JButton button7;
    private JPanel panel65;
    private JPanel panel66;
    private JButton button9;
    private JPanel panel59;
    private JPanel panel67;
    private JPanel panel63;
    private JPanel panel70;
    private JPanel panel68;
    private JPanel panel69;
    private JPanel panel60;
    private JPanel panel64;
    // End of variables declaration//GEN-END:variables


}
