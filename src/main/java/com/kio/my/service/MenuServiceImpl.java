package com.kio.my.service;

import com.kio.my.domain.Menu;
import com.kio.my.dto.MenuDTO;
import com.kio.my.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
            ,menuDTO.getAvailable(),menuDTO.getRecommanded(),menuDTO.getStock(),menuDTO.getEtc());

        }

        // 파일 처리
            if(file != null) {
                String inputExtension = file.getContentType();
                String fileExtensionName = "";
                File saveFile;
                String imgurl = menu.getImgurl();

                if ("image/png".equals(inputExtension)) {
                    fileExtensionName = ".png";
                } else if ("image/jpeg".equals(inputExtension)) {
                    fileExtensionName = ".jpg";
                } else if ("image/gif".equals(inputExtension)) {
                    fileExtensionName = ".gif";
                }
                if(fileExtensionName.length() > 1) {
                    if(!"".equals(imgurl)){
                    File existFile = new File(uploadPath + imgurl);
                    if(existFile.exists()) {
                        existFile.delete();
                    }
                    }
                    String newImgurl = UUID.randomUUID().toString() + fileExtensionName;
                    String newPath = uploadPath + newImgurl;
                    log.info(uploadPath + " ***********" + newImgurl);
                    saveFile = new File(newPath);
                try {
                    file.transferTo(saveFile);
                    menu.changeImgUrl(newImgurl);
                } catch (Exception e) {
                    log.info(e);
                }
                }
            }

        // 여기까지
        Menu result = menuRepository.save(menu);
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
