package com.grok.hunt.Repository;


import com.grok.hunt.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    Optional<Users> findByUsername(String username);
}


