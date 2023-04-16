package com.jpa.controller;

import com.jpa.domain.item.Book;
import com.jpa.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/items/new")
    public String createForm() {
        return "jsp/items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
