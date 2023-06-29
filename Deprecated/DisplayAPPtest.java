package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI.ComponentHandler;

import net.miginfocom.swing.MigLayout;

import javax.swing.Action;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.awt.*;
import javax.swing.JCheckBox;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
public class DisplayAPPtest extends JFrame {

	Random rand = new Random(); 
    int x = rand.nextInt(450);
    int y = rand.nextInt(450);
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayAPPtest frame = new DisplayAPPtest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DisplayAPPtest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[118px][200px:400px:1000px,grow][118px,right]", "[22px,grow][:280px:800px,grow][22px][50px][22px]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(null);
		panel.addComponentListener(resizeListener);
		
		//Graphics graf = panel.getGraphics();
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
				clearpanel(panel);
				paintComponents1(panel);
				
			}
		});
		contentPane.add(btnNewButton, "cell 1 3,grow");
	}
	
	
	public void clearpanel(JPanel p) {    
	   
		
		p.removeAll();
		p.revalidate();
		
		Graphics g = p.getGraphics();
		int W = p.getWidth();
	    int H = p.getHeight();
	    
	    Graphics2D g2d = (Graphics2D) g;
        g2d.setPaintMode();
        
	    g2d.setStroke(new BasicStroke(1));
	    g2d.setColor(Color.ORANGE);
	    
	   // AffineTransform tx1 = new AffineTransform();
	  
	    	

       	
            //tx1.scale(0.65, 0.65);
            //g2d.setTransform(tx1);
            g2d.fillRect(0,0,W,H);
	    
        
	}  
	
	
	
	public void paintComponents1(JPanel p) {    
	   
		String[] Url_list = {"https://longexamplethatisntreal.com", "https://longexamplethatisntreal.com", "https://longexamplethatisntreal.com", 
				"https://longexamplethatisntreal.com" , "https://longexamplethatisntreal.com", "https://longexamplethatisntreal.com" , 
				"https://theguardian.com", "https://longexdfdfdl.com" , "https://longexamplethatisntreal.com", 
					"https://longexamplethatisntreal.com" , "https://longexamplethatisntreal.com", "https://longexamplethatisntreal.com" , 
					"https://theguardian.com", "https://longexdfdfdl.com" , "https://longexamplethatisntreal.com","https://longexamplethatisntreal.com"

		}; // If more then 9 half line size
		String KeyWord = "???";
		MakeVertices.generateNodesEX(p, Url_list, KeyWord);
	}  
  
	
	
    
	private ComponentListener resizeListener = new ComponentListener(){
	   
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			//System.out.println("asd");
			for (Component jc : ((Container) e.getSource()).getComponents()) {
				//System.out.println(jc);
			    if ( jc instanceof JLabel) {
			    	if(((JLabel) jc).getText().matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]")){
			        //System.out.println("FOUND");
			    	((JPanel) e.getSource()).remove(jc);
			    	}
			    	//else System.out.println(((JLabel) jc).getText());
			    	
			
		}}
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}};
	        // recalculate value
	    
		   
		   
	   
}



