package com.driver.controller;

import com.driver.models.Blog;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
  @Autowired
  BlogService blogService;
    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestParam Integer userId ,
                                     @RequestParam String title,
                                     @RequestParam String content) {
        // Create a blog and add it under given user
        try {
            Blog blog = blogService.createAndReturnBlog(userId, title, content);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(new Blog(title,content),HttpStatus.CREATED);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        // Delete the blog using deleteById
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}




