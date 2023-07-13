package com.example.onlineproductdata.services;

import com.example.onlineproductdata.dao.ProductDAO;
import com.example.onlineproductdata.entites.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    ProductDAO productDAO;

    public Iterable<Product>getAllProducts(){
        return productDAO.findAll();
    }

    public void addProduct(String nume, String descriere, String categorie, int pret){
        Product product=new Product();
        product.setNume(nume);
        product.setDescriere(descriere);
        product.setCategorie(categorie);
        product.setPret(pret);
        productDAO.save(product);
    }
}
