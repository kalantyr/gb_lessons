package ru.kalantyr.java.lesson8;

import org.hibernate.cfg.Configuration;
import ru.kalantyr.orm.Repository;

import javax.persistence.EntityManagerFactory;

public class MainApp {
    public static void main(String[] args){
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        var st1 = new Student("Елена", "Петрова");
        var st2 = new Student("Иван", "Сидоров");

        // ---------- Первый контекст ----------

        var rep1 = new Repository<>(factory, Student.class);
        rep1.add(st1);
        rep1.add(st2);
        var r1 = rep1.get(1);
        var r2 = rep1.get(2);
        print(r1);
        print(r2);
        // --- output
        // Id = 1 Елена Петрова
        // Id = 2 Иван Сидоров

        // ---------- Второй контекст ----------

        var rep2 = new Repository<>(factory, Student.class);
        var r3 = rep2.get(1);
        var r4 = rep2.get(2);
        print(r3);
        print(r4);
        // --- output
        // Id = 1 Елена Петрова
        // Id = 2 Иван Сидоров
    }

    private static void print(Student st) {
        System.out.printf("Id = %d %s %s%n", st.getId(), st.getFirstName(), st.getLastName());
    }
}
