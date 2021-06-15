package com.project.shop.service;

import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;

import java.util.List;

public interface AuctionService {

    public void save(Auction auction);

    Auction findAuctionByAuctionItem(Item item);

    public void deletAuction(long auctionId);

    public List<Auction> listAuction();

    List<Auction> findAuctionsByUser(User user);

    List<Auction> findAuctionsByUserBought(String user);

    List<Auction> findAuctionsByUserAndUserBought(User user, String userBought);

    Auction findAuctionByAuctionId(long auctionId);
}

