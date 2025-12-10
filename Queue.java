public class Queue<T> {
    private Node first;
    private Node last;
    private int size;
    public Queue(){
        this.first=new Node(null);
        this.last=new Node<>(null);
        this.size =0;
    }
    public void add(T t){
        Node next = new Node<>(t);
        if(this.size ==0){
            this.last=next;
            this.first.next=next;
            next.prev=this.first;
        }
        else {
            next.next=first.next;
            first.next.prev=next;
            this.first.next = next;
            next.prev = this.first;
        }
        size+=1;
    }
    public T get(){
        if(size==0){
            return null;
        }
        Node out = this.last;
        this.last=this.last.prev;
        this.last.next=null;
        size-=1;
        return (T) out.e;
    }
    public void remove(T t){
        Node<T> current = first.next;
        while (current != null) {
            if (current.e.equals(t)) {
                current.prev.next = current.next;
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    last = current.prev; // Update last if removing last element
                }
                size--;
                return; // Element found and removed, exit the method
            }
            current = current.next;
        }
    }
    public ListIterator<T> iterator() {
        return new ListIterator<T>(this.first.next);
    }

    public int getSize() {
        return size;
    }
}
