package com.xapp.xjava.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private String tagline;
    private String plotSummary;
    private String type;
    private String countryOfOrigin;
    private String originalLanguage;
    private Date releaseDate;
    private Integer rating;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genre;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "movie_id", referencedColumnName = "movieId")
    // private Set<Review> reviews;

    @OneToMany(mappedBy = "movieId")
    private List<Review> reviews;

    private Integer likes;
}
