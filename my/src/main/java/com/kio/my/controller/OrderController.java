package com.kio.my.controller;


import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;



    public List<Ordered> list(Model model){

         List<Ordered> orderList = orderService.getOrderList();

        return orderList;

    }

    public void updateOrder(OrderDTO orderDTO){

        orderService.updateOrder(orderDTO);

    }

    public void deleteOrder(Long ono){

        orderService.deleteOrder(ono);

    }


    public List<Ordered> getOnePage(int pageNumber, Model model){
        Page<Ordered> pm = orderService.getOnePage(pageNumber);
        model.addAttribute("totalPage", pm.getTotalPages());
        return pm.stream().toList();

    }


   /* public List<Ordered> SearchOrder(Long ono){
        return orderService.SearchOrder(ono);
    }*/
}
