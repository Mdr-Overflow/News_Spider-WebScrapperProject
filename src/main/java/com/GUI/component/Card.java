package com.GUI.component;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;
import com.GUI.model.ModelCard;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import com.GUI.swing.*;

public class Card extends javax.swing.JPanel {

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }

    private Color colorGradient;

    public Card() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(112, 69, 246));
        colorGradient = new Color(255, 255, 255);
        pro.setBackground(new Color(255, 255, 255, 100));
        pro.setForeground(Color.WHITE);
    }

    public void setData(ModelCard data) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        lbTitle.setText(data.getTitle());
        lbValues.setText(df.format(data.getValues()));
        lbIcon.setIcon(data.getIcon());
        pro.setValue(data.getPercentage());
        lbPer.setText(df.format(data.getPercentage()) + "%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        lbTitle = new JLabel();
        lbValues = new JLabel();
        lbIcon = new JLabel();
        pro = new ProgressBarCustom();
        lbPer = new JLabel();

        //======== this ========
        setBorder(new EmptyBorder(10, 10, 10, 10));

        //---- lbTitle ----
        lbTitle.setFont(new Font("sansserif", Font.BOLD, 18));
        lbTitle.setForeground(new Color(0xe1e1e1));
        lbTitle.setText("Title");

        //---- lbValues ----
        lbValues.setFont(new Font("sansserif", Font.PLAIN, 24));
        lbValues.setForeground(new Color(0xe1e1e1));
        lbValues.setText("Values");

        //---- lbIcon ----
        lbIcon.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- lbPer ----
        lbPer.setForeground(Color.white);
        lbPer.setText("0%");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pro, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbPer))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(lbTitle)
                                .addComponent(lbValues))
                            .addGap(18, 18, 18)
                            .addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbTitle)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbValues))
                        .addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(lbPer, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(pro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight(), getBackground(), getWidth(), 0, colorGradient);
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel lbTitle;
    private JLabel lbValues;
    private JLabel lbIcon;
    private ProgressBarCustom pro;
    private JLabel lbPer;
    // End of variables declaration//GEN-END:variables
}
