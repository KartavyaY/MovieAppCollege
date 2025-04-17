package org.ncu.movieappcollege.Controller;

import org.ncu.movieappcollege.Model.CustomerProfile;
import org.ncu.movieappcollege.Repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerProfile> getCustomerProfiles() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public CustomerProfile getCustomerProfileById(@PathVariable long id) {
        if (customerRepository.findById(id).isPresent()) {
            return customerRepository.findById(id).get();
        }
        else {
            return null;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerProfile> saveCustomer(@RequestBody CustomerProfile customerProfile) {
        customerRepository.save(customerProfile);
        return ResponseEntity.ok(customerProfile);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CustomerProfile> deleteCustomer(@RequestBody CustomerProfile customerProfile) {
        customerRepository.delete(customerProfile);
        return ResponseEntity.accepted().body(customerProfile);
    }
}
