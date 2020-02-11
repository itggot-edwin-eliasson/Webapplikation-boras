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
public class Favorites implements Serializable {
    @EmbeddedId
    private FavoritesKey favoritesKey;
    
    @ManyToOne
    @MapsId("account_id")
    @JoinColumn(name = "account_id")
    Account account;
    
    @ManyToOne
    @MapsId("film_id")
    @JoinColumn(name = "film_id")
    Film film;
    
    private int score;
}
