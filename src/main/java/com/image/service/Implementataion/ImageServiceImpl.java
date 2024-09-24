package com.image.service.Implementataion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.image.Entity.Image;
import com.image.Repository.ImageRepository;
import com.image.service.ImageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

	private ImageRepository repo;
	@Override
	public Image saveImage(String name, String type, MultipartFile file) throws Exception {
		Image image=new Image();
		image.setName(name);
		image.setType(type);
		if(file!= null && !file.isEmpty()) {
			image.setData(file.getBytes());
			
		}else
		{
			throw new Exception("File is emplty or not provided");
		}
		return repo.save(image);
	}

	@Override
	public Optional<Image> getById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Image> getAllImages() {
		return repo.findAll();
	}
  
	
}
