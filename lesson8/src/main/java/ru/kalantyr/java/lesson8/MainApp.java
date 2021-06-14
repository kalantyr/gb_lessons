package ru.kalantyr.java.lesson8;

import org.hibernate.cfg.Configuration;
import ru.kalantyr.orm.IRepository;
import ru.kalantyr.orm.Repository;
import ru.kalantyr.orm.SearchMode;

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

        // ---------- Третий контекст ----------

        IRepository<Student> rep3 = new Repository<>(factory, Student.class);

        System.out.println("Ищем что-то похожее на 'Петров'");
        var searchResults = rep3.fullTextSearch("Петров", SearchMode.Like);
        for (var st : searchResults)
            print(st);
        // --- output
        // Id = 1 Елена Петрова

        System.out.println("Ищем 'Сидоров'");
        searchResults = rep3.fullTextSearch("Сидоров", SearchMode.Exact);
        for (var st : searchResults)
            print(st);
        // --- output
        // Id = 2 Иван Сидоров
    }

    private static void print(Student st) {
        System.out.printf("Id = %d %s %s%n", st.getId(), st.getFirstName(), st.getLastName());
    }
}
