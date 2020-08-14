package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/parameters")
    public String getUserPage() {
        return "admin/parameters";
    }

    @PostMapping("/admin/password")
    public String updatePassword(
            @RequestParam("oldPass") String oldPass,
            @RequestParam("newPass") String newPass,
            @RequestParam("confirmPass") String confirmPass
    ) {
        if (newPass.trim().toLowerCase().equals( confirmPass.trim().toLowerCase() )) {
            userService.updatePassword( oldPass, newPass );
        } else {
            System.out.println( "error" );
        }
        return "admin/parameters";
    }

    @PostMapping("/admin/username")
    public String updateUsername(
            @RequestParam("oldUsername") String oldUsername,
            @RequestParam("newUsername") String newUsername
    ) {
        userService.updateUsername( oldUsername, newUsername );
        return "admin/parameters";
    }

}
