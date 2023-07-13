package com.example.online_shop_app.dao;

import com.example.online_shop_app.entites.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer>  {
    // find user for login
    User findByEmail(String email);
    /**DAO poate genera sql pentru functiile noastre, trebuie sa avem grija ce nume dam metodei*/
}
