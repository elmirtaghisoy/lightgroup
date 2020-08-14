package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.services.ImageService;
import com.datacity.lightgroup.services.PortfolioService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final ImageService imageService;

    public PortfolioController(PortfolioService portfolioService, ImageService imageService) {
        this.portfolioService = portfolioService;
        this.imageService = imageService;
    }

    @GetMapping("/admin/portfolio/all")
    public String getPortfolioPage(
            Model model
    ) {
        model.addAttribute( "portfolios", portfolioService.getAllPortfolios() );
        return "admin/portfolio";
    }

    @GetMapping("/admin/portfolio")
    public String getPortfolioById(
            @RequestParam("id") String id,
            @RequestParam("opp") String opp,
            Model model
    ) {
        try {
            String UPDATE_MODAL_URL = "/admin/portfolio/formPortfolio";
            String DELETE_MODAL_URL = "/admin/portfolio/deletePortfolio";
            switch (opp) {
                case "delete":
                    model.addAttribute( "portfolio", portfolioService.findPortfolioById( Long.valueOf( id ) ) );
                    return DELETE_MODAL_URL;
                case "update":
                    model.addAttribute( "portfolio", portfolioService.findPortfolioById( Long.valueOf( id ) ) );
                    return UPDATE_MODAL_URL;
                default:
                    model.addAttribute( "portfolio", new PortfolioCommand() );
                    return UPDATE_MODAL_URL;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "redirect/admin/portfolio";
        }
    }

    @PostMapping("/admin/portfolio/delete")
    public String deleteBusinessTypeById(
            @RequestParam("id") Long id
    ) {
        portfolioService.deletePortfolio( id );
        return "redirect:/admin/portfolio/all";
    }

    @PostMapping("/admin/portfolio")
    public String addOrUpdatePortfolio(
            @RequestParam(value = "id", defaultValue = "0") String id,
            @RequestParam("img") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) throws NotFoundException {
        PortfolioCommand pc = new PortfolioCommand();
        fileOperationUtil( id, file, pc );
        pc.setId( Long.valueOf( id ) );
        pc.setDescription( description );
        pc.setTitle( title );
        portfolioService.savePortfolio( pc );
        return "redirect:/admin/portfolio/all";
    }

    private void fileOperationUtil(@RequestParam("id") String id, @RequestParam("img") MultipartFile file, PortfolioCommand pc) throws NotFoundException {
        if (file.getOriginalFilename().isEmpty() && Long.valueOf( id ) != 0) {
            pc.setImg( portfolioService.findPortfolioById( Long.valueOf( id ) ).getImg() );
        } else {
            pc.setImg( imageService.getBytesFromSavedImage( file ) );
        }
    }

    @GetMapping("image/{id}/portfolio")
    public void renderPortfolioImages(
            @PathVariable String id,
            HttpServletResponse response
    ) throws NotFoundException {
        PortfolioCommand portfolioCommand = portfolioService.findPortfolioById( Long.valueOf( id ) );
        imageService.imageRenderer( response, portfolioCommand.getImg() );
    }
}
