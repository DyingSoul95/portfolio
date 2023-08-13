package ru.kolotovkin.lastproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kolotovkin.lastproject.model.Message;
import ru.kolotovkin.lastproject.model.User;
import ru.kolotovkin.lastproject.service.MessageService;

@Controller
public class MainController {

    private final MessageService messageService;
    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String login(Model model){
        model.addAttribute("messages", messageService.getMassage());
        return ("/messages");
    }

    @GetMapping("/messages")
    public String message(Model model){
        model.addAttribute("messages", messageService.getMassage());
        return ("/messages");
    }

    @PostMapping("/messages")
    public String addMessage(Model model, @AuthenticationPrincipal User user, @RequestParam String message){
        Message msg = new Message(message, user.getUsername());
        messageService.addMassage(msg);
        model.addAttribute("messages", messageService.getMassage());
        return ("/messages");
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model){
        model.addAttribute("messages", messageService.filterMessage(filter));
        return ("/messages");
    }
}
