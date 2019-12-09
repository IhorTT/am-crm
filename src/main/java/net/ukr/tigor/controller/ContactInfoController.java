package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactInformation;
import net.ukr.tigor.domain.Enums.TypeOfContactInformation;
import net.ukr.tigor.dto.ContactInformationDto;
import net.ukr.tigor.repos.ClientRepo;
import net.ukr.tigor.repos.ContactInfoRepo;
import net.ukr.tigor.repos.ContactPersonRepo;
import net.ukr.tigor.repos.PositionRepo;
import net.ukr.tigor.service.ClientService;
import net.ukr.tigor.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/client")
public class ContactInfoController {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ContactInfoRepo contaktInfoRepo;
    @Autowired
    ContactPersonRepo contactPersonRepo;
    @Autowired
    ClientService clientService;
    @Autowired
    ContactInfoService contactInfoService;
    @Autowired
    PositionRepo positionRepo;

    @GetMapping("/{client_id}/editCI/{id}")
    public String get(@PathVariable("client_id") Client client,
                      @PathVariable("id") ContactInformation contactInformation, Model model
    ) {
        ContactInformationDto contactInfoDto = contactInfoService.getContactInfoDto(contactInformation);

        model.addAttribute("continfo", contactInfoDto);
        model.addAttribute("ci_types", TypeOfContactInformation.values());
        model.addAttribute("client", clientService.getSimpleClientDto(client,false));
        return "/edit/contactInfoEdit";
    }

    @PostMapping("/editCI")
    public String edit(@RequestParam Map<String, String> formData,
                       @RequestParam(required = false, defaultValue = "") String btn,
                       @RequestParam(required = false) Long id,
                       @RequestParam("client_id") Client client,
                       @RequestParam("type") TypeOfContactInformation type,
                       @RequestParam String details
    ) {

        if (btn.equals("btnOk")) {
            contactInfoService.createUpdate(id, formData, client, details, type);
        }
        return "redirect:/client/" + client.getId() + "?tabCl=ci";
    }

    @GetMapping(path = "/{client_id}/delCI/{id}")
    public String del(@PathVariable Long id, @PathVariable("client_id") Client client) {
        contactInfoService.delete(client, id);
        return "redirect:/client/" + client.getId() + "?tabCl=ci";
    }

}
