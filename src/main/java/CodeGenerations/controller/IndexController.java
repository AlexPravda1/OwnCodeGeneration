package CodeGenerations.controller;

import CodeGenerations.model.User;
import CodeGenerations.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequiredArgsConstructor
public class IndexController {
    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index() {
        List<User> userList = userRepository.findAll();
        return "Total users: " + userList.size() + ". Users in database: " + userList;
    }
}
