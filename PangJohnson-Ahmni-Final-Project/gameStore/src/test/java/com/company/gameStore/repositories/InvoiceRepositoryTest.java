package com.company.gameStore.repositories;

import com.company.gameStore.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    @Test
    public void shouldAddInvoice() {

        Invoice invoice = new Invoice();
        invoice.setName("Danny Jacobs");
        invoice.setStreet("2423 Maple Ave");
        invoice.setCity("Austin");
        invoice.setState("Texas");
        invoice.setZipcode("94923");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setQuantity(10);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("199.90"));
        invoice.setTax(new BigDecimal("11.99"));
        invoice.setProcessingFee(new BigDecimal("1.95"));
        invoice.setTotal(new BigDecimal("213.84"));

        invoiceRepository.save(invoice);

        assertEquals(1, invoiceRepository.findAll().size());
    }

    @Test
    public void shouldGetInvoiceById() {

        Invoice invoice = new Invoice();
        invoice.setName("Mark Jacobs");
        invoice.setStreet("0593 Allen Ave");
        invoice.setCity("San Diego");
        invoice.setState("California");
        invoice.setZipcode("94923");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setQuantity(10);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("199.90"));
        invoice.setTax(new BigDecimal("11.99"));
        invoice.setProcessingFee(new BigDecimal("1.95"));
        invoice.setTotal(new BigDecimal("213.84"));

        invoiceRepository.save(invoice);
        Invoice invoiceFromDb = invoiceRepository.findById(invoice.getInvoiceId()).orElse(null);

        assertEquals(invoice, invoiceFromDb);
    }

    @Test
    public void shouldGetAllInvoices() {

        Invoice invoice1 = new Invoice();
        invoice1.setName("Mark Jacobs");
        invoice1.setStreet("0593 Allen Ave");
        invoice1.setCity("San Diego");
        invoice1.setState("California");
        invoice1.setZipcode("94923");
        invoice1.setItemType("Game");
        invoice1.setItemId(1);
        invoice1.setQuantity(10);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setSubtotal(new BigDecimal("199.90"));
        invoice1.setTax(new BigDecimal("11.99"));
        invoice1.setProcessingFee(new BigDecimal("1.95"));
        invoice1.setTotal(new BigDecimal("213.84"));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Karl Marx");
        invoice2.setStreet("5023 Opper Ave");
        invoice2.setCity("Boulder");
        invoice2.setState("Colorado");
        invoice2.setZipcode("69402");
        invoice2.setItemType("Game");
        invoice2.setItemId(1);
        invoice2.setQuantity(10);
        invoice2.setUnitPrice(new BigDecimal("19.99"));
        invoice2.setSubtotal(new BigDecimal("199.90"));
        invoice2.setTax(new BigDecimal("11.99"));
        invoice2.setProcessingFee(new BigDecimal("1.95"));
        invoice2.setTotal(new BigDecimal("213.84"));

        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);

        assertEquals(2, invoiceRepository.findAll().size());
    }

    @Test
    public void shouldGetInvoiceByName() {

        Invoice invoice = new Invoice();
        invoice.setName("Mark Jacobs");
        invoice.setStreet("0593 Allen Ave");
        invoice.setCity("San Diego");
        invoice.setState("California");
        invoice.setZipcode("94923");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setQuantity(10);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("199.90"));
        invoice.setTax(new BigDecimal("11.99"));
        invoice.setProcessingFee(new BigDecimal("1.95"));
        invoice.setTotal(new BigDecimal("213.84"));

        invoiceRepository.save(invoice);
        Invoice invoiceFromDb = invoiceRepository.findByName(invoice.getName()).get(0);

        assertEquals(invoice, invoiceFromDb);
    }

}
