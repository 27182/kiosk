package com.kio.my.service;

import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;

import java.util.List;

public interface OrderService {


    List<Ordered> getOrderList();

    void updateOrder(OrderDTO orderDTO);

    void deleteOrder(Long ono);
}
