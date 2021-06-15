package com.project.shop.service;

import com.project.shop.dao.UserRepository;
import com.project.shop.model.User;
import com.project.shop.model.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public User find(String email){
        return  userRepository.findUser(email);
    }



    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


//    public User addUser(UserDto userDto){
//        Optional<User> userOptional = userRepository.findById(postDto.getAuthorId());
//        return userOptional.map(user ->
//                postRepository.save(
//                        new Post(postDto.getTitle(), postDto.getContent(), postDto.getCategory(), user))
//        ).orElse(null);
//    }

    public boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }


//    @Override
//    public void update(User user) {
//        List<Product> productlist1 = user.getProductList();
//        List<Product> productlist = (userRepository.findByEmail(user.getEmail())).getProductList();
//        productlist1.addAll(productlist);
//        user.setProductList(productlist1);
//        userRepository.save(user);
//    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }




}
