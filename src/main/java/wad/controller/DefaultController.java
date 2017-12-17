a/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wad.domain.News;
import wad.domain.Subject;
import wad.repository.SubjectRepository;
import wad.service.CategoryService;
import wad.service.NewsService;package wad.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wad.domain.News;
import wad.domain.Subject;
import wad.repository.SubjectRepository;
import wad.service.CategoryService;
import wad.service.NewsService;

@Controller
public class DefaultController {
    
    @Autowired
    private SubjectRepository categoryrepo;
    
    @Autowired
    private NewsService newsservice;
    
    
    
    @PostConstruct
    public void init() throws IOException {
        Subject c = new Subject();
        c.setName("Hyvinvointi");
        c.setNews(new ArrayList());
        categoryrepo.save(c);
        
        Subject c1 = new Subject();
        c1.setName("Kotimaa");
        c1.setNews(new ArrayList());
        categoryrepo.save(c1);
        
        Subject c2 = new Subject();
        c2.setName("Kolumnit");
        c2.setNews(new ArrayList());
        categoryrepo.save(c2);
        
        Subject c3 = new Subject();
        c3.setName("Kulttuuri");
        c3.setNews(new ArrayList());
        categoryrepo.save(c3);
        
        Subject c4 = new Subject();
        c4.setName("Politiikka");
        c4.setNews(new ArrayList());
        categoryrepo.save(c4);
        
        Subject c5 = new Subject();
        c5.setName("Pääkirjoitukset");
        c5.setNews(new ArrayList());
        categoryrepo.save(c5);
        
        Subject c6 = new Subject();
        c6.setName("Talous");
        c6.setNews(new ArrayList());
        categoryrepo.save(c6);
        
        Subject c7 = new Subject();
        c7.setName("Ulkomaat");
        c7.setNews(new ArrayList());
        categoryrepo.save(c7);
        
        Subject c8 = new Subject();
        c8.setName("Urheilu");
        c8.setNews(new ArrayList());
        categoryrepo.save(c8);

    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/news";
    }
}