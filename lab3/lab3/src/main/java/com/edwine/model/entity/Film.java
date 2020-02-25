package com.edwine.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable{
    @Id 
    private String id;
    
    @OneToMany(mappedBy = "film")
    private Set<Favorites> favorites;
}
