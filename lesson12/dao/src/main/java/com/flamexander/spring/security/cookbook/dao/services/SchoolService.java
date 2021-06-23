package com.flamexander.spring.security.cookbook.dao.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final String[] lessons = {
        "Русский язык",
        "Математика",
        "Литература",
        "Физкультура"
    };
    private final int[] grades = { 5, 4, 3, 2, 3, 4, 5 };

    /**
     * Возвращает список уроков
     */
    public String[] getLessons() {
        demand(Right.LessonVew);
        return lessons;
    }

    /**
     * Возвращает список оценок
     */
    public int[] getGrades() {
        demand(Right.GradeView);
        return grades;
    }

    /**
     * Сдать домашнюю работу
     */
    public void submitHomework(String homework) {
        demand(Right.SubmitHomework);
        System.out.println(homework);
    }

    /**
     * Дать оценку домашней работе
     */
    public void giveGrade(int grade) {
        demand(Right.GiveGrade);
        System.out.println(grade);
    }

    //---
    // Конечно, права будут проверяться и на уровне контроллеров...
    // Но проверка в сервисе - это надежнее, плюс её можно из юнит-тестов проверять (а контроллеры из юнит-тестов непонятно как...)
    //---
    /**
     * Проверяет наличие указанного права у текущего пользователя
     */
    private void demand(Right right) {
        var authorityName = right.name();

        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals(authorityName)))
            return;

        throw new SecurityException("Недостаточно прав для выполнения операции");
    }
}
