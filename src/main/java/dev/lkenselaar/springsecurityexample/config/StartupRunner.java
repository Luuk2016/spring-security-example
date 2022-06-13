package dev.lkenselaar.springsecurityexample.config;

import dev.lkenselaar.springsecurityexample.model.Role;
import dev.lkenselaar.springsecurityexample.model.User;
import dev.lkenselaar.springsecurityexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.add(new User(null, "John Doe", "john", "password", Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        userService.add(new User(null, "Jane Roe", "jane", "password", List.of(Role.ROLE_USER)));
    }
}
