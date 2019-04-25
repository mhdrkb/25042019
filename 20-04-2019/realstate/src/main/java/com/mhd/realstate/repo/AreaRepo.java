package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepo extends JpaRepository<Area, Long> {
    Optional<Area> findByAreaName(String cityName);
    boolean existsAreaByAreaName(String cityName);
}
