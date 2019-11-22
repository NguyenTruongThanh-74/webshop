package hello.shoppingwithspring.repository.user;



import hello.shoppingwithspring.model.product.Product_User;
import hello.shoppingwithspring.model.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductUserRepository extends CrudRepository<Product_User, Integer> {

    @Query("select u from Product_User u where u.user_id = ?1")
    List<Product_User> findByUser_id(Integer id);

    @Modifying
    @Query("delete from Product_User b where b.user_id=:user_id")
    void deleteUser(@Param("user_id") Integer user_id);


    @Query("select u from Product_User u where u.user_id=:user_id and u.product_id=:product_id")
    Product_User findByUser_idAndProduct_id(Integer user_id,Integer product_id);



}
