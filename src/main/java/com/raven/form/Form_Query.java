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

        //---- button2 ----
        button2.setText("Choose File");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
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
                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(201, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 442, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel1)
                    .addGap(65, 65, 65)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(35, 35, 35)
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
                    .addGap(37, 37, 37)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE))
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
    // End of variables declaration//GEN-END:variables
}
