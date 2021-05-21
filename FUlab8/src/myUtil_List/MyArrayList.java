package myUtil_List;

public class MyArrayList<E> implements MyList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	
	int size;  // non-private for testing purpose
	E [] elementData; // non-private for testing purpose
	
	/**
	 * Create an empty list of capacity capa
	 * 
	 * @param capa the initial capacity. Assume greater than 0.
	 */
	@SuppressWarnings({"unchecked"})
    public MyArrayList(int capa) {
		this.elementData = (E[])new Object [capa]; // generic for Array, have to do this
		size=0;
	}

	/**
	 * Create an empty list of default capacity
	 * 
	 */
    @SuppressWarnings({"unchecked"})
    public MyArrayList() {
		this.elementData = (E[])new Object [DEFAULT_CAPACITY];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public boolean contains(E o) {
		// TODO Auto-generated method stub
		boolean check=false;
		for(E e:elementData) {
			if(e!= null && o!=null && e.equals(o)) check=true;
		}
		return check;
	}

	@Override
	public void add(E o) {
		// TODO Auto-generated method stub
		if(size==elementData.length) {
			this.extendList();
		}
		elementData[size]=o;
		size++;
	}

	@Override
	public void remove(E o) {
		// TODO Auto-generated method stub
		int index=-1;
		for(int i=0; i<size;i++) {
			if(elementData[i].equals(o)) {
				index=i;
				break;
			}
		}
		if(index!=-1) {
			E[] copy=this.copyE(index);
			int j=1;
			for(int i=index;i<size && j<copy.length;i++) {
				elementData[i]=copy[j];
				j++;
			}
			size--;
			elementData[size]=null;
		}
	}
	
//	private void cleandata() {
//		for(int i=0; i<size;i++) {
//			elementData[i]=null;
//		}
//	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if(this.isEmpty() || index >=size) {
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		if(this.isEmpty() || index>= size) {
			throw new IndexOutOfBoundsException();
		}
		E e=elementData[index];
		elementData[index]=element;
		return e;
	}
	//helper method
	private E[] copyE(int ii) {
		E[] copy= (E[])new Object [size-ii];
		if(size==elementData.length) {
			copy= (E[])new Object [size+DEFAULT_CAPACITY];
		}
		int k=0;
		for(int i=ii;i<size;i++) {
			copy[k]=elementData[i];
			k++;
		}
		return copy;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		if(this.isEmpty() || index>=size) {
			throw new IndexOutOfBoundsException();
		}
		E[] copy= this.copyE(index);
		if(size==elementData.length) {
			this.extendList();
		}
		//from index to size,store element into copy
		//
		int j=0;
		elementData[index]=element;
		for(int i=index; i<size+1 && j<copy.length;i++) {
			elementData[i+1]=copy[j];
			j++;
		}
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		if(index >=size) {
			throw new IndexOutOfBoundsException();
		}
		E remove=elementData[index];
		E[] copy=this.copyE(index);
		int j=1;
		for(int i=index;i<size && j<copy.length;i++) {
			elementData[i]=copy[j];
			j++;
		}
		size--;
		elementData[size]=null;
		return remove;
	}

	@Override
	public int indexOf(E o) {
		// TODO Auto-generated method stub
		int index=-1;
		for(int i=0; i<elementData.length;i++) {
			if(elementData[i]!=null && elementData[i].equals(o)) {
				index=i;
				break;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(E o) {
		// TODO Auto-generated method stub
		int index=-1;
		for(int i=0; i<elementData.length;i++) {
			if(elementData[i]!=null && elementData[i].equals(o)) {
				index=i;
			}
		}
		return index;
	}
	private void extendList() {
		E[] copy= (E[])new Object [size+DEFAULT_CAPACITY];
		for(int i=0; i<size;i++) {
			copy[i] = elementData[i];
		}
		elementData=copy;
	}

}
