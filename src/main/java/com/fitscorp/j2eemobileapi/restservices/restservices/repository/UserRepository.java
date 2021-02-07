package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


//Repository
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM user WHERE email_address = :email")
	User findByEmail(String email);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE User u SET u.password = :password WHERE u.id = :userId")
	void updatePassword(Long userId, String password);

	@Query(nativeQuery = true, value = "SELECT enabled FROM user WHERE email_address = :email")
    Boolean findUserEnabled(String email);

	@Query(nativeQuery = true, value = "SELECT is_email_verified FROM user WHERE email_address = :email")
	Boolean findUserEmailVerified(String email);
}


