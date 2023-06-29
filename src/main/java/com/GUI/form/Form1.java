package com.GUI.form;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.*;

public class Form1 extends JPanel {

    public Form1() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();
        label8 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        label9 = new JLabel();
        label4 = new JLabel();
        label10 = new JLabel();
        label6 = new JLabel();
        label11 = new JLabel();
        label7 = new JLabel();
        label12 = new JLabel();

        //======== this ========
        setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[27,grow,fill]" +
                        "[10,fill]" +
                        "[10,fill]ind" +
                        "[27,fill]ind" +
                        "[fill]para" +
                        "[10,fill]0" +
                        "[119,grow,fill]0" +
                        "[19,fill]0" +
                        "[335,fill]ind" +
                        "[fill]ind" +
                        "[fill]ind" +
                        "[fill]" +
                        "[75,grow,fill]",
                // rows
                "[fill]" +
                        "[center]" +
                        "[28]para" +
                        "[]para" +
                        "[]para" +
                        "[]para" +
                        "[23]" +
                        "[45]" +
                        "[]" +
                        "[]"));

        //---- label1 ----
        label1.setText("Statistics");
        label1.setFont(new Font(Font.SANS_SERIF, label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 8));
        label1.setIcon(new ImageIcon("Resources\\GoogleStats.png"));
        add(label1, "cell 8 1,alignx center,growx 0");

        //---- label2 ----
        label2.setText("Pages Querried");
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label2, "cell 4 2");

        //---- label8 ----
        label8.setText("text");
        add(label8, "cell 6 2");

        //---- label3 ----
        label3.setText("Pages Querried");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label3, "cell 9 2");

        //---- label5 ----
        label5.setText("Succeses");
        label5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label5, "cell 4 3");

        //---- label9 ----
        label9.setText("text");
        add(label9, "cell 6 3");

        //---- label4 ----
        label4.setText("Safe Pages");
        label4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label4, "cell 4 4");

        //---- label10 ----
        label10.setText("text");
        add(label10, "cell 6 4");

        //---- label6 ----
        label6.setText("Blocked Pages");
        label6.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label6, "cell 4 5");

        //---- label11 ----
        label11.setText("text");
        add(label11, "cell 6 5");

        //---- label7 ----
        label7.setText("Error on Pages");
        label7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label7, "cell 4 6");

        //---- label12 ----
        label12.setText("text");
        add(label12, "cell 6 6");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel label1;
    private JLabel label2;
    private JLabel label8;
    private JLabel label3;
    private JLabel label5;
    private JLabel label9;
    private JLabel label4;
    private JLabel label10;
    private JLabel label6;
    private JLabel label11;
    private JLabel label7;
    private JLabel label12;
    // End of variables declaration//GEN-END:variables
}
