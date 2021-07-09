package com.fdm.stockmodulation.project.stockmodulation.repository;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
