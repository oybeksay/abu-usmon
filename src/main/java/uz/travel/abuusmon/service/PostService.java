package uz.travel.abuusmon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.travel.abuusmon.entity.Post;
import uz.travel.abuusmon.dto.PostDto;

public interface PostService {

    Post createPost(PostDto postDto);

    Post findPostById(Long id);

    Page<Post> findAllPosts(Pageable pageable);

    Post updatePost(Post post);

    void deletePost(Long id);

}
