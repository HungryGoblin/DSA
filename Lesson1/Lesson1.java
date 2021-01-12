public class Lesson1 {

    // 1. Описать простейшие алгоритмы
    //  1.1. Возведение в степень *используя чётность степени*
    //  1.2. Поиск минимального элемента в массиве
    //  1.3. Нахождение среднего арифметического массива
    // 2. Подсчитать сложность описанных алгоритмов
    // 3. Какие правила подсчёта применялись, оставьте комментарии в коде

    public static void main(String[] args) {
        System.out.printf("3pow2: %d%n", power(3, 3));
        System.out.printf("minEl {10,2,3,6,-4,0,8}: %d%n", minEl(new int[]{10, 2, 3, 6, -4, 0, 8}));
        System.out.printf("minEl {10,2,3,6,-4,0,8}: %.2f%n", avg(new float[]{10.0F, 2.0F, 3.0F, 6.0F, -4.0F, 0.0F, 8.0F}));
    }

    //  1.1. Возведение в степень *используя чётность степени*
    // сложность O(Log(N))
    public static int power(int base, int pow) {
        if (pow == 0) {
            return 1;
        } else if (pow % 2 == 1) {
            return power(base, pow - 1) * base;
        } else {
            int bpow = power(base, pow / 2);
            return bpow * bpow;
        }
    }

    //  1.2. Поиск минимального элемента в массиве
    // Сложность O(N)
    public static int minEl(int[] arr) {
        int minEl = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (minEl > arr[i]) {
                minEl = arr[i];
            }
        }
        return minEl;
    }

    //  1.3. Нахождение среднего арифметического массива
    // Сложность O(N)
    public static float avg(float[] arr) {
        float summ = 0f;
        for (int i = 0; i < arr.length; ++i) {
            summ += arr[i];
        }
        return summ / (float) (arr.length - 1);
    }
}
