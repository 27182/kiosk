package com.kio.my.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIAdmin {

    @Id
    private String mid;
    private String mpw;


    public void changePw(String mpw){
        this.mpw = mpw;
    }
}
