package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.File;

@Repository
public interface ImageRepository extends JpaRepository<File, Long> {

    @Query(nativeQuery = true, value = "SELECT path FROM file WHERE name = :id")
	public String findImageByName(String id);
	
}
