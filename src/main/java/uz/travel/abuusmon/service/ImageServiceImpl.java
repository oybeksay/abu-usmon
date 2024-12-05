package uz.travel.abuusmon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.travel.abuusmon.entity.Image;
import uz.travel.abuusmon.repository.ImageRepository;
import uz.travel.abuusmon.utils.FileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final FileService fileService;

    @Override
    public Image createImage(String photoUrl) {
        return imageRepository.save(Image.builder().path(photoUrl).build());
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new RuntimeException("Image not found"));
    }

    @Override
    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteImage(Long id) {
        Image image = getImage(id);

        //delete image from server
        String fileName = fileService.extractFileNameFromPath(image.getPath());
        fileService.deleteFile(fileName);

        imageRepository.deleteById(id);
    }
}
