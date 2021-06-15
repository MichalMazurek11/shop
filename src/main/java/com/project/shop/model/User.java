package com.project.shop.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.shop.model.enums.Status;
import com.project.shop.model.enums.Type;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String adress;

    @Column()
    private LocalDateTime createdDate ;

    @Column(nullable = false)
    private String role;


    @Enumerated(value = EnumType.STRING)
    // EnumType.ORDINAL - przechowuje indeks obiektów enum
    // EnumType.STRING  - przechowuje nazwy obiektów enum
    private Status accountStatus;

    @Enumerated(value = EnumType.STRING)
    private Type type;

//    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinTable(name = "userItemList", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "itemtId"))
//    private List<Item> itemList;


    @OneToMany(mappedBy = "user",  cascade =  CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Item> itemList;


    @OneToMany(mappedBy = "user",  cascade =  CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Auction> auctionList ;

    @OneToMany(mappedBy = "user",  cascade =  CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Rate> rateList ;


    public List<String> getRoleList() {
        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }

        return new ArrayList<String>();
    }

    public User(String email, String password, String state, String city, String adress, LocalDateTime createdDate, String role, Status accountStatus, Type type) {
        this.email = email;
        this.password = password;
        this.state = state;
        this.city = city;
        this.adress = adress;
        this.createdDate = createdDate;
        this.role = role;
        this.accountStatus = accountStatus;
        this.type = type;
    }

    public User(long userId, String email, String password, String state, String city, String adress, LocalDateTime createdDate, String role, Type type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.state = state;
        this.city = city;
        this.adress = adress;
        this.createdDate = createdDate;
        this.role = role;
        this.type = type;
    }



}
