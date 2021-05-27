package ru.kalantyr.java.lesson5;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(CARS_COUNT,
                new Road(60),
                new Tunnel(CARS_COUNT / 2),
                new Road(40)
        );
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            race.getPrepareCountDown().await(); // ждём, когда все машины будут готовы
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            race.getFinishCountDown().await(); // ждём, когда все машины закончат гонку
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

            System.out.println("-----------------------------------");
            System.out.printf("%s - WIN", race.getWinner().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
