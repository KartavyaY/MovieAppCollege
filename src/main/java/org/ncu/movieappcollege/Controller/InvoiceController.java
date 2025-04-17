package org.ncu.movieappcollege.Controller;

import org.ncu.movieappcollege.Model.Invoice;
import org.ncu.movieappcollege.Repository.InvoiceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping
    public List<Invoice> getInvoice() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable long id) {
        if (invoiceRepository.findById(id).isPresent()) {
            return invoiceRepository.findById(id).get();
        }
        else return null;
    }

    @PostMapping("/add")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/bulkadd")
    public ResponseEntity<List<Invoice>> bulkAddInvoice(@RequestBody List<Invoice> invoices) {
        invoiceRepository.saveAll(invoices);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice) {
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/paginated")
    public List<Invoice> paginated(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return invoiceRepository.findAll(pageable).getContent();
    }
}
