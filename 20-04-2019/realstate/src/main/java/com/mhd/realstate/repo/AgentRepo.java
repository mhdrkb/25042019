package com.mhd.realstate.repo;

import com.mhd.realstate.entity.Agents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepo extends JpaRepository<Agents, Long> {
    Optional<Agents> findByAgentName(String agentNAme);
    boolean existsAgentsByEmail(String email);
}
