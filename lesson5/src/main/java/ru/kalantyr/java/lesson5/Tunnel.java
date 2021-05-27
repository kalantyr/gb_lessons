package ru.kalantyr.java.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore semaphore;

    /**
     * ctor
     * @param maxCapacity сколько машин одновременно может находиться в тунеле
     */
    public Tunnel(int maxCapacity) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(maxCapacity);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire(); // впускаем машину в тоннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release(); // машина больше не в тоннеле
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}