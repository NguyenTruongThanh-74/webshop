package hello.shoppingwithspring.repository.user;

import hello.shoppingwithspring.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {


    User findByEmail(String email);
}
