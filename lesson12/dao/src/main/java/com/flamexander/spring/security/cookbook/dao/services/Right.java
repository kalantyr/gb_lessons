package com.flamexander.spring.security.cookbook.dao.services;

public enum Right {
    /**
     * Право видеть список уроков
     */
    LessonVew,

    /**
     * Право видеть оценки
     */
    GradeView,

    /**
     * Право сдать домашнюю работу
     */
    SubmitHomework,

    /**
     * Право дать оценку домашней работе
     */
    GiveGrade
}
