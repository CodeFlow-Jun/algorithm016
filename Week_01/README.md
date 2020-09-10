# 学习笔记

## ***一、Java中 PriorityQueue 的源码分析***

### **总结**
#### **1. 什么是优先队列？**
   * PriorityQueue，是0个或多个元素的集合，是无界的，不允许null元素入队。<br>
   * 集合中的每个元素都有一个权重值，每次出队都弹出优先级最大或最小的元素。<br>
#### **2. 怎样实现一个PriorityQueue？**
   * 一般使用 二叉堆 来实现。<br>
   * 因为优先队列的本质是一个 最小堆，而堆则是一个满足特殊性质的数组。<br>
   * PriorityQueue最底层采用 数组 来存放数据，它有很多构造方法。<br>
   * 如果使用无参的构造方法，那么队列的最大容量将会采用默认值11，当一直向队列中添加元素时，如果达到了最大容量，那么将会进行扩容。<br>
#### **3. PriorityQueue是线程安全的吗？**
   * 不是，而PriorityBlockingQueue是线程安全的。 <br>
#### **4. 为什么PriorityQueue中的add(e)方法没有做异常检查呢？**
   * 因为PriorityQueue是无限增长的队列，元素不够用了会扩容，所以添加元素不会失败。<br>
#### **5. PriorityQueue是有序的吗？**
   * PriorityQueue不是有序的，只有堆顶存储着最小的元素；<br>
#### **6. PriorityQueue中添加的元素，一定是能比较的大小的元素，而如何比较大小呢？**
   * 有两种选择:<br>
        第一：在创建PriorityQueue时「指定一个Comparator类型的比较器」；<br>
        第二：添加到队列中的元素「自身实现Comparable接口」。<br>
        注意：使用无参构造方法时，优先级队列内部的比较器为null，因此在这种情况下，添加到队列中的元素需要实现Comparable接口，否则将会出现ClassCastException异常。<br>
#### **7. 创建一个PriorityQueue对象：**
```java
    PriorityQueue<E> priorityQueue = new PriorityQueue<>(n, new Comparator<E>() {
        @Override
        public int compare(E o1, E o2) {
            return o2-o1;
        }
    });
    // n 为创建的 Object[] 数组大小
    // 第二个参数是传入的比较器Comparator对象，可以为 null
    // 在Java8及以后，可以传入lambda表达式，使得代码更简洁
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> (y - x));
```
#### **8. PriorityQueue源码分析：**
```Java
// 存放数据，元素存在数组中
transient Object[] queue;

// 默认的初始容量为 11 
private static final int DEFAULT_INITIAL_CAPACITY = 11;

// 元素个数
private int size = 0;

// 创建一个优先队列对象，默认大小为11，队列中的元素按照自然顺序排序。
public PriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY, null);
}
```

#### **优先队列有两个常用的操作：向队列中添加元素、取出元素，这两个操作的方法为 add(E e)和 poll()。**
#### **添加元素**
```Java
//向优先级队列中添加元素，实际上就是向堆中插入一个元素，当插入一个元素后，为了满足堆的性质，因此可能需要堆化。
//add(E e) 调用 offer()，无异常抛出！！

public boolean add(E e) {
    return offer(e);
}

// 插入元素后，要进行上浮siftUp（）
public boolean offer(E e) {
    // 不支持null元素
    if (e == null)
        throw new NullPointerException();
    modCount++;
    // 取size
    int i = size;
    // 元素个数达到最大容量了，扩容
    if (i >= queue.length)
        grow(i + 1);
    // 元素个数加1
    size = i + 1;
    // 如果还没有元素，直接插入到数组第一个位置(下标为0的位置
    // 而堆是从1开始的
    if (i == 0)
        queue[0] = e;
    else
        // 否则，插入元素到数组size的位置，也就是最后一个元素的下一位
        // 注意这里的size不是数组大小，而是元素个数
        // 然后，再做自下而上的堆化，上浮
        siftUp(i, e); 
    return true;
}

//上浮
private void siftUp(int k, E x) {
    // 根据是否有比较器，使用不同的方法
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}

@SuppressWarnings("unchecked")
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        // 找到父节点的位置
        // 因为元素是从0开始的，所以减1之后再除以2
        int parent = (k - 1) >>> 1;
        // 父节点的值
        Object e = queue[parent];
        // 比较插入的元素与父节点的值
        // 如果比父节点大，则跳出循环
        // 否则交换位置
        if (key.compareTo((E) e) >= 0)
            break;
        // 与父节点交换位置
        queue[k] = e;
        // 现在插入的元素位置移到了父节点的位置
        // 继续与父节点再比较
        k = parent;
    }
    // 最后找到应该插入的位置，放入元素
    queue[k] = key;
}
```

#### **扩容**
```java
// 当数组比较小（小于64）的时候每次扩容容量翻倍；
// 当数组比较大的时候每次扩容只增加一半的容量；

private void grow(int minCapacity) {
    // 旧容量
    int oldCapacity = queue.length;
    // Double size if small; else grow by 50%
    // 旧容量小于64时，容量翻倍
    // 旧容量大于等于64，容量只增加旧容量的一半
    int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));
    // overflow-conscious code
    // 检查是否溢出
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
        
    // 创建出一个新容量大小的新数组并把旧数组元素拷贝过去
    queue = Arrays.copyOf(queue, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
}
```
#### **从优先级队列中取出元素的过程，就是删除堆顶元素的过程。**
#### **在删除完堆顶元素后，为了满足堆的性质，因此需要进行 下沉 堆化。**

