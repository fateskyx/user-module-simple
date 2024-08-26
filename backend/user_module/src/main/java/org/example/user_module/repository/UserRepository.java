package org.example.user_module.repository;

import org.example.user_module.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods if needed

    Page<User> findByUserNameContainingIgnoreCase(String search, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    List<User> findAllByPropertyName(@Param("userName") String userName);

}
