package com.egor.shop.repo;

import com.egor.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername (String username);
}
