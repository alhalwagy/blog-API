package com.java.blogApp.controller;

import com.java.blogApp.dto.post.PostRequestModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping("")
  public List<PostResponseModel> getAllPosts() {
    return postService.findAll();
  }

  @PostMapping("")
  public PostResponseModel createPost(@RequestBody PostRequestModel postRequestModel) {
    return postService.createPost(postRequestModel);
  }

  @GetMapping("/my-posts")
  public List<PostResponseModel> getPostsByUser() {
    return postService.getMyPosts();
  }

  @PutMapping("{id}")
  public PostResponseModel updatePost(
      @PathVariable("id") int id, @RequestBody PostRequestModel postRequestModel) {
    return postService.updatePost(id, postRequestModel);
  }

  @DeleteMapping("{id}")
  public void deletePost(@PathVariable("id") int id) {
    postService.deletePostById(id);
  }
}
