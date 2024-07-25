package doit.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        quickSort(a, 0, n - 1, k - 1);
        System.out.println(a[k - 1]);
    }

    public static void quickSort(int[] a, int lo, int hi, int k) {
        if (lo < hi) {
            int pivot = partition(a, lo, hi);
            if (pivot == k) {
                return;
            }
            else if (k < pivot){
                quickSort(a, lo, pivot - 1, k);
            } else {
                quickSort(a, pivot + 1, hi, k);
            }
        }
    }

    public static int partition(int[] a, int lo, int hi) {
        if (lo + 1 == hi) {
            if(a[lo] > a[hi]) {
                swap(a, lo, hi);
            }
            return hi;
        }
        int mid = (lo + hi) / 2;
        swap(a, lo, mid);
        int pivot = a[lo];
        int i = lo + 1;
        int j = hi;
        while(i <= j){
            while (i >= lo + 1 && pivot < a[j]) {
                j--;
            }
            while(i <= hi && pivot > a[i]) {
                i++;
            }
            if(i >= j){
                swap(a, i++, j--);
            }
        }

        a[lo] = a[j];
        a[j] = pivot;
        return j;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
