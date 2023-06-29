package com.raven.form;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import DB.DB_UA;
import GUI.Proper.WebVisualization;
import Proj.Headers;
import Proj.Scout;
import Proj.SuperTask;
import Proj.Task;
import com.raven.model.ModelStudent;
import com.raven.swing.table.EventAction;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.ImageIcon;
import net.miginfocom.swing.*;

//import static GUI.MENU_OPTIONS.resolution;
import static GUI.MainW.options;

public class Form_Home extends javax.swing.JPanel {

    private JFrame parentFrame;

    public static JProgressBar bar = null;

    private JLabel gifLabel;
    static int progress = 0;

    public static ArrayList<HashMap<String, String>> Agents;

   private int rate = 0;
   private int threads = 0;

    public Form_Home(JFrame parentFrame) {

        this.parentFrame = parentFrame;
        initComponents();

        setOpaque(false);
        initData();

//        WebVisualization visualization = new WebVisualization( parentFrame , panel1);
//
//
//        visualization.setVisible(true);
//
//        Map<String, java.util.List<String>> domainURLs = new HashMap<>();
//        domainURLs.put("Domain 1", java.util.List.of("URL 1", "URL 2", "URL 3"));
//        domainURLs.put("Domain 2", java.util.List.of( "URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7","URL 4", "URL 5", "URL 6", "URL 7",
//                "URL 7" , "URL 7", "URL 7","URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7" , "URL 7"));
//        domainURLs.put("Domain 3", java.util.List.of("URL 8", "URL 9"));
//        domainURLs.put("www.pcgarage.ro", List.of("/acer", "/mini-laptop","/retelistica" , "/info" , "/placi-video" , "/sisteme" , "/telefoane" , "/servicii","/componente-calculatoare",
//                "/din-garaj", "/auto-calatorii", "/electrocasnice-mari",
//                "/gaming" , "/periferice", "/printing-si-birotica","/casa-si-ingrijire-personala" , "/vouchere-reducere-in-cos-ai-doar-de-castigat" , "/cumpara-voucher" , "/vizualizare-wishlist"));
//
//        visualization.setDomainURLs(domainURLs);
//        visualization.createDomainPoints();
//
//        panel1.add(visualization);


    }

    private void initData() {

        initTableData();
    }

    private void initTableData() {
        EventAction eventAction = new EventAction() {

            @Override
            public void delete(ModelStudent student) {

            }

            @Override
            public void update(ModelStudent student) {

            }
        };

    }

