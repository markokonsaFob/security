package com.fobsolutions.security;

import com.fobsolutions.security.service.DonationService;
import com.fobsolutions.security.service.LoginService;
import com.fobsolutions.security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by FOB Solutions
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private DonationService donationService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        loginService.addAdminUser();
        postService.addFirstPost();
    }
}
