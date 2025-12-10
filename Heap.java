import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private int size;
    private T[] heap;

    public Heap() {
        this.heap = (T[]) new Comparable[1];
        this.size = 0;
    }

    public Heap(int size) {
        if (size != 0) {
            this.heap = (T[]) new Comparable[size];
        } else {
            this.heap = (T[]) new Comparable[1];
        }
        this.size = 0;
    }

    public void percDown(int parentIndex, T[] arr) {
        int leftChildIndex = getLeftChildIndex(parentIndex);
        while (isIndexValid(leftChildIndex)) {
            int biggerChildIndex = leftChildIndex;
            int rightChildIndex = getRightChildIndex(parentIndex);
            if (isIndexValid(rightChildIndex) && arr[rightChildIndex].compareTo(arr[leftChildIndex]) > 0) {
                biggerChildIndex = rightChildIndex;
            }
            if (arr[biggerChildIndex].compareTo(arr[parentIndex]) > 0) {
                swapValues(biggerChildIndex, parentIndex, arr);
                parentIndex = biggerChildIndex;
                leftChildIndex = getLeftChildIndex(parentIndex);
            } else {
                break;
            }
        }
    }

    public void percUp(int childIndex, T[] arr) {
        int parentIndex = getParentIndex(childIndex);
        while (isIndexValid(parentIndex) && arr[childIndex].compareTo(arr[parentIndex]) > 0) {
            swapValues(childIndex, parentIndex, arr);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < this.size;
    }

    private int getRightChildIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private void swapValues(int childIndex, int parentIndex, T[] arr) {
        T temp = arr[parentIndex];
        arr[parentIndex] = arr[childIndex];
        arr[childIndex] = temp;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.heap.length == this.size;
    }

    public void add(T t) {
        if (isFull()) {
            resizeHeap();
        }
        this.heap[this.size] = t;
        this.size += 1;
        percUp(this.size - 1, this.heap);  // Use this.size - 1 for the last added element index
    }

    private void resizeHeap() {
        T[] arr = (T[]) new Comparable[this.heap.length * 2];
        for (int i = 0; i < this.size; i++) {
            arr[i] = this.heap[i];
        }
        this.heap = arr;
    }

    public String toString() {
        return Arrays.toString(this.heap);
    }

    public T get() {
        if (!isEmpty()) {
            T root = this.heap[0];
            swapValues(0, this.size - 1, this.heap);
            this.size -= 1;
            percDown(0, this.heap);
            this.heap[this.size] = null;
            return root;
        }
        return null;
    }

    public void remove(T t) {
        if (!isEmpty()) {
            for (int i = 0; i < this.size; i++) {
                if (this.heap[i].equals(t)) {
                    swapValues(i, this.size - 1, this.heap);
                    this.size -= 1;
                    percDown(i, this.heap);
                    this.heap[this.size] = null;
                    break;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }
}
