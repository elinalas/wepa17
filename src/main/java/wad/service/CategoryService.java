/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Subject;

import wad.repository.NewsRepository;
import wad.repository.SubjectRepository;

/**
 *
 * @author elinalassila
 */
@Service
public class CategoryService {
    
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
