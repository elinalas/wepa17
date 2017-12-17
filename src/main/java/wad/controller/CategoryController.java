/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wad.domain.Subject;
import wad.repository.NewsRepository;
import wad.repository.SubjectRepository;

/**
 *
 * @author elinalassila
 */
@Controller
public class CategoryController {
    
    @Autowired
    private SubjectRepository categoryrepo;
    
    @Autowired
    private NewsRepository newsrepo;
    
    public List<Subject> getCategories() {
        return categoryrepo.findAll();        
    }
    
    public Subject getOne(Long id) {
        return categoryrepo.getOne(id);
    }
    
}
