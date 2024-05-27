package com.example.photoapp.controllers;

import com.example.photoapp.models.Photo;
import com.example.photoapp.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        Optional<Photo> photo = photoService.getPhotoById(id);
        return photo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Photo addPhoto(@RequestBody Photo photo) {
        return photoService.addPhoto(photo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id, @RequestBody Photo updatedPhoto) {
        Optional<Photo> photo = photoService.getPhotoById(id);
        if (photo.isPresent()) {
            return ResponseEntity.ok(photoService.updatePhoto(id, updatedPhoto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        Optional<Photo> photo = photoService.getPhotoById(id);
        if (photo.isPresent()) {
            photoService.deletePhoto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = photoService.uploadPhoto(file);
        return ResponseEntity.ok(fileUrl);
    }
}
