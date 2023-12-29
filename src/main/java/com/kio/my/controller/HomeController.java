package com.kio.my.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;

@RestController
@Log4j2
public class HomeController {

    @Value("${file.upload.directory}")
    String path;

//    @GetMapping("/")
//    public String home(Model model){
//        log.info("welcome to my home.....");
//
//        return "/index";
//
//    }


    @GetMapping("/api/image")
    public String sendImageSource() {
        return path;
    }


    @GetMapping("/api/menu_img/{imagename}")
    @ResponseBody
    public ResponseEntity<byte[]> getImageDynamicType(@PathVariable("imagename") String imagename) throws IOException {

        int index = imagename.lastIndexOf(".");
        String extension = "";
        if (index > 0) {
            extension = imagename.substring(index + 1);
        }



        MediaType contentType = "png".equalsIgnoreCase(extension) ? MediaType.IMAGE_PNG :
                "gif".equalsIgnoreCase(extension) ? MediaType.IMAGE_GIF : MediaType.IMAGE_JPEG;
//        InputStream in =
//                getClass().getResourceAsStream(path + imagename);
        try {
        File file = new File(path + imagename);
  //      InputStream in = new FileInputStream(file);


//             byte[] arr = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
