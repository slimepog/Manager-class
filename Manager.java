public class Manager <T extends Comparable<T>>{
    private Heap<T> priority;
    private Queue<T> order;
    public Manager(){
        this.priority=new Heap<>();
        this.order=new Queue<>();
    }
    public void add(T t){
        this.order.add(t);
        this.priority.add(t);
    }
    public T getByCreationTime(){
        if(this.order.getSize()==0){
            return null;
        }
        T obj = this.order.get();
        this.priority.remove(obj);
        return obj;
    }
    public T getByPriority(){
        if(this.priority.getSize()==0){
            return null;
        }
        T obj = this.priority.get();
        this.order.remove(obj);
        return obj;
    }
}
