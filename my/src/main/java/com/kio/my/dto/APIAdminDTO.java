package com.kio.my.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
@Setter
@ToString
public class APIAdminDTO extends User {

    private String mid;
    private String mpw;

    public APIAdminDTO(String username, String password, Collection<GrantedAuthority> authorities) {
        super(username,password,authorities);
        this.mid = username;
        this.mpw = password;
    }
}
