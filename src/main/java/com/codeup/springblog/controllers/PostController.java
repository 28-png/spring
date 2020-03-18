package com.codeup.springblog.controllers;

import com.codeup.springblog.Posts;
import com.codeup.springblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PostController {
    private final Posts postsDao;
    public PostController(Posts postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("title", postsDao.findPostById(1L).getTitle());
        model.addAttribute("postDate", postsDao.findPostById(1L).getDate());
        model.addAttribute("body", postsDao.findPostById(1L).getBody());
        return "posts";
    }

//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String getPost(@PathVariable int id) {
//        return "view an individual post, id="+id;
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String getCreatePostForm(){
//        return "view the form for creating a post";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(){
//        return "create a new post";
//    }

    @DeleteMapping("/posts/delete")
    public String delete(@RequestParam int id, Model model) {
        Post post = postsDao.findPostById(id);
        String deleteTitle = post.getTitle();
        Date deleteDate = post.getDate();
        String deleteBody = post.getBody();
        model.addAttribute("title", deleteTitle);
        model.addAttribute("postDate", deleteDate);
        model.addAttribute("body", deleteBody);
        postsDao.deleteById(id);
        return "posts";
    }


}