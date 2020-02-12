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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author edwin
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Film implements Serializable{
    @Id @GeneratedValue
    private int id;
    
    @NonNull private String title;
    @NonNull private Integer releaseYear;
    @NonNull private String genre;
    @NonNull private String director;
    
    @OneToMany(mappedBy = "film")
    private Set<Favorites> favorites;
   
}
