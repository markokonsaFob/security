package com.fobsolutions.security.dao;

import com.fobsolutions.security.models.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by FOB Solutions
 */
public interface DonationRepository extends MongoRepository<Donation, String> {
}
