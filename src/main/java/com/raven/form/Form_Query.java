package com.raven.form;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;
import javax.swing.table.*;
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

public class Form_Query extends JPanel {

    public Form_Query() {
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
        jLabel1 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        radioButton1 = new JRadioButton();
        button1 = new JButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        textField1 = new JTextField();
        comboBox1 = new JComboBox();
        button2 = new JButton();
        label4 = new JLabel();
        separator1 = new JSeparator();
        label5 = new JLabel();
        label6 = new JLabel();
        spinner1 = new JSpinner();
        label7 = new JLabel();
        button3 = new JButton();
        label8 = new JLabel();
        spinner2 = new JSpinner();

        //======== this ========

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Query Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleQuery.png"));

        //---- label1 ----
        label1.setText("Keyword");
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label1.setForeground(new Color(0x0448d2));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("Topics");
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label2.setForeground(new Color(0x0448d2));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("Query File");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label3.setForeground(new Color(0x0448d2));
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button1 ----
        button1.setText("Save");
        button1.setForeground(new Color(0x0448d2));
        button1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- textField1 ----
        textField1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- comboBox1 ----
        comboBox1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- button2 ----
        button2.setText("Choose File");
        button2.setForeground(new Color(0x0448d2));
        button2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- label4 ----
        label4.setText("Search Options");
        label4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        label4.setForeground(new Color(0x0448d2));
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label5 ----
        label5.setText("Page Options");
        label5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        label5.setForeground(new Color(0x0448d2));
        label5.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label6 ----
        label6.setText(" dynamic component wait time");
        label6.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label6.setForeground(new Color(0x0448d2));
        label6.setHorizontalAlignment(SwingConstants.CENTER);

        //---- spinner1 ----
        spinner1.setModel(new SpinnerNumberModel(100, 50, 1000, 10));
        spinner1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- label7 ----
        label7.setText("ms");
        label7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label7.setForeground(new Color(0x0448d2));
        label7.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button3 ----
        button3.setText("Reset to Defaults");
        button3.setForeground(new Color(0x0448d2));
        button3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        //---- label8 ----
        label8.setText("Infinite Page Scroll Iterattions");
        label8.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label8.setForeground(new Color(0x0448d2));
        label8.setHorizontalAlignment(SwingConstants.CENTER);

        //---- spinner2 ----
        spinner2.setModel(new SpinnerNumberModel(2, 1, 2, 1));
        spinner2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(radioButton1)
                                                .addComponent(radioButton2)
                                                .addComponent(radioButton3))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup()
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup()
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(28, 28, 28)
                                                    .addGroup(layout.createParallelGroup()
                                                        .addComponent(button2)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(143, 143, 143)
                                    .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(label8, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(spinner2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                    .addGap(97, 97, 97)
                                    .addComponent(button3))
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(158, 158, 158)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
                    .addGap(233, 233, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2))
                                .addComponent(radioButton3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JRadioButton radioButton1;
    private JButton button1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton button2;
    private JLabel label4;
    private JSeparator separator1;
    private JLabel label5;
    private JLabel label6;
    private JSpinner spinner1;
    private JLabel label7;
    private JButton button3;
    private JLabel label8;
    private JSpinner spinner2;
    // End of variables declaration//GEN-END:variables
}
