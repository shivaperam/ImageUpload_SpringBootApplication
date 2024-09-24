package com.image.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.image.Entity.Image;

public interface ImageService {

	Image saveImage(String name, String type, MultipartFile file) throws Exception;
	
	Optional<Image> getById(Long id);
	
	List<Image> getAllImages();
}
