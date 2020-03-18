package com.codeup.springblog.controllers;

import com.codeup.springblog.Posts;
import com.codeup.springblog.model.Post;
import org.springframework.format.annotation.DateTimeFormat;
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
        model.addAttribute("posts", postsDao.findAll());
        return "posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPost(@PathVariable int id) {
        return "view an individual post, id="+id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "edit";
    }
    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam Date date ,@RequestParam String body) {
        Post p = postsDao.getOne(id);
        p.setTitle(title);
        p.setDate(date);
        p.setBody(body);
        postsDao.save(p);
        return "redirect:/posts";
    }


}