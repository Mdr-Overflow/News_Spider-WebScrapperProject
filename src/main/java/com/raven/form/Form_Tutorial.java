package com.raven.form;

//import com.intellij.uiDesigner.core.*;

import javax.swing.border.*;
import javax.swing.table.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
//import org.jdesktop.swingx.*;

public class Form_Tutorial extends JPanel {


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
                    Form_Tutorial frame = new Form_Tutorial();
                    //frame.setBackground(Color.yellow);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }







    public Form_Tutorial() {
        initComponents();
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        //setOpaque(false);
        initData();
        //---- button5 ----
        button5.setText("Back");
        button5.setForeground(new Color(0x0448d2));
        button5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
            }
        });
        add(button5, "cell 0 3,aligny center,grow 100 0");

    }

    private void initData() {

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button5 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 5 5",
            // columns
            "[:173:500,grow,fill]" +
            "[grow,fill]" +
            "[:119:500,grow,fill]",
            // rows
            "[10,grow]" +
            "[fill]" +
            "[grow,fill]" +
            "[129]"));

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setText("Instructions Page");
        jLabel1.setIcon(new ImageIcon("Resources\\GoogleInstr.png"));
        jLabel1.setForeground(new Color(0x0448d2));
        add(jLabel1, "cell 1 1,align center center,grow 0 0");

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[0,grow,fill]" +
                "[636,grow 300,fill]" +
                "[grow,fill]",
                // rows
                "[]" +
                "[440,grow 300,fill]" +
                "[]"));

            //======== scrollPane1 ========
            {

                //---- textArea1 ----
                textArea1.setEditable(false);
                textArea1.setText("Welcome to News Spider\n\nHello and welcome!\n\nWe're excited to introduce you to the world of web scraping. This powerful tool allows you to extract data from websites quickly and efficiently. But like any powerful tool, it's essential to use it responsibly and securely. Before we get into the details of how to use your web scraper, let's talk about some important safety guidelines.\nSafe and Secure Web Scraping\n\nWhen choosing which websites to scrape, your safety and the website's integrity should be your top priorities. Here are some key points to remember:\n\n    Secure Pages: Always look for secure websites to scrape data from. Secure pages use the HTTPS protocol, which encrypts the data between your web scraper and the website's server, protecting it from being read by third parties. You can tell if a website uses HTTPS by looking at the URL in your browser. If it begins with \"https://\", it's secure.\n\n    Trusted Certificates: Along with looking for HTTPS pages, it's also vital to ensure that the website has a trusted SSL certificate. An SSL certificate is a type of digital certificate that provides authentication for a website and enables an encrypted connection. Websites with trusted SSL certificates have validated their identity with a trusted certificate authority.\n\n    Trustworthy Websites: Even if a page is secure and has a trusted certificate, it's still crucial to consider the reputation of the site. Be sure to only scrape data from websites that you trust, or that have a positive reputation. Remember, just because a site is secure, doesn't mean it's trustworthy. Research the site and make sure it's a legitimate, reputable source before you start scraping.\n\nHow to Use a Web Scraper\n\nNow that you know about the importance of security in web scraping, let's dive into how to use your web scraper.\n\n    Identify the Target: First, identify the data you want to scrape. It could be anything from product information, user reviews, article text, etc.\n\n    Inspect the Website: Using your web browser's tools, inspect the HTML of the webpage. Identify the HTML tags that hold the data you want.\n\n    Configure the Scraper: Set up your web scraper. This involves defining the URL to scrape, the data to collect, and the structure of the output.\n\n    Run the Scraper: Once you've configured the scraper, you can run it. It will visit the URL, extract the data you defined, and save it in the format you specified.\n\n    Review the Data: After the scraper has run, review the collected data. Ensure it collected everything correctly.\n\n    Respect the Rules: Always respect the website's rules and policies, including the terms of service and robots.txt file. Scraping can be legally and ethically complex, so it's vital to scrape responsibly");
                textArea1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                textArea1.setLineWrap(true);
                scrollPane1.setViewportView(textArea1);
            }
            panel1.add(scrollPane1, "cell 1 1,grow");
        }
        add(panel1, "cell 1 2,growy");

        //---- button5 ----
        button5.setText("Back");
        button5.setForeground(new Color(0x0448d2));
        button5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        add(button5, "cell 0 3,aligny center,grow 100 0");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JPanel panel1;

    public JButton getButton5() {
        return button5;
    }

    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button5;
    // End of variables declaration//GEN-END:variables


}
