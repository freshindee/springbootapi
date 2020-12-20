package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ImageRepository;



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
}