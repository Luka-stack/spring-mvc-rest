package com.nisshoku.springmvcrest.repositories;


import com.nisshoku.springmvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
