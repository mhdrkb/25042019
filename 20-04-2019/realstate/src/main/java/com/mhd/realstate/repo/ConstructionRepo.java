package com.mhd.realstate.repo;

import com.mhd.realstate.entity.ConstructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructionRepo extends JpaRepository<ConstructionType , Long> {
    Optional<ConstructionType> findByConstTypeName(String constTypeName);
    boolean existsConstructionTypeByConstTypeName(String constTypeName);

}
