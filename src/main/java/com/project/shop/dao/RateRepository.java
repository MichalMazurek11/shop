package com.project.shop.dao;


import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.Rate;
import com.project.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {



      boolean existsByAuction(Auction m);


      Auction findByAuction(Auction auction);

      List<Rate> findRatesByUser(User user);

      Rate findRateByAuction(Auction auction);
}
