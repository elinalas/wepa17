/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.jetty.util.security.Password;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author elinalassila
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Writer  extends AbstractPersistable<Long> {
//    
//    @NotEmpty
    private String name;
    
//    @NotEmpty
//    @Size(min=6,max=35)
    private Password password;
    
    @ManyToMany
    private List<News> news;
    
}
