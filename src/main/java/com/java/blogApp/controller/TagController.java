package com.java.blogApp.controller;

import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.dto.tag.TagRequestModel;
import com.java.blogApp.dto.tag.TagResponseModel;
import com.java.blogApp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping()
    public List<TagResponseModel> getAllTags(){
        return tagService.getAllTags();
    }

    @PostMapping()
    public TagResponseModel createNewTag(@RequestBody TagRequestModel request){
        return tagService.createNewTag(request);
    }

    @PostMapping("/{postId}/{tagId}")
    public void addTagToPost(
            @PathVariable int postId,
            @PathVariable int tagId
    ){
        tagService.addTagToPost(postId, tagId);
    }

    @GetMapping(params = "id")
    public List<PostResponseModel> getAllPostsWithTagId(@RequestParam(name = "id")int id){
        return tagService.getAllPostsWithTagId(id);
    }

    @GetMapping(params = "name")
    public List<PostResponseModel> getAllPostsWithTagName(@RequestParam(name = "name")String name){
        return tagService.getAllPostsWithTagName(name);
    }
}
