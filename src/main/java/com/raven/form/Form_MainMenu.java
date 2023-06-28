package com.raven.form;

//import com.intellij.uiDesigner.core.*;

import javax.swing.border.*;
import javax.swing.table.*;

import GUI.Options;
import com.formdev.flatlaf.FlatLightLaf;
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
//import org.jdesktop.swingx.*;

public class Form_MainMenu extends JFrame {


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



    public Form_MainMenu() {
        initComponents();

        //setOpaque(false);
        initData();
    }

    private void initData() {

        initTableData();
    }

    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                if (showMessage("Delete Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }

            @Override
            public void update(ModelStudent student) {
                if (showMessage("Update Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
        };

    }


    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
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
        setLayout(new MigLayout(
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
            label1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\nodes.gif"));
            label1.setPreferredSize(new Dimension(600, 250));
            panel1.add(label1, "cell 0 0,grow");
        }
        add(panel1, "cell 1 1");

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
            jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\webDoodle-50x50.png"));
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
        add(panel2, "cell 3 1");
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
