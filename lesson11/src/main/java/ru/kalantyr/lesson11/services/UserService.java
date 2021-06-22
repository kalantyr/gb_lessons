package ru.kalantyr.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kalantyr.lesson11.dto.UserDto;
import ru.kalantyr.lesson11.entitites.Mapper;
import ru.kalantyr.lesson11.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper = new Mapper();

    @PostConstruct
    public void init() {
        var user1 = new UserDto();
        user1.setName("Василий Пупкин");
        add(user1);

        var user2 = new UserDto();
        user2.setName("Иван Иванов");
        add(user2);
    }

    public void add(UserDto userDto) {
        userRepository.save(mapper.map(userDto));
    }

    public List<UserDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toUnmodifiableList());
    }
}
