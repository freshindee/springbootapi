package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.UserToken;



//Repository
@Repository
public interface UserTokenRepository  extends JpaRepository<UserToken, Long>{
	UserToken findByUserId(Long userId);
}
