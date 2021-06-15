package com.project.shop.service;

import com.project.shop.dao.ItemRepository;
import com.project.shop.dao.UserRepository;
import com.project.shop.model.Auction;
import com.project.shop.model.Item;
import com.project.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addItem(Item item) {
         itemRepository.save(item);
    }

    @Override
    public List<Item> listItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemtById(long productId) {
        return itemRepository.findById(productId);
    }

    @Override
    public List<Item> findByCategory_CategoryIdAndStatus(long id, String status) {
        return itemRepository.findByCategory_CategoryIdAndStatus(id,status);
    }


    @Override
    public void deleteItem(long productId) {
        itemRepository.deleteById(productId);
    }

    @Override
    public List<Item> findUserByItemList(User user) {
        return  userRepository.findUserByItemList(user);
    }
    @Override
    public List<Item> findItemsByCategoryAndItemName(long categoryId, String itemName){

        return itemRepository.findItemsByCategoryAndItemName(categoryId,itemName);
    }


    @Override
    public List<Item> findByItemNameAndStatus(String keyword, String status) {
        return itemRepository.findByItemNameAndStatus(keyword,status);
    }

    @Override
    public List<Item> findItemsByItemNameAndStatus(String name, String status) {
        return itemRepository.findItemsByItemNameAndStatus(name,status);
    }


    @Override
    public List<Item> findAllByUser_UserId(long userId) {
        return itemRepository.findAllByUser_UserId(userId);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItemsByItemId(long id) {
        return  itemRepository.findItemsByItemId(id);
    }


    @Override
    public List<Item> findItemsByUser_UserId(long userId) {
        return itemRepository.findItemsByUser_UserId(userId);
    }

    @Override
    public List<Item> findItemsByUser(User user) {
        return itemRepository.findItemsByUser(user);
    }

    @Override
    public Item findItemByItemId(long id) {
        return  itemRepository.findItemByItemId(id);
    }

    @Override
    public List<Item> findItemsByUserAndStatus(User user, String status) {
        return itemRepository.findItemsByUserAndStatus(user,status);
    }

    @Override
    public List<Item> findItemsByStatus(String t) {
        return itemRepository.findItemsByStatus(t);
    }


}





