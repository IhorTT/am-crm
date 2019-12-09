package net.ukr.tigor.service;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactPerson;
import net.ukr.tigor.domain.Enums.SortOfEvent;
import net.ukr.tigor.domain.Enums.StateOfEvent;
import net.ukr.tigor.domain.Enums.TypeOfEvent;
import net.ukr.tigor.domain.Event;
import net.ukr.tigor.domain.User;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.dto.DateCreationDto;
import net.ukr.tigor.dto.EventDto;
import net.ukr.tigor.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class EventService {

    @Autowired
    ClientService clientService;
    @Autowired
    ContactPersonService contactPersonService;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    UserService userService;

    public EventDto getEventDto(Event event, ClientDto clientDto, User user) {

        EventDto eventDto = new EventDto();

        if (event != null) {
            eventDto.setId(event.getId().intValue());
            eventDto.setContent(event.getContent());
            eventDto.setDate(new DateCreationDto(event.getDate()));
            eventDto.setDateStart(new DateCreationDto(event.getDateStart()));
            eventDto.setDateEnd(new DateCreationDto(event.getDateEnd()));
            eventDto.setNumber(event.getNumber());
            eventDto.setSubject(event.getSubject());
            eventDto.setType(event.getType().label);
            eventDto.setState(event.getState().label);
            eventDto.setPerson(contactPersonService.getPersonDto(event.getPerson(), clientDto));
            eventDto.setManager(userService.getManagerDto(event.getManager()));
        } else {
            eventDto.setNumber(getNextNumber());
            eventDto.setPerson(contactPersonService.getPersonDto(null, null));
            eventDto.setManager(userService.getManagerDto(user));
        }

        eventDto.setClient(clientDto);
        return eventDto;
    }

    private Integer getNextNumber() {
        Integer result = eventRepo.getMuxNumber();
        if (result == null) {
            result = 0;
        }
        return result + 1;
    }

    public Integer getCountByUser(User user) {
        return eventRepo.countAllByManager(user);
    }

    public void createUpdate(Event event, Client client, ContactPerson person, Integer number,
                             LocalDateTime date, LocalDateTime dateStart, LocalDateTime dateEnd,
                             TypeOfEvent type, StateOfEvent state, String subject,
                             String content, User manager) {

        if (event == null) {
            event = new Event();

        }
        event.setManager(manager);
        event.setClient(client);
        event.setPerson(person);
        event.setNumber(number);
        event.setDate(date);
        event.setDateStart(dateStart);
        event.setDateEnd(dateEnd);
        event.setType(type);
        event.setState(state);
        event.setSubject(subject);
        event.setContent(content);
        eventRepo.save(event);
    }

    public void findDelCiFromClient(Client client, Long id) {
        Set<Event> clientEvents = client.getEvents();
        for (Event event : clientEvents) {
            if (event.getId() == id) {
                clientEvents.remove(event);
                break;
            }
        }
    }

    public void delete(Client client, Long id) {
        findDelCiFromClient(client, id);
        clientService.saveClient(client);
    }

    public Set<EventDto> getListDto(Map<String, Object> selected, SortOfEvent sortEnum) {
        Set<EventDto> eventsDto = new LinkedHashSet<>();
        Sort sort;
        if (sortEnum == null) {
            sort = Sort.by(Sort.Direction.ASC, "date");
        } else {
            sort = Sort.by(sortEnum.direction, sortEnum.field);
        }
        Iterable<Event> events = eventRepo.findAll(Specification.where(EventsSpecifications.getSpecifications(selected)), sort);

        for (Event event : events) {
            ClientDto clientDto = clientService.getSimpleClientDto(event.getClient(), false);
            eventsDto.add(getEventDto(event, clientDto, event.getManager()));
        }
        return eventsDto;
    }

}
