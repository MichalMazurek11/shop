package com.project.shop.service;

import com.project.shop.model.Auction;
import com.project.shop.model.Rate;
import com.project.shop.model.User;

import java.util.List;

public interface RateService {

    public void save(Rate rate);

    boolean existsByAuction(Auction m);

    Auction findByAuction(Auction auction);

    List<Rate> findRatesByUser(User user);

    Rate findRateByAuction(Auction auction);


    public void update(Rate rate);
}
