package controller;

public class DoublyLinkedNode<T> {
    private final T value;
    private DoublyLinkedNode<T> previous;
    private DoublyLinkedNode<T> next;


    // Constructeur qui initialise le nœud avec une valeur
    public DoublyLinkedNode(T value) {
        this.value = value;
    }

    // Méthode pour obtenir la valeur du nœud
    public T getValue() {
        return value;
    }

    // Méthode pour obtenir le nœud précédent dans la liste
    public DoublyLinkedNode<T> getPrevious() {
        return previous;
    }

    // Méthode pour définir le nœud précédent dans la liste
    public void setPrevious(DoublyLinkedNode<T> previous) {
        this.previous = previous;
    }

    // Méthode pour obtenir le nœud suivant dans la liste
    public DoublyLinkedNode<T> getNext() {
        return next;
    }

    // Méthode pour définir le nœud suivant dans la liste
    public void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }
}
