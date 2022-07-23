public class LinkedListDeque<T> implements Deque<T> {
    private class DequeNode{
        private T Item;
        private DequeNode prev;
        private DequeNode next;

        public DequeNode(T item) {
            Item = item;
        }

        public T getRecursive(int index) {
            if(index == 0){
                return Item;
            }
            return next.getRecursive(index - 1);
        }
    }

    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        DequeNode first = new DequeNode(item);
        DequeNode p = sentinel.next;
        sentinel.next = first;
        p.prev = first;
        first.next = p;
        first.prev = sentinel;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        DequeNode last = new DequeNode(item);
        DequeNode p = sentinel.prev;
        sentinel.prev = last;
        p.next = last;
        last.prev = p;
        last.next = sentinel;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque() {
        DequeNode p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.Item + " ");
            p = p.next;
        }
    }

    @Override
    public T removeFirst() {
        if(size > 0){
            DequeNode first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            size = size - 1;
            return first.Item;
        }else{
            return null;
        }
    }

    @Override
    public T removeLast() {
        if(size > 0){
            DequeNode last = sentinel.prev;
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            size = size - 1;
            return last.Item;
        }else{
            return null;
        }
    }

    @Override
    public T get(int index) {
        DequeNode p = sentinel.next;
        while(p != sentinel){
            if(index == 0){
                return p.Item;
            }else{
                index = index - 1;
                p = p.next;
            }
        }
        return null;
    }

    public T getRecursive(int index) {
        if(index > size - 1 || index < 0 || sentinel.next == null){
            return null;
        }
        return sentinel.next.getRecursive(index);
    }
}