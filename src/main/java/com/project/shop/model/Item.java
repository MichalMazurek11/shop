package com.project.shop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @Column
    private String itemName;

    @Column
    private String itemDescription;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    private String image;

    private Float itemPrice;

    private String status = "AKTYWNA";

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "auctionItem",  cascade =  CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Auction> auction;




//    @ManyToMany(mappedBy = "itemList",fetch = FetchType.EAGER)
//    private List<User> userList;








}
