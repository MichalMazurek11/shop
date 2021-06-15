package com.project.shop.controller;

import com.project.shop.model.*;
import com.project.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;


    @Autowired
    private AuctionService auctionService;

    static  List<Integer> rateInteagerList = null;
    static  {
        rateInteagerList = new ArrayList<>();
        rateInteagerList.add(1);
        rateInteagerList.add(2);
        rateInteagerList.add(3);
        rateInteagerList.add(4);
        rateInteagerList.add(5);
    }


//    @Autowired
//    public HomeController(CategoryService categoryService, ItemService itemService, FileUploadService fileUploadService) {
//        this.categoryService = categoryService;
//        this.itemService = itemService;
//        this.fileUploadService = fileUploadService;
//    }

    @GetMapping("shop/home")
    public String home(@ModelAttribute User user) {



        return "shop/home";
    }

    @GetMapping("shop/admin")
    public String home2(@ModelAttribute User user, Model model) {


        return "admin/index";
    }




    @GetMapping({"shop/index", "/"})
    public String index(Principal prin, Model model) {


            if(prin == null)
            {
                model.addAttribute("currentuser", "noBodyHome");

            }
            else
            {
                model.addAttribute("currentuser", prin.getName());

            }




        model.addAttribute("categoryList", categoryService.listCategory());
        model.addAttribute("itemList", itemService.findItemsByStatus("AKTYWNA"));
        return "shop/index";
    }


    @GetMapping("shop/items")
    public String allProduct(Model model, Authentication auth) {

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println("Logging credentials: " + userService.find(userDetails.getUsername()));

        model.addAttribute("itemList", itemService.findItemsByStatus("AKTYWNA"));
        model.addAttribute("categoryList", categoryService.listCategory());
        return "shop/index";
    }

    @GetMapping("shop/getProducts/{categoryId}")
    public ModelAndView getProductFromCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("shop/index");
        long categoryLongId = Long.parseLong(categoryId);
        System.out.println(categoryLongId);

        mv.addObject("itemList",itemService.findByCategory_CategoryIdAndStatus(categoryLongId,"AKTYWNA"));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }


    //find itemByName
    @GetMapping("shop/getProducts/itemName{itemName}")
    public ModelAndView getProductFromName(@PathVariable("itemName")String itemName) {
        ModelAndView mv = new ModelAndView("shop/index");

    //    System.out.println(categoryLongId);

        if(itemName != null){
            mv.addObject("itemList",itemService.findItemsByItemNameAndStatus(itemName,"AKTYWNA"));
            mv.addObject("categoryList", categoryService.listCategory());
        }else{
            mv.addObject("itemList",itemService.findItemsByStatus("AKTYWNA"));
            mv.addObject("categoryList", categoryService.listCategory());
        }

//        mv.addObject("itemList",itemService.findItemsByItemName(itemName));
//        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("shop/purchase/{itemId}")
    public ModelAndView getPurchaseItem(@PathVariable("itemId")String itemId,Model model) {
        ModelAndView mv = new ModelAndView("shop/purchase");

        long itemLongId = Long.parseLong(itemId);
        System.out.println(itemLongId);

        Item item = itemService.findItemByItemId(itemLongId);
        User user = item.getUser();




        model.addAttribute("currentuser",user.getEmail());
        mv.addObject("itemList", itemService.findItemsByItemId(itemLongId));
        return mv;
    }






    @GetMapping("shop/itemName")
    public ModelAndView getProductName(String itemName) {
        ModelAndView mv = new ModelAndView("shop/index");


      System.out.println(itemName.equals(""));
        if(itemName != null ) {
            mv.addObject("itemList", itemService.findItemsByItemNameAndStatus(itemName, "AKTYWNA"));
            mv.addObject("categoryList", categoryService.listCategory());
        }
       if(  Objects.equals(itemName,"")){
            mv.addObject("itemList",itemService.findItemsByStatus("AKTYWNA"));
            mv.addObject("categoryList", categoryService.listCategory());
        }


        return mv;
    }








    @GetMapping("error")
    public String error() {
        return "error";
    }

    @GetMapping("shop/item-form")
    public ModelAndView listProduct() {
        ModelAndView mv = new ModelAndView("shop/item-form");

        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("itemList", itemService.listItems());
        return mv;
    }

    @PostMapping("shop/purchase/{itemId}")
    public String toPurchaseItem(@PathVariable("itemId")String itemId, Auction auction,Authentication auth,User user) {

        long itemLongId = Long.parseLong(itemId);
       Item item = itemService.findItemByItemId(itemLongId);
       item.setStatus("NIEAKTYWNA");
       itemService.save(item);
       System.out.println(itemLongId);
        auction =  auctionService.findAuctionByAuctionItem(itemService.findItemByItemId(itemLongId));

        String login = auth.getName();
        user = userService.find(login);

        auction.setUserBought(user.getEmail());

        auctionService.save(auction);


        return "redirect:/shop/index";
    }


    @PostMapping("shop/item-form")
    public String addProduct(Auction auction, User user, Item item, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("shop/item-form");
        System.out.println("file: " + file.getOriginalFilename());
        String filePath = fileUploadService.upload(file);
        item.setImage(filePath);

        itemService.addItem(item);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        user = userService.find(login);
        item.setUser(user);
        itemService.save(item);

        auction.setAuctionItem(item);
        auction.setUser(user);
        auction.setPrice(item.getItemPrice());
        auctionService.save(auction);

        mv.addObject("categorytList", categoryService.listCategory());
        mv.addObject("itemtList", itemService.listItems());
        return "shop/item-form";
    }





    @GetMapping("shop/deleteItem/{itemId}")
    public ModelAndView deleteProduct(Authentication auth,User user,@PathVariable("itemId")String itemId) {
        ModelAndView mv = new ModelAndView("shop/my-auction");

        itemService.deleteItem(Long.parseLong(itemId));

        String login = auth.getName();
        user = userService.find(login);
       Auction auction = auctionService.findAuctionByAuctionId(Long.parseLong((itemId)));
        auctionService.deletAuction(auction.getAuctionId());

        mv.addObject("itemList", itemService.findItemsByUserAndStatus(user,"AKTYWNA"));
        return mv;
    }

    @GetMapping("shop/updateItem/{itemId}")
    public ModelAndView updateProduct(@PathVariable("itemId")String itemId) {
        ModelAndView mv = new ModelAndView("shop/updateItem");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("Item", itemService.getItemtById(Long.parseLong(itemId)).get());
        return mv;
    }

    @GetMapping("shop/my-auction")
    public ModelAndView auction(Authentication auth,User user) {
        ModelAndView mv = new ModelAndView("shop/my-auction");

        String login = auth.getName();
        user = userService.find(login);



        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("itemList",itemService.findItemsByUserAndStatus(user,"AKTYWNA") );
        return mv;
    }

    @GetMapping("shop/rate-auction/{auctionId}")
    public ModelAndView getRateItem(@PathVariable("auctionId")String auctionId) {
        ModelAndView mv = new ModelAndView("shop/rate-auction");

        long auctionLongId = Long.parseLong(auctionId);
        System.out.println(auctionLongId);

        mv.addObject("rateInteagerList", rateInteagerList);
        mv.addObject("auctionList", auctionService.findAuctionByAuctionId(auctionLongId));
        return mv;
    }


    @PostMapping("shop/rate-auction/{auctionId}")
    public String toRateAuction(@PathVariable("auctionId")String auctionId,Rate rate, Auction auction,Authentication auth,User user) {

        long auctionLongId = Long.parseLong(auctionId);

        auction =  auctionService.findAuctionByAuctionId(auctionLongId);

        rateService.findByAuction(auction);

        String login = auth.getName();
        user = userService.find(login);
        rate.setUser(auction.getUser());
        rate.setRater(user.getEmail());
        rate.setAuction(auction);
        rateService.save(rate);

        return "redirect:/shop/my-history";
    }




    @GetMapping("shop/my-history")
    public ModelAndView history(Authentication auth,User user) {
        ModelAndView mv = new ModelAndView("shop/my-history");

        String login = auth.getName();
        user = userService.find(login);




        mv.addObject("rateInteagerList", rateInteagerList);
        mv.addObject("auctionItem",auctionService.findAuctionsByUserBought(user.getEmail()) );
        return mv;
    }




    @GetMapping("shop/my-sale")
    public ModelAndView mySale(Authentication auth,User user) {
        ModelAndView mv = new ModelAndView("shop/my-sale");

        String login = auth.getName();
        user = userService.find(login);
      List<Auction>  list = auctionService.findAuctionsByUser(user);


        mv.addObject("auctionItem", list.stream().filter(x -> x.getUserBought().length() >2 ).collect(Collectors.toList()));
        return mv;
    }









}
