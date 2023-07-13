package com.example.online_shop_app.controller;

import com.example.online_shop_app.entites.Product;
import com.example.online_shop_app.userservices.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServices userServices;
    @GetMapping("")
    public ModelAndView firstPage(){
        ModelAndView modelAndView=new ModelAndView("login.html");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView registerPage(){
        ModelAndView modelAndView=new ModelAndView("register.html");
        return modelAndView;
    }

    @PostMapping("/register-action")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("password1")String password1,
                                       @RequestParam("password2")String password2,
                                       @RequestParam("firstName")String firstName,
                                       @RequestParam("LastName")String lastName){
        userServices.saveUser(email, password1, password2, firstName, lastName);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/login-action")
    public ModelAndView loginAction(@RequestParam("email")String email,
                                    @RequestParam("password")String password){
        if(userServices.loginUser(email, password)) {
            return new ModelAndView("redirect:/dashboard");
        }else {
            return new ModelAndView("redirect:/");
        }
    }
    @GetMapping("/dashboard")
    public ModelAndView dashboardPage(){
        ModelAndView modelAndView=new ModelAndView("dashboard.html");
        RestTemplate restTemplate=new RestTemplate();
        //atentie la protocolul de securitate https
        List<Product>products=restTemplate.getForEntity("http://localhost:8081/product", List.class).getBody();
        modelAndView.addObject("productList", products);
        return modelAndView;
    }
}
