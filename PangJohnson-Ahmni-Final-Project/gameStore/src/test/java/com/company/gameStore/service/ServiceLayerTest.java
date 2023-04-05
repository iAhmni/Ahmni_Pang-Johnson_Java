package com.company.gameStore.service;

import com.company.gameStore.models.Invoice;
import com.company.gameStore.repositories.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    InvoiceRepository invoiceRepository;
    GameRepository gameRepository;
    ConsoleRepository consoleRepository;
    TShirtRepository tshirtRepository;
    FeeRepository feeRepository;
    TaxRepository taxRepository;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceRepositoryMock();

        service = new ServiceLayer(invoiceRepository, gameRepository, consoleRepository,
                                        tshirtRepository, feeRepository, taxRepository);

    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
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

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice1);

        doReturn(Optional.of(invoice1)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findByName("Mark Jacobs");
        doReturn(invoiceList).when(invoiceRepository).findAll();
    }

    @Test
    public void shouldFindInvoiceById() {

        Invoice expectedResult = new Invoice();
        expectedResult.setInvoiceId(1);
        expectedResult.setName("Mark Jacobs");
        expectedResult.setStreet("0593 Allen Ave");
        expectedResult.setCity("San Diego");
        expectedResult.setState("California");
        expectedResult.setZipcode("94923");
        expectedResult.setItemType("Game");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(10);
        expectedResult.setUnitPrice(new BigDecimal("19.99"));
        expectedResult.setSubtotal(new BigDecimal("199.90"));
        expectedResult.setTax(new BigDecimal("11.99"));
        expectedResult.setProcessingFee(new BigDecimal("1.95"));
        expectedResult.setTotal(new BigDecimal("213.84"));

        Invoice invoice = service.getInvoice(1);
        assertEquals(invoice, expectedResult);

    }

    @Test
    public void shouldFindInvoiceByName() {

        Invoice expectedResult = new Invoice();
        expectedResult.setInvoiceId(1);
        expectedResult.setName("Mark Jacobs");
        expectedResult.setStreet("0593 Allen Ave");
        expectedResult.setCity("San Diego");
        expectedResult.setState("California");
        expectedResult.setZipcode("94923");
        expectedResult.setItemType("Game");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(10);
        expectedResult.setUnitPrice(new BigDecimal("19.99"));
        expectedResult.setSubtotal(new BigDecimal("199.90"));
        expectedResult.setTax(new BigDecimal("11.99"));
        expectedResult.setProcessingFee(new BigDecimal("1.95"));
        expectedResult.setTotal(new BigDecimal("213.84"));

        Invoice invoice = service.getByName("Mark Jacobs");
        assertEquals(invoice, expectedResult);
    }

    @Test
    public void shouldFindAllInvoices() {

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
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

        List<Invoice> expectedResult = new ArrayList<>();
        expectedResult.add(invoice1);

        List<Invoice> invoices = service.getInvoices();

        assertEquals(expectedResult.size(), invoices.size());
    }



}
