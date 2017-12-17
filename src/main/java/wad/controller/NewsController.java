/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.domain.Subject;
import wad.domain.News;
import wad.domain.Writer;
import wad.repository.NewsRepository;
import wad.repository.WriterRepository;
import wad.service.CategoryService;
import wad.service.NewsService;
import wad.service.WriterService;
import wad.repository.SubjectRepository;


/**
 *
 * @author elinalassila
 */
@Controller
public class NewsController {
    
    @Autowired
    private NewsService newsservice;
    
    @Autowired
    private CategoryService categoryservice;
    
    @Autowired
    private WriterService writerservice;
    
    
    @GetMapping("/news")
    public String getNews(Model model) {       
        model.addAttribute("allnews", newsservice.getNews());
        return "allnews.html";
    }
    
    @GetMapping("/news/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        News news = newsservice.getOne(id);
        model.addAttribute("news", news);
        return "news.html";
    }
    
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsservice.deleteNews(id);
        return "redirect:/allnews";
    }
    
    @GetMapping("/news/addnews")
    public String save(Model model) {
        model.addAttribute("categories", categoryservice.getCategories());
        model.addAttribute("writers", writerservice.getWriters());
        return "addnews";
    }
    
    @PostMapping("/news/addingnews") 
    public String addNews(@RequestParam String content, @RequestParam String title,
            @RequestParam("file") MultipartFile file, @RequestParam Set<String> categories) throws IOException {
        newsservice.addNews(title, content, file, categories);
        return "redirect:/news";
    }
    
    @GetMapping("/news/{id}/updatingnews")
    public String updatingNews(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryservice.getCategories());
        model.addAttribute("writers", writerservice.getWriters());
        model.addAttribute("news", newsservice.getOne(id));
        return "updatingnews";
    }
    
    
    @PutMapping("/news/{id}") 
    public String updateNews(@PathVariable Long id, @RequestParam String name, 
            @RequestParam String text, @RequestParam("file") MultipartFile file, @RequestParam Set<String> categories) throws IOException {
        newsservice.updateNews(id, name, text, file, categories);
        return "redirect:/news";
    }

}
