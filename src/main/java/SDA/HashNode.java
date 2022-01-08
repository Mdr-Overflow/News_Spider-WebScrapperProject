package SDA;

public class HashNode<K,V> {
	K key;
	V value;
	HashNode<K, V>next;
	int hashCode;
	boolean is_start_of_bucket = false;
	boolean is_collided = false;
	 public HashNode(K key, V value, int hashCode)
	    {
	        this.key = key;
	        this.value = value;
	          this.hashCode = hashCode;
	    }
	 
	 public void set_is_start() {
		 
		 this.is_start_of_bucket = true;
	 }
	 
	 public void set_is_collided() {
		this.is_collided = true;
	 }
	 
	 
	 
	 
	@Override
	public String toString() {
		return "HashNode [key=" + key + ", value=" + value + ", next=" + next + ", hashCode=" + hashCode
				+ ", is_start_of_bucket=" + is_start_of_bucket + ", is_collided=" + is_collided + "]";
	}
}
 