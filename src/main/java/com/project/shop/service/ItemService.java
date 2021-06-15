package com.project.shop.service;

import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public void addItem(Item item);

    public List<Item> listItems();

    public Optional<Item> getItemtById(long productId);

    public List<Item> findByCategory_CategoryIdAndStatus(long id, String status);

    public void deleteItem(long productId);

    List<Item> findUserByItemList(User user);

    public List<Item> findItemsByCategoryAndItemName(long CategoryId, String ItemName);


    public List<Item> findByItemNameAndStatus(@Param("itemName") String keyword ,String status );

    public List<Item> findItemsByItemNameAndStatus(String name,String status);

    public List<Item> findAllByUser_UserId(long userId);

    public void save(Item item);


    public List<Item> findItemsByItemId(long id);

    public List<Item> findItemsByUser_UserId(long userId);


    public List<Item> findItemsByUser(User user);

    public Item findItemByItemId(long id);


    public List<Item> findItemsByUserAndStatus(User user,String status);


    public List<Item> findItemsByStatus(String t );

}
