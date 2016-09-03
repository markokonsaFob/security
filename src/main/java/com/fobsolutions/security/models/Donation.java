package com.fobsolutions.security.models;

import org.springframework.data.annotation.Id;

/**
 * Created by FOB Solutions
 */
public class Donation {

    @Id
    public String id;
    public String name;
    public String amount;

    public Donation(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
