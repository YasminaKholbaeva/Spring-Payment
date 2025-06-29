package com.example.payment.repository;

import com.example.payment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByMobileNumber(String mobileNumber);

    Optional<User> findById(Integer id);

}
