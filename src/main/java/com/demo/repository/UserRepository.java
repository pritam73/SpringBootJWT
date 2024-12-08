package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	@Query("select count(e)>0 from User e where e.email =:email OR  e.mobileNo =:mobileNo ")
	boolean existByEmailOrMobile(String email, String mobileNo);

	User findByUuid(String id);

	User findByMobileNo(String mobileNo);

}
