package doit.arrayandlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Q002Test {

    @Test
    public void testSolution(){
        assertEquals(75.0, Q002.solution(3, new int[]{40, 80, 60}), 0.000001);
        assertEquals(66.666667, Q002.solution(3, new int[]{10, 20, 30}), 0.000001);
        assertEquals(75.25, Q002.solution(4, new int[]{1, 100, 100, 100}), 0.000001);
        assertEquals(38.75, Q002.solution(5, new int[]{1, 2, 4, 8, 16}), 0.000001);
        assertEquals(65.0, Q002.solution(2, new int[]{3, 10}), 0.000001);
    }

}