public class Node<T> {
        T e;            // The E object that this node refers to
        Node<T> next;
        Node<T> prev; // The Node object that this node refers to

        /**
         * Constructs a generic node that refers to the given element.
         * @param e the element.
         */
        public Node(T e) {
            this.e = e;
            this.next = null;
            this.prev = null;
        }

        /**
         * Returns a textual representation of the element that this node refers to,
         * by calling the toString() method of the element.
         */
        public String toString() {
            return e.toString();
        }
}
