package hello.shoppingwithspring.service;

import hello.shoppingwithspring.model.user.User;
import hello.shoppingwithspring.web.dto.userregister.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
