package com.kio.my.domain;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;
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


    @Column(nullable = false)
    @ColumnDefault("0")
    private int isPaid;


    public void changeOneOrder(String content, Long totalPrice, String etc,int isPaid){
        this.content = content;
        this.totalPrice = totalPrice;
        this.etc = etc;
        if(isPaid == 0 || isPaid == 1){
            this.isPaid = isPaid;
        }
    }


    public void confirmPaid(int digit){
        if(digit == 0 || digit == 1){
        this.isPaid = digit;
        }
    }
}
