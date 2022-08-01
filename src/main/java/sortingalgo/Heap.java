package sortingalgo;

import java.util.Arrays;

public class Heap {

    public static void main(String[] args) {
        int[] arr = {7, 9, 6, 4, 3, 1, 8, 5, 2};
        Heap heap = new Heap();
        heap.sorting(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public void sorting(int[] arr) {
        int size = arr.length;

        //정렬 불필요
        if (size < 2) {
            return;
        }

        /*
         * left child node = index * 2 + 1
         * right child node = index * 2 + 2
         * parent node = (index - 1) / 2
         */

        //가장 마지막 요소의 부모 인덱스
        int parent = getParent(size - 1);

        //heap 생성
        for (int i = parent; i >= 0; i--) {
            heapify(arr, i, size - 1);
        }

        for (int i = size - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i - 1);
        }

    }

    private void heapify(int[] arr, int parent, int last) {

        int left, right, large;

        /*
         * 현재 부모 인덱스의 자식 노드 인덱스가
         * 마지막 인덱스를 넘지 않을 때 까지 반복한다.
         *
         * 이 때 왼쪽 자식 노드를 기준으로 해야 한다.
         * 오른쪽 자식 노드를 기준으로 범위를 검사하게 되면
         * 마지막 부모 인덱스가 왼쪽 자식만 갖고 있을 경우
         * 왼쪽 자식노드와는 비교 및 교환을 할 수 없기 때문이다.
         */
        while (parent * 2 + 1 <= last) {
            left = parent * 2 + 1;
            right = parent * 2 + 2;
            large = parent;

            //left
            if (arr[left] > arr[large]) {
                large = left;
            }

            //right
            if (right <= last && arr[right] > arr[large]) {
                large = right;
            }

            /*
             * 교환이 발생했을 경우 두 원소를 교체 한 후
             * 교환이 된 자식노드를 부모 노드가 되도록 교체한다.
             */
            if (large != parent) {
                swap(arr, parent, large);
                parent = large;
            } else {
                return;
            }

        }

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int getParent(int child) {
        return (child - 1) / 2;
    }


}
