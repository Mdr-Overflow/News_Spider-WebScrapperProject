/*
 * Created by JFormDesigner on Fri Jun 09 03:00:17 EEST 2023
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author ADMIN
 */
public class DisplayAppProper extends JFrame {
    public DisplayAppProper() {
        initComponents();
    }

    private void btn_1MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_2MouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_3MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_4MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void jPanel2MouseDragged(MouseEvent e) {
        // TODO add your code here
    }

    private void jPanel2MousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void btn_exitMousePressed(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Madaras Andrei
        side_pane = new JPanel();
        btn_1 = new JPanel();
        ind_1 = new JPanel();
        jLabel8 = new JLabel();
        btn_2 = new JPanel();
        ind_2 = new JPanel();
        jLabel9 = new JLabel();
        btn_3 = new JPanel();
        ind_3 = new JPanel();
        jLabel10 = new JLabel();
        btn_4 = new JPanel();
        ind_4 = new JPanel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jPanel2 = new JPanel();
        jTextField1 = new JTextField();
        jLabel7 = new JLabel();
        jPanel6 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.white);
        setLocationByPlatform(true);
        setUndecorated(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== side_pane ========
        {
            side_pane.setBackground(new Color(0x172333));
            side_pane.setLayout(null);

            //======== btn_1 ========
            {
                btn_1.setBackground(new Color(0x172333));
                btn_1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        btn_1MousePressed(e);
                    }
                });

                //======== ind_1 ========
                {
                    ind_1.setOpaque(false);
                    ind_1.setPreferredSize(new Dimension(3, 43));

                    GroupLayout ind_1Layout = new GroupLayout(ind_1);
                    ind_1.setLayout(ind_1Layout);
                    ind_1Layout.setHorizontalGroup(
                        ind_1Layout.createParallelGroup()
                            .addGap(0, 3, Short.MAX_VALUE)
                    );
                    ind_1Layout.setVerticalGroup(
                        ind_1Layout.createParallelGroup()
                            .addGap(0, 43, Short.MAX_VALUE)
                    );
                }

                //---- jLabel8 ----
                jLabel8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                jLabel8.setForeground(Color.white);
                jLabel8.setText("Results Page");

