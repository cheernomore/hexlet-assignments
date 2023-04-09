package exercise;

import static exercise.App.take;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> assertList;

    @BeforeEach
    void prepareData() {
        this.assertList = new ArrayList<>();
        this.assertList.add(1);
        this.assertList.add(2);
        this.assertList.add(3);
        this.assertList.add(4);
        this.assertList.add(5);
    }

    @Test
    void testTake() {
        List<Integer> answerList = new ArrayList<>();
        answerList.add(1);
        answerList.add(2);
        answerList.add(3);

        assertArrayEquals(App.take(assertList, 3).toArray(), answerList.toArray());
        // END
    }
}
