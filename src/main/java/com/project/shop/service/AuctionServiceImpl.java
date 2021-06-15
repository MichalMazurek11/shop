package com.project.shop.service;

import com.project.shop.dao.AuctionRepository;
import com.project.shop.dao.ItemRepository;
import com.project.shop.dao.UserRepository;
import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Auction auction) {
        auctionRepository.save(auction);

    }

    @Override
    public Auction findAuctionByAuctionItem(Item item) {
        return auctionRepository.findAuctionByAuctionItem(item);

    }

    @Override
    public void deletAuction(long auctionId) {
        auctionRepository.deleteById(auctionId);
    }





    @Override
    public List<Auction> listAuction() {
        return auctionRepository.findAll();
    }

    @Override
    public List<Auction> findAuctionsByUser(User user) {
        return auctionRepository.findAuctionsByUser(user);
    }

    @Override
    public List<Auction> findAuctionsByUserBought(String user) {
        return auctionRepository.findAuctionsByUserBought(user);
    }

    @Override
    public List<Auction> findAuctionsByUserAndUserBought(User user, String userBought) {
        return auctionRepository.findAuctionsByUserAndUserBought(user,userBought);
    }

    @Override
    public Auction findAuctionByAuctionId(long auctionId) {
        return auctionRepository.findAuctionByAuctionId(auctionId);
    }
}