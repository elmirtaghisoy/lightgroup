package com.datacity.lightgroup.repositories;

import com.datacity.lightgroup.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findFirst3ByOrderById();
}
