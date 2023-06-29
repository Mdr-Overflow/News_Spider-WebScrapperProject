package com.GUI.form;

//import com.intellij.uiDesigner.core.*;

import DB.Create_Tables;
import DB.DB_UA;
import GUI.DB_WINDOW;
import Proj.Headers;
import com.GUI.dialog.Message;
import com.GUI.main.Main;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
//import org.jdesktop.swingx.*;


class Form_TutorialE extends Form_Tutorial {
    private Form_MainMenu mainMenuInstance;

    // Constructor
    public Form_TutorialE(Form_MainMenu instance) {
        super(); // Call the constructor of parent class

        this.mainMenuInstance = instance;

        getButton5().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenuInstance.switchPanel("MainMenu");
            }
        });
    }
}


public class Form_MainMenu extends JFrame {


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


    public static void main(String[] args) {


        // ZOOM IN OPTION
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

//        FlatLightLaf.setup();


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form_MainMenu frame = new Form_MainMenu();
                    //frame.setBackground(Color.yellow);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private final JPanel panel_MainMenu = new JPanel(); // Main Menu Panel
    private final JFrame panel_Dashboard = new Main(this);
    private final JPanel panel_Instructions = new Form_TutorialE(this);
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);


    public Form_MainMenu() {

        this.setTitle("News Spider");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);

        // Create and add components to panel_MainMenu
        initComponents();


        ImageIcon img = new ImageIcon("Resources/webDoodle-50x50.png");
        setIconImage(img.getImage());


        mainPanel.add(panel_MainMenu, "MainMenu");
//        mainPanel.add(panel_Dashboard, "Dashboard");
        mainPanel.add(panel_Instructions, "Instructions");

        button2.addActionListener(e -> openDashboard());
        button3.addActionListener(e -> switchPanel("Instructions"));
        button4.addActionListener(e -> openDBManager());
        button5.addActionListener(e -> System.exit(0));

        setContentPane(mainPanel);
        panel_Dashboard.setVisible(false);
        switchPanel("MainMenu");

        initData();
    }

//    private void initComponents() {
//        button2 = new JButton("Dashboard");
//        button3 = new JButton("Instructions Page");
//        button4 = new JButton("Database Manager");
//        button5 = new JButton("Exit");
//    }

    // Method to switch panels
    void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private void openDBManager() {
        System.out.println(done[0] + "<---------------------------------------------------------");
        if (Objects.equals(done[0], "?")) {

            DB_WINDOW dbw = new DB_WINDOW();
            done[0] = "??";
            DB_WINDOW.main(done);
        } else if (Objects.equals(done[0], "Yes")) {
            DB_WINDOW dbw = new DB_WINDOW();
            done[0] = "??";
            DB_WINDOW.main(done);

        } else {
            JOptionPane.showMessageDialog(this,
                    "Only one instance of dbmanager allowed");
        }


    }

    private void initData() {

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
            Agents = new ArrayList<HashMap<String, String>>(h.Generate_Agents(ag_db));


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "COULD NOT ESTABLISH CONNECTION TO DATABASE OR NOT ENOUGH MEMORY FOR TABLES");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            System.exit(-1);
        }


//        initTableData();
    }

    private void openDashboard() {
        this.setVisible(false);
        panel_Dashboard.setVisible(true);
    }

//    public void closeDashboard(){
//        this.setVisible(true);
//        panel_Dashboard.setVisible(false);
//
//    }


    private boolean showMessage(String message) {
        Message obj = new Message(getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        panel_MainMenu.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[52:63:250,grow 10,fill]" +
                        "[:651:1000,grow,fill]" +
                        "[::50,grow 10,fill]" +
                        "[338,growprio 99,grow,fill]" +
                        "[grow,fill]",
                // rows
                "[grow]" +
                        "[:426:2000,grow,fill]" +
                        "[47:47:350,grow]"));

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

            //---- label1 ----
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(new ImageIcon("Resources\\nodes.gif"));
            label1.setPreferredSize(new Dimension(600, 250));
            panel1.add(label1, "cell 0 0,grow");
        }
        panel_MainMenu.add(panel1, "cell 1 1");

        //======== panel2 ========
        {
            panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[316,grow,fill]",
                    // rows
                    "[]" +
                            "[62]para" +
                            "[52:52:70,growprio 10,grow 10]para" +
                            "[52:52:70,grow 10]para" +
                            "[52:52:70,grow 10]para" +
                            "[52:52:70,grow 10]"));

            //---- jLabel1 ----
            jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
            jLabel1.setText("News Spider");
            jLabel1.setIcon(new ImageIcon("Resources\\webDoodle-50x50.png"));
            panel2.add(jLabel1, "cell 0 0,align center center,grow 0 0");

            //---- jLabel2 ----
            jLabel2.setFont(new Font("sansserif", Font.BOLD, 16));
            jLabel2.setForeground(new Color(0x0448d2));
            jLabel2.setText("~ Generalised Webscraper ~");
            jLabel2.setIcon(null);
            panel2.add(jLabel2, "cell 0 1,align center top,grow 0 0");

            //---- button2 ----
            button2.setText("Dashboard");
            button2.setForeground(new Color(0x0448d2));
            button2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            panel2.add(button2, "cell 0 2,growy");

            //---- button3 ----
            button3.setText("Instructions Page");
            button3.setForeground(new Color(0x0448d2));
            button3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            panel2.add(button3, "cell 0 3,growy");

            //---- button4 ----
            button4.setText("Database Manager");
            button4.setForeground(new Color(0x0448d2));
            button4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            panel2.add(button4, "cell 0 4,growy");

            //---- button5 ----
            button5.setText("Exit");
            button5.setForeground(new Color(0x0448d2));
            button5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            panel2.add(button5, "cell 0 5,growy");
        }
        panel_MainMenu.add(panel2, "cell 3 1");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // End of variables declaration//GEN-END:variables
}
