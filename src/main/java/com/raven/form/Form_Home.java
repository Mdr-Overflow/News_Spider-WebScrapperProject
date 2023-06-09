package com.raven.form;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;
import javax.swing.table.*;
import com.jgoodies.forms.layout.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelCard;
import com.raven.model.ModelStudent;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import com.raven.swing.noticeboard.*;
import com.raven.swing.noticeboard.ModelNoticeBoard;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.miginfocom.swing.*;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();

        setOpaque(false);
        initData();
    }

    private void initData() {

        initTableData();
    }

    private void initTableData() {
        EventAction eventAction = new EventAction() {

            @Override
            public void delete(ModelStudent student) {

            }

            @Override
            public void update(ModelStudent student) {

            }
        };

    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        separator2 = new JSeparator();
        panel2 = new JPanel();
        label1 = new JLabel();
        separator1 = new JSeparator();
        radioButton2 = new JRadioButton();
        radioButton1 = new JRadioButton();
        label2 = new JLabel();
        spinner1 = new JSpinner();
        comboBox1 = new JComboBox<>();
        label3 = new JLabel();
        spinner2 = new JSpinner();
        comboBox2 = new JComboBox<>();
        panel1 = new JPanel();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[39,left]" +
            "[fill]" +
            "[fill]" +
            "[20:47,fill]" +
            "[grow,fill]" +
            "[400:580:800,grow,fill]" +
            "[170,grow,fill]",
            // rows
            "[]" +
            "[2,fill]" +
            "[grow]" +
            "[400,grow,fill]" +
            "[grow,center]"));

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Results Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleStart.png"));
        add(jLabel1, "cell 1 1");
        add(separator2, "cell 1 3");

        //======== panel2 ========
        {
            panel2.setMinimumSize(new Dimension(300, 124));
            panel2.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 0 0",
                // columns
                "[300:n,fill]",
                // rows
                "[fill]" +
                "[fill]" +
                "[36,fill]" +
                "[]" +
                "[38,fill]" +
                "[51,fill]" +
                "[fill]" +
                "[50,center]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- label1 ----
            label1.setText("Mode");
            label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            label1.setForeground(new Color(0x0448d2));
            panel2.add(label1, "cell 0 0,align center center,grow 0 0");
            panel2.add(separator1, "cell 0 1");

            //---- radioButton2 ----
            radioButton2.setText("Scraper Mode");
            radioButton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel2.add(radioButton2, "cell 0 3,alignx center,growx 0");

            //---- radioButton1 ----
            radioButton1.setText("Crawler Mode");
            radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel2.add(radioButton1, "cell 0 5,alignx center,growx 0");

            //---- label2 ----
            label2.setText("Total Time");
            label2.setMaximumSize(new Dimension(60, 16));
            panel2.add(label2, "cell 0 7");
            panel2.add(spinner1, "cell 0 7");

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "seconds",
                "minutes",
                "hours"
            }));
            panel2.add(comboBox1, "cell 0 7");

            //---- label3 ----
            label3.setText("Refresh At");
            label3.setMaximumSize(new Dimension(60, 16));
            panel2.add(label3, "cell 0 8");
            panel2.add(spinner2, "cell 0 8");

            //---- comboBox2 ----
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "seconds",
                "minutes",
                "hours"
            }));
            panel2.add(comboBox2, "cell 0 8");
        }
        add(panel2, "cell 1 3,aligny top,growy 0");

        //======== panel1 ========
        {
            panel1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            panel1.setLayout(new BorderLayout());
        }
        add(panel1, "cell 5 3");

        //---- button1 ----
        button1.setText("Start");
        button1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        add(button1, "cell 5 4");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JSeparator separator2;
    private JPanel panel2;
    private JLabel label1;
    private JSeparator separator1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton1;
    private JLabel label2;
    private JSpinner spinner1;
    private JComboBox<String> comboBox1;
    private JLabel label3;
    private JSpinner spinner2;
    private JComboBox<String> comboBox2;
    private JPanel panel1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
