package sortingalgo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HeapTest {

    @Test
    void sorting() {
        Heap heap = new Heap();
        int[] arr = {7, 9, 6, 4, 3, 1, 8, 5, 2};
        heap.sorting(arr);

        assertThat(arr).isEqualTo(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
}