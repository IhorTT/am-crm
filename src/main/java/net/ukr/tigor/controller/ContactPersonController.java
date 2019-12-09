package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactPerson;
import net.ukr.tigor.domain.Position;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.dto.PersonDto;
import net.ukr.tigor.repos.ContactPersonRepo;
import net.ukr.tigor.repos.PositionRepo;
import net.ukr.tigor.service.ClientService;
import net.ukr.tigor.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/persons")
public class ContactPersonController {
    @Autowired
    ClientService clientService;
    @Autowired
    ContactPersonRepo contactPersonRepo;
    @Autowired
    ContactPersonService contactPersonService;
    @Autowired
    PositionRepo positionRepo;

    @GetMapping("/{client_id}/editCP/{id}")
    public String get(@PathVariable("client_id") Client client, @PathVariable("id") ContactPerson contactPerson,
                      @RequestParam("page") String page, Model model) {
        PersonDto personDto = contactPersonService.getPersonDto(contactPerson, clientService.getSimpleClientDto(client, false));
        model.addAttribute("person", personDto);
        model.addAttribute("positions", positionRepo.findAll());
        model.addAttribute("page", page);
        return "/edit/contactPersonEdit";
    }

    @PostMapping("/editCP")
    public String edit(@RequestParam Map<String, String> formData,
                       @RequestParam(required = false, value = "") String btn,
                       @RequestParam(required = false) Long id,
                       @RequestParam(name = "client_id", required = false) Client client,
                       @RequestParam(name = "positionId", required = false) Position position,
                       @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate,
                       @RequestParam(name = "page", required = false) String page
    ) {

        if (btn.equals("btnOk")) {
            contactPersonService.createUpdate(client, id, formData, position, birthDate);
        }

        if (page.equals("persons")) {
            return "redirect:/persons";
        } else {
            return "redirect:/client/" + client.getId() + "?tabCl=cp";
        }

    }

    @GetMapping(path = "/{client_id}/delCP/{id}")
    public String del(@PathVariable Long id, @PathVariable("client_id") Client client,
                      @RequestParam("page") String page) {
        contactPersonService.delete(client, id);
        if (page.equals("persons")) {
            return "redirect:/persons";
        } else {
            return "redirect:/client/" + client.getId() + "?tabCl=cp";
        }
    }

    @GetMapping
    public String getList(@AuthenticationPrincipal User user,
                          @RequestParam(required = false, defaultValue = "") String filter,
                          @RequestParam(required = false, defaultValue = "") String btn, Model model) {
        if (btn.equals("btnCancel")) {
            filter = "";
        }
        Set<PersonDto> persons = contactPersonService.getListDto(filter);
        Set<ClientDto> clients = clientService.getList(true, "", user);

        model.addAttribute("persons", persons);
        model.addAttribute("clients", clients);
        model.addAttribute("filter", filter);
        return "/lists/сontactList";
    }

    @PostMapping("/new")
    public String newPersonFromList(@RequestParam String client_id) {
        return "redirect:/persons/" + client_id + "/editCP/0" + "?page=persons";
    }
}
