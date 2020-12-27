package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitscorp.j2eemobileapi.restservices.restservices.entities.File;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.FileRepository;

public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public List<File> getAllFiles() {
		return fileRepository.findAll();
	}
	
	public List<File> getFilesById(List<Long> ids) {
		return fileRepository.findAllById(ids);
	}
}
