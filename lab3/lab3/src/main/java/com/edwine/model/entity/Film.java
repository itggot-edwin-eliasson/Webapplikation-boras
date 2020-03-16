package com.edwine.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable {

    @Id
    private String id;

    private String title;
    private String releaseYear;
    private String type;
    private String poster;

    //@OneToMany(mappedBy = "film")
    //private Set<Favorites> favorites;
}
