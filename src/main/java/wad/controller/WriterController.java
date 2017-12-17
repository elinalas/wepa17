/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wad.domain.Writer;
import wad.repository.NewsRepository;

/**
 *
 * @author elinalassila
 */
@Controller
public class WriterController {
    
    @Autowired
    public NewsRepository newsrepo;
    
    
    @GetMapping("/writers")
    public List<Writer> getWriters() {
        return null;
    }
    
}
