/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edwine.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;

/**
 *
 * @author edwin
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    @Id @NonNull private String username;
    @NonNull private String password;
    
    @OneToMany(mappedBy="account")
    private Set<Favorites> favorites;
}
