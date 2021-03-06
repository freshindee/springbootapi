package com.fitscorp.j2eemobileapi.restservices.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.File;

@Repository
public interface ImageRepository extends JpaRepository<File, Long> {

    @Query(nativeQuery = true, value = "SELECT path FROM file WHERE name = :id" +
            " AND table_id = :tableId AND table_def = :tableDef")
	public String findImageByName(String id, String tableId, String tableDef);
	
}
