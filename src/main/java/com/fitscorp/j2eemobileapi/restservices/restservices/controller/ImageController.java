package com.fitscorp.j2eemobileapi.restservices.restservices.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.services.ImageService;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@ResponseBody
	@GetMapping(value = "/images/{imageId}")
	public ResponseEntity<ByteArrayResource> getImageById(@PathVariable String imageId) throws NotFoundException {
		try {
	        final ByteArrayResource inputStream = imageService.getImageById(imageId);
	        final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        return ResponseEntity
	        		.status(HttpStatus.OK)
	        		.headers(headers)
	                .contentLength(inputStream.contentLength())
	                .body(inputStream);
		} catch (IOException e) {
			return null;
		}
	}
}
