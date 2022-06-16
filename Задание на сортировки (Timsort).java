import java.util.Arrays;
import java.util.Random;

public class TimeSort {

    public static final int ARRAY_SIZE = 50000;
    static int MIN_MERGE = 32;

    public static void main(String[] args) {

        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        int[] checkArray = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        System.arraycopy(array, 0, checkArray, 0, ARRAY_SIZE);
        System.out.println("Start user sort");
        long time = System.currentTimeMillis();
        timSort(array, array.length);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis() - time) / 1000d);
        System.out.println("Start java lib sort");
        time = System.currentTimeMillis();
        Arrays.sort(checkArray);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis() - time) / 1000d);
        System.out.println("Check result");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != checkArray[i]) {
                throw new RuntimeException("Error in index: " + i);
            }
        }
        System.out.println("OK");
    }

    public static int minRunLength(int n) {

        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void insertionSort(int[] arr, int left, int right) {

        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {

        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        System.arraycopy(arr, l, left, 0, len1);
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void timSort(int[] arr, int n) {

        int minRun = minRunLength(MIN_MERGE);
        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min((i + MIN_MERGE - 1), (n - 1)));
        }
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                if (mid < right) merge(arr, left, mid, right);
            }
        }
    }

}
