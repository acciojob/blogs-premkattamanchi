package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {;

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image(dimensions,description,blog);

        //adding image in the parent entity list Blog
        List<Image> list=blog.getImageList();
        list.add(image);
        blog.setImageList(list);

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
        String[] screen_arr=screenDimensions.split("x");
        String[] image_arr=imageDimensions.split("x");
        int count=0;
        if(screen_arr.length==1 || image_arr.length==1)
            return 0;
        int x=Integer.parseInt(screen_arr[0])/Integer.parseInt(image_arr[0]);
        int y=Integer.parseInt(screen_arr[1])/Integer.parseInt(image_arr[1]);
        count=x*y;
        return count;
    }
}
