package com.raven.main;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

import DB.Create_Tables;
import DB.DB_UA;
import GUI.DB_WINDOW;
import GUI.Proper.OptionsProper;
import GUI.Proper.URLProper;
import Proj.Headers;
import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.form.*;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import static GUI.Options.resizeWindowAndComponents;

public class Main extends javax.swing.JFrame {

    static int rate = -1;
    static int threads = -1;
    static String[] Real_URLS;
    private final JPanel panel_OPTIONS = new JPanel();
    private final JPanel panel_Urls = new JPanel();
    public static JProgressBar bar = null;

    private JLabel gifLabel;
    static int progress = 0;

    public static String[] options = {" ", " ", " ", " ", " ", " "};
    public static ArrayList<HashMap<String, String>> Agents;


    public String[] done = {"?"};

    private static double SCALE_FACTOR = 1.5;

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;

    public boolean show = true;

    private JFrame ParentFrame;

    public Main(JFrame parentFrame) {

        this.ParentFrame = parentFrame;
        initComponents();
        init();




    }



    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        setMinimumSize(new Dimension(1200,600));



        ///  ESTABLISH CONN TO THE DATABASE AND CREATE TABLES IN MEM IF THEY DON'T EXIST ALREADY
        try {
            Create_Tables ct = new Create_Tables();


            // CREATE DEFAULTS FOR USERAGENTS AND HEADERS
            DB_UA ua_db = new DB_UA();
            DB.DB_HEADERS hd_db = new DB.DB_HEADERS();

            ua_db.Populate_UA_DEFAULT();
            hd_db.Populate_HEADERS_DEFAULT();


            hd_db.Populate_HEADERS_DEFAULT();
            // CREATE AGENTS DB

            ua_db.END_CONNECTION();
            hd_db.END_CONNECTION();

            Headers h = new Headers();

            DB.DB_AGENTS ag_db = new DB.DB_AGENTS();
            ag_db.del_AGENTS();
            ag_db.Create_AGENTS(10);
            Agents = new ArrayList<HashMap<String, String>>(h.Generate_Agents(ag_db));


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "COULD NOT ESTABLISH CONNECTION TO DATABASE OR NOT ENOUGH MEMORY FOR TABLES");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            System.exit(-1);
        }



        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {

                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                int newSizeW = getWidth();
                int newSizeH = getHeight();

                System.out.println(newSizeW + "X" + newSizeH);
                resizeWindowAndComponents(frame, newSizeW, newSizeH);
            }

        });

        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Home(Main.this));
                    } else if (subMenuIndex == 1) {
                        main.showForm(new Form1());
                    }
                }
                if (menuIndex == 1) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Query());
                    }
                }

                if (menuIndex == 2) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Cookies());
                    }
                }

                if (menuIndex == 3) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_Proxies());
                    }
                }

                if (menuIndex == 4) {
                    if (subMenuIndex == 0) {
                        main.showForm(new Form_AI());
                    }
                }

                if (menuIndex == 5) {
                    if (subMenuIndex == 0) {
                        main.setLayout(new BorderLayout());
                        main.showForm(new Form_Tasks());
                    }
                }

                if (menuIndex == 6) {
                    if (subMenuIndex == 0) {
                        Main.this.setVisible(false);
                        Main.this.ParentFrame.setVisible(true);
                    }
                }  // MENU

                if (menuIndex == 7) {
                    if (subMenuIndex == 0) {
                        main.showForm(new OptionsProper(Main.this));
                    }
                }

                if (menuIndex == 8) {

                    System.out.println(done[0] + "<---------------------------------------------------------");
                    if (Objects.equals(done[0], "?")) {

                        DB_WINDOW dbw = new DB_WINDOW();
                        done[0] = "??";
                        DB_WINDOW.main(done);
                    } else if (Objects.equals(done[0], "Yes")) {
                        DB_WINDOW dbw = new DB_WINDOW();
                        done[0] = "??";
                        DB_WINDOW.main(done);

                    } else {
                        JOptionPane.showMessageDialog(Main.this,
                                "Only one instance of dbmanager allowed");
                    }

                    if (subMenuIndex == 0) {
                        System.out.println("a");
                    }
                }


            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new Form_Home(Main.this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        bg = new JLayeredPane();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 800));
        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\webDoodle-50x50.png").getImage());
        setTitle("NewsSpider");
        var contentPane = getContentPane();

        //======== bg ========
        {
            bg.setBackground(new Color(0xf5f5f5));
            bg.setOpaque(true);
            bg.setLayout(new FlowLayout());
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(bg)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(bg)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */



//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Main().setVisible(true);
//
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLayeredPane bg;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    // End of variables declaration//GEN-END:variables
}
