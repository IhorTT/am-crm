package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.repos.ClientRepo;
import net.ukr.tigor.service.ClientService;
import net.ukr.tigor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    UserService userService;
    @Autowired
    ClientService clientService;

    @GetMapping()
    public String clietnList(@AuthenticationPrincipal User user,
                             @RequestParam(required = false, defaultValue = "") String filter,
                             @RequestParam(required = false, defaultValue = "") String btn, Model model) {
        if (btn.equals("btnCancel")) {
            filter = "";
        }
        model.addAttribute("clients", clientService.getList(false, filter, user));
        model.addAttribute("filter", filter);
        return "/lists/clientList";
    }

    @GetMapping("{id}")
    public String get(@PathVariable("id") Client client, @AuthenticationPrincipal User user,
                      @RequestParam(value = "tabCl", required = false, defaultValue = "main") String tab, Model model) {
        ClientDto clientDto = clientService.getClientDto(client, user);
        model.addAttribute("managers", userService.getUsersDto());
        model.addAttribute("client", clientDto);
        model.addAttribute("tabCl", tab);
        return "/edit/clientEdit";
    }

    @PostMapping(path = "/edit")
    public String edit(@AuthenticationPrincipal User user,
                       @RequestParam(required = false, value = "") String btn,
                       @RequestParam Map<String, String> formData,
                       @RequestParam(required = false) Long id,
                       Model model
    ) {

        String page = "";
        switch (btn) {
            case "btnCancel":
                model.addAttribute("clients", clientService.getList(false, "", user));
                page = "redirect:/client";
                break;
            case "btnOk":
                clientService.createUpdate(id, formData);
                model.addAttribute("clients", clientService.getList(false, "", user));
                page = "redirect:/client";
                break;
            case "btnSave":
                Client client = clientService.createUpdate(id, formData);
                page = "redirect:/client/" + client.getId() + "?tabCl=main";
                break;
        }

        return page;
    }

    @GetMapping(path = "/del/{id}")
    public String del(@PathVariable Long id, Model model) {
        clientRepo.deleteById(id);
        return "redirect:/client";
    }
}
