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
 

            Ordered order = Ordered.builder().content("{햄버거:2, 치즈버거:35, 베이컨 토마토 디럭스:30, 후렌치 후라이: 3, 코카-콜라: 353}").totalPrice((long) 5400)
                    .build();

            Ordered result = or.save(order);
            log.info("ONO: " + result.getOno());
        
    }
}
