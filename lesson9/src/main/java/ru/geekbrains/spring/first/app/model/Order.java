package ru.geekbrains.spring.first.app.model;

public class Order {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Order() {
    }

    public Order(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
