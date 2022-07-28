package programers.level3.p84021;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class P84021Test {

    @Test
    void rotateTest () {
        //given
        //기준 좌표
        int sx = 0, sy = 0;

        //회전 하려는 좌표
        int tx1 = 0, ty1 = 1;
        int tx2 = 1, ty2 = 0;

        //when
        int tmp;

        tmp = tx1;
        tx1 = -ty1;
        ty1 = tmp;

        tmp = tx2;
        tx2 = -ty2;
        ty2 = tmp;

        //then
        assertThat(sx).isEqualTo(0);
        assertThat(sy).isEqualTo(0);

        assertThat(tx1).isEqualTo(-1);
        assertThat(ty1).isEqualTo(0);

        assertThat(tx2).isEqualTo(0);
        assertThat(ty2).isEqualTo(1);

    }
}