package doit.sort;

import java.io.*;

public class Q020 {
    public static int[] a, tmp;
    public static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(1, n);
        for (int i = 1; i <= n; i++) {
            bw.write(a[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void mergeSort(int s, int e){
        if (e - s < 1){
            return;
        }
        int m = s + (e - s) / 2;
        mergeSort(s, m);
        mergeSort(m + 1, e);
        for(int i = s; i <= e; i++){
            tmp[i] = a[i];
        }
        int k = s;
        int index1 = s;
        int index2 = m + 1;
        while(index1 <= m && index2 <= e){
            if(tmp[index1] <= tmp[index2]){
                a[k] = tmp[index1];
                k++;
                index1++;
            } else {
                a[k] = tmp[index2];
                k++;
                index2++;
            }
        }
        while(index1 <= m){
            a[k] = tmp[index1];
            k++;
            index1++;
        }
        while(index2 <= e){
            a[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
