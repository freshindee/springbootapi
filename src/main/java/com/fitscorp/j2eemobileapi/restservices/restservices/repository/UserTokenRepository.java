package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;



//Repository
@Repository
public interface UserTokenRepository  extends JpaRepository<UserToken, Long>{
	@Query("SELECT u FROM UserToken u WHERE u.userId = :userId")
	UserToken findByUserId(Long userId);
}
