package myUtil_Set;

import java.util.HashMap;
import java.util.Iterator;

public class MyHashSet<E> extends MyAbstractSet<E> {

	
	private HashMap <E, Object> map;   
	
	/** Dummy value to associate with an Object in the backing Map
	    used as vlaues for all the keys in the backing Map
	*/
    private static final Object PRESENT = new Object();
	private int size;
	
    /**
     * Constructs a new, empty set; 
     */
    public MyHashSet() {
        map = new HashMap<>();
        size=0;
    }
    
    /**
	 * Create an empty set of default capacity
	 * 
	 */
    public MyHashSet(int initialCapacity) {
        super(); // can be skipped
    	this.map = new HashMap<>(initialCapacity);
    }
    
     
	@Override
    public Iterator<E> iterator(){
    	return this.map.keySet().iterator();
    }
       
	@Override
	public boolean contains(E o) { 
		// COMPLETE THIS
		return map.containsKey(o);
	}
	
	@Override
	// Hint. add e into map as key, with the Dummy object PRESENT as value.
	// Hint. look Map API for the return type of put
	public boolean add(E e) { 
		// COMPLETE THIS
		if(map.put(e, PRESENT)==null) {
			size++;
		}
		return map.put(e, PRESENT)==null;
	}
	
	@Override
	public boolean remove(E o) {
		// COMPLETE THIS
		if(map.remove(o)!=null) {
			size--;
		}
		return map.remove(o)!=null;
	}

	@Override
	public void clear() {
		// COMPLETE THIS
		size=0;
	}
	
	//Other methods, such as the following size(), isEmpty(), addAll(),   
	//are inherited from MyAbstractSet
     public int size() {
    	 return size;
     }
	 
     public boolean isEmpty() {
    	 return size==0;
     }
     @Override
	 public void addAll(MyCollection<? extends E> c) {
    	 for(E cc : c) {
    		 if(this.add(cc)==true)size++;
    	 }
	 }


}
