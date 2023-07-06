package codegenerations.controller;
 
import codegenerations.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import codegenerations.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;
 
@RestController
public class IndexControllerExtras {
 
    private final UserRepository userRepository;
 
    protected IndexControllerExtras(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    @GetMapping("/")
    public final String handleIndexController() {
        List<User> userList = userRepository.findAll();
        return "Total users: " + userList.size() + ". Users in database: " + userList;
    }
}
