package com.edwine.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String imdbRating;
    private String metascore;
    

}
