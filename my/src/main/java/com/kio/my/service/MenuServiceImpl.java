package com.kio.my.service;

import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import com.kio.my.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final ModelMapper modelMapper;

    private final MenuRepository menuRepository;


    @Override
    public Long register(MenuDTO menuDTO) {

        Menu menu = modelMapper.map(menuDTO, Menu.class);

        return menuRepository.save(menu).getMno();
    }

    @Override
    public MenuDTO getOneMenu(Long mno) {

        Optional<Menu> result = menuRepository.findById(mno);

        Menu menu = result.orElseThrow();

        MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);

        return menuDTO;
    }

    @Override
    public void updateMenu(MenuDTO menuDTO) {

        Menu menu;
        if(menuDTO.getMno() == null){
            menu = modelMapper.map(menuDTO, Menu.class);
        } else {
        Optional<Menu> result = menuRepository.findById(menuDTO.getMno());
            menu = result.get();
            menu.changeOneMenuInfo(menuDTO.getMname(),menuDTO.getMtype(),menuDTO.getPrice()
            ,menuDTO.getAvailable(),menuDTO.getRecommanded(),menuDTO.getStock(),menuDTO.getEtc(),menuDTO.getImgurl());

        }
        menuRepository.save(menu);


    }

    @Override
    public void remove(Long mno) {

        menuRepository.deleteById(mno);
    }

    @Override
    public List<Menu> getMenuList(Model model) {

        List<Menu> menulist = menuRepository.findAll();


        model.addAttribute("menulist", menulist);

        return menulist;

    }

    @Override
    public Page<Menu> getOnePage(int pageNumber) {

        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        return menuRepository.findAll(pageRequest);
    }


}
