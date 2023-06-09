package com.raven.swing.noticeboard;

import javax.swing.*;
import javax.swing.GroupLayout;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class NoticeBoard extends javax.swing.JPanel {

    public NoticeBoard() {
        initComponents();
        setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        panel.setLayout(new MigLayout("nogrid, fillx"));
    }

    public void addNoticeBoard(ModelNoticeBoard data) {
        JLabel title = new JLabel(data.getTitle());
        title.setFont(new Font("sansserif", 1, 12));
        title.setForeground(data.getTitleColor());
        panel.add(title);
        JLabel time = new JLabel(data.getTime());
        time.setForeground(new Color(180, 180, 180));
        panel.add(time, "gap 10, wrap");
        JTextPane txt = new JTextPane();
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setForeground(new Color(120, 120, 120));
        txt.setSelectionColor(new Color(150, 150, 150));
        txt.setBorder(null);
        txt.setOpaque(false);
        txt.setEditable(false);
        txt.setText(data.getDescription());
        panel.add(txt, "w 100::90%, wrap");
    }

    public void addDate(String date) {
        JLabel lbDate = new JLabel(date);
        lbDate.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbDate.setFont(new Font("sansserif", 1, 13));
        lbDate.setForeground(new Color(80, 80, 80));
        panel.add(lbDate, "wrap");
    }

    public void scrollToTop() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jScrollPane1.getVerticalScrollBar().setValue(0);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        panel = new JPanel();

        //======== this ========

        //======== jScrollPane1 ========
        {
            jScrollPane1.setBorder(null);
            jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //======== panel ========
            {
                panel.setBackground(Color.white);

                GroupLayout panelLayout = new GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                    panelLayout.createParallelGroup()
                        .addGap(0, 329, Short.MAX_VALUE)
                );
                panelLayout.setVerticalGroup(
                    panelLayout.createParallelGroup()
                        .addGap(0, 351, Short.MAX_VALUE)
                );
            }
            jScrollPane1.setViewportView(panel);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JScrollPane jScrollPane1;
    private JPanel panel;
    // End of variables declaration//GEN-END:variables
}
