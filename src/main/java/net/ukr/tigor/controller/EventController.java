package net.ukr.tigor.controller;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactPerson;
import net.ukr.tigor.domain.Enums.SortOfEvent;
import net.ukr.tigor.domain.Enums.StateOfEvent;
import net.ukr.tigor.domain.Enums.TypeOfEvent;
import net.ukr.tigor.domain.Event;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.dto.EventDto;
import net.ukr.tigor.dto.ManagerDto;
import net.ukr.tigor.service.ClientService;
import net.ukr.tigor.service.EventService;
import net.ukr.tigor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/events")
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    ClientService clientService;
    @Autowired
    UserService userService;

    @GetMapping("/{client_id}/editEvent/{id}")
    public String get(@PathVariable("client_id") Client client, @PathVariable("id") Event event,
                      @AuthenticationPrincipal User user, @RequestParam("page") String page, Model model) {

        ClientDto clientDto = clientService.getSimpleClientDto(client, true);
        EventDto eventDto = eventService.getEventDto(event, clientDto, user);

        model.addAttribute("types", TypeOfEvent.values());
        model.addAttribute("states", StateOfEvent.values());
        model.addAttribute("event", eventDto);
        model.addAttribute("managers", userService.getUsersDto());
        model.addAttribute("page", page);

        return "/edit/eventEdit";
    }

    @PostMapping("/editEvent")
    public String edit(@RequestParam(required = false, value = "") String btn,
                       @RequestParam(name = "id") Event event,
                       @RequestParam(name = "client_id", required = false) Client client,
                       @RequestParam(name = "personId", required = false) ContactPerson person,
                       @RequestParam(name = "managerId", required = false) User manager,
                       @RequestParam(name = "number") Integer number,
                       @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                       @RequestParam("dateStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart,
                       @RequestParam("dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateEnd,
                       @RequestParam(name = "type", required = false) TypeOfEvent type,
                       @RequestParam(name = "state", required = false) StateOfEvent state,
                       @RequestParam(name = "subject", required = false) String subject,
                       @RequestParam(name = "content", required = false) String content,
                       @RequestParam(name = "page", required = false) String page
    ) {

        if (btn.equals("btnOk")) {
            eventService.createUpdate(event, client, person, number, date, dateStart, dateEnd, type, state, subject, content, manager);
        }
        if (page.equals("events")) {
            return "redirect:/events";
        } else {
            return "redirect:/client/" + client.getId() + "?tabCl=events";
        }

    }

    @GetMapping(path = "/{client_id}/delEvent/{id}")
    public String del(@PathVariable Long id, @PathVariable("client_id") Client client,
                      @RequestParam("page") String page) {
        eventService.delete(client, id);
        if (page.equals("events")) {
            return "redirect:/events";
        } else {
            return "redirect:/client/" + client.getId() + "?tabCl=events";
        }
    }


    @GetMapping()
    public String getList(@AuthenticationPrincipal User user, Model model,
                          @RequestParam(name = "clientId", required = false) Client selectedClient,
                          @RequestParam(name = "type", required = false) TypeOfEvent selectedType,
                          @RequestParam(name = "state", required = false) StateOfEvent selectedState,
                          @RequestParam(name = "managerId", required = false) User selectedManager,
                          @RequestParam(name = "sort", required = false) SortOfEvent selectedSort) {

        Map<String, Object> filter = new HashMap<>();
        filter.put("client", selectedClient);
        filter.put("type", selectedType);
        filter.put("state", selectedState);

        if (user.isOwnObjects()) {
            filter.put("manager", user);
            selectedManager = user;
        } else {
            filter.put("manager", selectedManager);
        }

        Set<EventDto> events = eventService.getListDto(filter, selectedSort);
        Set<ClientDto> clients = clientService.getList(true, "", user);
        ClientDto selectedClientDto = clientService.getSimpleClientDto(selectedClient, false);
        ManagerDto selectedManagerDto = userService.getManagerDto(selectedManager);

        model.addAttribute("events", events);
        model.addAttribute("clients", clients);
        model.addAttribute("types", TypeOfEvent.values());
        model.addAttribute("states", StateOfEvent.values());
        model.addAttribute("sort", SortOfEvent.values());
        model.addAttribute("managers", userService.getUsersDto());

        model.addAttribute("selectedType", selectedType == null ? "" : selectedType);
        model.addAttribute("selectedState", selectedState == null ? "" : selectedState);
        model.addAttribute("selectedSort", selectedSort == null ? "" : selectedSort);
        model.addAttribute("selectedClient", selectedClientDto);
        model.addAttribute("selectedManager", selectedManagerDto);

        return "/lists/eventList";

    }

    @PostMapping("/new")
    public String newPersonFromList(@RequestParam String client_id) {
        return "redirect:/events/" + client_id + "/editEvent/0" + "?page=events";
    }
}
