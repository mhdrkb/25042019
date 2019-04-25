package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface HousingRepo extends JpaRepository<Housing, Long> {
    Optional<Housing> findByHousingName(String housingName);
    boolean existsHousingByHousingName(String housingName);
}
