package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Availablilty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailRepo extends JpaRepository<Availablilty, Long> {
    Optional<Availablilty> findByAvailName(String availName);
}
