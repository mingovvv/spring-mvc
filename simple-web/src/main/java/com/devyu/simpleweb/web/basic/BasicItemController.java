package com.devyu.simpleweb.web.basic;

import com.devyu.simpleweb.domain.item.Item;
import com.devyu.simpleweb.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     *  삼품등록 V2
     *
     * @param item obj
     * @return
     */
//    @PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        // @ModelAttribute("item") - Model 객체를 만들어 주고 addAttribute까지 수행해주는 역할 / name이 KEY가 된다.
//        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     *  삼품등록 V3
     *
     * @param item obj
     * @return
     */
//    @PostMapping("/add")
    public String saveV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        // @ModelAttribute("item") - Model 객체를 만들어 주고 addAttribute까지 수행해주는 역할
        // name이 존재하지 않는 경우, 클래스 명의 첫 레터럴만 소문자로 변환시켜 사용 = item
//        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     *  삼품등록 V4
     *
     * @param item obj
     * @return
     */
//    @PostMapping("/add")
    public String saveV4(Item item) {
        itemRepository.save(item);
        // @ModelAttribute("item") - 생략
//        model.addAttribute("item", item);
        return "redirect:/basic/items/" + item.getId(); // PRG(post - redirect - get)
    }

    /**
     *  삼품등록 V5
     *
     * @param item obj
     * @return
     */
    @PostMapping("/add")
    public String saveV5(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); // query parameter
        return "redirect:/basic/items/{itemId}"; // PRG(post - redirect - get)
    }

    @GetMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트 데이터
     */
    @PostConstruct
    public void inti() {
        itemRepository.save(new Item("피자", 1000, 15));
        itemRepository.save(new Item("햄버거", 8000, 24));
    }

}
