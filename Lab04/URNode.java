class URNode<E> { // Doubly linked list node

    private E e; // Value for this node
    private URNode<E> n; // Reference to next node in list
    private URNode<E> p; // Reference to previous node

    // Constructors
    URNode(E it, URNode<E> inp, URNode<E> inn) { e = it; p = inp; n = inn; }
    URNode(URNode<E> inp, URNode<E> inn) { p = inp; n = inn; }

    // Get and set methods for the data members
    public E element() { return e; } // Return the value
    public E setElement(E it) { return e = it; } // Set element value
    public URNode<E> next() { return n; } // Return next link
    public URNode<E> setNext(URNode<E> nextval) { return n = nextval; } // Set next link
    public URNode<E> prev() { return p; } // Return prev link
    public URNode<E> setPrev(URNode<E> prevval) { return p = prevval; } // Set prev link

}

