package com.image.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.Entity.Image;
import com.image.service.Implementataion.ImageServiceImpl;

@RestController
@RequestMapping("/images")
public class ImageRestController {

	@Autowired
	private ImageServiceImpl service;
	 @PostMapping("/upload")
	    public ResponseEntity<Image> uploadImage(
	            @RequestParam("name") String name,
	            @RequestParam("file") MultipartFile file) {
	        try {
	            String type = file.getContentType();
	            Image savedImage = service.saveImage(name, type, file);
	            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
	        } catch (Exception e) {
	            // Log the error (not shown here for brevity)
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws  Exception{
	        Optional<Image> imageOptional = service.getById(id);
	        
	        if (imageOptional.isPresent()) {
	            Image image = imageOptional.get();
	            
	            // Returning the binary data of the image
	            return ResponseEntity.ok(image.getData());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @GetMapping("/getallimages")
	    public ResponseEntity<List<Image>> getAllImages() throws  Exception{
	        List<Image> images = service.getAllImages();

	        // Convert the list of Image entities to a simplified response (without the binary data)
	        List<Image> imageResponses = images.stream().map(image -> 
	            new Image()
	        ).collect(Collectors.toList());

	        return ResponseEntity.ok(imageResponses);
	    }
	    }
	 

