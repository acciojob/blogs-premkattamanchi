package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image(dimensions,description,blog);

        //adding image in the parent entity list Blog
        List<Image> list=blog.getImagesList();
        list.add(image);
        blog.setImagesList(list);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        if(imageRepository2.findById(id).get()!=null)
           imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String imageDimensions=image.getDimensions();
  return 0;
    }
}
