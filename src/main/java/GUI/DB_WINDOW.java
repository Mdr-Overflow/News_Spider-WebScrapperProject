package GUI;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
 
public class DB_WINDOW implements ItemListener {
    JPanel cards; 
	private static final String USERAGENTS = "UserAgents Table";
	private static final String HEADERS = "Headers Table";
	private static final String AGENTS = "Agents Table";
	private static final String BADURLS = "Badurls Table";
	private static final String RESULTS = "Results Table";
    static String[] argss = {" "};
    
    public void addComponentToPane(Container pane1) {
       
        JPanel comboBoxPane = new JPanel(); 
        String comboBoxItems[] = { USERAGENTS, HEADERS, AGENTS, BADURLS, RESULTS };
        @SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
         
        
        
        
        DB_MENU ua_m = new DB_MENU();
        DB_HEADERS hd_m = new DB_HEADERS(); 
        DB_AGENTS ag_m = new DB_AGENTS(); 
        DB_BADURL bd_m = new DB_BADURL();
        DB_RESULT res_m = new DB_RESULT();
        
        JPanel card1 = (JPanel) ua_m.getContentPane();
        JPanel card2 = (JPanel) hd_m.getContentPane();
        JPanel card3 = (JPanel) ag_m.getContentPane();
        JPanel card4 = (JPanel) bd_m.getContentPane();
        JPanel card5 = (JPanel) res_m.getContentPane();
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, USERAGENTS);
        cards.add(card2, HEADERS);
        cards.add(card3, AGENTS);
        cards.add(card4, BADURLS);
        cards.add(card5, RESULTS);
         
        pane1.add(comboBoxPane, BorderLayout.PAGE_START);
        pane1.add(cards, BorderLayout.CENTER);
    }
     
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(String[] closed) {
        //Create and set up the window.
        JFrame frame = new JFrame("News Spider Database Manager");
        
       
        
        
        
        frame.setBounds(100, 100, 880, 610);
        frame.setResizable(false);
        
      //https://iconarchive.com/show/flat-halloween-icons-by-uiconstock/Halloween-Spider-2-icon.html
      		String pathToFileOnDisk = Paths.get("IconDoodle.png").toString();
      		ImageIcon img = new ImageIcon(pathToFileOnDisk);
      		frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(javax.swing.
                WindowConstants.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, 
                    "Are you sure you want to close this window?", "Close Window?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                	closed[0] = "Yes";
                	 frame.setDefaultCloseOperation(javax.swing.
                             WindowConstants.DISPOSE_ON_CLOSE);
                	frame.dispose();
                	//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
                else {  } 
                }}
              ); 
                	
        
		
        //Create and set up the content pane.
        DB_WINDOW demo = new DB_WINDOW();
        demo.addComponentToPane(frame.getContentPane());
         
        //Display the window.
       
        
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
      
    	
    	//COMMENT IF TESTING                                   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   IF COMMENTED IT WILL NOT WORK
    	 argss = args;
    	
    	
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                createAndShowGUI(argss);
            }
        });
    }
    

    
    
}