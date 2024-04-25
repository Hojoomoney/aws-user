package com.kubernetesdemo.awsuser.user.repository;

import com.kubernetesdemo.awsuser.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findAllByOrderById();

    @Modifying
    @Query("update users set token = :token where id = :id")
    void modifyTokenById(@Param("id") Long id, @Param("token") String token);
}
