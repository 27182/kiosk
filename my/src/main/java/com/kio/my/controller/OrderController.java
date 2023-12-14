package com.kio.my.controller;


import com.kio.my.domain.Menu;
import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.service.MenuService;
import com.kio.my.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    }

    public void deleteOrder(Long ono){

    }
}
