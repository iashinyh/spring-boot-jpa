package inv.iashinyh.jpa.rest;

import inv.iashinyh.jpa.dto.User;
import inv.iashinyh.jpa.repository.UserRepository;
import inv.iashinyh.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public User findOne(@PathVariable String username) {
        return userService.findOne(username);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/users/{username}")
    public User modify(@PathVariable String username, @RequestBody User user){
        return userService.modify(username, user);
    }

    @DeleteMapping("/users/{username}")
    public User delete(@PathVariable String username) {
        return userService.delete(username);
    }
}
