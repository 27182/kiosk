package com.kio.my.service;

import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.dto.ReadyResponseDTO;

public interface KakaoPayService {

    ReadyResponseDTO readytoPay(OrderDTO orderDTO);


    Ordered approvePayment(String pg_token,String ono);
}
