package com.project.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rate")
public class Rate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rateId;

    private int rateTransaction;

    private String rater;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", rateTransaction=" + rateTransaction +
                ", rater='" + rater + '\'' +
                ", user=" + user +
                ", auction=" + auction +
                '}';
    }
}
