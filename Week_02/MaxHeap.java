package Week02;



import java.util.ArrayList;

import java.util.Random;

public class MaxHeap<E extends Comparable> {

    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }
    public MaxHeap() {
        data = new ArrayList<>();
    }

    // Heapify()
    // 将任意数组整理成堆的形状
    public MaxHeap(ArrayList<E> arr) {
        data = (ArrayList<E>) arr.clone();
        for (int i = parent(arr.size() - 1); i > 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return data.size();
    }

    // 返回一个布尔值，表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return  index * 2 + 2;
    }

    // 向堆中添加元素，涉及到sift up
    public void add(E e) {
        // 在数组的末尾添加一个元素
        data.add(e);
        // 需要siftUp元素的索引,即，数组中最后一个元素的索引
        siftup(data.size() - 1);
    }

    private void siftup(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 交换 k 与 parent(k)
            swap(k, parent(k));
            k = parent(k); // k 来到其父亲节点的位置
        }
    }

    private void swap(int i, int j) {
        if (i < 0 || i >= data.size() || j < 0 || j >= data.size())
            throw new IllegalArgumentException("Index is illegal.");
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    // 查看堆中的最大元素
    public E findMax() {
        if (data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 从堆中取出最大元素，涉及sift down
    public E extractMax() {
        E ret = findMax();
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        // k 左孩子所在节点的索引如果比数组的元素总数还要小，说明k有左孩子，可以sift down
        while(leftChild(k) < data.size()) {
            int j = leftChild(k);
            if (j + 1 < data.size() && data.get(j+1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            // 索引j 所在的节点为leftChild 和 rightChild 中的最大值
            // 比较 k 与 其最大孩子节点的索引j 的大小
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            swap(k, j);
            k = j; // 维护k在数组中的索引
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
            // System.out.println(arr[i]);
        }
        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test Maxheap completed.");
    }

    // 取出堆中的最大元素，并且替换成新元素e;
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
