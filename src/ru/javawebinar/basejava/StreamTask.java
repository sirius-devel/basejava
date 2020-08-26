package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamTask {

    public static void main(String[] args) {
        int[] values = {1, 3, 3, 2, 1, 1};
        List<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(5);
        list.add(10);
        list.add(13);

        StreamTask task = new StreamTask();
        System.out.println(task.minValue(values));
        System.out.println(task.oddOrEven(list));
    }

    int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> 10 * x  + y);
    }

    List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> evenInts = new ArrayList<>();
        List<Integer> oddInts = new ArrayList<>();
        return integers.stream().reduce(0, (x, y) -> {
            if (y % 2 == 0) {
                evenInts.add(y);
            } else {
                oddInts.add(y);
            }
            return x + y;
        }) % 2 == 0 ? oddInts : evenInts;
    }

}
