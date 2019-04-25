package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepo extends JpaRepository<Summary , Long> {
}