    public static Runnable fill() {
        progress = 0;
        try {
            while (progress <= 100) {
                System.out.println("..................");
                if (progress > 30 && progress < 70)
                    bar.setString("Waiting for pages to load");
                else if (progress > 70)
                    bar.setString("Parsing...");
                else
                    bar.setString("Loading Started");

                // fill the menu bar
                bar.setValue(progress + 10);

                // delay the thread
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                }

                progress += 30;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void init_progressbar(JPanel p) {

        bar = new JProgressBar();

        bar.setStringPainted(true);

        bar.setValue(0);

        p.add(bar);

        bar.setBounds(p.getWidth() / 2 - 100, p.getHeight() / 2, 200, 20);
        p.add(bar);

        bar.setVisible(true);

    }


    public static void kill_progressbar(JProgressBar bar) {
        bar.setVisible(false);

    }

    public static void paintComponents2(JPanel p, List<Task> taskList, String KeyWord, String[] UrlList) throws URISyntaxException {

        WebVisualization visualization = new WebVisualization();
        visualization.setVisible(true);

        Map<String, java.util.List<String>> domainURLs = new HashMap<>();

        for (Task task : taskList) {
            if ("Multiple Pages".equals(task.getDomain())) {
                for (String url : task.getUrls()) {
                    List<String> sameDomainUrls = getSameDomainUrls(url, UrlList);
                    domainURLs.put(url, sameDomainUrls);
                }
            } else if ("Single Page".equals(task.getDomain())) {
                domainURLs.put(task.getName(), task.getUrls());
            }
        }

        visualization.setDomainURLs(domainURLs);
        visualization.createDomainPoints();
    }

    private static List<String> getSameDomainUrls(String url, String[] urlList) throws URISyntaxException {
        List<String> sameDomainUrls = new ArrayList<>();
        URI uri = new URI(url);
        String domain = uri.getHost();

        for (String candidateUrl : urlList) {
            URI candidateUri = new URI(candidateUrl);
            if (candidateUri.getHost().equals(domain)) {
                sameDomainUrls.add(candidateUrl);
            }
        }

        return sameDomainUrls;
    }

    public static void clearpanel(JPanel p) {

        p.removeAll();
        p.revalidate();

        Graphics g = p.getGraphics();
        int W = p.getWidth();
        int H = p.getHeight();

        Graphics2D g2d = (Graphics2D) g;
//        g2d.setPaintMode();
//
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(0, 0, W, H);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        separator2 = new JSeparator();
        panel2 = new JPanel();
        label1 = new JLabel();
        separator1 = new JSeparator();
        radioButton2 = new JRadioButton();
        radioButton1 = new JRadioButton();
        label2 = new JLabel();
        spinner1 = new JSpinner();
        comboBox1 = new JComboBox<>();
        label3 = new JLabel();
        spinner2 = new JSpinner();
        comboBox2 = new JComboBox<>();
        panel1 = new JPanel();
        button1 = new JButton();




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


//            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@





                        //////// DEFAULTS 															@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



                        ArrayList<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
                        String params = "All";



                try {

                    //GET ALL INFO

                    SuperTask sudo = new SuperTask();
                    rate = Integer.parseInt(sudo.getOptions().get("Rate"));
                    threads = Integer.parseInt(sudo.getOptions().get("Threads"));
                    String[] Options = sudo.getOptions().values().toArray(new String[0]);
                    String KeyWord = sudo.getQuery().getSearch().replace(" ", "|");
                    System.out.println(KeyWord);



                    Proj.Scout scout = new Scout();
                    List<String> urls = new ArrayList<>();
                    List<Task> tasks  = new ArrayList<Task>();
                    for (Proj.Task task : sudo.getTasks()) {
                        urls.addAll(task.getUrls());
                        tasks.add(task);
                        if (Objects.equals(task.getDomain(), "Multiple Pages")){

                            for (String url : urls)
                            // WAIT FOR SCOUT

                                try {
                                    scout.getRandomURLs(url);
                                }
                                catch (Exception e1){
                                    System.out.println(e1.toString());
                                    continue;
                                }
                        }

                    }


                    // here

                    String[] Real_URLS = urls.toArray(new String[0]);






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


                    // INIT PROGRESS BAR

                    init_progressbar(panel1);

                    button1.setEnabled(false);

                    SwingWorker<Boolean, Integer> bar_worker = new SwingWorker<Boolean, Integer>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            futures.add((Future<Runnable>) fill());
                            {
                                return true;
                            }
                        }


                        protected void done() {

                            boolean status;

                            try {

                                status = get();

                                progress = 0;

                            } catch (InterruptedException e) {

                                // This is thrown if the thread's interrupted.
                            } catch (ExecutionException ignored) {

                            }
                        }


                    };


                    // THIS IS WHERE THE FUN BEGINS
                    SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            futures.add((Future<Runnable>) Proj.driver2.RunInGui(params, KeyWord, Real_URLS, rate, threads, Agents, options));
                            {
                                return true;
                            }
                        }

                        protected void done() {

                            boolean status;

                            try {

                                status = get();
                                // CHECK STATUS AND KILL THE BAR
                                    button1.setEnabled(true);
                                    progress = 0;
                                    kill_progressbar(bar);

                                    clearpanel(panel1);


//                                // !!!!!!!!!!!!!!!  THIS IS WERE WE GENERATED NODES !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 paintComponents2(panel1, tasks, KeyWord, Real_URLS);
                                    futures.clear();
                                    if (!bar_worker.isDone()) {
                                        bar_worker.cancel(status);
                                    }
                            } catch (Exception exception)
                            {
                                System.out.println(exception.toString());
                            }
                        }

                    };

