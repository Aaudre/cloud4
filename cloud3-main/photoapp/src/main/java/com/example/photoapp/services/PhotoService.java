package com.example.photoapp.services;

import com.example.photoapp.models.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhotoService {
    List<Photo> getAllPhotos();
    Optional<Photo> getPhotoById(Long id);
    Photo addPhoto(Photo photo);
    Photo updatePhoto(Long id, Photo updatedPhoto);
    void deletePhoto(Long id);
    String uploadPhoto(MultipartFile file) throws IOException;
}
