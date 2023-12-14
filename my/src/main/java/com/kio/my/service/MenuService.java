package com.kio.my.service;

import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

public interface MenuService {

    Long register(MenuDTO menuDTO);

    MenuDTO getOneMenu(Long mno);

    void updateMenu(MenuDTO menuDTO);

    void remove(Long mno);

    List<Menu> getMenuList(Model model);

    Page<Menu> getOnePage(int pageNumber);
}
