package uz.travel.abuusmon.service;

import uz.travel.abuusmon.entity.Image;

import java.util.List;

public interface ImageService {

    Image createImage(String photoUrl);

    Image getImage(Long id);

    List<Image> getImages();

    void deleteImage(Long id);

}
