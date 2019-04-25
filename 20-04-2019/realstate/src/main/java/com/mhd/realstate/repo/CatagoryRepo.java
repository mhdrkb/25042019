package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Catagory;
import com.mhd.realstate.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatagoryRepo extends JpaRepository<Catagory, Long> {
    Optional<City> findByCatagoryName(String catagoryName);
    boolean existsCatagoryByCatagoryName(String cityName);
}
