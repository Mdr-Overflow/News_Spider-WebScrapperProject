package SDA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Line;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.*;
import java.awt.geom.*;

import org.javatuples.Pair;



public class VerticesGEN {
	
	ArrayList<Pair<Double,Double>> Vertices;
	Double angle;
	HashNode<String, Integer> node_in;
		// Save a and vertices
		
		public static Color ColorChoice(int index) {
			
			 final Random r=new Random();
			 Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),255);
			
			
			return c;
			
		}
		
		
		
		
		public VerticesGEN() {
			super();
			Vertices  = new ArrayList<Pair<Double,Double>>();
			this.angle = 0.0;
			this.node_in = new HashNode<String, Integer>(null, null, 0);
		}




		//step should be reseeded every clear
		
		
		public int Recur(Double a, ArrayList<Pair<Double,Double>> Vertices, int i , int Nr_of_Vertices ,
				int r , int x, int y , Double a_b, ArrayList<HashNode<String, Integer>> nodes ,
				JPanel panel, int Size, Double size_coef, Graphics2D g2d) {
		   Double step = Math.random();
		   Double min = 0.25;
		   Double cos = 0.;
		   Double sin = 0.;
		   r=80;
		   int depth = 1;
		   
		  // angle = a;
		  
		        // Angle = (2 * Math.PI) / i
		    if (i < Nr_of_Vertices) {
		    	HashNode<String, Integer> node = nodes.get(i);
		    	 while(node.next != null) {
					   depth++;
					   node = node.next;
					   
		    	 }
		            if (a < 20 * Math.PI ) {
		            	if(a > 2 * Math.PI ) {r+=60; }
		            	if(a > 4 * Math.PI ) {r+=60; }
		            	if(a > 6 * Math.PI ) {r+=60; }
		            	if(a > 8 * Math.PI ) {r+=60; }
		            	 a += (30.0 + (10.0 * step)) * Math.PI  / 180.0 ;
		            	cos = Math.cos(a); 
		            	sin = Math.sin(a);
		                while(depth!=0) {
		                    
		                	
		                	
		                    
		                    if(depth > 1) {
		                    	
		                    	Vertices.add(new Pair<Double,Double>(x + r * cos, y + r * sin));
		                    	r+=30;
		                    	this.node_in = nodes.get(i).next;
		                    	depth--;
		                    	//int j = 0;
		                    	while(depth != 0) {
		                    	//r+=30;
		                    	Pair<Double,Double> v = new Pair<Double,Double>(x + r * cos, y + r * sin);
		                    	
		                    		String key = node_in.key;
		                    		String value =Integer.toString(node_in.value);
		                    		String hash =Integer.toString(node_in.hashCode);
				                    	 JLabel button = new JLabel("Key: "+ key + ", Value: "+ value +", hcode: " + hash);
				     			        button.setBounds((int) (v.getValue0().intValue()  + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue()  + Size*size_coef/2 - Size/4),
				     			        		(int) ( Size* 0.5), (int) (Size* 0.5));
				     			       
				     			        
				     			        panel.add(button);
				     			        button.addMouseListener(new MouseListener() {
				     		               
				     						@Override
				     						public void mouseClicked(MouseEvent e) {
				     							// TODO Auto-generated method stub
				     							System.out.println(button.getText());
				     							System.out.println(SwingUtilities.getWindowAncestor((Component) e.getSource()));
				     							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		
				     							
				     							
				     							//UIManager.put("OptionPane.maximumSize",new Dimension(500,500)); 
				     							UIManager.put("OptionPane.minimumSize",new Dimension(200,200)); 
				     							UIManager.put("OptionPane.messageFont", new Font("Arial Black", Font.BOLD, 12));
				     							UIManager.put("OptionPane.buttonFont", new Font("Arial Black", Font.BOLD, 12));
				     						
				     							UIManager.put("OptionPane.messageForeground",new Color(255, 153, 0));
				     							UIManager.put("OptionPane.messageDialogTitle","Resuts");
				     							ArrayList <String> Up_to = new ArrayList <String>(); 
				     						
				     							Up_to.add(button.getText());
				     							
				     							JOptionPane.showMessageDialog(topFrame, Up_to);
				     							
				     							UIManager.put("OptionPane.minimumSize",new Dimension(50,50));
				     							
				     						}
		
				     						@Override
				     						public void mousePressed(MouseEvent e) {
				     							// TODO Auto-generated method stub
				     							
				     						}
		
				     						@Override
				     						public void mouseReleased(MouseEvent e) {
				     							// TODO Auto-generated method stub
				     							
				     							
				     							
				     							
				     							//g2d.setColor(Color.BLACK);
				     						}
		
				     						@Override
				     						public void mouseEntered(MouseEvent e) {
				     							// TODO Auto-generated method stub
				     							g2d.setColor(Color.BLACK);
				     							
				     							//Integer.parseInt(button.getText()) //nodes.get(i).hashCode
				     							JLabel lurl = new JLabel("Hash:" + button.getText().substring(button.getText().lastIndexOf("hcode:")+7));
				     							//System.out.println(Url_list[Integer.parseInt(button.getText())]);
				     							lurl.setFont(new Font("Bodoni MT Black", Font.PLAIN, 15));
				     							lurl.setBounds(20,10,800,20);
				     							panel.add(lurl);
				     							lurl.setForeground(Color.BLACK);
				     							
				     						}
		
				     						@Override
				     						public void mouseExited(MouseEvent e) {
				     							// TODO Auto-generated method stub
				     						// TODO Auto-generated method stub
				    							for (Component jc : panel.getComponents()) {
				    								
				    							    if ( jc instanceof JLabel) {
				    							    	if(((JLabel) jc).getText().contains("Hash:") ) {
				    							        //System.out.println("FOUND");
				    							    	((JLabel) jc).setText(" ");
				    							    }
				    							    	  //System.out.println(((JLabel) jc).getText());
				    							    
				    							    }
				    							    
				    							}}}
				    							
				    						
    							
		
				     		            );
				     			        
				     			        
				     			       
				     			        button.setOpaque(true);
				     			       
				     			        
				     		        g2d.setColor(Color.BLACK);
				     		        g2d.drawOval((int) (v.getValue0().intValue() + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue() + Size*size_coef/2 - Size/4),
				     		        		(int) ( Size* 0.5), (int) (Size* 0.5));
				     		        g2d.setColor(ColorChoice(i));
				     		        g2d.fillOval((int) (v.getValue0().intValue() + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue() + Size*size_coef/2 - Size/4),
				     		        		(int) ( Size* 0.5), (int) (Size* 0.5));
				                    	
				                    	
				                    	
				     		       
				     		       depth--;
				     		       node_in = node_in.next;
		                    }
		                    
		                    
		                    }
		                    
		                    if(depth == 1) {
		                    depth--;
		                    Pair<Double,Double> TEMP_Pair = new Pair<Double,Double>(x + r * cos, y + r * sin);
		                    Vertices.add( TEMP_Pair);
		                    }
		                }
		                i += 1;
		                Recur(a, Vertices , i , Nr_of_Vertices , r , x , y , a - (20.0 + (40.0 * step)) * Math.PI / 180.0, nodes, panel, Size, size_coef, g2d);
		               
		         return 0;
		    }
		          
		            

		    }
		    else {
		        System.out.println("Exceeded limit");
		        return -1;
		    }
			return -1;
		
		
		  
			}
		
		
			public  void generateNodesEX(JPanel panel ,  ArrayList<HashNode<String, Integer>> nodes, int last_index, Double ZOOM) {
		   
				
				
				System.out.println(" --- Generated Nodes ---");
		    
		    System.out.println(" @@@@@LAST ANGLE WAS -------> " + angle);
		    System.out.println(" @@@@@LAST INDEX WAS -------> " + last_index);
		    Double size = 2.0;
		    int i = 0;
		  
		    int ch = panel.getHeight()/2;
		    int cw = panel.getWidth()/2;
		    double resize_coef = 1.0;
		 
		   // int leng = Url_list.length;
		    
		 
		    int Nr_of_Vertices = nodes.size();
		    System.out.println(Nr_of_Vertices);
		    //int r = 150  + Nr_of_Vertices ; 
		    
		    AffineTransform tx1 = new AffineTransform();
		    tx1.scale(ZOOM, ZOOM);
            //tx1.translate(cw  * 0.4, ch * 0.3 );
		    
		    
		   int r=10;
		
		    int x= cw;
		    int y = ch;
		    // coords of center
		
		    size = (double) (80.0 / (1.0 + Nr_of_Vertices/72.0));
		    
		   
		    Double step = 0.5 ;
		    //angle+=Math.random();
		    Double a = 0.;
		    
		    ArrayList<Pair<Double,Double>> VerticesN  = new ArrayList<Pair<Double,Double>>();
		    
		    
		    Graphics g = panel.getGraphics();
			int W = panel.getWidth();
		    int H = panel.getHeight();
		    
		    Graphics2D g2d = (Graphics2D) g;
	        g2d.setPaintMode();
	        g2d.setTransform(tx1);
	        Stroke stroke3 = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	 	    g2d.setStroke(stroke3);
	        int Size = size.intValue();
		    double size_coef = (W + H)/2.0 * 0.001 ; 
		    
		    int res = Recur(a + (30.0 + (4.0 * step)) * Math.PI/ 180.0 ,VerticesN,i,Nr_of_Vertices,r,x,y, a, nodes,panel, Size, size_coef, g2d);
		    
		    
		    
		    
		    this.Vertices = VerticesN;
		    
		    
		    System.out.println("GENERATED VERTICES <-------");	
		    
		   
		    i=0;
		    
		    
		    // CORRECTION IF WINDOW SMALL
		   
		   
		    if (res != -1 ) {
		    	
		    	g2d.setColor(Color.BLACK);
		    for (Pair <Double,Double> v : Vertices) {
		        
		        if (i <(VerticesN.size())-1){
		        	 Line2D lin = new Line2D.Float(cw, ch, (int) (v.getValue0().intValue() + Size*size_coef/2), (int)(v.getValue1().intValue() + Size*size_coef/2));
		        	 
		        	 g2d.draw(lin);
		        	 
		           // canvas.create_line(cw , ch , v[0] + size*size_coef/2 , v[1] + size*size_coef/2 , fill="black", width=3)
		             
		        	 Line2D lin2 = new Line2D.Float((int) (v.getValue0().intValue() + size*size_coef/2), (int)(v.getValue1().intValue() + size*size_coef/2), 
		        			 (int) (VerticesN.get(i+1).getValue0().intValue() + size*size_coef/2), (int)(VerticesN.get(i+1).getValue1().intValue() + size*size_coef/2));
		        	 
		        	 g2d.draw(lin2);
		        	 
		           // canvas.create_line(v[0] + size*size_coef/2,v[1] + size*size_coef/2 , Vertices[i+1][0] + size*size_coef/2, Vertices[i+1][1] + size*size_coef/2 ,fill="black", width=3 )
		            i+=1;
		        }
		        else {
		            //canvas.create_line(cw, ch, v[0] + size * size_coef / 2, v[1] + size * size_coef / 2, fill="black", width=3)
		        	Line2D lin = new Line2D.Float(cw, ch, (int) (v.getValue0().intValue() + Size*size_coef/2), (int)(v.getValue1().intValue() + Size*size_coef/2));
		        	 
		        	 g2d.draw(lin);
		        	
		  
		        }
		        
		    }
		    
		    g2d.setColor(Color.BLACK);
		    g2d.drawOval((int) (cw - size.intValue()/2 ), (int) (ch - size.intValue()/2 ), (int) (size.intValue()) , (int) (size.intValue()));
		    g2d.setColor(Color.GRAY);
		    g2d.fillOval((int) (cw - size.intValue()/2 ), (int) (ch - size.intValue()/2 ), (int) (size.intValue()) , (int) (size.intValue()));
		    
		    
		   // Wlb = canvas.create_text( (x , y )  , text="CENTER" , font=('aria', 11, 'bold',) , fill = "green" )
		    i = 0;
		    //list_to_bind = []
		    
		    int text_size = 20;
		    if ( Nr_of_Vertices < 10){
		    	text_size = 15;
		    }
		    
		    g2d.setFont(new Font("TimesRoman", Font.BOLD, text_size));
		    //AffineTransform tx2 = new AffineTransform();
		   
		    for(Pair <Double,Double> v : VerticesN) {
		    	//g2d.setTransform(tx1);
		    	
		    	//"Key: "+ key + ", Value: "+ value +", hcode: " + hash
		    	String key = nodes.get(i).key;
		    	String value =Integer.toString(nodes.get(i).value);
		    	String hashCode = Integer.toString(nodes.get(i).hashCode);
		    	
		    
		    	 JLabel button = new JLabel("Key: "+ key + ", Value: "+ value +", hcode: " + hashCode);
			        button.setBounds((int) (v.getValue0().intValue()  + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue()  + Size*size_coef/2 - Size/4),
			        		(int) ( Size* 0.5), (int) (Size* 0.5));
			       
			        
			        panel.add(button);
			        button.addMouseListener(new MouseListener() {
		               
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							System.out.println(button.getText());
							System.out.println(SwingUtilities.getWindowAncestor((Component) e.getSource()));
							JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

							
							
							//UIManager.put("OptionPane.maximumSize",new Dimension(500,500)); 
							UIManager.put("OptionPane.minimumSize",new Dimension(200,200)); 
							UIManager.put("OptionPane.messageFont", new Font("Arial Black", Font.BOLD, 12));
							UIManager.put("OptionPane.buttonFont", new Font("Arial Black", Font.BOLD, 12));
						
							UIManager.put("OptionPane.messageForeground",new Color(255, 153, 0));
							UIManager.put("OptionPane.messageDialogTitle","Resuts");
							ArrayList <String> Up_to = new ArrayList <String>(); 
						
							Up_to.add(button.getText());
							
							JOptionPane.showMessageDialog(topFrame, Up_to);
							
							UIManager.put("OptionPane.minimumSize",new Dimension(50,50));
							
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
							
							
							
							//g2d.setColor(Color.BLACK);
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							g2d.setColor(Color.BLACK);
							
							//Integer.parseInt(button.getText()) //nodes.get(i).hashCode
							JLabel lurl = new JLabel("Hash:" + button.getText().substring(button.getText().lastIndexOf("hcode:")+7));
							//System.out.println(Url_list[Integer.parseInt(button.getText())]);
							lurl.setFont(new Font("Bodoni MT Black", Font.PLAIN, 15));
							lurl.setBounds(20,10,800,20);
							panel.add(lurl);
							lurl.setForeground(Color.BLACK);
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							for (Component jc : panel.getComponents()) {
								
							    if ( jc instanceof JLabel) {
							    	if(((JLabel) jc).getText().contains("Hash:") ) {
							        //System.out.println("FOUND");
							    	((JLabel) jc).setText(" ");
							    }
							    	  //System.out.println(((JLabel) jc).getText());
							    
							    }
							    
							}
							
						}

		            });
			        
			        
			       
			        button.setOpaque(true);
			       
			        
		        g2d.setColor(Color.BLACK);
		        g2d.drawOval((int) (v.getValue0().intValue() + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue() + Size*size_coef/2 - Size/4),
		        		(int) ( Size* 0.5), (int) (Size* 0.5));
		        g2d.setColor(ColorChoice(i));
		        g2d.fillOval((int) (v.getValue0().intValue() + Size*size_coef/2 - Size/4), (int)(v.getValue1().intValue() + Size*size_coef/2 - Size/4),
		        		(int) ( Size* 0.5), (int) (Size* 0.5));
		       
		        i+=1;
		        
		   
		        /*  g2d.setTransform(tx2);
		        if (text_size == 20) {
		        if (W > 400 && i < 4 )
		        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/3*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  ); 
		        if (W > 600 && i < 8 )
		        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/3*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  );
		        if (W > 800 && i < 12 )
		        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/3*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  );
		        }
		        else {
		        	if (W > 600 && i < 4 )
			        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/5*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  );
		        		
			        if (W > 1000 && i < 8 )
			        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/5*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  );
			        if (W > 1400 && i < 12 )
			        	g2d.drawString(Url_list[i].substring(8) ,0 + (W/5*(int)(i/4)),(int)(ch*1.6) + (text_size * (int)(i%4))  );
		        	
		        	
		        }*/
		    
		    
		                    }
		   
		    
		    
		
		    }
			}
		

	}

