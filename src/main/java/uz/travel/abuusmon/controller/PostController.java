package uz.travel.abuusmon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.travel.abuusmon.dto.PostDto;
import uz.travel.abuusmon.entity.Post;
import uz.travel.abuusmon.service.PostService;
import uz.travel.abuusmon.utils.FileService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Post Management",description = "REST endpoints for creating, reading, updating and deleting posts")
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    @PostMapping(value = "/create")
    @Operation(summary = "Create post",description = "Create post")
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    @PostMapping(value = "/image/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload image",description = "Upload image to server")
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String photoPath = fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("photoPath",photoPath));
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Find post by id",description = "Find post by id")
    public ResponseEntity<Post> findPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping
    @Operation(summary = "Find all posts",description = "Find all posts")
    public ResponseEntity<PagedModel<Post>> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Post> allPosts = postService.findAllPosts(PageRequest.of(page, size));
        return ResponseEntity.ok(new PagedModel<>(allPosts));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete post",description = "Delete post")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update post",description = "Update post")
    public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post) {
        return ResponseEntity.status(200).body(postService.updatePost(post));
    }
}