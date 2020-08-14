package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.HeaderCommand;
import com.datacity.lightgroup.services.HeaderService;
import com.datacity.lightgroup.services.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HeaderController {

    private final HeaderService headerService;
    private final ImageService imageService;

    public HeaderController(HeaderService headerService, ImageService imageService) {
        this.headerService = headerService;
        this.imageService = imageService;
    }

    @PostMapping("/admin/header/update")
    public String updateHeader(
            @RequestParam("title") String title
    ) {
        headerService.updateHeader( title );
        return "redirect:/admin/texts";
    }

    @PostMapping("/admin/about/update")
    public String updateAboutUs(
            @RequestParam("aboutUs") String aboutUs
    ) {
        headerService.updateAboutUs( aboutUs );
        return "redirect:/admin/texts";
    }

    @GetMapping("/admin/static/images")
    public String getAllPhotosPage() {
        return "admin/photos";
    }

    @PostMapping("admin/save/static/image")
    public String saveHeaderImages(
            @RequestParam("file") MultipartFile file,
            @RequestParam("whichImage") int whichImage

    ) {
        imageService.saveHeaderImages( file, whichImage );
        return "redirect:/admin/static/images";
    }

    @GetMapping("image/{id}/header")
    public void renderHeaderImage(
            @PathVariable String id,
            HttpServletResponse response
    ) {
        HeaderCommand headerCommand = headerService.findCommandById( 1L );
        if (Integer.valueOf( id ) == 1) {
            imageService.imageRenderer( response, headerCommand.getLeftImg() );
        } else if ((Integer.valueOf( id ) == 2)) {
            imageService.imageRenderer( response, headerCommand.getRightImg() );
        }
    }

    // display exception & succes messages
    // sort messages
    // global logging
    // liquibase migration

}
