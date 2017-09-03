
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
            int[] arrFor = afterFor(new int[]{4, 2, 3, 1, 1, 4});
            System.out.println(Arrays.toString(arrFor));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        boolean for_one = haveForOne(new int[]{4, 1, 1});
        System.out.println(for_one);

    }

    public static int[] afterFor(int[] a) {
        int index = -1;
        int[] b;
        if (a.length == 0) throw new RuntimeException("Array is empty!");

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == 4) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            if (index == a.length - 1) {
                b = new int[0];
            } else {
                b = Arrays.copyOfRange(a, index + 1, a.length);
            }
        } else {
            throw new RuntimeException("4 not found!");
        }
        return b;
    }

    public static boolean haveForOne(int[] a) {
        if (a.length == 0) return false;
        Arrays.sort(a);
        if (!(a[0] == 1 && a[a.length - 1] == 4)) return false;

        boolean b = true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 1 && a[i] != 4) {
                b = false;
                break;
            }
        }
        return b;
    }
}
