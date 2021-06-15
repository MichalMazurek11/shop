package com.project.shop.dao;

import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByCategory_CategoryId(long CategoryId);

    public List<Item> findItemsByCategoryAndItemName(long CategoryId, String ItemName);


    public List<Item> findItemsByItemNameAndStatus(String name,String status);

    public List<Item> findByCategory_CategoryIdAndStatus(long id, String status);





//    @Query(value = "select e from Item e where e.itemName LIKE %:itemName ", nativeQuery = true)
//    public List<Item> findByItemName(@Param("itemName") String keyword );

    public List<Item> findByItemNameAndStatus(@Param("itemName") String keyword ,String status );

    public List<Item> findAllByUser_UserId(long userId);

    public List<Item> findItemsByUser_UserId(long userId);


    public List<Item> findItemsByUser(User user);

    public List<Item> findItemsByUserAndStatus(User user,String status);

    public List<Item> findItemsByItemId(long id);


    public Item findItemByItemId(long id);

    public List<Item> findItemsByStatus(String t );


}
