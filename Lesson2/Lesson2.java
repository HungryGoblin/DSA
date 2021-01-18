import java.util.Arrays;

public class Lesson2 {

    private static class Array {
        private int arr[];
        private int size;
        private boolean isSorted;

        private Array() {
            isSorted = false;
        }

        public Array(int size) {
            this();
            this.size = size;
            this.arr = new int[size];
        }

        public Array(int... args) {
            this();
            this.size = args.length;
            this.arr = args;
        }

        public Array(boolean isSorted, int... args) {
            this(args);
            this.isSorted = isSorted;
        }

        public int get(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            return arr[index];
        }

        public void set(int index, int value) {
            arr[index] = value;
            isSorted = false;
        }

        public boolean delete() { // last
            if (size == 0) return false;
            size--;
            return true;
        }

        public boolean delete(int index) {
            checkIndex(index);
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            size--;
            return true;
        }

        public boolean checkIndex(int index) {
            if (index > size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            return true;
        }

        public boolean deleteAll(int value) {
            for (int index = 0; index < size; index++) {
                if (arr[index] == value) {
                    if (index > 0)
                        System.arraycopy(arr, 0, arr, 0, size - index - 1);
                    if (index < size)
                        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
                    size--;
                }
            }
            return true;
        }

        public boolean insert(int index, int value) {
            checkIndex(index);
            append(value);
            System.arraycopy(arr, 0, arr, 0, index);
            System.arraycopy(arr, index, arr, index + 1, size - index - 1);
            arr[index] = value;
            if (isSorted) sortBubble();
            return true;
        }

        public boolean deleteAll() {
            size = 0;
            return true;
        }

        public void append(int value) {
            if (size >= arr.length - 1) {
                int[] temp = arr;
                arr = new int[size * 2];
                System.arraycopy(temp, 0, arr, 0, size);
            }
            arr[size++] = value;
            isSorted = false;
        }

        public boolean isInArray(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value) {
                    return true;
                }
            }
            return false;
        }

        public int hasValue(int value) {
            if (!isSorted)
                throw new RuntimeException("Trying to search in unsorted array");

            int l = 0;
            int r = size;
            int m;
            while (l < r) {
                m = (l + r) >> 1;
                if (value == arr[m]) {
                    return m;
                } else {
                    if (value < arr[m]) {
                        r = m;
                    } else {
                        l = m + 1;
                    }
                }
            }

            return -1;
        }

        private void swap(int a, int b) {
            int tmp = this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }

        public void sortBubble() {
            for (int out = size - 1; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    if (this.arr[in] > arr[in + 1]) {
                        swap(in, in + 1);
                    }
                }
            }
            isSorted = true;
        }

        public void sortSelect() {
            for (int i = 0; i < size; i++) {
                int flag = i;
                for (int j = i + 1; j < size; j++) {
                    if (arr[j] < arr[flag])
                        flag = j;
                }
                swap(i, flag);
            }
            isSorted = true;
        }

        public void sortInsert() {
            for (int out = 1; out < size; out++) {
                int temp = arr[out];
                int in = out;
                while (in > 0 && arr[in - 1] >= temp) {
                    arr[in] = arr[in - 1];
                    in--;
                }
                arr[in] = temp;
            }
            isSorted = true;
        }

        public void sortCount() {
            int[] countArr = new int[2048];
            for (int i = 0; i < size; i++) {
                countArr[arr[i] + 1024]++;
            }
            int len = 0;
            for (int i = 0; i < countArr.length; i++) {
                for (int j = 0; j < countArr[i]; j++) {
                    arr[len] = i - 1024;
                    len++;
                }
            }
            isSorted = true;
        }

        @Override
        public String toString() {
            if (arr == null)
                return "null";
            int iMax = size - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arr[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
    }

    public static void main(String[] args) {
//        Array array = new Array(9, 2, 3, 7, 4, 5, 3, 6, 7, 1, 8, 0, 9);
        Array array = new Array(-3,-2,-1,0,1,2,3);
        System.out.println(array);
//        array.deleteAll(9);
//        System.out.println(array);
        array.insert(0, 10);
        array.insert(3, 10);
        array.insert(array.size, 10);
        System.out.println(array);
        array.sortCount();
        System.out.println(array);
//        array.delete();
//        array.delete();
//        System.out.println(array);
//        array.delete(2);
//        System.out.println(array);
//        array.sortInsert();
//        System.out.println(array);
//        System.out.println(array.hasValue(7));
    }

    private static void standardArrayThings() {
        int[] arr;
        int ar2[];
        ar2 = new int[5];
        arr = new int[]{1, 2, 3, '_', 5, 6, 7};
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
