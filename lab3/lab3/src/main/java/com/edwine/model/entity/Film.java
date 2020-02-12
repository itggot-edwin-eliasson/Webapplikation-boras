/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author edwin
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable{
    @Id
    private String film_id;
    
    private String title;
    private int releaseYear;
    private String genre;
    private String director;
    
    @OneToMany(mappedBy = "film")
    Set<Favorites> favorites;
   
}
