package com.project.shop.dao;


import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

    Auction findAuctionByAuctionItem(Item item);

    List<Auction> findAuctionsByUserBought(String user);

    List<Auction> findAuctionsByUser(User user);

    List<Auction> findAuctionsByUserAndUserBought(User user, String userBought);


    Auction findAuctionByAuctionId(long auctionId);

}
