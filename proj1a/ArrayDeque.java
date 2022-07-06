public class ArrayDeque<T> {

    private T[] item;
    private int first;
    private int last;
    private int size;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        first = last = size = 0;
    }

    private void resize(int cap) {
        T it[] = (T[]) new Object[cap];
        if(first >= last){
            System.arraycopy(item, first, it, 0, item.length - first);
            System.arraycopy(item, 0, it, item.length - first, last);
        }else{
            System.arraycopy(item, first, it, 0, size);
        }
        item = it;
        first = 0;
        last = size;
    }

    public void addFirst(T it) {
        if(isFull()){
            resize(size * 2);
        }
        first = (first - 1 + item.length ) % item.length;
        item[first] = it;
        size = size + 1;
    }

    public void addLast(T it) {
        if(isFull()){
            resize(size * 2);
        }
        item[last] = it;
        last = (last + 1) % item.length;
        size = size + 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private boolean isFull(){
        return size == item.length;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        if(!isEmpty()){
            int pos = first;
            do{
                System.out.print(item[pos] + " ");
                pos = pos + 1;
            }while(pos % item.length != last);
        }
    }

    private boolean isRatioValid() {
        if(item.length >= 16 && (1.0 * size / item.length) < 0.25){
            return false;
        }else{
            return true;
        }
    }

    public T removeFirst() {
        if(isEmpty()) return null;
        if(!isRatioValid()){
            resize(size * 2);
        }
        T it = item[first];
        item[first] = null;
        first = (first + 1) % item.length;
        size = size - 1;
        return it;
    }

    public T removeLast() {
        if(isEmpty()) return null;
        if(!isRatioValid()){
            resize(size * 2);
        }
        int lastPos = (last - 1 + item.length) % item.length;
        T it = item[lastPos];
        item[lastPos] = null;
        last = lastPos;
        size = size - 1;
        return it;
    }

    public T get(int index){
        if(index > size - 1 || index < 0) {
            return null;
        }
        int getPos = (first + index) % item.length;
        return item[getPos];
    }
}
