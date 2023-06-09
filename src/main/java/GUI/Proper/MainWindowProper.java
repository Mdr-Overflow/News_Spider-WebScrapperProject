package GUI.Proper;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.io.File;



    public class MainWindowProper extends JFrame {




            private JPanel currentPanel;

        public MainWindowProper(JPanel currentPanel) {
            setTitle("Main Window");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            currentPanel = new JPanel();
            currentPanel.setLayout(new MigLayout("", "[80px:80px:300px,grow 50,left][100px:457px:1400px,grow,center][80px:80px:300px,grow 50,right]", "[30px:30px:30px,top][100px:264px:800px,grow,center][30px:n:50px,grow 10][100px:80px:200px,grow 50,fill]"));
            currentPanel.setBackground(Color.ORANGE);
            currentPanel.setLocation(new Point(1, 1));
            currentPanel.setBounds(new Rectangle(2, 2, 2, 2));

            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            separator.setPreferredSize(new Dimension(2, currentPanel.getHeight()));

            try {
                File f = new File("Resources/nodes.gif");
                URL image = f.toURI().toURL();

                ImageIcon gifIcon = new ImageIcon(image);
                gifIcon.setImage(gifIcon.getImage().getScaledInstance((int) (getWidth() / 1.5), (int) (getHeight() / 1.2), Image.SCALE_DEFAULT));
                JLabel gifLabel = new JLabel(gifIcon);

                currentPanel.add(gifLabel, "span 1 4");
                currentPanel.add(separator, "cell 0 0 1 4");
            } catch (Exception e) {
                e.printStackTrace();
            }


                ComponentListener resizeListener = new ComponentListener(){

                @Override
                public void componentResized(ComponentEvent e) {

                    for (Component jc : ((Container) e.getSource()).getComponents()) {
                        //System.out.println(jc);
                        if ( jc instanceof JLabel) {
                            if(((JLabel) jc).getText().matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]")){
                                //System.out.println("FOUND");
                                ((JPanel) e.getSource()).remove(jc);
                            }

                        }}
                }

                @Override
                public void componentMoved(ComponentEvent e) {

                }

                @Override
                public void componentShown(ComponentEvent e) {

                }

                @Override
                public void componentHidden(ComponentEvent e) {

                }};

                JPanel panel_1 = new JPanel();
            panel_1.setBackground(new Color(255, 255, 255));
            panel_1.setMinimumSize(new Dimension(100, 100));
            panel_1.setPreferredSize(new Dimension(1, 1));
            panel_1.setMaximumSize(new Dimension(32000, 32000));
            panel_1.addComponentListener(resizeListener);
            panel_1.setBounds(new Rectangle(2, 2, 2, 2));
            panel_1.setLayout(null);
            currentPanel.add(panel_1, "cell 1 1,grow");

            Panel panel_2 = new Panel();
            panel_2.setBackground(new Color(255, 255, 255));
            panel_2.setMinimumSize(new Dimension(100, 100));
            panel_2.setMaximumSize(new Dimension(4000, 2000));
            panel_2.setLayout(new MigLayout("", "[100px:100px:100px,grow 10,left][100px:250px:250px,grow 50,fill][100px:100px:100px,grow 10,right]", "[22px:22px:66px,grow][30px:50px:50px,grow 10][30px:50px:50px,grow]"));
            currentPanel.add(panel_2, "cell 1 3,alignx center,aligny center");

            JLabel label = new JLabel("Enter Words to Search");
            label.setFont(new Font("Arial Black", Font.PLAIN, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setForeground(new Color(255, 153, 0));
            panel_2.add(label, "cell 1 0,growx,aligny center");

            JTextField keywordTextField = new JTextField();
            keywordTextField.setMinimumSize(new Dimension(20, 20));
            keywordTextField.setFont(new Font("Arial", Font.BOLD, 16));
            keywordTextField.setForeground(new Color(204, 51, 0));
            panel_2.add(keywordTextField, "cell 1 1,grow");

            JButton button = new JButton("START");
            button.setFont(new Font("Arial Black", Font.PLAIN, 12));
            button.setForeground(new Color(255, 153, 0));
            panel_2.add(button, "cell 1 2,grow");

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Perform action on button click
                }
            });

            add(currentPanel, BorderLayout.CENTER);
            pack();
        }


    }

