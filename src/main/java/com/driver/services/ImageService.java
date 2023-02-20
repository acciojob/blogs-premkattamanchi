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

//    @Autowired
    BlogRepository blogRepository2=new BlogRepository() {
        @Override
        public List<Blog> findAll() {
            return null;
        }

        @Override
        public List<Blog> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Blog> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends Blog> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Blog> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Blog> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Blog getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends Blog> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Blog> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Blog> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Blog> S save(S s) {
            return null;
        }

        @Override
        public Optional<Blog> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Blog blog) {

        }

        @Override
        public void deleteAll(Iterable<? extends Blog> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Blog> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Blog> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Blog> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Blog> boolean exists(Example<S> example) {
            return false;
        }
    };
//    @Autowired
    ImageRepository imageRepository2=new ImageRepository() {
    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public List<Image> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Image> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public <S extends Image> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Image> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Image> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Image getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Image> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Image> S save(S s) {
        return null;
    }

    @Override
    public Optional<Image> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Image image) {

    }

    @Override
    public void deleteAll(Iterable<? extends Image> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Image> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Image> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Image> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Image> boolean exists(Example<S> example) {
        return false;
    }
};

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
