package com.example.onlineproductdata.controller;

import com.example.onlineproductdata.entites.Product;
import com.example.onlineproductdata.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductServices productServices;

    @GetMapping("")
    public ModelAndView firstPage(){
        ModelAndView modelAndView=new ModelAndView("addProduct.html");
        return modelAndView;
    }
    @PostMapping("/add-product-action")
    public ModelAndView addProduct(@RequestParam("nume")String nume,
                                   @RequestParam("descriere") String descriere,
                                   @RequestParam("categorie")String categorie,
                                   @RequestParam("pret")Integer pret){
        productServices.addProduct(nume, descriere, categorie, pret);
        return new ModelAndView("redirect:/products");
    }
    @GetMapping("/products")
    public ModelAndView getAllProducts(){
        ModelAndView modelAndView=new ModelAndView("products.html");
        modelAndView.addObject("productList", productServices.getAllProducts());
        return modelAndView;
    }
    //web services
    @GetMapping("/product")
    @ResponseBody
    public Iterable<Product>getAllProductApi(){
        return productServices.getAllProducts();
    }
}
