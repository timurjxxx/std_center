package com.backend.studycenter.common.repository.security;

import com.backend.studycenter.common.model.security.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM usr WHERE username= :name", nativeQuery = true)
    Optional<User> findByUsername(@Param("name") String username);
}
