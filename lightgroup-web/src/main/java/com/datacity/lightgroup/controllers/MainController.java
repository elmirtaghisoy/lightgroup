package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final HeaderService headerService;
    private final ServiceService serviceService;
    private final PortfolioService portfolioService;
    private final BusinessTypeService businessTypeService;

    public MainController(HeaderService headerService, ServiceService serviceService, PortfolioService portfolioService, BusinessTypeService businessTypeService) {
        this.headerService = headerService;
        this.serviceService = serviceService;
        this.portfolioService = portfolioService;
        this.businessTypeService = businessTypeService;
    }

    @GetMapping("/")
    public String getHome(
            Model model
    ) {
        model.addAttribute( "header", headerService.getData() );
        model.addAttribute( "service", serviceService.getAllServices() );
        model.addAttribute( "portfolio", portfolioService.get3Portfolio() );
        model.addAttribute( "businessTypes", businessTypeService.getAllBusinessType() );
        model.addAttribute( "message", new MessageCommand() );
        return "client/index";
    }

    @GetMapping("/portfolio")
    public String getPortfolio(
            Model model
    ) {
        model.addAttribute( "portfolio", portfolioService.getAllPortfolios() );
        return "client/portfolio";
    }

    @GetMapping("/admin/index")
    public String getIndex() {
        return "admin/index";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

}
