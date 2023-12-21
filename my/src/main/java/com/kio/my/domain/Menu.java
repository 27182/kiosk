package com.kio.my.domain;


import com.kio.my.repository.MenuRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 500, nullable = false)
    private String mname;

    @Column(nullable = false)
    private int price;

    @Column(length = 500,nullable = false)
    private String mtype;

    private Long stock;

    @ColumnDefault("true")
    @Column(length = 50)
    private String available;

    @ColumnDefault("false")
    @Column(length = 50)
    private String recommanded;

    @Column(length = 500)
    private String etc;

    @Column(length = 500)
    private String imgurl;

    public void changeOneMenuInfo(String mname, String mtype, int price, String available
            , String recommanded, Long stock, String etc, String imgurl){

        this.mname = mname;
        this.mtype = mtype;
        this.price = price;
        this.available = available;
        this.recommanded = recommanded;
        this.stock = stock;
        this.etc = etc;
        this.imgurl = imgurl;
    }



}
