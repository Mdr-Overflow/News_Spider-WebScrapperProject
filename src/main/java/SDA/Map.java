package SDA;

import java.util.ArrayList;
import java.util.Objects;

import org.javatuples.Pair;

public class Map<K,V> {

	 // bucketArray is used to store array of chains
    private ArrayList<HashNode<K, V> > bucketArray;
 
    // Current capacity of array list
    private int numBuckets;
 
    // Current size of array list
    private int size;
 
    ArrayList<Integer> HashCodes;
    // Constructor (Initializes capacity, size and
    // empty chains.
    public Map()
    {
        bucketArray = new ArrayList<>();  ///   @@@@@@@@@@@@@@@@@@@@@@@@@@@ 
        numBuckets = 10;					/// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ num of nodes per div  change to 9 
        size = 0;
       HashCodes = new ArrayList<Integer>();
        // Create empty chains
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }
 
    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }
     
      private final int hashCode (K key) {
    	  int hash = Objects.hashCode(key);
    	  
        return hash;
    }
   
    // This implements hash function to find index
    // for a key
    private int getBucketIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        // key.hashCode() coule be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }
 
    // Method to remove a given key
    public V remove(K key)
    {
        // Apply hash function to find index for given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of chain
        HashNode<K, V> head = bucketArray.get(bucketIndex);
 
        // Search for key in its chain
        HashNode<K, V> prev = null;
        while (head != null) {
            // If Key found
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
 
            // Else keep moving in chain
            prev = head;
            head = head.next;
        }
 
        // If key was not there
        if (head == null)
            return null;
 
        // Reduce size
        size--;
 
        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);
 
        return head.value;
    }
 
    // Returns value for a key
    public V get(K key)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
          int hashCode = hashCode(key);
       
        HashNode<K, V> head = bucketArray.get(bucketIndex);
 
        // Search key in chain
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
 
        // If key not found
        return null;
    }
 
    // Adds a key value pair to hash
    public void add(K key, V value)
    {
        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
          int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        
        //if(head == null ) 
        
        // Check if key is already present
        while (head != null) {
        	
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }
 
        // Insert key in chain
        HashCodes.add(hashCode);
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode
            = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        if (head != null)
        head.is_start_of_bucket = false;
        else {}  // 
        newNode.set_is_start();
        bucketArray.set(bucketIndex, newNode);
        System.out.println(newNode.key + " " + newNode.value + " " +newNode.hashCode + " " + newNode.is_start_of_bucket +" " + newNode.next);
        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0 * size) / numBuckets >= 0.7) {
        	HashCodes.clear();
        	ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                	
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
		
		
		/*public ArrayList<K > getKeys(){
			
			ArrayList<K>  Keys = new ArrayList<K>();
			int bucketIndex = getBucketIndex(firstKey);
			 HashNode<K, V> head = bucketArray.get(bucketIndex);
			if(size != 0) {
				while (head != null) {
					 Keys.add(head.key);
		            }
		            head = head.next;
		        }
		    	 
		    	
		
			return Keys;
			}
			else return null;
			
		}*/
    
    public ArrayList<HashNode<K, V>> getAllNodes() {
    	
    	 
      // Make Allow Collison atribute , if true change alpha (load) part , make add parameter in node that tells if collided , when parsing 
    	// give red outline to is_collided() nodes 
    	// draw line from center for each node in next path
      
    	ArrayList<HashNode<K,V>> values = new ArrayList<HashNode<K,V>>();
       // Search key in chain
      
    	   for(int hash : HashCodes) {
    		   System.out.println(hash+"<-------------HASH");
    		   int index = hash % numBuckets;
    	       
    	        index = index < 0 ? index * -1 : index;
    	        
    		   HashNode<K, V> head = bucketArray.get(index);
    		   //System.out.println(head);
    		   if ( head != null && head.is_start_of_bucket == true ) {
    			   //System.out.println(head);
    			 
    				 
    				 
    	        if(!values.contains(head))       
    				   values.add(head);
    				   //return values;
    			   
    			      
    			   
    	       }
    		  
    		   
    		   }
    	   
          
       
       
	return values;

    	
    }
		
		
		
		
	}
	

