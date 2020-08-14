package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.ServiceCommand;
import com.datacity.lightgroup.services.ImageService;
import com.datacity.lightgroup.services.ServiceService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ServiceController {

    private final ServiceService serviceService;
    private final ImageService imageService;

    public ServiceController(ServiceService serviceService, ImageService imageService) {
        this.serviceService = serviceService;
        this.imageService = imageService;
    }

    @GetMapping("/admin/service/all")
    public String getAllServices(
            Model model
    ) {
        model.addAttribute( "services", serviceService.getAllServices() );
        return "admin/services";
    }

    @GetMapping("/admin/service")
    public String getPortfolioById(
            @RequestParam("id") String id,
            @RequestParam("opp") String opp,
            Model model
    ) {
        try {
            String FORM_MODAL_URL = "/admin/service/formService";
            String DELETE_MODAL_URL = "/admin/service/deleteService";
            switch (opp) {
                case "delete":
                    model.addAttribute( "service", serviceService.findServiceById( Long.valueOf( id ) ) );
                    return DELETE_MODAL_URL;
                default:
                    model.addAttribute( "service", new ServiceCommand() );
                    return FORM_MODAL_URL;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin/service/all";
        }
    }

    @PostMapping("/admin/service")
    public String saveService(
            @RequestParam("img") MultipartFile file,
            @RequestParam("title") String title
    ) {
        ServiceCommand sc = new ServiceCommand();
        sc.setImg( imageService.getBytesFromSavedImage( file ) );
        sc.setTitle( title );
        serviceService.saveService( sc );
        return "redirect:/admin/service/all";
    }

    @PostMapping("/admin/service/delete")
    public String deleteService(
            @RequestParam("id") Long id
    ) {
        serviceService.deleteBusinessType( id );
        return "redirect:/admin/service/all";
    }

    @GetMapping("image/{id}/service")
    public void renderServiceImages(
            @PathVariable String id,
            HttpServletResponse response
    ) throws NotFoundException {
        ServiceCommand serviceCommand = serviceService.findServiceById( Long.valueOf( id ) );
        imageService.imageRenderer( response, serviceCommand.getImg() );
    }

}
