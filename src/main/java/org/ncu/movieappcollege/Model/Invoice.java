package org.ncu.movieappcollege.Model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    @Column(nullable = false)
    private String customerName;
    private double amount;
    private String movieName;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

}
