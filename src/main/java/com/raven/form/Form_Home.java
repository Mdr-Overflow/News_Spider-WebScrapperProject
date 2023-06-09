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
        panel1 = new JPanel();
        button1 = new JButton();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[39,left]" +
            "[fill]" +
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

        //======== panel1 ========
        {
            panel1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            panel1.setLayout(new BorderLayout());
        }
        add(panel1, "cell 3 3");

        //---- button1 ----
        button1.setText("Start");
        button1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        add(button1, "cell 3 4");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JPanel panel1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
