package ru.kalantyr.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kalantyr.lesson11.dto.UserDto;
import ru.kalantyr.lesson11.services.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Получить список всех пользователей
     */
    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }
}
