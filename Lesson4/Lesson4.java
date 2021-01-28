import java.util.LinkedList;
import java.util.Objects;

public class Lesson4 {
    // class iteroCat
    // reset()
    // next(), prev(for dll)
    // getCurrent()
    // atEnd()
    // insertBefore();
    // insertAfter();
    // deleteCurrent();

    private static class Cat {
        int age;
        String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat(a=%d,n=%s)", age, name);
        }
    }

    private static class SingleLinkedList {
        //here is your path...
        private class Node {
            Cat c;
            SingleLinkedList.Node next;

            public Node(Cat c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                SingleLinkedList.Node node = (SingleLinkedList.Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }

        public SingleLinkedList.Node head;

        public SingleLinkedList() {
            this.head = null;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void push(Cat c) {
            SingleLinkedList.Node n = new SingleLinkedList.Node(c);
            n.next = head;
            head = n;
        }

        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }

        public boolean contains(Cat c) {
            SingleLinkedList.Node n = new SingleLinkedList.Node(c);
            SingleLinkedList.Node current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }

        public void delete(Cat c) {
            SingleLinkedList.Node n = new SingleLinkedList.Node(c);
            SingleLinkedList.Node current = head;
            SingleLinkedList.Node previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }

        }
    }

    private static class DoubleLinkedList extends SingleLinkedList {

        private SingleLinkedList.Node child;

        public DoubleLinkedList() {
            super();
            child = null;
        }

        private class Node extends SingleLinkedList.Node {
            SingleLinkedList.Node prev;

            public Node(Cat c) {
                super(c);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next) &&
                        Objects.equals(prev, node.prev);
            }
        }

        public void push(Cat c) {
            SingleLinkedList.Node n = new SingleLinkedList.Node(c);
            n.next = head;
            head = n;
        }

        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }

        public boolean contains(Cat c) {
            SingleLinkedList.Node n = new SingleLinkedList.Node(c);
            SingleLinkedList.Node current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }

        private class Iterator {

            Iterator(DoubleLinkedList list) {
                this.list = list;
            }

            private DoubleLinkedList list;
            private Node current;

            public void reset() {
                current = head;
            }

            public Cat next() {
                return current.c;
            }

            public boolean hasNext() {
                return current.next != null;
            }

            public void add() {

            }

            public void insertBefore() {

            }

            public void insertAfter() {

            }

            public void remove() {
                delete(current.c);
            }

            public boolean hasPrevious() {
                return true;
            }

            public void previous() {

            }
        }

        public Iterator getIterator() {
            return new Iterator(this);
        }

    }

    public static void main(String[] args) {
        DoubleLinkedList catoList = new DoubleLinkedList();
        catoList.push(new Cat(10, "Мурка"));
        catoList.push(new Cat(5, "Кошка"));
        catoList.push(new Cat(5, "Луна"));
        catoList.push(new Cat(7, "Васька"));
        catoList.push(new Cat(3, "Пират"));

        System.out.println(catoList);
    }

}
