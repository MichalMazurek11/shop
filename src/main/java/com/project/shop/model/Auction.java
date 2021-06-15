package com.project.shop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auction")
public class Auction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionId;


    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item auctionItem;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "auction",  cascade =  CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Rate> rateAuctionList ;

    private Float price;

    private String userBought = "";

    private LocalDateTime timeBought = LocalDateTime.now();


    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", auctionItem=" + auctionItem +
                ", user=" + user +
                ", rateAuctionList=" + rateAuctionList +
                ", price=" + price +
                ", userBought='" + userBought + '\'' +
                ", timeBought=" + timeBought +
                '}';
    }
}
