package com.project.shop.dao;

import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   // User findByEmail(String email);
   User findByEmail(String email);

//
    @Query(value =  "SELECT u FROM User u WHERE u.email = ?1")
    User findUser(String email);

    List<Item> findUserByItemList(User user);





}
