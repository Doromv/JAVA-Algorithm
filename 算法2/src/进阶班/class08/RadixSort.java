package 进阶班.class08;

import java.util.Arrays;

/**
 * @author Doromv
 * @create 2022-05-10-15:54
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static void radixSort(int[] arr, int l, int r, int digit) {
        int[] help=new int[r-l+1];
        final int radix=10;
        for (int d = 1; d <=digit; d++) {
            int[] count=new int[radix];
            for (int i=l;i<=r;i++){
                int j = getDigit(arr[i], d);
                count[j]++;
            }
            for (int i=1;i<radix;i++){
                count[i]+=count[i-1];
            }
            for (int i=r;i>=l;i--){
                int j=getDigit(arr[i],d);
                help[count[j]-1]=arr[i];
                count[j]--;
            }
            for (int i=l,j=0;i<=r;i++,j++){
                arr[i]=help[j];
            }
        }
    }

    public static int maxbits(int[] arr) {
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int res=0;
        while (max!=0){
            res++;
            max/=10;
        }
        return res;
    }

    public static int getDigit(int x, int d) {
        int chu= (int) Math.pow(10,d-1);
        return (x/chu)%10;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }

}
