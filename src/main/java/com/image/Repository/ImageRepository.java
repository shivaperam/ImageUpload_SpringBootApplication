package com.image.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.image.Entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
