package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.BusinessTypeCommand;
import com.datacity.lightgroup.services.BusinessTypeService;
import com.datacity.lightgroup.services.HeaderService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class BusinessTypeController {

    private final BusinessTypeService businessTypeService;
    private final HeaderService headerService;

    public BusinessTypeController(BusinessTypeService businessTypeService, HeaderService headerService) {
        this.businessTypeService = businessTypeService;
        this.headerService = headerService;
    }

    @GetMapping("/admin/texts")
    public String getTextPage(
            Model model
    ) {
        model.addAttribute( "businessTypeList", businessTypeService.getAllBusinessType() );
        model.addAttribute( "headerText", headerService.getData() );
        return "admin/blog";
    }

    @GetMapping("/admin/business")
    public String getBusinessTypeById(
            @RequestParam("id") String id,
            @RequestParam("opp") String opp,
            Model model
    ) {
        try {
            String FORM_URL = "/admin/business/formBusiness";
            String DELETE_MODAL_URL = "/admin/business/deleteBusiness";
            switch (opp) {
                case "delete":
                    model.addAttribute( "businessType", businessTypeService.findBusinessTypeById( Long.valueOf( id ) ) );
                    return DELETE_MODAL_URL;
                case "update":
                    model.addAttribute( "businessType", businessTypeService.findBusinessTypeById( Long.valueOf( id ) ) );
                    return FORM_URL;
                default:
                    model.addAttribute( "businessType", new BusinessTypeCommand() );
                    return FORM_URL;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "redirect/admin/texts";
        }
    }

    @PostMapping("/admin/business/delete")
    public String deleteBusinessTypeById(
            @RequestParam("id") Long id
    ) {
        businessTypeService.deleteBusinessType( id );
        return "redirect:/admin/texts";
    }

    @PostMapping("/admin/business")
    public String addOrUpdateBusinessType(
            @Valid @ModelAttribute("businessType") BusinessTypeCommand businessTypeCommand,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println( error.getDefaultMessage() );
            }
            return "redirect:/admin/texts";
        }
        businessTypeService.saveBusinessType( businessTypeCommand );
        return "redirect:/admin/texts";
    }

}
