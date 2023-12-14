package com.kio.my.repository;


import com.kio.my.domain.Ordered;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository or;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Ordered order = Ordered.builder().content("1,1~2,2~3,3").totalPrice((long) i)
                    .build();

            Ordered result = or.save(order);
            log.info("ONO: " + result.getOno());
        });
    }
}
