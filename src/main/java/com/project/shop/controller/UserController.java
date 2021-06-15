package com.project.shop.controller;




import com.project.shop.model.User;
import com.project.shop.model.dtos.UserDto;
import com.project.shop.model.enums.Status;
import com.project.shop.model.enums.Type;
import com.project.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping("login")
    public String login() {

        return "login";
    }

    @GetMapping("signup")
    public String signUp() {
        return "signup";
    }




    @PostMapping("signup")
    public String signup( @ModelAttribute User user){
        System.out.println(user.getEmail());

//
//        if(userService.findEmail(user.getEmail())==true){
//
//            System.out.println("JEst taki uzytkownik");
//        }
//        if(userService.find(user.getEmail()) == true){
//
//            System.out.println("Istnieje taki uzytkownik");
//        }

            user.setCreatedDate(LocalDateTime.now());
            user.setAccountStatus(Status.AKTYWNE);
            user.setType(Type.USER);
            user.setRole(Type.USER + "");
            userService.save(user);
            return "redirect:/login";       // przekierowanie na adres metodą GET

    }

//
//    @GetMapping("shop/home")
//    public String home(@ModelAttribute User user) {
//
//
//
//        return "shop/home";
//    }

//    @PostMapping("signup")
//    public ModelAndView signUp(User user) {
//        ModelAndView mv = new ModelAndView("/index");
//        userService.save(user);
//        mv.addObject("productList", productService.listProduct());
//        mv.addObject("categoryList", categoryService.listCategory());
//        return mv;
//    }

//    @GetMapping("allProduct")
//    public String allProduct(Model model) {
//        model.addAttribute("productList", productService.listProduct());
//        model.addAttribute("categoryList", categoryService.listCategory());
//        return "index";
//    }
//
//    @GetMapping("getProducts/{categoryId}")
//    public ModelAndView getProductFromCategory(@PathVariable("categoryId")String categoryId) {
//        ModelAndView mv = new ModelAndView("index");
//        long categoryLongId = Long.parseLong(categoryId);
//        System.out.println(categoryLongId);
//        mv.addObject("productList", productService.findByCategory(categoryLongId));
//        mv.addObject("categoryList", categoryService.listCategory());
//        return mv;
//    }

//    @GetMapping("error")
//    public String error() {
//        return "error";
//    }







}
