package com.fobsolutions.security.controllers;

import com.fobsolutions.security.service.DonationService;
import com.fobsolutions.security.service.LoginService;
import com.fobsolutions.security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by FOB Solutions
 */

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private PostController postController;

    @RequestMapping("/")
    public String landingPage(Model model, HttpServletRequest request) {
        model.addAttribute("isLoggedIn", loginService.isLoggedIn(request.getCookies()));
        return "index";
    }

    @RequestMapping("/reset")
    public String reset(Model model, HttpServletRequest request, HttpServletResponse response) {
        loginService.reset();
        donationService.reset();
        postService.reset();
        loginService.logout(request.getCookies(), response);

        loginService.addAdminUser();
        postService.addFirstPost();

        return postController.blogMainPage(model, request);
    }


}
