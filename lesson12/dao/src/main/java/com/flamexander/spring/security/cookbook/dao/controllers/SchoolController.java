package com.flamexander.spring.security.cookbook.dao.controllers;

import com.flamexander.spring.security.cookbook.dao.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    /**
     * Получить список уроков
     */
    @GetMapping("/lessons")
    public String[] getLessons() {
        return schoolService.getLessons();
    }

    /**
     * Возвращает список оценок
     */
    @GetMapping("/grades")
    public int[] getGrades() {
        return schoolService.getGrades();
    }

    /**
     * Сдать домашнюю работу
     */
    @PutMapping("/homework")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitHomework(@RequestBody String homework) {
        schoolService.submitHomework(homework);
    }

    /**
     * Дать оценку домашней работе
     */
    @PutMapping("/grade/{gr}")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveGrade(@PathVariable int gr) {
        schoolService.giveGrade(gr);
    }

    // на случай, если внутри сервиса сработала проверка прав
    @ExceptionHandler
    public ResponseEntity<?> catchException(SecurityException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
