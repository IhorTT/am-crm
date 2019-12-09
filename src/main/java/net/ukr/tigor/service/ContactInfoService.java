package net.ukr.tigor.service;

import net.ukr.tigor.domain.Client;
import net.ukr.tigor.domain.ContactInformation;
import net.ukr.tigor.domain.Enums.TypeOfContactInformation;
import net.ukr.tigor.dto.ContactInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class ContactInfoService {

    @Autowired
    ClientService clientService;

    public void findDelCiFromClient(Client client, Long id) {
        Set<ContactInformation> clientCI = client.getContactInformation();
        for (ContactInformation tmpCI : clientCI) {
            if (tmpCI.getId() == id) {
                clientCI.remove(tmpCI);
                break;
            }
        }
    }

    public void createUpdate(Long id, Map<String, String> formData, Client client,
                             String details, TypeOfContactInformation type) {

        Set<ContactInformation> clientCI = client.getContactInformation();
        ContactInformation editCI = new ContactInformation(type, details, client);

        if (id == 0) {
            clientCI.add(editCI);
        } else {
            editCI.setId(id);
            findDelCiFromClient(client, id);
            clientCI.add(editCI);
        }
        clientService.saveClient(client);
    }

    public void delete(Client client, Long id) {
        findDelCiFromClient(client, id);
        clientService.saveClient(client);
    }

    public ContactInformationDto getContactInfoDto(ContactInformation contInfo) {
        ContactInformationDto ci = new ContactInformationDto();
        if (contInfo != null) {
            ci.setId(contInfo.getId().toString());
            ci.setTypeCI(contInfo.getTypeCI().label);
            ci.setDetails(contInfo.getDetails());
        } else {
            ci.setId("0");
            ci.setTypeCI(TypeOfContactInformation.PHONE.label);
            ci.setDetails("");
        }

        return ci;
    }

}
