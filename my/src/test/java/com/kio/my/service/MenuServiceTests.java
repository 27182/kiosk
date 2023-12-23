package com.kio.my.service;


import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void testRegister() {

        log.info(menuService.getClass().getName());

        MenuDTO menuDTO = MenuDTO.builder()
                .mname("Sample name")
                .mtype("Sameple type")
                .price(73251)
                .build();

        Long mno  = menuService.register(menuDTO);

        log.info("mno: " + mno);
    }


}
