package com.GUI.component;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();

        //======== this ========

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 24));
        jLabel1.setForeground(new Color(0xe0e0e0));
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\webDoodle-50x50.png"));
        jLabel1.setText("Scrapper");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
