package project3;

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		head = null;
		tail = null;
		size = 0;
		this.comparator = comparator;
	}
	
	public SortedDoubleLinkedList<T> add(T data) {
		Node node = new Node(data); 
		Node nodeCheck = head; 
		
		if (head == null) {
			head = node;
			tail = node;
		} else if (comparator.compare(nodeCheck.data, node.data) > 0) {
			node.next = head;
			head.prev = node;
			head = node;
		} else if (comparator.compare(tail.data, node.data) < 0) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else { 
			while (nodeCheck.next != null && comparator.compare(nodeCheck.next.data, node.data) < 0) {
				nodeCheck = nodeCheck.next;
			}
			node.next = nodeCheck.next;
			node.next.prev = node;
			nodeCheck.next = node;
			node.prev = nodeCheck;
		}	
		size++;
		return this;
	}
	
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}
	
	
}
