package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;

import javax.transaction.Transactional;


//Repository
@Repository
public interface UserTokenRepository  extends JpaRepository<UserToken, Long>{
	@Query("SELECT u FROM UserToken u WHERE u.userId = :userId")
	UserToken findByUserId(Long userId);

	@Query(value = "SELECT * FROM user_token WHERE access_token = :token AND user_id = :userId ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
	UserToken findTokenByRefreshToken(Long userId, String token);

	@Transactional
	@Modifying
	@Query(value = "UPDATE user_token SET access_token = :token WHERE user_id = :userId", nativeQuery = true)
	void updateTokenByRefreshToken(Long userId, String token);
}
