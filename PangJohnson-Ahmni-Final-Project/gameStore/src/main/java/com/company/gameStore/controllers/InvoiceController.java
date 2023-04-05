package com.company.gameStore.controllers;

import com.company.gameStore.models.Invoice;
import com.company.gameStore.service.ServiceLayer;
import com.company.gameStore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id) {
        return serviceLayer.getInvoice(id);
    }

    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.getInvoices();
    }

    @GetMapping("/invoice/customer/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceByName(@PathVariable String name) {
        return serviceLayer.getByName(name);
    }

}
