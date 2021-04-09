package mau.sort;

import java.util.function.Function;

public class QuackSort {

    public static <T> void sort(T[] objects, Function<T, Integer> objectMapper) {
        int[] keys = new int[objects.length];
        for (int i = 0; i < objects.length; i++) keys[i] = objectMapper.apply(objects[i]);

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : keys) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        int valueLength = max - min + 1;
        Object[] values = new Object[valueLength];

        for (int i = 0; i < keys.length; i++)
            values[keys[i] - min] = objects[i];

        for (int i = 0, count = 0; i < valueLength; i++)
            if (values[i] != null) //noinspection unchecked
                objects[count++] = (T) values[i];
    }
}
