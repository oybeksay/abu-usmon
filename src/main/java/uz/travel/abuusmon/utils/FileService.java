package uz.travel.abuusmon.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileService {

    @Value("${file.upload.base-url}")
    private String baseUrl;

    @Value("${file.upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) {
        try {

            checkAndCreateDirectory();

            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = uploadPath + File.separator + uniqueFileName;

            File destination = new File(filePath);
            file.transferTo(destination);

            return baseUrl + "/files/" + uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Fayl yuklanmadi: " + e.getMessage());
        }
    }


    public void deleteFile(String fileName) {
        try {
            Path fileToDeletePath = Path.of(uploadPath + "\\" + fileName);
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            throw new RuntimeException("File could not be deleted: " + e.getMessage());
        }
    }

    public String extractFileNameFromPath(String photoPath) {
        try {
            String decodedPath = URLDecoder.decode(photoPath, StandardCharsets.UTF_8);
            return decodedPath.substring(decodedPath.lastIndexOf("/") + 1);
        } catch (Exception e) {
            throw new RuntimeException("Error get file name " + e.getMessage());
        }
    }


    private void checkAndCreateDirectory() {
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Could not create upload directory: " + uploadPath);
            }
        }
    }
}