                GroupLayout btn_1Layout = new GroupLayout(btn_1);
                btn_1.setLayout(btn_1Layout);
                btn_1Layout.setHorizontalGroup(
                    btn_1Layout.createParallelGroup()
                        .addGroup(btn_1Layout.createSequentialGroup()
                            .addComponent(ind_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addGap(0, 33, Short.MAX_VALUE))
                );
                btn_1Layout.setVerticalGroup(
                    btn_1Layout.createParallelGroup()
                        .addGroup(btn_1Layout.createSequentialGroup()
                            .addComponent(ind_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(btn_1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            side_pane.add(btn_1);
            btn_1.setBounds(0, 100, 120, btn_1.getPreferredSize().height);

            //======== btn_2 ========
            {
                btn_2.setBackground(new Color(0x172333));
                btn_2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        btn_2MouseReleased(e);
                    }
                });

                //======== ind_2 ========
                {
                    ind_2.setOpaque(false);
                    ind_2.setPreferredSize(new Dimension(3, 43));

                    GroupLayout ind_2Layout = new GroupLayout(ind_2);
                    ind_2.setLayout(ind_2Layout);
                    ind_2Layout.setHorizontalGroup(
                        ind_2Layout.createParallelGroup()
                            .addGap(0, 3, Short.MAX_VALUE)
                    );
                    ind_2Layout.setVerticalGroup(
                        ind_2Layout.createParallelGroup()
                            .addGap(0, 43, Short.MAX_VALUE)
                    );
                }

                //---- jLabel9 ----
                jLabel9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                jLabel9.setForeground(Color.white);
                jLabel9.setText("Proxies");

                GroupLayout btn_2Layout = new GroupLayout(btn_2);
                btn_2.setLayout(btn_2Layout);
                btn_2Layout.setHorizontalGroup(
                    btn_2Layout.createParallelGroup()
                        .addGroup(btn_2Layout.createSequentialGroup()
                            .addComponent(ind_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addGap(0, 61, Short.MAX_VALUE))
                );
                btn_2Layout.setVerticalGroup(
                    btn_2Layout.createParallelGroup()
                        .addGroup(btn_2Layout.createSequentialGroup()
                            .addComponent(ind_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }
            side_pane.add(btn_2);
            btn_2.setBounds(0, 220, 120, btn_2.getPreferredSize().height);

            //======== btn_3 ========
            {
                btn_3.setBackground(new Color(0x172333));
                btn_3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        btn_3MousePressed(e);
                    }
                });

                //======== ind_3 ========
                {
                    ind_3.setOpaque(false);
                    ind_3.setPreferredSize(new Dimension(3, 43));

                    GroupLayout ind_3Layout = new GroupLayout(ind_3);
                    ind_3.setLayout(ind_3Layout);
                    ind_3Layout.setHorizontalGroup(
                        ind_3Layout.createParallelGroup()
                            .addGap(0, 3, Short.MAX_VALUE)
                    );
                    ind_3Layout.setVerticalGroup(
                        ind_3Layout.createParallelGroup()
                            .addGap(0, 43, Short.MAX_VALUE)
                    );
                }

                //---- jLabel10 ----
                jLabel10.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                jLabel10.setForeground(Color.white);
                jLabel10.setText("Query");

                GroupLayout btn_3Layout = new GroupLayout(btn_3);
                btn_3.setLayout(btn_3Layout);
                btn_3Layout.setHorizontalGroup(
                    btn_3Layout.createParallelGroup()
                        .addGroup(btn_3Layout.createSequentialGroup()
                            .addComponent(ind_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel10)
                            .addGap(0, 67, Short.MAX_VALUE))
                );
                btn_3Layout.setVerticalGroup(
                    btn_3Layout.createParallelGroup()
                        .addGroup(btn_3Layout.createSequentialGroup()
                            .addComponent(ind_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(btn_3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            side_pane.add(btn_3);
            btn_3.setBounds(0, 140, 120, btn_3.getPreferredSize().height);

            //======== btn_4 ========
            {
                btn_4.setBackground(new Color(0x172333));
                btn_4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        btn_4MousePressed(e);
                    }
                });

                //======== ind_4 ========
                {
                    ind_4.setOpaque(false);
                    ind_4.setPreferredSize(new Dimension(3, 43));

                    GroupLayout ind_4Layout = new GroupLayout(ind_4);
                    ind_4.setLayout(ind_4Layout);
                    ind_4Layout.setHorizontalGroup(
                        ind_4Layout.createParallelGroup()
                            .addGap(0, 3, Short.MAX_VALUE)
                    );
                    ind_4Layout.setVerticalGroup(
                        ind_4Layout.createParallelGroup()
                            .addGap(0, 43, Short.MAX_VALUE)
                    );
                }

                //---- jLabel11 ----
                jLabel11.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                jLabel11.setForeground(Color.white);
                jLabel11.setText("Cookies");

                GroupLayout btn_4Layout = new GroupLayout(btn_4);
                btn_4.setLayout(btn_4Layout);
                btn_4Layout.setHorizontalGroup(
                    btn_4Layout.createParallelGroup()
                        .addGroup(btn_4Layout.createSequentialGroup()
                            .addComponent(ind_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel11)
                            .addGap(0, 57, Short.MAX_VALUE))
                );
                btn_4Layout.setVerticalGroup(
                    btn_4Layout.createParallelGroup()
                        .addGroup(btn_4Layout.createSequentialGroup()
                            .addComponent(ind_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, btn_4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            side_pane.add(btn_4);
            btn_4.setBounds(0, 180, 120, btn_4.getPreferredSize().height);

            //---- jLabel12 ----
            jLabel12.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            jLabel12.setForeground(Color.white);
            jLabel12.setText("AI");
            side_pane.add(jLabel12);
            jLabel12.setBounds(20, 260, jLabel12.getPreferredSize().width, 43);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < side_pane.getComponentCount(); i++) {
                    Rectangle bounds = side_pane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = side_pane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                side_pane.setMinimumSize(preferredSize);
                side_pane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(side_pane);
        side_pane.setBounds(0, 0, 120, 590);

        //======== jPanel2 ========
        {
            jPanel2.setBackground(new Color(0x4778c5));
            jPanel2.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    jPanel2MouseDragged(e);
                }
            });
            jPanel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    jPanel2MousePressed(e);
                }
            });

            //---- jTextField1 ----
            jTextField1.setBackground(new Color(0x7b9ce1));
            jTextField1.setForeground(Color.white);
            jTextField1.setBorder(null);
            jTextField1.setCaretColor(Color.white);
            jTextField1.setPreferredSize(new Dimension(2, 20));

            //---- jLabel7 ----
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/swing/images/icons8_Search_18px.png")));

            GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(747, Short.MAX_VALUE)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(34, 34, 34))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
            );
        }
        contentPane.add(jPanel2);
        jPanel2.setBounds(120, 0, 950, 50);

        //======== jPanel6 ========
        {
            jPanel6.setBackground(Color.white);

            //======== panel1 ========
            {
                panel1.setBackground(new Color(0xcccc00));
                panel1.setLayout(new BorderLayout());
            }

            GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup()
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(135, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup()
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(90, Short.MAX_VALUE))
            );
        }
        contentPane.add(jPanel6);
        jPanel6.setBounds(440, 50, 630, 510);

        //---- label1 ----
        label1.setText("Statistics");
        label1.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(260, 115), label1.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayAppProper frame = new DisplayAppProper();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JPanel side_pane;
    private JPanel btn_1;
    private JPanel ind_1;
    private JLabel jLabel8;
    private JPanel btn_2;
    private JPanel ind_2;
    private JLabel jLabel9;
    private JPanel btn_3;
    private JPanel ind_3;
    private JLabel jLabel10;
    private JPanel btn_4;
    private JPanel ind_4;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JPanel jPanel2;
    private JTextField jTextField1;
    private JLabel jLabel7;
    private JPanel jPanel6;
    private JPanel panel1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
