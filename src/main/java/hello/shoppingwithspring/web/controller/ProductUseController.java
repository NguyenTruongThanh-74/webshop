package hello.shoppingwithspring.web.controller;


import hello.shoppingwithspring.model.product.Product;
import hello.shoppingwithspring.model.product.Product_User;
import hello.shoppingwithspring.repository.user.ProductRepository;
import hello.shoppingwithspring.repository.user.ProductUserRepository;
import hello.shoppingwithspring.repository.user.UserRepository;
import jdk.internal.jline.internal.Nullable;
import jdk.internal.jline.internal.TestAccessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ProductUseController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;



    @Autowired
    ProductUserRepository productUserRepository;




    @GetMapping("/showproduct")
    public String show(Model model){
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Product> list = productRepository.findAll();
        model.addAttribute("list",list);

    //    List<User_Product> list_userproduct = userProductRepository.findByUser_id(userRepository.findByEmail(authentication.getName()).getId());
        long price = 0;

   //     User_Product user_product =new User_Product();
        //user_product.setId();


      /*  for (User_Product p: list_userproduct) {
            Collection<Product_User> product_users=p.getId_Product();
            System.out.println("user+id         " + p.getUser_id());
            System.out.println("PRODUCT             " +  product_users.size());
       //     price += productRepository.findById(product_users).get().getPriceProduct();
        }
        System.out.println("size   " + list.size());

        System.out.println("price             "+price); */

        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //  List<User_Product> list_userproduct = userProductRepository.findByUser_id(userRepository.findByEmail(authentication.getName()).getId());
        /*List<Product_User> list_product_user = new ArrayList<>();
        for (Product product:list) {
            Product_User product_user=new Product_User();
            product_user.setProduct_id(product.getId());
            list_product_user.add(product_user);
        }*/

        //model.addAttribute("list_product_user",list_product_user);
        Integer a[] =new Integer[]{};
        model.addAttribute("numberproduct",a);
        model.addAttribute("p",price);
        return "dung/showproduct";
    }

    @PostMapping("/showproduct")
    public String NumeberProduct(@ModelAttribute("numberproduct") Integer numberproduct[])
    {
        for (Integer i: numberproduct) {
            System.out.println("&&&&&&&&&&&&&&" + i);
        }
        return "asdasd";

    }


    @GetMapping("/listproduct")
    public String showlistproduct(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("list",productList);
        return "dung/listproduct";
    }

    @GetMapping("/viewdetail/{id}")
    public String detailproduct(@PathVariable("id") Integer id , Model model){




        System.out.println(id);
      //  System.out.println(product_user.getId());//sai o day





        Product product = productRepository.findById(id).get();
        System.out.println("#########################");

        model.addAttribute("p",product);
        Integer num = 0;
        System.out.println("#########################");

        model.addAttribute("num",num);
        System.out.println("#########################");


        return "dung/detailproduct";
    }
  //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

  //  Integer id_user =  userRepository.findByEmail(authentication.getName()).getId();

  //  Product_User product_user =productUserRepository.findByUser_idAndProduct_id(id_user,id);
    @GetMapping("viewdetail/buyproduct/{id}")
    public String buyproduct(@ModelAttribute("num") Integer num, @PathVariable("id") Integer id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Product_User product_user1 =productUserRepository.findByUser_idAndProduct_id(userRepository.findByEmail(authentication.getName()).getId(),id);

        if(product_user1== null){

            Product_User product_user = new Product_User();
            product_user.setProduct_id(id);
            product_user.setNumberProduct(num);
            product_user.setUser_id(userRepository.findByEmail(authentication.getName()).getId());
            productUserRepository.save(product_user);
        }
        else {
            product_user1.setNumberProduct(num);
            productUserRepository.save(product_user1);}

        List<Product_User> listProduct_users = productUserRepository.findByUser_id(userRepository.findByEmail(authentication.getName()).getId());
        List<Product> list = new ArrayList<Product>();
        long price = 0;
        for(int i = 0; i < listProduct_users.size(); i++){

            list.add(productRepository.findById(listProduct_users.get(i).getProduct_id()).get());
            price += list.get(i).getPriceProduct()*listProduct_users.get(i).getNumberProduct();
        }

        model.addAttribute("list",list);
        model.addAttribute("p",price);
        return "dung/carts";
    }

    @GetMapping("submit")
    public String submit(){
        return "dung/submit";
    }

    @GetMapping("delete")
    @TestAccessible
    @Transactional
    public String delete (Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer deleteUser =  userRepository.findByEmail(authentication.getName()).getId();
        productUserRepository.deleteUser(deleteUser);
        List<Product_User> listProduct_users = productUserRepository.findByUser_id(userRepository.findByEmail(authentication.getName()).getId());
        List<Product> list = new ArrayList<Product>();
        long price = 0;
        for(int i = 0; i < listProduct_users.size(); i++){

            list.add(productRepository.findById(listProduct_users.get(i).getProduct_id()).get());
            price += list.get(i).getPriceProduct()*listProduct_users.get(i).getNumberProduct();
        }
        model.addAttribute("price",price);
        return "dung/payment";
    }


    @GetMapping("/viewcarts")
    public String viewcarts(Model model){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer deleteUser =  userRepository.findByEmail(authentication.getName()).getId();


        List<Product_User> listProduct_users = productUserRepository.findByUser_id(userRepository.findByEmail(authentication.getName()).getId());
        List<Product> list = new ArrayList<Product>();
        long price = 0;
        for(int i = 0; i < listProduct_users.size(); i++){

            list.add(productRepository.findById(listProduct_users.get(i).getProduct_id()).get());
            price += list.get(i).getPriceProduct()*listProduct_users.get(i).getNumberProduct();
        }
        model.addAttribute("list",list);
        model.addAttribute("p",price);
        return "dung/carts";
    }

    @GetMapping("test")
    public void test(){
        Product_User product_user =productUserRepository.findByUser_idAndProduct_id(1,1);
        System.out.println(product_user.getId());
    }




}
