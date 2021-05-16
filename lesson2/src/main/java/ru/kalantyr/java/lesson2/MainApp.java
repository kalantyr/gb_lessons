package ru.kalantyr.java.lesson2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainApp {
    private static final Random random = new Random();

    public static final int readsCount = 10000;

    public static void main(String[] args) {
        System.out.println("--------- Read speed ----------");
        for (var count: new int[] { 10, 1000, 100000, 10000000 }) {
            var speed = readSpeedTest(new ArrayList<>(count), count);
            System.out.printf("[ArrayList] count = %d Speed (nanos) = %d%n", count, speed.toNanos());
            speed = readSpeedTest(new LinkedList<>(), count);
            System.out.printf("[LinkedList] count = %d Speed (nanos) = %d%n", count, speed.toNanos());
        }

        System.out.println("--------- Remove speed ----------");
        for (var count: new int[] { 100, 10000, 100000 }) {
            var speed = removeSpeedTest(new ArrayList<>(count), count);
            System.out.printf("[ArrayList] count = %d Speed (nanos) = %d%n", count, speed.toNanos());
            speed = removeSpeedTest(new LinkedList<>(), count);
            System.out.printf("[LinkedList] count = %d Speed (nanos) = %d%n", count, speed.toNanos());
        }
    }

    private static Duration readSpeedTest(List<Integer> list, int count) {
        for (var i = 0; i < count; i++)
            list.add(random.nextInt());

        var index = count / 2;
        var startTime = System.nanoTime();
        for (var i = 0; i < readsCount; i++)
            list.get(index);
        var finishTime = System.nanoTime();

        return Duration.ofNanos((finishTime - startTime) / readsCount);
    }

    private static Duration removeSpeedTest(List<Integer> list, int count) {
        for (var i = 0; i < count; i++)
            list.add(random.nextInt());

        var startTime = System.nanoTime();
        for (var i = 0; i < count / 2; i++) {
            var index = list.size() / 2;
            list.remove(index);
        }
        var finishTime = System.nanoTime();

        return Duration.ofNanos((finishTime - startTime) / (count / 2));
    }
}
