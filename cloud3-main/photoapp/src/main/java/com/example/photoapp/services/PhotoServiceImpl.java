package com.example.photoapp.services;

import com.example.photoapp.models.Photo;
import com.example.photoapp.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final AzureBlobService azureBlobService;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, AzureBlobService azureBlobService) {
        this.photoRepository = photoRepository;
        this.azureBlobService = azureBlobService;
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo updatePhoto(Long id, Photo updatedPhoto) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if (optionalPhoto.isPresent()) {
            Photo existingPhoto = optionalPhoto.get();
            existingPhoto.setTitle(updatedPhoto.getTitle());
            existingPhoto.setDescription(updatedPhoto.getDescription());
            existingPhoto.setUrl(updatedPhoto.getUrl());
            return photoRepository.save(existingPhoto);
        } else {
            throw new IllegalArgumentException("Photo not found");
        }
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public String uploadPhoto(MultipartFile file) throws IOException {
        return azureBlobService.uploadFile(file);
    }
}
