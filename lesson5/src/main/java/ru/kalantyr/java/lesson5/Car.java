package ru.kalantyr.java.lesson5;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getPrepareCountDown().countDown(); // сообщаем о готовности
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            race.getPrepareCountDown().await(); // ждём, когда другие машины тоже приготовятся к гонке
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        race.trySetWinner(this);
        race.getFinishCountDown().countDown(); // всё, эта машина закончила гонку
    }
}