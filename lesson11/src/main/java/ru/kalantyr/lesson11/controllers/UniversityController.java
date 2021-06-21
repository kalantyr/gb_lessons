package ru.kalantyr.lesson11.controllers;

import ru.kalantyr.lesson11.entitites.Group;
import ru.kalantyr.lesson11.entitites.Student;
import ru.kalantyr.lesson11.entitites.University;
import ru.kalantyr.lesson11.repositories.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityRepository universityRepository;

    @GetMapping("/demo")
    public void findById() {
        University university = universityRepository.findByTitle("university1");
        for (Group g : university.getGroups()) {
            for (Student s : g.getStudents()) {
                System.out.println(s.getName());
            }
        }
    }
}
