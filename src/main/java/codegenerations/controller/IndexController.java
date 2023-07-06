package codegenerations.controller;

import ann.Handleable;
import codegenerations.repository.UserRepository;

@Handleable
public class IndexController extends codegenerations.controller.IndexControllerExtras {

    protected IndexController(UserRepository userRepository) {
        super(userRepository);
    }
}
