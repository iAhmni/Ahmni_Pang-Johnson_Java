package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {
    @Autowired
    CustomerRepository customerRepo;


    @Before
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }
    @Test
    public void getCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        Customer customer2 = new Customer();
        customer2.setFirstName("Bob");
        customer2.setLastName("Marley");
        customer2.setPhone("222-333-4567");
        customer2.setCompany("Independent");
        customer2.setAddress1("456 Timberly Lane");
        customer2.setAddress2("123 Fake St");
        customer2.setCity("Lawrence");
        customer2.setState("KS");
        customer2.setPostalCode(63);
        customer2.setCountry("United States");
        customerRepo.save(customer2);
        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }
    @Test
    public void getCustomersByState() {
        //Arrange...

        //Act...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        customerRepo.save(customer);

        Customer customer2 = new Customer();
        customer2.setFirstName("Bob");
        customer2.setLastName("Marley");
        customer2.setPhone("222-333-4567");
        customer2.setCompany("Independent");
        customer2.setAddress1("456 Timberly Lane");
        customer2.setAddress2("123 Fake St");
        customer2.setCity("Lawrence");
        customer2.setState("KS");
        customer2.setPostalCode(63);
        customer2.setCountry("United States");
        customerRepo.save(customer2);

        List<Customer> customerList = customerRepo.findByState("KS");

        //Assert...
        assertEquals(2, customerList.size());
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        customerRepo.save(customer);

        //Act...
        customer.setFirstName("UPDATED");

        customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("456 Timberly Lane");
        customer.setAddress2("123 Fake St");
        customer.setCity("Topeka");
        customer.setState("KS");
        customer.setPostalCode(60);
        customer.setCountry("United States");

        customerRepo.save(customer);

        //Act...
        customerRepo.deleteById(customer.getId());

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }
}
