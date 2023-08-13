package main.rest;

import java.util.Collections;
import java.util.Map;
import main.model.Role;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/registration")
  public String registration(){
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(User user, Map<String, Object> model){
    User userFromDb = userRepository.findByUsername(user.getUsername());

    if (userFromDb != null){
      model.put("message", "User exist!");
      return "registration";
    }

    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    userRepository.save(user);

    return "redirect:/login";
  }
}
