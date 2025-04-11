package com.example.authservice.repository;

import com.example.authservice.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvaildatedTokenRepository extends JpaRepository<InvalidatedToken,String> {
}
