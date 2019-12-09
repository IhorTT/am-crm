package net.ukr.tigor.service;

import net.ukr.tigor.domain.*;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.dto.ContactInformationDto;
import net.ukr.tigor.dto.ParkDto;
import net.ukr.tigor.dto.PersonDto;
import net.ukr.tigor.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ContactPersonService contactPersonService;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UniParkService parkService;
    @Autowired
    private UserService userService;

    public Client createUpdate(Long id, Map<String, String> formData) {

        String name = formData.get("name");
        String edpnou = formData.get("edpnou");
        String itn = formData.get("itn");
        User user = userService.getUserById(getLongFromString(formData.get("managerId")));

        Client client = new Client(name, edpnou, itn);
        client.setManager(user);
        //create
        if (id == 0) {
            clientRepo.save(client);
            //update
        } else {
            Optional<Client> clientFromBd = clientRepo.findById(id);
            if (clientFromBd.isPresent()) {
                clientFromBd.get().setHeadProperties(client);
                clientRepo.save(clientFromBd.get());
                client = clientFromBd.get();
            }
        }
        return client;
    }

    private Long getLongFromString(String str) {
        if (str == null || str.isEmpty())
            return 0l;
        else
            return Long.valueOf(str);
    }

    public Set<ClientDto> getList(boolean isSimpleList, String filter, User user) {
        Iterable<Client> all;
        if (filter.isEmpty()) {
            if (user.isOwnObjects())
                all = clientRepo.findByManagerNullOrManagerEquals(user, Sort.by(Sort.Direction.ASC, "name"));
            else
                all = clientRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        } else {
            if (user.isOwnObjects())
                all = clientRepo.findByNameContainsOrEdpnouContainsOrItnContainsAndManagerNullAndManagerEqualsOrderByName(filter, filter, filter, user);
            else
                all = clientRepo.findByNameContainsOrEdpnouContainsOrItnContainsOrderByName(filter, filter, filter);
        }

        Set<ClientDto> clientsDto = getClientsDto(all, isSimpleList, user);
        return clientsDto;
    }

    private Set<ClientDto> getClientsDto(Iterable<Client> clients, boolean isSimpleList, User user) {
        Set<ClientDto> clientsDto = new LinkedHashSet<>();
        if (isSimpleList)
            for (Client client : clients) {
                clientsDto.add(getSimpleClientDto(client, false));
            }
        else
            for (Client client : clients) {
                clientsDto.add(getClientDto(client, user));
            }
        return clientsDto;
    }

    public ClientDto getSimpleClientDto(Client client, boolean needsPersons) {
        ClientDto clientDto = new ClientDto();
        String name = "";
        int id = 0;
        if (client != null) {
            name = client.getName();
            id = client.getId().intValue();
        }
        clientDto.setName(name);
        clientDto.setId(id);
        if (needsPersons) {
            Set<PersonDto> persons = new HashSet<PersonDto>();
            for (ContactPerson contactPerson : client.getContactPersons()) {
                persons.add(contactPersonService.getPersonDto(contactPerson, clientDto));
            }
            clientDto.setPersons(persons);
        }
        return clientDto;
    }

    private void fillSets(Client client, ClientDto clientDto, User user) {

        if (client!=null) {
            Set<PersonDto> persons = new LinkedHashSet<PersonDto>();
            for (ContactPerson contactPerson : client.getContactPersons()) {
                persons.add(contactPersonService.getPersonDto(contactPerson, clientDto));
            }
            clientDto.setPersons(persons);

            Set<ParkDto> equipments = new LinkedHashSet<ParkDto>();
            for (EquipmentPark eqPark : client.getEquipmentPark()) {
                equipments.add(parkService.getEquipmentDto(eqPark));
            }
            clientDto.setEquipmentPark(equipments);

            Set<ParkDto> requirementPark = new LinkedHashSet<ParkDto>();
            for (RequirementPark reqPark : client.getRequirementPark()) {
                requirementPark.add(parkService.getRequirementDto(reqPark));
            }
            clientDto.setRequirementPark(requirementPark);

            Set<ContactInformationDto> contInfoDto = new LinkedHashSet<ContactInformationDto>();
            for (ContactInformation contInfo : client.getContactInformation()) {
                contInfoDto.add(contactInfoService.getContactInfoDto(contInfo));
            }
            clientDto.setContactInformation(contInfoDto);
        }
        // события
        Map<String, Object> selection = new HashMap<>();
        selection.put("client", client);
        if (user != null && !user.isAdmin())
            selection.put("manager", user);
        clientDto.setEvents(eventService.getListDto(selection, null));

    }

    public ClientDto getClientDto(Client client, User user) {

        ClientDto clientDto = new ClientDto();
        if (client != null) {
            clientDto.setId(client.getId().intValue());
            clientDto.setName(client.getName());
            clientDto.setEdpnou(client.getEdpnou());
            clientDto.setItn(client.getItn());
            clientDto.setManager(userService.getManagerDto(client.getManager()));
        } else {
            clientDto.setManager(userService.getManagerDto(user));
        }
        fillSets(client, clientDto, user);
        return clientDto;
    }

    public void saveClient(Client client) {
        clientRepo.save(client);
    }

    public Integer getCountByUser(User user) {
        return clientRepo.countAllByManager(user);
    }
}
