package com.kio.my.service;


import com.kio.my.domain.Menu;
import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Override
    public List<Ordered> getOrderList() {

        List<Ordered> orderList = orderRepository.findAll();

        return orderList;
    }

    @Override
    public Long updateOrder(OrderDTO orderDTO) {
        
        Ordered ordered;
        log.info(orderDTO);
        if(orderDTO.getOno() == null){
            ordered = modelMapper.map(orderDTO, Ordered.class);
        } else {
            Optional<Ordered> result = orderRepository.findById(orderDTO.getOno());
            ordered = result.get();
            ordered.changeOneOrder(orderDTO.getContent(),orderDTO.getTotalPrice(),orderDTO.getEtc());

        }

        log.info(ordered);

        Ordered saved = orderRepository.save(ordered);
        return saved.getOno();

    }

    @Override
    public void deleteOrder(Long ono) {
       orderRepository.deleteById(ono);
    }

    @Override
    public Page<Ordered> getOnePage(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        return orderRepository.findAll(pageRequest);
    }

}
