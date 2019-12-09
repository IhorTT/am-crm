package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.EquipmentPark;
import net.ukr.tigor.domain.Manufacturer;
import net.ukr.tigor.domain.TypeOfAgriculturalMachinery;
import net.ukr.tigor.dto.ParkDto;
import net.ukr.tigor.repos.ManufacturerRepo;
import net.ukr.tigor.repos.TypeOfAgriculturalMachineryRepo;
import net.ukr.tigor.service.ClientService;
import net.ukr.tigor.service.UniParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping(path = "/client")
public class EquipmentParkController {

    @Autowired
    ClientService clientService;
    @Autowired
    ManufacturerRepo manufacturerRepo;
    @Autowired
    TypeOfAgriculturalMachineryRepo typeOfAgrMachRepo;
    @Autowired
    UniParkService uniParkService;


    @GetMapping("/{client_id}/editEP/{id}")
    public String get(@PathVariable("client_id") Client client, @PathVariable("id") EquipmentPark equipment, Model model) {

        ParkDto equipmentDto = uniParkService.getEquipmentDto(equipment);
        model.addAttribute("client", clientService.getSimpleClientDto(client, true));
        model.addAttribute("equipment", equipmentDto);
        model.addAttribute("manufacturers", manufacturerRepo.findAll());
        model.addAttribute("typesOfAgrMach", typeOfAgrMachRepo.findAll());

        return "/edit/parkEquipmentEdit";
    }

    @PostMapping("/editEP")
    public String edit(@RequestParam Map<String, String> formData,
                       @RequestParam(required = false, value = "") String btn,
                       @RequestParam(required = false) Long id,
                       @RequestParam(name = "client_id", required = false) Client client,
                       @RequestParam(name = "manufacturerId", required = false) Manufacturer manufacturer,
                       @RequestParam(name = "typeOfAgrMachId", required = false) TypeOfAgriculturalMachinery typeOfAgrMach,
                       @RequestParam("dateCreation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreation
    ) {

        if (btn.equals("btnOk")) {
            uniParkService.createUpdate(client, id, formData, manufacturer, typeOfAgrMach, dateCreation);
        }
        return "redirect:/client/" + client.getId() + "?tabCl=ep";
    }

    @GetMapping(path = "/{client_id}/delEP/{id}")
    public String del(@PathVariable Long id, @PathVariable("client_id") Client client, Model model) {
        uniParkService.delete(client, id);
        return "redirect:/client/" + client.getId() + "?tabCl=ep";
    }
}
