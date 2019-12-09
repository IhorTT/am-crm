package net.ukr.tigor.service;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactPerson;
import net.ukr.tigor.domain.Position;
import net.ukr.tigor.dto.AxiliaryThesarus;
import net.ukr.tigor.dto.ClientDto;
import net.ukr.tigor.dto.PersonDto;
import net.ukr.tigor.repos.ClientRepo;
import net.ukr.tigor.repos.ContactPersonRepo;
import net.ukr.tigor.repos.PositionRepo;
import net.ukr.tigor.repos.Related;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ContactPersonService implements Related {
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    PositionRepo positionRepo;
    @Autowired
    ContactPersonRepo contactPersonRepo;
    @Autowired
    ClientService clientService;

    public void findDelFromClient(Client client, Long id) {
        Set<ContactPerson> clientCP = client.getContactPersons();
        for (ContactPerson tmpCP : clientCP) {
            if (tmpCP.getId() == id) {
                clientCP.remove(tmpCP);
                break;
            }
        }
    }

    public void createUpdate(Client client, Long id, Map<String, String> formData, Position position, Date birthDate) {
        String firstName = formData.get("firstName");
        String lastName = formData.get("lastName");
        String patronymic = formData.get("patronymic");
        String phone = formData.get("phone");
        String email = formData.get("email");

        Set<ContactPerson> clientCP = client.getContactPersons();
        ContactPerson editCP = new ContactPerson(firstName, lastName, patronymic, birthDate, position, phone, email, client, 0l);

        if (id == null) {
            clientCP.add(editCP);
        } else {
            findDelFromClient(client, id);

            editCP.setId(id);
            clientCP.add(editCP);
        }
        clientRepo.save(client);
    }

    public void delete(Client client, Long id) {
        findDelFromClient(client, id);

        try {
            clientRepo.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDateStr(ContactPerson person) {
        String dateStr = "";

        if (person.getBirthDate() != null) {
            dateStr = person.getBirthDate().toString().substring(0, 10);
        }
        return dateStr;
    }

    public PersonDto getPersonDto(ContactPerson person, ClientDto clientDto) {
        PersonDto personDto = new PersonDto();
        if (person != null) {
            personDto.setId(person.getId().intValue());
            personDto.setName(person.getFullName());
            personDto.setFirstName(person.getFirstName());
            personDto.setLastName(person.getLastName());
            personDto.setPatronymic(person.getPatronymic());
            personDto.setBirthDate(getDateStr(person));
            personDto.setEmail(person.getEmail());
            personDto.setPhone(person.getPhone());
            if (person.getPosition() != null) {
                personDto.setPosition(new AxiliaryThesarus(person.getPosition().getName(),
                        person.getPosition().getId().intValue()));
            }
        }
        if (clientDto != null) {
            personDto.setClient(clientDto);
        }

        return personDto;

    }

    public Set<PersonDto> getListDto(String filter) {
        Set<PersonDto> personsDto = new LinkedHashSet<>();
        Iterable<ContactPerson> persons;
        if (filter.isEmpty()) {

            persons = contactPersonRepo.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
        } else {
            persons = contactPersonRepo.findByEmailContainsOrPhoneContainsOrFullNameContainsOrderByFullName(filter, filter, filter);
        }
        for (ContactPerson person : persons) {
            personsDto.add(getPersonDto(person, clientService.getSimpleClientDto(person.getClient(), false)));
        }
        return personsDto;
    }

    @Override
    public boolean checkRelated(Long id, String nameThesaurus) {
        return contactPersonRepo.findByPositionId(id).size() == 0;
    }
}
