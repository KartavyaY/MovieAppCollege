package org.ncu.movieappcollege.Repository;

import org.ncu.movieappcollege.Model.CustomerProfile;
import org.ncu.movieappcollege.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerProfile, Long> {
}
