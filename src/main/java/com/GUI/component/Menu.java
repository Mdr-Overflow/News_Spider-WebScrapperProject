package com.GUI.component;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import com.GUI.event.EventMenu;
import com.GUI.event.EventMenuSelected;
import com.GUI.event.EventShowPopupMenu;
import com.GUI.model.ModelMenu;
import com.GUI.swing.MenuAnimation;
import com.GUI.swing.MenuItem;
import com.GUI.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleStart.png"), "Results Page", "Results Page"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleQuery.png"), "Query Page", "Query Page"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleCookie.png"), "Cookies Page", "Cookies Page"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleProxie.png"), "Proxies", "Proxies"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleFilter.png"), "Filters", "Filters"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleTask.png"), "Pages", "Tasks"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleMenu.png"), "Main Menu", "Menu"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleSettings.png"), "Settings", "Options"));
        addMenu(new ModelMenu(new ImageIcon("Resources/GoogleDatabase.png"), "Database Manager", "DB Manager"));



    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        sp = new JScrollPane();
        panel = new JPanel();
        profile1 = new Profile();

        //======== this ========

        //======== sp ========
        {
            sp.setBorder(null);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setViewportBorder(null);

            //======== panel ========
            {
                panel.setOpaque(false);

                GroupLayout panelLayout = new GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                    panelLayout.createParallelGroup()
                        .addGap(0, 312, Short.MAX_VALUE)
                );
                panelLayout.setVerticalGroup(
                    panelLayout.createParallelGroup()
                        .addGap(0, 523, Short.MAX_VALUE)
                );
            }
            sp.setViewportView(panel);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(sp, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addComponent(profile1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(profile1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JScrollPane sp;
    private JPanel panel;
    private Profile profile1;
    // End of variables declaration//GEN-END:variables
}
