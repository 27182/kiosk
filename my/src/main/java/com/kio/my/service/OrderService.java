package com.kio.my.service;

import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {


    List<Ordered> getOrderList();

    Long updateOrder(OrderDTO orderDTO);

    void deleteOrder(Long ono);

    Page<Ordered> getOnePage(int pageNumber);



}
