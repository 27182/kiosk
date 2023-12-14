package com.kio.my.repository;

import com.kio.my.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordered, Long> {
}