#### **取出元素**
```Java
// 比较简单的做法就是，将数组中最后的一个元素搬到堆顶，然后再从上到下来进行siftDown()。
// remove()调用的poll()，只是没有元素的时候会抛出异常。

public E remove() {
    // 调用poll弹出队首元素
    E x = poll();
    if (x != null)
        // 有元素就返回弹出的元素
        return x;
    else
        // 没有元素就抛出异常
        throw new NoSuchElementException();
}

@SuppressWarnings("unchecked")
public E poll() {
    // 如果size为0，说明没有元素
    if (size == 0)
        return null;
    // 弹出元素，元素个数减1
    int s = --size;
    modCount++;
    // 队列首元素
    E result = (E) queue[0];
    // 队列末元素
    E x = (E) queue[s];
    // 将队列末元素删除
    queue[s] = null;
    // 如果弹出元素后还有元素
    if (s != 0)
        // 将队列末元素移到队列首
        // 再做自上而下的堆化
        siftDown(0, x);
    // 返回弹出的元素
    return result;
}

private void siftDown(int k, E x) {
    // 根据是否有比较器，选择不同的方法
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

@SuppressWarnings("unchecked")
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    // 只需要比较一半就行了，因为叶子节点占了一半的元素
    // >>>的含义是无符号右移， 「>>> 1」 实际上就是除以2
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        // 寻找子节点的位置，这里加1是因为元素从0号位置开始
        int child = (k << 1) + 1; // assume left child is least
        // 左子节点的值
        Object c = queue[child];
        // 右子节点的位置
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            // 左右节点取其小者
            c = queue[child = right];
        // 如果比子节点都小，则结束
        if (key.compareTo((E) c) <= 0)
            break;
        // 如果比最小的子节点大，则交换位置
        queue[k] = c;
        // 指针移到最小子节点的位置继续往下比较
        k = child;
    }
    // 找到正确的位置，放入元素
    queue[k] = key;
}
```

#### **查看队首元素**
```java
// 查看队首元素，element()调用的peek()，只是没取到元素时抛出异常。
// 队首元素下标为0
public E element() {
    E x = peek();
    if (x != null)
        return x;
    else
        throw new NoSuchElementException();
}
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}
```

## ***二、Java中 Queue 的源码分析***
#### Queue接口，FIFO，由LinkedList类实现
#### 创建一个Queue对象：
```java
    Queue<E> q=new LinkedList<>();
```
#### Queue与List、Set同一级别，都是继承了Collection接口。
#### LinkedList实现了Deque接口。
#### **Queue源码分析：**
```java
package java.util;
public interface Queue<E> extends Collection<E> {
    boolean add(E e); // 添加队首元素，添加成功，返回true，否则抛出异常
    boolean offer(E e); //  添加队首元素，添加成功，返回true，否则抛出异常，终止插入，并返回false
    E remove(); // 移除队首元素，改变队列结构，queue为空则抛出异常
    E poll();  // 移除队首元素，改变队列结构
    E element(); // 返回队首元素，不改变队列结构，queue为空则抛出异常
    E peek(); // 返回队首元素，不改变队列结构
}
```

## ***三、Java中 Stack 的源码分析***
#### Stack栈，FILO，继承于Vector
#### 由于Vector是通过数组实现的，这就意味着，Stack也是通过数组实现的，并具备Vector所有性质。
#### 创建一个Stack对象：
```java
    Stack<E> stack = new Stack<>();
```
#### 当然，我们也可以将LinkedList当作栈来使用。
#### **Stack源码分析：**
```java
package java.util;
public class Stack<T> extends Vector<T> {
    
    // 版本ID，这个用于版本升级控制
    private static final long serialVersionUID = 1224463164541339165L;
    
    public Stack() {}
    
    //push()，入栈，是通过将元素追加的数组的末尾中
    public T push(T item) {
        // addElement()的实现在Vector.java中
        addElement(item);
        return item;
    }
    
    //pop()，取出栈顶元素，并将该元素从栈中删除，是取出数组末尾的元素，然后将该元素从数组中删除
    public synchronized T pop() {
        if (elementCount == 0)
            throw new EmptyStackException();

        modCount++;
        T obj = elementData[--elementCount];
        
        elementData[elementCount] = null;
        return obj;
    }
    
    //取出栈顶元素，不执行删除，peek()，是返回数组末尾的元素
    public synchronized T peek() {
        if (elementCount == 0)
            throw new EmptyStackException();
        
        return elementData[elementCount - 1];
    }
    
    public synchronized boolean empty() {
        return elementCount == 0;
    }
    
    public synchronized int search(Object o) {
        int i = elementCount;
        while (--i >= 0)
            if (equals(o, elementData[i]))
                return elementCount - i;
        
        // 没有该元素，则返回 -1
        return -1;
    }
}
```
