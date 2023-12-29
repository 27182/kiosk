package com.kio.my.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiAdminDTO {

    private String id;
    private String pw;

}
