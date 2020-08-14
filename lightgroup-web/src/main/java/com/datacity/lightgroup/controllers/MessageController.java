package com.datacity.lightgroup.controllers;

import com.datacity.lightgroup.commands.MessageCommand;
import com.datacity.lightgroup.commands.PortfolioCommand;
import com.datacity.lightgroup.services.BusinessTypeService;
import com.datacity.lightgroup.services.MessageService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MessageController {

    private final MessageService messageService;
    private final BusinessTypeService businessTypeService;

    public MessageController(MessageService messageService, BusinessTypeService businessTypeService) {
        this.messageService = messageService;
        this.businessTypeService = businessTypeService;
    }

    @GetMapping("/admin/messages/{which}")
    public String getAllMessage(
            @PathVariable("which") String which,
            Model model
    ) {
        switch (which) {
            case "all":
                System.out.println( "all" );
                model.addAttribute( "messages", messageService.getAllMessage( 3 ) );
                return "admin/messages";
            case "unread":
                System.out.println( "unread" );
                model.addAttribute( "messages", messageService.getAllMessage( 2 ) );
                return "admin/messages";
            default:
                System.out.println( "new" );
                model.addAttribute( "messages", messageService.getAllMessage( 1 ) );
                return "admin/messages";
        }
    }


    @GetMapping("/admin/message")
    public String getMessageById(
            @RequestParam("id") String id,
            @RequestParam("opp") String opp,
            Model model
    ) {
        String UPDATE_MODAL_URL = "/admin/message/oneMessage";
        String DELETE_MODAL_URL = "/admin/message/deleteMessage";
        switch (opp) {
            case "delete":
                model.addAttribute( "message", messageService.findMessageById( Long.valueOf( id ) ) );
                return DELETE_MODAL_URL;
            default:
                model.addAttribute( "message", messageService.findMessageById( Long.valueOf( id ) ) );
                return UPDATE_MODAL_URL;
        }
    }

    @PostMapping("/admin/message/delete")
    public String deleteMessage(
            @RequestParam("id") String id
    ) {
        messageService.deleteById( id );
        return "redirect:/admin/messages/all";
    }

    @PostMapping("/message/send")
    public String sendMessage(
            @Valid @ModelAttribute("message") MessageCommand messageCommand,
            @RequestParam("bt") Long bt
    ) throws NotFoundException {
        messageCommand.setBusinessTypeCommand( businessTypeService.findBusinessTypeById( bt ) );
        messageService.sendMessage( messageCommand );
        return "redirect:/";
    }

}
