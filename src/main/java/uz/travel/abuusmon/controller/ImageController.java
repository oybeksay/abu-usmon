package uz.travel.abuusmon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.travel.abuusmon.entity.Image;
import uz.travel.abuusmon.service.ImageService;
import uz.travel.abuusmon.utils.FileService;

import java.util.List;

@RestController
@RequestMapping("/images")
@Tag(name = "Gallery Management API", description = "API for image management")
public class ImageController {

    private final FileService fileService;
    private final ImageService imageService;

    @Autowired
    public ImageController(FileService fileService, ImageService imageService) {
        this.fileService = fileService;
        this.imageService = imageService;
    }


    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a new image", description = "Upload a new image to the gallery")
    public ResponseEntity<Image> upload(@RequestParam("file") MultipartFile file) {
        String photoDownloadPath = fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createImage(photoDownloadPath));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an image by ID", description = "Get an image by its ID")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }

    @GetMapping
    @Operation(summary = "Get all images", description = "Get all images in the gallery")
    public ResponseEntity<List<Image>> getImages() {
        return ResponseEntity.ok(imageService.getImages());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an image by ID", description = "Delete an image by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

}
