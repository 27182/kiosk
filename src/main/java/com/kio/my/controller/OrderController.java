package com.kio.my.controller;


import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;



    public List<Ordered> list(Model model){

         List<Ordered> AllorderList = orderService.getOrderList();

         List<Ordered> orderList = AllorderList.stream().filter(a->a.getIsPaid()==1).collect(Collectors.toList());

        return orderList;

    }


    @PostMapping("api/order")
    public Map<Object,Object> updateOrder(@RequestBody OrderDTO orderDTO){

        boolean isSuccess = true;
        long ono = 0;
        Map<Object,Object> result = new HashMap<>();

        try {
            orderDTO.setIsPaid(1);
            ono = orderService.updateOrder(orderDTO);
            result.put("isSuccess",isSuccess);
            result.put("ono", ono);
        } catch(Exception e){
            log.info(e);
            isSuccess = false;
            result.put("isSuccess",isSuccess);
        }
        return result;
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
