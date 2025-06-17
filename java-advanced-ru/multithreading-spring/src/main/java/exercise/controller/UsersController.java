package exercise.controller;

import exercise.model.User;
import io.r2dbc.spi.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping("/{id}")
    public Mono<User> show(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public Mono<User> update(@RequestBody User user, @PathVariable Integer id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
    // END
}
