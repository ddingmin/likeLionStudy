package com.example.likelionstudy.controller.item;

import com.example.likelionstudy.dto.Item;
import com.example.likelionstudy.service.item.ItemService;
import com.example.likelionstudy.service.item.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemService = itemServiceImpl;
    }

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("itemForm", new Item());
        return "items/createItemForm";
    }

    @PostMapping("new")
    public String create(Item item) {
        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("")
    public String findAll(Model model) {
        List<Item> itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        return "items/itemList";
    }

    @GetMapping("{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("itemFormUpdate",
                itemService.findById(id));
        return "items/updateItemForm";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("itemFormUpdate") Item item){
        itemService.update(item.getId(), item);
        return "redirect:/items";
    }
}


