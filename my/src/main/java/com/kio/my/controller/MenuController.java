package com.kio.my.controller;


import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import com.kio.my.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@Log4j2
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/list")
    @ResponseBody
    public List<Menu> list(Model model){

        List<Menu> menuList = menuService.getMenuList(model);

        return menuList;
    }

    public void updateMenu(MenuDTO menuDTO){

        menuService.updateMenu(menuDTO);

    }

    public void deleteMenu(Long mno){
        menuService.remove(mno);
    }


    @GetMapping("/page/{pageNumber}")
    @ResponseBody
    public List<Menu> getOnePage(@PathVariable int pageNumber){
         Page<Menu> pm = menuService.getOnePage(pageNumber);
         return pm.stream().toList();

    }
}
