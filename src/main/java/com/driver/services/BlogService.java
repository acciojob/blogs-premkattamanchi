package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content){
        //create a blog at the current time
        Blog blog=new Blog(title,content);
        User user=userRepository1.findById(userId).get();

        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());

        //saving foreign key in Blog entity
        blog.setUser(user);
            //setting bi-directional mapping in parent entity Use
            List<Blog> list=user.getBlogList();
            list.add(blog);
            user.setBlogList(list);
        userRepository1.save(user);// child entity blog will also be saved due to cascading effect
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
