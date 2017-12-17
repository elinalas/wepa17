/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.domain.Subject;
import wad.domain.News;
import wad.domain.Writer;
import wad.repository.NewsRepository;
import wad.repository.WriterRepository;
import wad.repository.SubjectRepository;

/**
 *
 * @author elinalassila
 */
@Service
public class NewsService {

    @Autowired
    private NewsRepository newsrepo;

    @Autowired
    private SubjectRepository categoryrepo;

    @Autowired
    private WriterRepository writerrepo;

    public List<News> getNews() {
        return newsrepo.findAll();
    }

    public News getOne(Long id) {
        News news = newsrepo.getOne(id);
        news.setViews(news.getViews() +1);
        newsrepo.save(news);
        return newsrepo.getOne(id);
    }

    public News deleteNews(Long id) {
        News news = newsrepo.getOne(id);
        newsrepo.deleteById(id);
        return news;
    }

    public News addNews(String name,
            String text, MultipartFile file, Set<String> categories) throws IOException {
        News news = new News();
        
        FileObject fo = new FileObject();
        
        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());
        
        
        news.setFile(fo);
        news.setViews(0);
        news.setContent(text);
        news.setTitle(name);
        news.setDate(LocalDateTime.now());

        categories.stream().forEach(c -> {
            boolean loytyi = false;
            for (Subject category : categoryrepo.findAll()) {
                if (category.getName().equals(c)) {
                    addCategoryToNews(news.getId(), category.getId());
                    loytyi = true;
                }
            }
            if (!loytyi) {
                Subject category = new Subject();
                category.setName(name);
                category.setNews(new ArrayList());
                addCategoryToNews(news.getId(), category.getId());
            }
        });

        return newsrepo.save(news);
    }

    public News updateNews(Long id, String name,
            String text, MultipartFile file, Set<String> categories) throws IOException {
        News news = newsrepo.getOne(id);
        FileObject fo = new FileObject();
        
        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());
        
        
        news.setFile(fo);
        news.setContent(text);
        news.setTitle(name);        
                

        categories.stream().forEach(c -> {
            boolean loytyi = false;
            for (Subject category : categoryrepo.findAll()) {
                if (category.getName().equals(c)) {
                    addCategoryToNews(news.getId(), category.getId());
                    loytyi = true;
                }
            }
            if (!loytyi) {
                Subject category = new Subject();
                category.setName(name);
                category.setNews(new ArrayList());
                addCategoryToNews(news.getId(), category.getId());
            }
        });

        return newsrepo.save(news);
    }

    public News deleteWriterFromNews(Long id, Long writerId) {
        News news = newsrepo.getOne(id);
        Set<Writer> writers = news.getWriters();
        writers.remove(writerrepo.getOne(writerId));
        news.setWriters(writers);
        return newsrepo.save(news);
    }

    public News addWriterToNews(Long id, Long writerId) {
        News news = newsrepo.getOne(id);
        Set<Writer> writers = news.getWriters();
        writers.add(writerrepo.getOne(writerId));
        news.setWriters(writers);
        return newsrepo.save(news);
    }

    public News deleteCategoryFromNews(Long id, Long categoryId) {
        News news = newsrepo.getOne(id);
        Set<Subject> categories = news.getCategories();
        categories.remove(categoryrepo.getOne(categoryId));
        news.setCategories(categories);
        return newsrepo.save(news);
    }

    public News addCategoryToNews(Long id, Long categoryId) {
        News news = newsrepo.getOne(id);
        Set<Subject> categories = news.getCategories();
        categories.add(categoryrepo.getOne(categoryId));
        news.setCategories(categories);
        Subject category = categoryrepo.getOne(id);
        List<News> categorynews = category.getNews();
        categorynews.add(news);
        category.setNews(categorynews);
        categoryrepo.save(category);
        return newsrepo.save(news);
    }

    public List<News> getNewsAccorgindToViews() {
        List<News> mostViewsNews = newsrepo.findAll();        
        mostViewsNews.stream().sorted((News eka, News toka) -> {
        return toka.getViews() - eka.getViews();
        }).collect(Collectors.toList());
        return mostViewsNews;
    }

    public List<News> getNewsAccordingToCategory(Subject category) {
        List<News> news = new ArrayList();
        newsrepo.findAll().stream().forEach(n -> {
            if (n.getCategories().contains(category)) {
                news.add(n);
            }
            });
        return news;
    }
 

}