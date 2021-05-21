package myUtil_Set;


import java.util.Iterator;
import java.util.LinkedHashMap;

public class MyLinkedHashSet<E> extends MyAbstractSet<E> {

	
	public LinkedHashMap <E, Object> map;  // non-private for testing purposes
	private int size;
	/** Dummy value to associate with an Object in the backing Map
    used as vlaues for all the keys in the backing Map
    */
    private static final Object PRESENT = new Object();
	
	
    /**
     * Constructs a new, empty set; 
     */
    public MyLinkedHashSet() {
    	// COMPLETE THIS
    	map = new LinkedHashMap();
    	size=0;
    }
    
    /**
	 * Create an empty set of default capacity
	 * 
	 */
    public MyLinkedHashSet(int initialCapacity) {
    	// COMPLETE THIS
    	map = new LinkedHashMap(initialCapacity);
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
