package hello.shoppingwithspring.repository.user;

import hello.shoppingwithspring.model.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {


    @Query("select u from Product u")
    List<Product> findAll();


}
