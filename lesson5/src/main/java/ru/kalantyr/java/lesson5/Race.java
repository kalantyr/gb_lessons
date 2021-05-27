package ru.kalantyr.java.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }

    private final CountDownLatch prepareCountDown;
    private final CountDownLatch finishCountDown;

    /**
     * Примитив синхронизации для этапа подготовки к гонкам
     */
    public CountDownLatch getPrepareCountDown() {
        return prepareCountDown;
    }

    /**
     * Примитив синхронизации для завершения всей гонки
     */
    public CountDownLatch getFinishCountDown() {
        return finishCountDown;
    }

    public Race(int carsCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        prepareCountDown = new CountDownLatch(carsCount);
        finishCountDown = new CountDownLatch(carsCount);
    }
}