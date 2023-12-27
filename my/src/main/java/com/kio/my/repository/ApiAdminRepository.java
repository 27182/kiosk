package com.kio.my.repository;

import com.kio.my.domain.ApiAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAdminRepository extends JpaRepository<ApiAdmin, String> {
}
