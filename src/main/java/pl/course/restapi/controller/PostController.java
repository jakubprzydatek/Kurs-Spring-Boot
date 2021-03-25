
package pl.course.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.course.restapi.controller.dto.PostDto;
import pl.course.restapi.model.Post;
import pl.course.restapi.service.PostService;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/api/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >=0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection));
    }

    @GetMapping("/api/posts/comments")
    public List<Post> getPostsWithComment(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >=0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }



    @GetMapping("/api/post/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getSinglePost(id);
    }

    @PostMapping("/api/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PutMapping("/api/posts")
    public Post editPost(@RequestBody Post post){
        return postService.editPost(post);
    }

    @DeleteMapping("/api/posts{id}")
    public void deletePost(@PathVariable long id){
        postService.deletePost(id);
    }
}

