package com.kio.my.repository;

import com.kio.my.domain.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>{

    List<Menu> findMenuByMnameLike(String name);


}
