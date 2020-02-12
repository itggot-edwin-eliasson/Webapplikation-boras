/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author benja
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FavoritesKey implements Serializable {
    @Column(name="film_id")
    private String film;
    @Column(name="account_id")
    private String account;
}
