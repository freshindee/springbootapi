package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.User;



//Repository
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM user WHERE email_address = :email")
	User findByEmail(String email);
}


