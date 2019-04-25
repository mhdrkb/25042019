package com.mhd.realstate.repo;



import com.mhd.realstate.entity.Role;
import com.mhd.realstate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String userName, String email);
    User findByUserName(String username);

     boolean existsByEmail(String email);
     User findByConfirmationToken(String token);
     User findByConfirmationTokenAndTokenExpired(String token,boolean tokenExpired);
}
