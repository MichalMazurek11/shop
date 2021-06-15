package com.project.shop.controller;

import com.project.shop.model.Auction;
import com.project.shop.model.Rate;
import com.project.shop.model.User;
import com.project.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class RateController {

    @Autowired
    AuctionService auctionService;

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


    @PostMapping("shop/rate-auction/{auctionId}")
    public String toRateAuction(@PathVariable("auctionId")String auctionId, Rate rate, Auction auction, Authentication auth, User user) {

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



}

