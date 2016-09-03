package com.fobsolutions.security.controllers;

import com.fobsolutions.security.service.DonationService;
import com.fobsolutions.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FOB Solution
 */

@Controller
public class DonateController {

    @Autowired
    private DonationService service;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/donate")
    public String donatePage(
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        return "blog/donate";
    }

    @PostMapping("/donate")
    public String addDonation(
            Model model,
            HttpServletRequest request,
            @RequestParam(value = "success", required = true) String success,
            @RequestParam(value = "amount", required = true) String amount,
            @RequestParam(value = "name", required = true) String name
    ) {
        boolean isSuccess = Boolean.valueOf(success);
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        if (isSuccess) {
            model.addAttribute("name", name);
            model.addAttribute("amount", amount);
            service.addDonation(name, amount);
            model.addAttribute("donations", service.getAllDonations());
            return "blog/thanks";
        } else {
            return "blog/donate";
        }
    }

    @PostMapping("/check")
    public String checkDonation(
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name,
            @RequestParam(value = "amount", required = true) String amount,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        model.addAttribute("name", name);
        model.addAttribute("amount", amount);
        return "blog/check";
    }
}
