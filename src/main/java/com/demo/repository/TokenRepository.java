package com.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	@Modifying
	@Transactional
	@Query("DELETE FROM Token t WHERE t.expiryDate < :now")
	void deleteExpiredTokens(@Param("now") LocalDateTime now);
}
