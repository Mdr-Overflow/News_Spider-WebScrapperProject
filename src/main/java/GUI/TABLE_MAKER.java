package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;


import DB.DB_CONNECT;
import DB.DB_UA;

public class TABLE_MAKER {

	public static JScrollPane  Make_UA_TABLE(DB_CONNECT ua , JPanel panel , JLabel Table_N, String TABLESTATE  , String to_search, String column, 
			String what_to_order_by , String order) {
		

		String[] columns = { "Error"};
	      String[][]  data = new String[1][2];
	      
		//DB_UA ua = new DB_UA();
		//ua.ResultsQ.substring(ag.ResultsQ.lastIndexOf("FROM")+5)
		
		String[] View_name= new String[1];
		
		System.out.println(ua.ResultsQ);
		System.out.println("___________________________________________________________________");
		//Make view
		Connection dbConnView = null;
		if (TABLESTATE == "normal")
		{
		 dbConnView = DB.DB_ViewMaker.Table_make_view(ua.getCon(),ua.ResultsQ.substring(ua.ResultsQ.lastIndexOf("FROM")+5),View_name);
		Table_N.setText(View_name[0]);
		}
		
		if (TABLESTATE == "search")
		{
			System.out.println("WE SEARCHINNNNNNNNNNNNNNNNNN'");
		 dbConnView = DB.DB_ViewMaker.Table_make_view_search(ua.getCon(),ua.ResultsQ.substring(ua.ResultsQ.lastIndexOf("FROM")+5), to_search ,column  ,View_name);
		Table_N.setText(View_name[0]);
		}
		
		if (TABLESTATE == "order")
		{
			System.out.println(order + " WE BE ORDERRRINNNNN'");
		 dbConnView = DB.DB_ViewMaker.Table_make_view_ordered(ua.getCon(),ua.ResultsQ.substring(ua.ResultsQ.lastIndexOf("FROM")+5), what_to_order_by ,order ,View_name);
		Table_N.setText(View_name[0]);
		}
		
		
		DefaultTableModel model = new DefaultTableModel(data,columns);
		System.out.println(View_name[0]);
		try {
			//Pair<String[],String[][]> ModelPair = new Pair<String[],String[][]>(DB.DB_UTILS.GetTableModel(ua.GetConn(),ua.ResultsQ.substring(ua.ResultsQ.lastIndexOf("FROM")+5) ));
			
			
			model= DB.DB_UTILS.GetTableModel(dbConnView,View_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		
		//APP.setForeground(new Color(255, 153, 51));
		
		JTable table = new JTable(model);
		//table.setBounds(10, 11, 576, 313);
	      table.setShowGrid(true);
	      table.setShowVerticalLines(true);
	      table.getCellEditor(0, 0).addCellEditorListener(table);
	      table.setFont(new Font("Arial Black", Font.BOLD, 14));
	      table.setForeground(new Color(255, 153, 51));
	      
	      JScrollPane pane = new JScrollPane(table);
	      pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	      pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	      System.out.println("---------->" + panel.getX() + panel.getY()  + panel.getHeight() + panel.getWidth());
	      //pane.setViewportView(panel.getRootPane());
	      
	      pane.setBounds(panel.getX() , panel.getY()  , panel.getHeight() , panel.getWidth());
	      
	      table.addMouseListener(mous);
	      
	      
	      return pane;
	      // pane.setBounds(panel.getBounds(getBounds()));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static MouseInputListener mous = new MouseInputListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			jTable1MouseClicked(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	};




	private static void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		JTable source = (JTable)evt.getSource();
		int row = source.rowAtPoint(evt.getPoint());
		int column = source.columnAtPoint(evt.getPoint());
		String s = source.getModel().getValueAt(row, column) + "";

		// Create a JTextArea with the message and set its maximum size
		JTextArea messageTextArea = new JTextArea(s);
		messageTextArea.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setEditable(false);

		// Put the JTextArea in a JScrollPane
		JScrollPane scrollPane = new JScrollPane(messageTextArea);
		scrollPane.setPreferredSize(new Dimension(250, 250));

		// Display the JOptionPane with the JScrollPane as the message component
		// and provide a custom title
		JOptionPane.showMessageDialog(null, scrollPane, "Data", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
	
	
	
}
