package ru.kalantyr.java.lesson4;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainApp {
    static final int SIZE = 100_000_000;

    public static void main(String[] args) {
        var dur1 = calculate();
        System.out.printf("Длительность однопоточного вычисления, сек.: %d", dur1.getSeconds());
        System.out.println();

        var threadCount = Runtime.getRuntime().availableProcessors(); // сколько ядер доступно
        var dur2 = calculate(threadCount);
        System.out.printf("Длительность многопоточного (потоков: %d) вычисления, сек.: %d", threadCount, dur2.getSeconds());
        System.out.println();

        /* output:
        Длительность однопоточного вычисления, сек.: 11
        Длительность многопоточного (потоков: 4) вычисления, сек.: 3
         */
    }

    /**
     * Вычисление в основном потоке
     * @return длительность работы
     */
    public static Duration calculate() {
        var array = createArray();

        var startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++)
            array[i] = calculateItem(i, array[i]);
        var finishTime = System.nanoTime();

        return Duration.ofNanos(finishTime - startTime);
    }

    /**
     * Многопоточное вычисление
     * @return длительность работы
     */
    public static Duration calculate(int threadCount) {
        var array = createArray();
        var blockLength = array.length /  threadCount;

        ExecutorService execService = Executors.newFixedThreadPool(threadCount);

        var startTime = System.nanoTime();
        for (var threadNo = 0; threadNo < threadCount; threadNo++) {
            var startIndex = blockLength * threadNo;
            execService.execute(() -> {
                for (var i = startIndex; i < startIndex + blockLength; i++)
                    array[i] = calculateItem(i, array[i]);
            });
        }

        execService.shutdown();
        try {
            execService.awaitTermination(1, TimeUnit.MINUTES);
            return Duration.ofNanos(System.nanoTime() - startTime);
        } catch (InterruptedException e) {
            return Duration.ofNanos(System.nanoTime() - startTime);
        }
    }

    private static float[] createArray() {
        var array = new float[SIZE];
        Arrays.fill(array, 1);
        return array;
    }

    private static float calculateItem(int i, float currentValue){
        return (float)(currentValue * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
