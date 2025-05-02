package org.ncu.movieappcollege.Model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.util.List;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    private String bookingDate;
    private String bookingTime;
}
