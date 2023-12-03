package controller;

public class DoublyLinkedStack<T> {
    private final DoublyLinkedNode<T> trail;
    private DoublyLinkedNode<T> head;
    private int length;
    private int currentElementPosition;

    // Constructeur qui initialise la pile avec un nœud de fin
    public DoublyLinkedStack() {
        trail = new DoublyLinkedNode<>(null);
        head = trail;
        length = 0;
        currentElementPosition = -1;
    }

    // Méthode pop pour retirer et retourner l'élément en haut de la pile
    public T pop() {
        T returnValue = null;
        if (head != trail) {
            returnValue = head.getValue();
            head = head.getPrevious();
            --currentElementPosition;
        }
        return returnValue;
    }

    // Méthode next pour passer à l'élément suivant dans la pile
    public T next() {
        T returnValue = null;
        if (head.getNext() != null) {
            head = head.getNext();
            returnValue = head.getValue();
            ++currentElementPosition;
        }
        return returnValue;
    }

    // Méthode hasNext pour vérifier s'il y a un élément suivant dans la pile
    public boolean hasNext() {
        return head.getNext() != null;
    }

    // Méthode push pour ajouter un élément en haut de la pile
    public void push(T element) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode<>(element);
        head.setNext(node);
        node.setPrevious(head);
        head = node;
        ++currentElementPosition;
        length = currentElementPosition + 1;
    }
}
