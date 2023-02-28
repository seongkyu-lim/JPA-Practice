package com.jpa.service;

import com.jpa.domain.item.Book;
import com.jpa.domain.item.Item;
import com.jpa.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;


    @Test
    @DisplayName("묵품 생성 로직 테스트")
    void saveItem(){
        Item item = new Book();
        item.setName("testItem");
        item.setPrice(1000);
        item.setStockQuantity(2);

        itemService.saveItem(item);

        assertEquals(item, itemService.findOne(item.getId()));
    }
}
