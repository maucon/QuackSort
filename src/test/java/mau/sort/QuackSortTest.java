package mau.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuackSortTest {

    @BeforeAll
    static void beforeAll() {
        QuackSort.sort(new Integer[]{1, 2, 3}, (i) -> i);
    }

    @Order(0)
    @Test
    void sortTest() {
        Integer[] unsorted = {1, 8, 7};
        Integer[] unsorted2 = {1, 8, 7};

        QuackSort.sort(unsorted, (i) -> i);
        Arrays.sort(unsorted2);

        assertArrayEquals(unsorted, unsorted2);
    }

    @Order(1)
    @Test
    void sortTest2() {
        Integer[] unsorted = {1, 0, 7};
        Integer[] unsorted2 = {1, 0, 7};

        QuackSort.sort(unsorted, (i) -> i);
        Arrays.sort(unsorted2);

        assertArrayEquals(unsorted, unsorted2);
    }

    @Order(2)
    @ParameterizedTest(name = "{index} Sorting {0} numbers")
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000})
    void speedTest(int size) {
        Integer[] unsorted = new Integer[size];
        for (int i = 1; i < size + 1; i++) {
            unsorted[i - 1] = i;
        }
        List<Integer> toSort = Arrays.asList(unsorted);
        Collections.shuffle(toSort);

        unsorted = toSort.toArray(new Integer[0]);
        Integer[] unsorted2 = Arrays.copyOf(unsorted, size);

        long time = System.nanoTime();
        QuackSort.sort(unsorted, (i) -> i);
        long finalTimeSort = System.nanoTime() - time;
        System.out.println("SORT: " + finalTimeSort + " ns");

        time = System.nanoTime();
        Arrays.sort(unsorted2);
        long finalTimeJava = System.nanoTime() - time;
        System.out.println("JAVA: " + finalTimeJava + " ns");

        System.out.println(String.format("RATE: %.2fx", ((float) finalTimeJava / finalTimeSort)));

        assertArrayEquals(unsorted, unsorted2);
    }
}
