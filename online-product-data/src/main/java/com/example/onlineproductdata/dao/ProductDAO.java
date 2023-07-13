package com.example.onlineproductdata.dao;

import com.example.onlineproductdata.entites.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {

}
