package GUI.Proper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebVisualization extends JPanel {


    private static  int FRAME_WIDTH = 528;
    private static  int FRAME_HEIGHT = 408;
    private static final int NODE_RADIUS = 20;
    private static final int NODE_GAP = 1;
    private double scaleFactor = 1.0;

    private int fontSize = 12; // Initial font size

    private JFrame parentFrame;
    private JPanel parentPanel;

    private  double maxScale = 2.0 ;

    private  double minScale = 0.5 ;


    private  int maxFontSize = 33 ;

    private  int minFontSize = 6 ;

    private  boolean shiftPressed = false;

    private int oldWidth = FRAME_WIDTH;
    private int oldHeight = FRAME_HEIGHT;
    private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};

    private Map<String, List<String>> domainURLs;
    private List<String> domains;
    private Map<String, List<Point>> domainPoints;

    private void addMouseEventsToLabels(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel labelFound = (JLabel) component;
                String labelText = labelFound.getText();

                labelFound.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem menuItem = new JMenuItem("Vizualizeaza datele de pe " + labelText);
                        popupMenu.add(menuItem);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        labelFound.setForeground(Color.decode("#9400D3")); // Deep purple
                        labelFound.setFont(new Font("Arial", Font.BOLD, fontSize)); // Bigger font
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        labelFound.setForeground(Color.BLACK); // Original color
                        labelFound.setFont(new Font("Arial", Font.BOLD, fontSize)); // Original font size
                    }
                });
            }
        }
    }

    public WebVisualization(JFrame parentFrame, JPanel parentPanel) {

        this.parentFrame = parentFrame;
        this.parentPanel = parentPanel;
        FRAME_HEIGHT = parentPanel.getHeight() ;
        FRAME_WIDTH = parentPanel.getWidth() ;
        System.out.println(parentPanel.getWidth());
//        setTitle("Web Visualization");
//        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);

        parentPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shiftPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shiftPressed = false;
                }
            }
        });
        parentPanel.setFocusable(true);

        parentPanel.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                // Remove all JLabel components
                Component[] components = parentPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        parentPanel.remove(component);
                    }
                }


                centerCircles();
                parentPanel.repaint();
                repaint();

                // Update old size
                oldWidth = parentPanel.getWidth();
                oldHeight = parentPanel.getHeight();
            }
        });

        domainURLs = new HashMap<>();
        domains = new ArrayList<>();

        JMenuBar menuBar = new JMenuBar();
        JMenu domainMenu = new JMenu("Domains");
        menuBar.add(domainMenu);

        parentPanel.add(menuBar,BorderLayout.NORTH);

        JMenuItem domainItem;
        for (String domain : domains) {
            domainItem = new JMenuItem(domain);
            domainMenu.add(domainItem);
            domainItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedDomain = e.getActionCommand();
                    showDomainPanel(selectedDomain);
                }
            });
        }

        JTextField searchBar = new JTextField(20);
        JButton searchButton = new JButton("Search");
        menuBar.add(searchBar);
        menuBar.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchBar.getText();
                Component[] components = parentPanel.getComponents();
                boolean found = false;
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        if (label.getText().equals(searchText)) {
                            label.setForeground(Color.RED);
                            label.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Nothing Found!");
                }
            }
        });

        addMouseEventsToLabels((JPanel) parentPanel);



    }

    private void centerCircles() {
        int centerX = parentPanel.getWidth() / 2;
        int centerY = parentPanel.getHeight() / 2;

        for (String domain : domains) {
            List<Point> points = domainPoints.get(domain);

            for (Point point : points) {
                point.x += centerX - oldWidth / 2;
                point.y += centerY - oldHeight / 2;
            }
        }
    }
    private void showDomainPanel(String domain) {
        List<String> urls = domainURLs.get(domain);
        List<Point> points = domainPoints.get(domain);

        // listen for mouse whell movement for resizing
        parentPanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (shiftPressed) {
                    int notches = e.getWheelRotation();


                    scaleFactor += notches * 0.1;
                    scaleFactor = Math.max(0.1, scaleFactor); // Ensure scaleFactor is not less than 0.1

                    if ( scaleFactor >= maxScale) scaleFactor = maxScale;
                    if ( scaleFactor <= minScale) scaleFactor = minScale;


                    // Update the font size of the labels

                    fontSize += notches; // Adjust font size
                    fontSize = Math.max(8, fontSize); // Ensure fontSize is not less than 8

                    if ( fontSize > maxFontSize) fontSize = maxFontSize;
                    if ( fontSize < minFontSize) fontSize = minFontSize;


                    // Remove all JLabel components
                    Component[] components = parentPanel.getComponents();
                    for (Component component : components) {
                        if (component instanceof JLabel) {
                            parentPanel.remove(component);
                        }
                    }



                    parentPanel.repaint();
                    repaint();
                }
            }
        });

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;


                int increasedRadius = NODE_RADIUS + 12;






                // Draw domain circle
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(2.0f));
                Point domainPoint = points.get(0);
                int domainNodeX = domainPoint.x - increasedRadius - 5;
                int domainNodeY = domainPoint.y - increasedRadius - 5;
                g2d.drawOval(domainNodeX, domainNodeY, increasedRadius * 2 + 10, increasedRadius * 2 + 10);

                // Create domain label
                String domainLabel = domain;
                JLabel domainLabelComponent = new JLabel(domainLabel);
                domainLabelComponent.setFont(new Font("Arial", Font.BOLD, fontSize));
                domainLabelComponent.setForeground(new Color(0x0448d2));
                int labelWidth = domainLabelComponent.getPreferredSize().width;
                int labelHeight = domainLabelComponent.getPreferredSize().height;
                int labelX = domainNodeX + (increasedRadius * 2 + 10 - labelWidth) / 2; // Center the label on the x-axis
                int labelY = domainNodeY + (increasedRadius * 2 + 10 - labelHeight) / 2; // Center the label on the y-axis
                domainLabelComponent.setBounds(labelX, labelY, labelWidth, 20);
                domainLabelComponent.setFont(new Font("Arial", Font.BOLD, fontSize));
                // Draw URL circles and create labels in multiple layers


                System.out.println(parentPanel.getWidth());

                int   startIndex = 0;
                int numUrls = urls.size();
                int radius = increasedRadius ;
                int  nRadius = 2;
                int layerCount = 0;
                int layerMax = (int) (360 / increasedRadius / 1.5);
                int iters = 1 ;

                radius = (int) (radius * scaleFactor);
                //  int modifiedLineLength = (int) (lineLength * scaleFactor);

                // Connect URLs to the domain circle with lines





                while (numUrls > 0) {
                    System.out.println(iters);
                    System.out.println(numUrls);
                    System.out.println(layerMax);

                    int layerUrls = Math.min(numUrls, layerMax); // clever
                    double angleStep = 2 *   Math.PI /  layerUrls ;

                    float[] dashPattern = { (float) (iters * radius) /2,  (float) ( ( iters -1 ) * radius) /2};
                    g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 100.0f, dashPattern, 0.0f));

                    g2d.setColor(Color.BLACK);
                    // draw the lines

                    ArrayList<Point> endPointList = new ArrayList<>();

                    if ( iters == 1) {


                        for (int j = 0; j < layerUrls; j++) {


                            double angle = j * 2 * Math.PI / layerUrls;
                            int lineStartX = (int) (domainPoint.x + (nRadius / 2 * increasedRadius + 5) * Math.cos(angle));
                            int lineStartY = (int) (domainPoint.y + (nRadius / 2 * increasedRadius + 5) * Math.sin(angle));
                            int lineEndX = (int) (domainPoint.x + ((nRadius * increasedRadius + 5) + (radius + 10)) * Math.cos(angle));
                            int lineEndY = (int) (domainPoint.y + ((nRadius * increasedRadius + 5) + (radius + 10)) * Math.sin(angle));
                            g2d.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);

                            Point endPoint = new Point(lineEndX, lineEndY);
                            endPointList.add(endPoint);
                        }
                    }
                    else if (iters == 4 ) {

                        for (int j = 1; j <= layerUrls; j+=2) {


                            double angle = j * 2 * Math.PI / layerUrls;
                            int lineStartX = (int) (domainPoint.x + (nRadius / 2 * increasedRadius + 5 + 2* radius) * Math.cos(angle));
                            int lineStartY = (int) (domainPoint.y + (nRadius / 2 * increasedRadius + 5 + 2 *radius) * Math.sin(angle));
                            int lineEndX = (int) (domainPoint.x + ((2* nRadius * increasedRadius + 5) + (radius + 10)) * Math.cos(angle));
                            int lineEndY = (int) (domainPoint.y + ((2 *nRadius * increasedRadius + 5) + (radius + 10)) * Math.sin(angle));
                            g2d.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);
                            Point endPoint = new Point(lineEndX, lineEndY);
                            endPointList.add(endPoint);
                        }
                    }

                    else {

                        for (int j = 1; j <= layerUrls; j+=2) {


                            double angle = j * 2 * Math.PI / layerUrls;
                            int lineStartX = (int) (domainPoint.x + (nRadius / 2 * increasedRadius + 5) * Math.cos(angle));
                            int lineStartY = (int) (domainPoint.y + (nRadius / 2 * increasedRadius + 5) * Math.sin(angle));
                            int lineEndX = (int) (domainPoint.x + ((nRadius * increasedRadius + 5) + (radius + 10)) * Math.cos(angle));
                            int lineEndY = (int) (domainPoint.y + ((nRadius * increasedRadius + 5) + (radius + 10)) * Math.sin(angle));
                            g2d.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);
                            Point endPoint = new Point(lineEndX, lineEndY);
                            endPointList.add(endPoint);
                        }


                    }

                    if (layerUrls > 1) {
                        for (int i = 0; i < endPointList.size(); i += 1) {
                            if (i != endPointList.size() - 1) {
                                Point p1 = endPointList.get(i);
                                Point p2 = endPointList.get(i + 1);
                                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);


                            } else {

                                if (endPointList.size() == 2) {
                                    Point p1 = endPointList.get(0);
                                    Point p2 = endPointList.get(1);

                                    // Calculate the center and radius of the half-circle
                                    double cx = (p1.x + p2.x) / 2.0;
                                    double cy = (p1.y + p2.y) / 2.0;
                                    double R = Math.hypot(p1.x - p2.x, p1.y - p2.y) / 2;

                                    // Define the starting angle and extent of the arc
                                    double startAngle = Math.toDegrees(Math.atan2(p1.y - cy, p1.x - cx));
                                    double startAngle2 = startAngle + 180.0; // start from the other side
                                    double extent = 180.0; // semi-circle

                                    // Create the Arc2D for the half-circle
                                    Arc2D.Double arc = new Arc2D.Double(cx - R, cy - R, 2 * R, 2 * R, startAngle, extent, Arc2D.OPEN);
                                    Arc2D.Double arc2 = new Arc2D.Double(cx - R, cy - R, 2 * R, 2 * R, startAngle2, extent, Arc2D.OPEN);

                                    // Draw the arcs
                                    g2d.draw(arc);
                                    g2d.draw(arc2);
                                } else {
                                    Point p1 = endPointList.get(i);
                                    Point p2 = endPointList.get(0);
                                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);

                                }

                            }
                        }
                    }

                    for (int j = startIndex; j < layerUrls; j++) {

                        Color color = COLORS[( domains.indexOf(domain) + iters ) % COLORS.length];
                        g2d.setColor(color);

                        double angle = j * angleStep  ;
                        int urlNodeX = (int) (domainPoint.x + (radius + nRadius *radius) * Math.cos(angle)) - radius;
                        int urlNodeY = (int) (domainPoint.y + (radius + nRadius *radius) * Math.sin(angle)) - radius;

                        g2d.fillOval(urlNodeX, urlNodeY, radius * 2 , radius * 2);


                        String urlLabel =  urls.get(layerCount + j);
                        String truncatedLabel = urlLabel.length() > 8 ? urlLabel.substring(0, 8) + "..." : urlLabel;
                        JLabel urlLabelComponent = new JLabel(truncatedLabel);
//                        JLabel urlLabelComponent = new JLabel(urlLabel);
                        urlLabelComponent.setFont(new Font("Arial", Font.PLAIN, fontSize));
                        urlLabelComponent.setForeground(new Color(0x0448d2));
                        urlLabelComponent.setMaximumSize(new Dimension(1, 10));
                        labelWidth = urlLabelComponent.getPreferredSize().width;
                        labelX = urlNodeX + (radius ) / 2; // Center the label on the x-axis
                        labelY = urlNodeY + (radius ) / 2; // Center the label on the y-axis
                        urlLabelComponent.setBounds(labelX, labelY, labelWidth, 20);
                        urlLabelComponent.setFont(new Font("Arial", Font.BOLD, fontSize)); // Use bold font

                        parentPanel.add(urlLabelComponent);



                    }



                    startIndex += 0;
                    numUrls -= layerUrls;
                    layerCount += layerUrls;
                    layerMax *= 2;
                    nRadius *= 2;

                    iters ++;
                }

                // Add domain label
                parentPanel.add(domainLabelComponent);
                addMouseEventsToLabels(parentPanel);

            }
        };

        panel.setLayout(null); // Use absolute positioning for the labels
        //setContentPane(panel);
        parentPanel.add(panel);

       parentPanel.revalidate();
       revalidate();
       parentPanel.repaint();
       repaint();

    }


    public void setDomainURLs(Map<String, List<String>> domainURLs) {
        this.domainURLs = domainURLs;
        this.domains = new ArrayList<>(domainURLs.keySet());

        JMenuBar menuBar = null;
        Component[] components = parentPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JMenuBar) {
                menuBar = (JMenuBar) component;
                break;
            }
        }

        if (menuBar != null) {
            JMenu domainMenu = menuBar.getMenu(0);
            domainMenu.removeAll();

            JMenuItem domainItem;
            for (String domain : domains) {
                domainItem = new JMenuItem(domain);
                domainMenu.add(domainItem);
                domainItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedDomain = e.getActionCommand();
                        showDomainPanel(selectedDomain);
                    }
                });
            }
        }
    }

    public void createDomainPoints() {
        domainPoints = new HashMap<>();

        int centerX = parentPanel.getWidth() / 2;
        int centerY = parentPanel.getHeight() / 2;

        int numDomains = domains.size();
        int numURLsPerDomain = 0;
        for (String domain : domains) {
            numURLsPerDomain = Math.max(numURLsPerDomain, domainURLs.get(domain).size());
        }

        int totalHeight = numURLsPerDomain * NODE_GAP;
        int totalWidth = (numDomains - 1) * NODE_GAP;

        int startY = centerY - (totalHeight / 2);
        int startX = centerX - (totalWidth / 2);

        int minDistance = 0; // Minimum distance between nodes

        for (int i = 0; i < numDomains; i++) {
            String domain = domains.get(i);
            List<String> urls = domainURLs.get(domain);
            int numURLs = urls.size();

            int domainCenterX = startX + (i * NODE_GAP);
            int domainCenterY = startY + ((numURLs - 1) * NODE_GAP) / 2;

            domainPoints.put(domain, new ArrayList<>());

            for (int j = 0; j < numURLs; j++) {
                int x = domainCenterX;
                int y = domainCenterY + ((j - numURLs / 2) * NODE_GAP);

                // Adjust node position if it is too close to other nodes
                for (int k = 0; k < j; k++) {
                    Point p = domainPoints.get(domain).get(k);
                    int dx = x - p.x;
                    int dy = y - p.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    if (distance < minDistance) {
                        double angle = Math.atan2(dy, dx);
                        x = (int) (p.x + minDistance * Math.cos(angle));
                        y = (int) (p.y + minDistance * Math.sin(angle));
                    }
                }

                domainPoints.get(domain).add(new Point(x, y));
            }
        }
    }
    // Add a ComponentListener to handle resize events




    // ALSO CHECK FOR LOOPING , IF URL IS ALDREADY IN URL LIST DON'T ENTER IT IN THE NODE MAKER
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                WebVisualization visualization = new WebVisualization();
//                visualization.setVisible(true);
//
//                Map<String, List<String>> domainURLs = new HashMap<>();
//                domainURLs.put("Domain 1", List.of("URL 1", "URL 2", "URL 3"));
//                domainURLs.put("Domain 2", List.of( "URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                        "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7"));
//                domainURLs.put("Domain 3", List.of("URL 8", "URL 9"));
//                domainURLs.put("www.pcgarage.ro", List.of("/acer", "/mini-laptop","/retelistica" , "/info" , "/placi-video" , "/sisteme" , "/telefoane" , "/servicii","/componente-calculatoare",
//                        "/din-garaj", "/auto-calatorii", "/electrocasnice-mari",
//                        "/gaming" , "/periferice", "/printing-si-birotica","/casa-si-ingrijire-personala" , "/vouchere-reducere-in-cos-ai-doar-de-castigat" , "/cumpara-voucher" , "/vizualizare-wishlist"));
//
//                visualization.setDomainURLs(domainURLs);
//                visualization.createDomainPoints();
//
//            }
//        });
//    }




}