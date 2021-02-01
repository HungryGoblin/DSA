public class Power {

    public static int pow (int base, int pow) {
        if (pow == 0) return 1;
        return base * pow(base, pow - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 16; i++) {
            System.out.printf("2^%d = %d%n", i, pow(2, i));
        }
    }

}
