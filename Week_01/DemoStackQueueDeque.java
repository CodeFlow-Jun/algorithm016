package Week01;

import javax.swing.*;
import java.util.*;

public class DemoStackQueueDeque {

    public static void main(String[] args) {
        DemoStackQueueDeque stack = new DemoStackQueueDeque();
        stack.re_stack();

        DemoStackQueueDeque queue = new DemoStackQueueDeque();
        queue.re_queue();

        DemoStackQueueDeque deque = new DemoStackQueueDeque();
        deque.re_deque();
    }

    public void re_stack() {

        Stack<String> stack = new Stack<>();

        stack.push("我爱学习");
        stack.push("学习使我快乐");
        stack.push("我爱刷题");
        stack.push("刷题可以找到好工作");
        stack.push("五毒神掌好");
        stack.push("头发蹭蹭掉");
        stack.push("待我学成时");
        stack.push("我要睡大觉");

        System.out.println(stack);

        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("*********stack*********");
        System.out.println("peek(),查看栈顶元素，不改变栈内结构: " + stack.peek());
        System.out.println("stack size: " + stack.size());
        System.out.println(stack);

        System.out.println("*********stack*********");
        System.out.println("search(),查看栈中元素的索引: " + "我要睡大觉 —— " + stack.search("我要睡大觉"));
        System.out.println("search(),查看栈中元素的索引: " + "我爱学习 —— " + stack.search("我爱学习"));
        System.out.println("栈顶索引为 1 ");

        System.out.println("*********stack*********");
        System.out.println("pop(),栈顶元素出栈，改变栈内结构：" + stack.pop());
        System.out.println("stack size: " + stack.size());
        System.out.println(stack);

        System.out.println("*********stack*********");
        while (stack.size() > 0) {
            System.out.println(stack.pop());
            System.out.println(stack);
        }

        System.out.println("*********stack*********");
        System.out.println("isEmpty(),空为false，反之true：" + stack.isEmpty());

    }

    private void re_queue() {

        // The offer() method inserts an element if possible, otherwise returning false.
        // the Collection.add() method, which can fail to add an element only by throwing an unchecked exception.

        // When the queue is empty:
        // the remove() method throws an exception,
        // while the poll() method returns null.

        // The element() and peek() methods return, but do not remove, the head of the queue.

        Queue<String> queue = new LinkedList<>();
        queue.offer("我");
        queue.offer("爱");
        queue.offer("刷");
        queue.offer("题");
        queue.offer("呵呵");

        System.out.println("*********queue*********");
        System.out.println(queue);
        System.out.println("*********queue*********");
        System.out.println("查看队首元素，不改变队列结构：" + queue.peek());
        System.out.println(queue.size());
        System.out.println("*********queue*********");
        System.out.println("取出队首元素，改变队列结构：" + queue.poll());
        System.out.println(queue.size());
        System.out.println("*********queue*********");
        while (queue.size() > 0) {
            System.out.println(queue.poll());
            System.out.println(queue);
        }
    }

    private void re_deque() {

        Deque<String> deque = new LinkedList<>();

        //Queue Method	Equivalent Deque Method
        //add(e)	    addLast(e)
        //offer(e)	    offerLast(e)
        //remove()	    removeFirst()
        //poll()	    pollFirst()
        //element()	    getFirst()
        //peek()	    peekFirst()

        //Stack Method	Equivalent Deque Method
        //push(e)	    addFirst(e)
        //pop()	        removeFirst()
        //peek()	    getFirst()

        System.out.println("*********deque*********");
        deque.addFirst("a");
        deque.addLast("a");
        deque.offerFirst("b");
        deque.offerLast("b");
        //add(), offer() 默认队尾添加
        deque.add("c");
        deque.offer("c");
        System.out.println(deque);

        System.out.println("peek(): " + deque.peek());
        System.out.println("peekFirst(): " + deque.peekFirst());
        System.out.println("peekLast(): " + deque.peekLast());
        System.out.println("*********deque*********");

        //remove(), poll() 默认队首删除
        System.out.println(deque);
        deque.remove();
        deque.poll();
        System.out.println("remove(), poll(): " + deque);
        deque.removeFirst();
        deque.removeLast();
        System.out.println("removeFirst(), removeLast(): " + deque);
        deque.pollFirst();
        deque.pollLast();
        System.out.println("pollFirst(),pollLast(): " + deque);
        System.out.println("*********deque*********");
    }
}
