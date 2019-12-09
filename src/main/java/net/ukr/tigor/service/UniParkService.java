package net.ukr.tigor.service;

import net.ukr.tigor.domain.*;
import net.ukr.tigor.dto.AxiliaryThesarus;
import net.ukr.tigor.dto.DateCreationDto;
import net.ukr.tigor.dto.ParkDto;
import net.ukr.tigor.repos.ClientRepo;
import net.ukr.tigor.repos.EquipmentParkRepo;
import net.ukr.tigor.repos.Related;
import net.ukr.tigor.repos.RequirementParkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class UniParkService implements Related {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    EquipmentParkRepo equipmentParkRepo;
    @Autowired
    RequirementParkRepo requirementParkRepo;

    // common {
    public int getIntFromString(String str) {
        int result = 0;
        if (!str.isEmpty()) {
            result = Integer.parseInt(str.replace(" ", ""));
        }
        return result;
    }
    // common}

    // equipment park {
    public void findDelFromClientEP(Client client, Long id) {
        Set<EquipmentPark> clientsEP = client.getEquipmentPark();
        for (EquipmentPark tmpEP : clientsEP) {
            if (tmpEP.getId() == id) {
                clientsEP.remove(tmpEP);
                break;
            }
        }
    }

    public ParkDto getEquipmentDto(EquipmentPark equipment) {

        ParkDto equipmentDto = new ParkDto();
        if (equipment != null) {
            equipmentDto.setId(equipment.getId().intValue());
            equipmentDto.setModel(equipment.getModel());
            equipmentDto.setSerialNumber(equipment.getModel());
            equipmentDto.setCount(equipment.getCount().toString());
            equipmentDto.setDescription(equipment.getDescription());
            equipmentDto.setYearOfManufacture(equipment.getYearOfManufacture().toString());
            equipmentDto.setDateCreation(new DateCreationDto(equipment.getDateCreation()));
            Manufacturer manufacturer = equipment.getManufacturer();
            if (manufacturer != null) {
                equipmentDto.setManufacturer(new AxiliaryThesarus(manufacturer.getName(), manufacturer.getId().intValue()));
            }
            TypeOfAgriculturalMachinery type = equipment.getTypeOfAgrMach();
            if (type != null) {
                equipmentDto.setTypeOfAgrMach(new AxiliaryThesarus(type.getName(), type.getId().intValue()));
            }
        }
        return equipmentDto;
    }

    public void createUpdate(Client client, Long id, Map<String, String> formData,
                             Manufacturer manufacturer, TypeOfAgriculturalMachinery typeOfAgrMachId, LocalDateTime dateCreation) {
        String model = formData.get("model");
        String serialNumber = formData.get("serialNumber");
        int count = getIntFromString(formData.get("count"));
        int yearOfManufacture = getIntFromString(formData.get("yearOfManufacture"));
        String description = formData.get("description");

        Set<EquipmentPark> clientEP = client.getEquipmentPark();
        EquipmentPark editEP = new EquipmentPark(client, manufacturer, typeOfAgrMachId, model, serialNumber, count, yearOfManufacture, description, dateCreation);

        if (id == 0) {
            clientEP.add(editEP);
        } else {
            findDelFromClientEP(client, id);
            editEP.setId(id);
            clientEP.add(editEP);
        }
        clientRepo.save(client);
    }

    public void delete(Client client, Long id) {
        findDelFromClientEP(client, id);
        clientRepo.save(client);
    }
    // } equipment park

    // { requirement park
    public void findDelFromClientRP(Client client, Long id) {
        Set<RequirementPark> clientsRP = client.getRequirementPark();
        for (RequirementPark tmpRP : clientsRP) {
            if (tmpRP.getId() == id) {
                clientsRP.remove(tmpRP);
                break;
            }
        }
    }

    private String getDateStr(RequirementPark requirement) {
        String dateStr = "";

        if (requirement.getDateBuying() != null) {
            dateStr = requirement.getDateBuying().toString().substring(0, 10);
        }
        return dateStr;
    }

    public ParkDto getRequirementDto(RequirementPark requirement) {
        ParkDto requirementDto = new ParkDto();
        if (requirement != null) {
            requirementDto.setId(requirement.getId().intValue());
            requirementDto.setDateBuying(getDateStr(requirement));
            requirementDto.setCount(requirement.getCount().toString());
            requirementDto.setDescription(requirement.getDescription());
            requirementDto.setDateCreation(new DateCreationDto(requirement.getDateCreation()));
            Manufacturer manufacturer = requirement.getManufacturer();
            if (manufacturer != null) {
                requirementDto.setManufacturer(new AxiliaryThesarus(manufacturer.getName(), manufacturer.getId().intValue()));
            }
            TypeOfAgriculturalMachinery type = requirement.getTypeOfAgrMach();
            if (type != null) {
                requirementDto.setTypeOfAgrMach(new AxiliaryThesarus(type.getName(), type.getId().intValue()));
            }
        }

        return requirementDto;
    }

    public void createUpdateReq(Client client, Long id, Map<String, String> formData,
                                Manufacturer manufacturer, TypeOfAgriculturalMachinery typeOfAgrMachId,
                                Date dateBuying, LocalDateTime dateCreation) {

        int count = getIntFromString(formData.get("count"));
        String description = formData.get("description");

        Set<RequirementPark> clientRP = client.getRequirementPark();
        RequirementPark editRP = new RequirementPark(client, manufacturer, typeOfAgrMachId, count, dateBuying, description, dateCreation);

        if (id == 0) {
            clientRP.add(editRP);
        } else {
            findDelFromClientRP(client, id);
            editRP.setId(id);
            clientRP.add(editRP);
        }
        clientRepo.save(client);
    }

    public void deleteReq(Client client, Long id) {
        findDelFromClientRP(client, id);
        clientRepo.save(client);
    }
    // } requirement park

    @Override
    public boolean checkRelated(Long id, String nameThesaurus) {
        int count = 0;
        if (nameThesaurus.equals("Manufacturer")) {
            count = equipmentParkRepo.countAllByManufacturerId(id)
                    + requirementParkRepo.countAllByManufacturerId(id);
        } else if (nameThesaurus.equals("TypeOfAgriculturalMachinery")) {
            count = equipmentParkRepo.countAllByTypeOfAgrMachId(id)
                    + requirementParkRepo.countAllByTypeOfAgrMachId(id);
        }
        return count == 0;
    }
}
