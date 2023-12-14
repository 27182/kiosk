package com.kio.my.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
public class Ordered extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;


    @Column(length = 2000)
    private String content;


    private Long totalPrice;

    @Column(length = 500)
    private String etc;


    public void changeOneOrder(String content, Long totalPrice, String etc){
        this.content = content;
        this.totalPrice = totalPrice;
        this.etc = etc;
    }

}
