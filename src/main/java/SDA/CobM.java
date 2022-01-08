package SDA;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.javatuples.Pair;



public class CobM {

	
	
	Map<String, Integer> map;
	int last_index ;
	VerticesGEN vgen;
	
	
	public CobM() {
		super();
		map =new Map<String, Integer>();
		last_index = 0;
		vgen = new VerticesGEN();
	}




	public  void Start(ArrayList<HashNode<String, Integer>> nodes, JPanel panel, int last_index, Double ZOOM) {
		
		
		
		vgen.generateNodesEX(panel,nodes,last_index, ZOOM);
		
		
		
		/*for (HashNode<String, Integer> node : nodes) {
			
			
			
		}
		*/
		
		
	}
	
	
	
	
	public  void StartDrw(String Key, String Value,JPanel panel)
	{
		
		
		
		
		map.add(Key, Integer.parseInt(Value));
		
	
		ArrayList<HashNode<String, Integer>> Nodes = new ArrayList<HashNode<String, Integer>>(map.getAllNodes());
		
		for (HashNode node : map.getAllNodes())
			System.out.println(node);
		
		Start(map.getAllNodes(),panel,last_index,1.);
		last_index++;
		
		
	}
	
	
	public void KILL() {
		
		map = new Map<String, Integer>();
		vgen = new VerticesGEN();
		last_index = 0;
	}
	
	
	public ArrayList<Pair<Double,Double>> getVertices(){
		
		return vgen.Vertices;
		
	}
	
	
	public void Redraw(JPanel panel) {
		Start(map.getAllNodes(),panel,last_index,1.);
		last_index++;
		
		
	}




	public void RedrawZOOM(JPanel panel, Double ZOOM) {
		
		Start(map.getAllNodes(),panel,last_index,ZOOM);
		last_index++;
		
	}





}
