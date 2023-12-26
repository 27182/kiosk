package com.kio.my.repository;


import com.kio.my.domain.Menu;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MenuRepositoryTests {

    @Autowired
    private MenuRepository mr;


    public void testInsert() {
//        IntStream.rangeClosed(1, 11).forEach(i -> {
//            Menu menu = Menu.builder().mname("name").price(i).mtype("type").build();
//
//            Menu result = mr.save(menu);
//            log.info("MNO: " + result.getMno());
//        });

        Menu menu = Menu.builder().mname("햄버거").price(2500).mtype("main").build();
        mr.save(menu);
    }
}
