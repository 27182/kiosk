package com.kio.my.repository;

import com.kio.my.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Ordered, Long> {



    @Query("select o from Ordered o where cast(o.ono as string ) like  concat(:ono,'%')")
    List<Ordered> findByOnoLike(@Param("ono") Long ono);
}
