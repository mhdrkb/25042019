package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuilldingRepo extends JpaRepository<Buildings, Long> {
    Buildings findByBuildingName(String buildingName);
}
