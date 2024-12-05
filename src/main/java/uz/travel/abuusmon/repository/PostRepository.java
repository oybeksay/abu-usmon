package uz.travel.abuusmon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.travel.abuusmon.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query("update Post p set p.title = ?1, p.description = ?2, p.photoPath = ?3 where p.id = ?4")
    int updateTitleAndDescriptionAndPhotoPathById(String title, String description, String photoPath, Long id);
}