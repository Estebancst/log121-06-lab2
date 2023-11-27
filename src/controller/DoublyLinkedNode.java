package controller;

public class DoublyLinkedNode<T> {
    private final T value;
    private DoublyLinkedNode<T> previous;
    private DoublyLinkedNode<T> next;


    public DoublyLinkedNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public DoublyLinkedNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyLinkedNode<T> previous) {
        this.previous = previous;
    }

    public DoublyLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }
}
