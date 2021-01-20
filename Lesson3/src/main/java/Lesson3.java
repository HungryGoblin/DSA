import java.util.Arrays;

public class Lesson3 {

    private static class Stack {
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new int[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(int i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public int pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }

    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }

    private static class Queue {
        protected int[] queue;
        protected int head;
        protected int tail;
        protected int capacity;

        public Queue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length - 1)
                tail = -1;
            queue[++tail] = i;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }
    }

    // 1. Создать программу, которая переворачивает вводимые строки (читает справа налево) при помощи стека.
    public static String reverse(String message) {
        Stack stack = new Stack(message.length());
        byte[] bytes = message.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            stack.push(bytes[i]);
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) stack.pop();
        }
        return new String(bytes);
    }

    // 2. Создать класс для реализации дека.
    private static class Deque extends Queue {

        public Deque(int size) {
            super(size);
        }

        public void insertFirst(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (isEmpty()) insert(i);
            else {
                capacity++;
                if (head > 0) head--;
                else {
                    tail++;
                    System.arraycopy(queue, 0, queue, 1, tail);
                }
                queue[head] = i;
            }
        }

        public int removeLast() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            capacity--;
            int tmp = queue[tail];
            tail--;
            return tmp;
        }
    }

    // 3. Создать класс с реализацией приоритетной очереди
    static class PriorityQueue {

        private int[] priority;
        private int[] queue;
        protected int capacity;

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        private int getPriorIndex() {
            if (isEmpty()) return -1;
            int idx = 0;
            int max = this.priority[0];
            for (int i = 0; i < capacity; i++) {
                if (max < this.priority[i]) {
                    idx = i;
                    max = this.priority[i];
                }
            }
            return idx;
        }

        public void insert(int value, int priority) {
            if (isFull()) throw new RuntimeException("Queue is full!");
            this.queue[capacity] = value;
            this.priority[capacity] = priority;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty!");
            int idx = getPriorIndex();
            int val = queue[idx];
            capacity--;
            if (capacity > 0) {
                System.arraycopy(queue, idx + 1, queue, idx, capacity - idx);
                System.arraycopy(priority, idx + 1, priority, idx, capacity - idx);
            }
            return val;
        }

        public PriorityQueue(int initial) {
            queue = new int[initial];
            priority = new int [queue.length];
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse("message"));
    }
}
