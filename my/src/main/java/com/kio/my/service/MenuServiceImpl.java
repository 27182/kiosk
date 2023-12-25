package com.kio.my.service;

import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import com.kio.my.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final ModelMapper modelMapper;

    private final MenuRepository menuRepository;

    @Value("${file.upload.directory}")
    String uploadPath;


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
    public void updateMenu(MenuDTO menuDTO, MultipartFile file) {

        Menu menu;

        if(menuDTO.getMno() == null){
            menu = modelMapper.map(menuDTO, Menu.class);
        } else {
        Optional<Menu> result = menuRepository.findById(menuDTO.getMno());
            menu = result.get();
            menu.changeOneMenuInfo(menuDTO.getMname(),menuDTO.getMtype(),menuDTO.getPrice()
            ,menuDTO.getAvailable(),menuDTO.getRecommanded(),menuDTO.getStock(),menuDTO.getEtc(),menuDTO.getImgurl());

        }
        Menu result = menuRepository.save(menu);

        // 파일 처리
            if(file != null){
                File saveFile = new File(uploadPath, result.getMno().toString());
                try{
                    file.transferTo(saveFile);
                }  catch(Exception e ){
                    log.info(e);
                }
            }



        // 여기까지


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
