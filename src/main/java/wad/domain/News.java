/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author elinalassila
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class News extends AbstractPersistable<Long> {
    
//    @NotEmpty
//    @Size(min=3, max=50)
    private String title;
    
//    @NotEmpty
//    @Size(min=20, max=1500)
    private String content;
    
    @OneToOne
    private FileObject file;
    
    @ManyToMany
    private Set<Subject> categories;
    
    @ManyToMany
    private Set<Writer> writers;
    
    private int views;
    
//    @NotEmpty
    private LocalDateTime date;
    
}
