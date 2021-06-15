package com.project.shop.service;

import com.project.shop.dao.RateRepository;
import com.project.shop.model.Auction;
import com.project.shop.model.Rate;
import com.project.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;


    @Override
    public void save(Rate rate) {
        rateRepository.save(rate);
    }

    @Override
    public boolean existsByAuction(Auction m) {
        return rateRepository.existsByAuction(m);
    }

    @Override
    public Auction findByAuction(Auction auction) {
        return rateRepository.findByAuction(auction);
    }

    @Override
    public List<Rate> findRatesByUser(User user) {
        return rateRepository.findRatesByUser(user);
    }

    @Override
    public Rate findRateByAuction(Auction auction) {
        return rateRepository.findRateByAuction(auction);
    }

    @Override
    public void update(Rate rate) {
        rateRepository.save(rate);
    }


}