                    // EXECUTE SEPARATE OVERLORD THREADS
                    bar_worker.execute();
                    worker.execute();

                } catch (IOException ex) {
//                    rate = 200;
//                    threads = 12;

                    System.out.println(ex.toString());
                } catch (SQLException ignored) {

                }


//

            }
        });






        // Put the two radio buttons in the same group
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButton1);
        radioButtonGroup.add(radioButton2);

        // Action listener for radioButton1
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinner1.setEnabled(true);
                spinner2.setEnabled(true);
                comboBox1.setEnabled(true);
                comboBox2.setEnabled(true);
            }
        });

        // Action listener for radioButton2
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinner1.setEnabled(false);
                spinner2.setEnabled(false);
                comboBox1.setEnabled(false);
                comboBox2.setEnabled(false);
            }
        });


        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[39,left]" +
            "[fill]" +
            "[fill]" +
            "[20:47,fill]" +
            "[grow,fill]" +
            "[400:635:800,grow,fill]" +
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
        jLabel1.setIcon(new ImageIcon("Resources\\GoogleStart.png"));
        add(jLabel1, "cell 1 1");
        add(separator2, "cell 1 3");

        //======== panel2 ========
        {
            panel2.setMinimumSize(new Dimension(300, 124));
            panel2.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 0 0",
                // columns
                "[300:n,fill]",
                // rows
                "[fill]" +
                "[fill]" +
                "[36,fill]" +
                "[]" +
                "[38,fill]" +
                "[51,fill]" +
                "[fill]" +
                "[50,center]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- label1 ----
            label1.setText("Mode");
            label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            label1.setForeground(new Color(0x0448d2));
            panel2.add(label1, "cell 0 0,align center center,grow 0 0");
            panel2.add(separator1, "cell 0 1");

            //---- radioButton2 ----
            radioButton2.setText("Scraper Mode");
            radioButton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel2.add(radioButton2, "cell 0 3,alignx center,growx 0");

            //---- radioButton1 ----
            radioButton1.setText("Crawler Mode");
            radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            panel2.add(radioButton1, "cell 0 5,alignx center,growx 0");

            //---- label2 ----
            label2.setText("Total Time");
            label2.setMaximumSize(new Dimension(60, 16));
            panel2.add(label2, "cell 0 7");
            panel2.add(spinner1, "cell 0 7");

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "seconds",
                "minutes",
                "hours"
            }));
            panel2.add(comboBox1, "cell 0 7");

            //---- label3 ----
            label3.setText("Refresh At");
            label3.setMaximumSize(new Dimension(60, 16));
            panel2.add(label3, "cell 0 8");
            panel2.add(spinner2, "cell 0 8");

            //---- comboBox2 ----
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "seconds",
                "minutes",
                "hours"
            }));
            panel2.add(comboBox2, "cell 0 8");
        }
        add(panel2, "cell 1 3,aligny top,growy 0");

        //======== panel1 ========
        {
            panel1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            panel1.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[660,grow,fill]",
                // rows
                "[grow,fill]"));
        }
        add(panel1, "cell 5 3");

        //---- button1 ----
        button1.setText("Start");
        button1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
        add(button1, "cell 5 4");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JSeparator separator2;
    private JPanel panel2;
    private JLabel label1;
    private JSeparator separator1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton1;
    private JLabel label2;
    private JSpinner spinner1;
    private JComboBox<String> comboBox1;
    private JLabel label3;
    private JSpinner spinner2;
    private JComboBox<String> comboBox2;
    private JPanel panel1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
