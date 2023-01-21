package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        //Update this
        List<Customer> customerList = new ArrayList<>();
        List<Integer> addedIds = new ArrayList<>();
        for (int i = 0; i < customerData.size(); i++) {
            int id = Integer.parseInt(customerData.get(i)[0]);
            AccountRecord record = new AccountRecord();
            record.setCharge(Integer.parseInt(customerData.get(i)[2]));
            record.setChargeDate(customerData.get(i)[3]);
            if (addedIds.contains(Integer.parseInt(customerData.get(i)[0]))) {
                customerList.get(id-1).getCharges().add(record);
                continue;
            };
            Customer cus = new Customer();
            cus.setId(id);
            addedIds.add(Integer.parseInt(customerData.get(i)[0]));
            cus.setName(customerData.get(i)[1]);
            customerList.add(cus);
            customerList.get(id-1).getCharges().add(record);
        }
        System.out.println("Positive accounts:");
        for (Customer customer : customerList) {
            if (customer.getBalance() > 0) System.out.println(customer.getName());
        }
        System.out.println("Negative accounts:");
        for (Customer customer : customerList) {
            if (customer.getBalance() < 0) System.out.println(customer.getName());
        }
    }
}
