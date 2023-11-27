package controller;

public class DoublyLinkedStack<T> {
    private final DoublyLinkedNode<T> trail;
    private DoublyLinkedNode<T> head;
    private int length;
    private int currentElementPosition;

    public DoublyLinkedStack() {
        trail = new DoublyLinkedNode<>(null);
        head = trail;
        length = 0;
        currentElementPosition = -1;
    }

    public T pop() {
        T returnValue = null;
        if (head != trail) {
            returnValue = head.getValue();
            head = head.getPrevious();
            --currentElementPosition;
        }
        return returnValue;
    }

    public T next() {
        T returnValue = null;
        if (head.getNext() != null) {
            head = head.getNext();
            returnValue = head.getValue();
            ++currentElementPosition;
        }
        return returnValue;
    }

    public boolean hasNext() {
        return head.getNext() != null;
    }

    public void push(T element) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode<>(element);
        head.setNext(node);
        node.setPrevious(head);
        head = node;
        ++currentElementPosition;
        length = currentElementPosition + 1;
    }
}
