package project3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>{
	
	Node head; 
	Node tail; 
	int size;
	
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int getSize() {
	
		return size; 
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node node = new Node(data);
		
		if (tail == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail; 
			tail = node;
		}
		
		size++;
		
		return this; 
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		
		Node node = new Node(data);
		
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
		
		size++;
		
		return this; 
	}
	
	public T getFirst() {
		if (head == null) {
			return null;
		} else {
			return head.data;
		}
	}
	
	public T getLast() {
		if (tail == null) {
			return null;
		} else {
			return tail.data;
		}
	}
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		
		Node node = head;
		
		if (comparator.compare(targetData, node.data) == 0) {
			if (getSize() == 1) {
				head = null;
				tail = null;
				size--;
			} else {
				head = node.next;
				head.prev = null;
				size--;
			}
			
		}
		
		while (node.next != null) {
			node = node.next;
			if (comparator.compare(targetData, node.data) == 0) {
				if (comparator.compare(node.data, tail.data) == 0) {
					if (getSize() == 1) {
						head = null;
						tail = null;
						size--;
					} else {
						tail = node.prev;
						tail.next = null;
						size--;
					}					
					break;
				}
				Node nodePrev = node.prev;
				Node nodeNext = node.next;
				node = null;
				nodePrev.next = nodeNext;
				nodeNext.prev = nodePrev;
				size--;
				break;
			}
		}
		 
		return this; 
	}
	
	public T retrieveFirstElement() {
		Node node = head;
		
		if (head == null) {
			return null;
		} else {
			T data = head.data;
			head = node.next;
			head.prev = null;
			size--;
			return data;
		} 
	}

	public T retrieveLastElement() {
		Node node = tail;
		
		if (tail == null) {
			return null;
		} else {
			T data = tail.data;
			tail = node.prev;
			tail.next = null;
			size--;
			return data;
		} 
	}
	
	public ArrayList<T> toArrayList() {
		ArrayList<T> a = new ArrayList<T>();
		Node node = head;
		
		while (node != null) {
			a.add(node.data);
			node = node.next;
		}
		
		return a;
	}
	

	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		
		return new Iterate(); 
	}
	
	class Node {
		T data;
		Node next;
		Node prev;
		Node (T data) {
			this.data = data;
		}
	}
	
	class Iterate implements ListIterator<T> {	

		Node node; 
		Node nodePrev; 
		
		Iterate() {
			node = head;
		}
		
		
		@Override
		public T next() throws NoSuchElementException {
			if (node != null) {
				T data = node.data;
				nodePrev = node;
				node = node.next;
				return data;
			} else {					
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public T previous() throws NoSuchElementException {
			if (nodePrev != null) {
				T data = nodePrev.data;
				node = nodePrev;
				nodePrev = node.prev;
				return data;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public boolean hasNext() {
			if (node != null) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean hasPrevious() {
			if (nodePrev != null) {
				return true;
			} else {
				return false;
			} 
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			 throw new UnsupportedOperationException();
		} 
	
		@Override
		public void set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}	
	}
}
