package com.kio.my.controller;


import com.kio.my.domain.Menu;
import com.kio.my.domain.Ordered;
import com.kio.my.dto.MenuDTO;
import com.kio.my.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {

    private OrderController orderController;
    private MenuController menuController;


    @Autowired
    public void setMenuController(MenuController menuController){
        this.menuController = menuController;
    }

    @Autowired
    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    @GetMapping("")
    public String adminHome(){

        return "/admin/admin";
    }

    @GetMapping("/menu/list")
    @ResponseBody
    public List<Menu> menuList(Model model){
        return menuController.list(model);
    }

    @GetMapping("/order/list")
    @ResponseBody
    public List<Ordered> orderList(Model model){
        return orderController.list(model);
    }


    @PostMapping("/menu/update")
    public String updateMenu(@RequestBody MenuDTO menuDTO){
        menuController.updateMenu(menuDTO);
        return "/admin/admin";
    }
    @DeleteMapping("/menu/{mno}")
    public void deleteMenu(@PathVariable("mno") String mno){

        try {
            menuController.deleteMenu(Long.parseLong(mno));
        } catch (NumberFormatException e){
            log.info(e.getStackTrace());
        }

    }


    @PostMapping("/order/update")
    public void updateOrder(@RequestBody OrderDTO orderDTO){
        orderController.updateOrder(orderDTO);

    }
    @DeleteMapping("/menu/{ono}")
    public void deleteOrder(@PathVariable("ono") String ono){

        try {
            orderController.deleteOrder(Long.parseLong(ono));
        } catch (NumberFormatException e){
            log.info(e.getStackTrace());
        }

    }

    @GetMapping("/menu/page/{pageNumber}")
    @ResponseBody
    public List<Menu> getMenuPage(@PathVariable int pageNumber, Model model){
        return menuController.getOnePage(pageNumber-1, model);
    }

    @GetMapping("/order/page/{pageNumber}")
    @ResponseBody
    public List<Ordered> getOrderPage(@PathVariable int pageNumber, Model model){
        return orderController.getOnePage(pageNumber-1, model);
    }

}
