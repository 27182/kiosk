package com.kio.my.repository;

import com.kio.my.domain.Ordered;
import com.kio.my.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {


    @Query("select o from Payment o where o.ono = :ono")
    Optional<Payment> findByOno(@Param("ono") String ono);
}
