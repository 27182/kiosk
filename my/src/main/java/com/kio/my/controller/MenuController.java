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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
@Log4j2
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    public List<Menu> list(Model model){

        List<Menu> menuList = menuService.getMenuList(model);

        return menuList;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Menu> getAvailableMenuList(Model model){
        List<Menu> menuList = menuService.getMenuList(model);
        List<Menu> availableMenu = menuList.stream().filter(a -> a.getAvailable().equals("1")).toList();
        return availableMenu;
    }

    public void updateMenu(MenuDTO menuDTO, MultipartFile file){

        menuService.updateMenu(menuDTO ,file);

    }

    public void deleteMenu(Long mno){
        menuService.remove(mno);
    }



    public List<Menu> getOnePage(int pageNumber, Model model){
         Page<Menu> pm = menuService.getOnePage(pageNumber);
         model.addAttribute("totalPage", pm.getTotalPages());
         return pm.stream().toList();

    }
}
