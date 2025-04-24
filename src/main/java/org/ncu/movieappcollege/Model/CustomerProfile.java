package org.ncu.movieappcollege.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column(nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
