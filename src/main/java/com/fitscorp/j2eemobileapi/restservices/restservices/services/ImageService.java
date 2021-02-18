package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.RestResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	ServletContext context;
	
	public ByteArrayResource getImageById(String id) throws IOException, NotFoundException {
		String path = imageRepository.findImageByName(id);
	    if (path != null) {
			File file = ResourceUtils.getFile("classpath:uploads/" + path);
	        final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(file.toPath()));
		    return inputStream;
	    }
	    throw new NotFoundException("Image not found");
	}

	public ResponseEntity<?> getImageUrlById(String id) {
		try {
			String url = imageRepository.findImageByName(id);
			return new ResponseEntity<>(new RestResponse(200, new ArrayList(), url), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return null;
		}
	}
}