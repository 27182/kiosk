package com.kio.my.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Long mno;

    private String mname;
    private String mtype;
    private String available;
    private String recommanded;
    private String etc;
    private int price;
    private Long stock;
    private String imgurl;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
