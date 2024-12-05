package uz.travel.abuusmon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.travel.abuusmon.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}