package uz.travel.abuusmon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.travel.abuusmon.entity.Post;
import uz.travel.abuusmon.dto.PostDto;
import uz.travel.abuusmon.mapper.PostMapper;
import uz.travel.abuusmon.repository.PostRepository;
import uz.travel.abuusmon.utils.FileService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final FileService fileService;

    @Override
    public Post createPost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = findPostById(id);

        //delete image from server
        String fileName = fileService.extractFileNameFromPath(post.getPhotoPath());
        fileService.deleteFile(fileName);

        postRepository.deleteById(id);
    }
}
