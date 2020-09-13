package Week01;

import java.util.Deque;

public class Design_Circular_Deque_641 {

    class MyCircularDeque {

        int[] arr;
        int size;
        int front;
        int last;

        // front永远指向队列的头部元素，last永远指向队列尾部元素的下一个位置。
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            if (k < 0) {
                throw new IllegalArgumentException("Deque size is less than 0!");
            }
            this.arr = new int[k];
            this.size = 0;
            this.front = 0;
            this.last = 0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (size == arr.length) {
                return false;
            }
            if (front == 0) {
                arr[arr.length - 1] = value;
                front = arr.length - 1;
            } else {
                arr[--front] = value;
            }
            size ++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (size == arr.length) {
                return false;
            }
            arr[last] = value;
            size ++;
            last = last == arr.length - 1 ? 0 : last + 1;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }
            front = front == arr.length - 1 ? 0 : front + 1;
            size--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }
            if (last == 0) {
                last = arr.length - 1;
            } else {
                last --;
            }
            size --;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if (size == 0) {
                return -1;
            }
            return arr[front];
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if (size == 0) {
                return -1;
            }
            return last == 0 ? arr[arr.length - 1] : arr[last - 1];
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size == arr.length;
        }
    }
}
