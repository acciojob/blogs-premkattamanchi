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

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image(dimensions,description,blog);

        //adding image in the parent entity list Blog
        List<Image> list=blog.getImageList();
        list.add(image);
        blog.setImageList(list);

        blogRepository2.save(blog);
    }

    public void deleteImage(Integer id){
        if(imageRepository2.findById(id).get()!=null)
           imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        /*String imageDimensions=image.getDimensions();
        String[] screen_arr=screenDimensions.split("x");
        String[] image_arr=imageDimensions.split("x");
        int count=0;
        int x=Integer.parseInt(screen_arr[0])/Integer.parseInt(image_arr[0]);
        int y=Integer.parseInt(screen_arr[1])/Integer.parseInt(image_arr[1]);
        count=x*y;
        return count;*/
        String dimensions = image.getDimensions();
        int xi = 0;
        int yi = 0;
        int xs = 0;
        int ys = 0;
        int num = 0;
        for(int i = 0; i<dimensions.length(); i++){
            if(dimensions.charAt(i) == 'X'){
                xi = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (dimensions.charAt(i) - '0');
        }
        yi = num;
        num = 0;
        for(int i = 0; i<screenDimensions.length(); i++){
            if(screenDimensions.charAt(i) == 'X'){
                xs = num;
                num = 0;
                continue;
            }
            num *= 10;
            num += (screenDimensions.charAt(i) - '0');
        }
        ys = num;

        int ans = (int) (Math.floor(((double)xs)/((double)xi)) * Math.floor(((double)ys)/((double)yi)));
        return ans;
    }
}
