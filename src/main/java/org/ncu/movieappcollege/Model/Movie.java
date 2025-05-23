package org.ncu.movieappcollege.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;

    private String movieName;

}
