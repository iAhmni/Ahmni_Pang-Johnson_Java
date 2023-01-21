package com.company;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerTest {


    @Test
    public void shouldCalculateBalance() {
        Customer customer = new Customer();
        List<Customer> customerAccounts = new ArrayList<>();
        AccountRecord accountRecord = new AccountRecord();
        AccountRecord accountRecord2 = new AccountRecord();
        customer.setId(1);
        customer.setName("Test Customer");
        accountRecord.setCharge(10000);
        accountRecord.setChargeDate("10-06-2020");

        accountRecord2.setCharge(-5000);
        accountRecord2.setChargeDate("11-06-2020");

        customer.getCharges().add(accountRecord);
        customer.getCharges().add(accountRecord2);

        assert(5000 == customer.getBalance());
    }

    @Test
    public void shouldCalculateString() {
        Customer customer = new Customer();
        Customer customer2 = new Customer();
        customer.setId(0);
        customer.setName("Customer 1");
        customer2.setId(1);
        customer2.setName("Customer 2");
        assert(customer.toString().equals("ID: 0 Name: Customer 1 Balance: 0"));
        assert(customer2.toString().equals("ID: 1 Name: Customer 2 Balance: 0"));
    }
}