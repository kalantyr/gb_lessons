package ru.kalantyr.java.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }

    private final CountDownLatch prepareCountDown;
    private final CountDownLatch finishCountDown;
    private Car winner;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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

    /**
     * Победитель гонки
     */
    public Car getWinner() {
        try {
            lock.readLock().lock();
            return winner;
        }
        finally{
            lock.readLock().unlock();
        }
    }

    /**
     * Пробует установить победителя гонки
     */
    public void trySetWinner(Car winner) {
        try {
            lock.writeLock().lock();
            if (this.winner == null)
                this.winner = winner;
        }
        finally{
            lock.writeLock().unlock();
        }
    }
}