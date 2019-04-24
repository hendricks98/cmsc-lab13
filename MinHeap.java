
public class MinHeap < E extends Comparable<? super E>> {
	
	private E[] heap;
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 8;
	
	@SuppressWarnings("unchecked")
	public MinHeap(int capacity) {
		heap = (E[]) new Comparable[capacity];
	}
	
	public MinHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	 private void expand() {
		 E[] newHeap = (E[]) new Comparable[heap.length * 2];
		 for (int i = 0; i < size(); i++) {
			 newHeap[i] = heap[i];
		 }
		 heap = newHeap;
	}
	 
	 private void swapElements(int p1, int p2) {
		 E temp = heap[p1];
		 heap[p1] = heap[p2];
		 heap[p2] = temp;
	 }
	 
	 private int getParentIndex(int childIndex) {
		 // if odd, child is a left node
		 if (childIndex % 2 != 0) {
			 return childIndex / 2;
		 }
		 // if even, child is a right node
		 else {
			 return childIndex / 2 - 1;
		 }
	 }
	 
	 public void insert(E element) {
		 int position = size();
		 
		 if (position == size()) {
			 this.expand();
		 }
		 
		 size++;
		 heap[position] = element;
		 
		 int parent = getParentIndex(position);
		 
		 while (position > 0 && heap[position].compareTo(heap[parent]) < 0) {
			 swapElements(parent, position);
			 position = getParentIndex(position);
			 parent = getParentIndex(position);
		 }
	 }
	 
	 public int smallerChildIndex(int position) {
		 
		 int smallest = position;
		 
		 int right = smallest * 2 + 2;
		 int left = smallest * 2 + 1;
		 
		 if (left < size() - 1 && (heap[left]).compareTo(heap[smallest]) < 0) {
			 smallest = left;
		 }
		 
		 if (right < size() - 1 && (heap[right]).compareTo(heap[smallest]) < 0) {
			 smallest = right;
		 }
		 
		 return smallest;	 
		 
	 }
	 
	 public E remove() {
		 
		 if (isEmpty()) {
			 return null;
		 }
		 
		 E min = heap[0];
		 heap[0] = heap[size() - 1];
		 heap[size() - 1] = null;
		 size--;
		 
		 int position = 0;
		 int smallerChild = (int) smallerChildIndex(position);
		 
		 while ((int) smallerChildIndex(position) != position) {
			 swapElements(position, smallerChild);
			 position = smallerChild;
			 smallerChild = (int) smallerChildIndex(position);
		 }
		 
		 return min;
	 }
	 
	 public E get(int position) {
		 return heap[position];
	 }
	 
	 public static void main(String args[]) {
		 MinHeap<Integer> mh = new MinHeap<Integer>();
		 
		 mh.insert(2);
		 mh.insert(4);
		 mh.insert(1);
		 mh.insert(10);
		 mh.insert(3);
		 mh.insert(6);
		 mh.insert(15);
		 mh.insert(12);
		 mh.insert(16);
		 mh.insert(5);
		 
		 while (!mh.isEmpty()) {
			 System.out.println("Removed: " + mh.remove());
		 }
		 
	 }
	 
	 
	
}
