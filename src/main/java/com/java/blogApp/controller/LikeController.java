package com.java.blogApp.controller;

import com.java.blogApp.dto.like.LikeResponseModel;
import com.java.blogApp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @GetMapping("/post/{postId}")
    public List<LikeResponseModel> getAllLikesForPost(@PathVariable int postId ) {

        return likeService.getLikesByPostId(postId);

    }

    @PostMapping("{postId}")
    public LikeResponseModel createLike(@PathVariable int postId){
        return likeService.createLike(postId);
    }

    @DeleteMapping("{likeId}")
    public void deleteLike(@PathVariable int likeId){
        likeService.deleteLike(likeId);
    }

    @GetMapping("{likeId}")
    public LikeResponseModel getLike(@PathVariable int likeId){
        return likeService.findById(likeId);
    }

}
