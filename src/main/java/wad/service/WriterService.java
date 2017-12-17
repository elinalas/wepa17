/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Writer;
import wad.repository.NewsRepository;
import wad.repository.WriterRepository;

/**
 *
 * @author elinalassila
 */
@Service
public class WriterService {
    
    @Autowired
    private WriterRepository writerrepo;
    
    @Autowired
    private NewsRepository newsrepo;
    
    public List<Writer> getWriters() {
        return writerrepo.findAll();
    }
    
    
    public Writer getOne(Long id) {
        return writerrepo.getOne(id);
    }
    
}
