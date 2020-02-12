/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author benja
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoritesKey.class)
public class Favorites implements Serializable {
    //@EmbeddedId
    //private FavoritesKey favoritesKey;
    
    @Id
    @ManyToOne
    //@MapsId("account_id")
    private Account account;
   
    
    @Id
    @ManyToOne
    //@MapsId("film_id")
    private Film film;
    
    private int score;
}
