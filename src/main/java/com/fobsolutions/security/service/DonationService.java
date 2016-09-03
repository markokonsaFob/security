package com.fobsolutions.security.service;

import com.fobsolutions.security.dao.DonationRepository;
import com.fobsolutions.security.models.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FOB Solution
 */

@Service
public class DonationService {

    @Autowired
    private DonationRepository repository;


    public Donation addDonation(String name, String amount) {
        if (name.isEmpty() || amount.isEmpty()) {
            return null;
        } else {
            return repository.save(new Donation(name, amount));
        }
    }

    public void reset() {
        repository.deleteAll();
    }

    public List<Donation> getAllDonations() {
        return repository.findAll();
    }
}
