package net.ukr.tigor.dto;

import java.util.Objects;
import java.util.Set;

public class ClientDto {

    private String name = "";
    private int id = 0;
    private String edpnou = "";
    private String itn = "";
    private ManagerDto manager;
    private Set<PersonDto> persons;
    private Set<ContactInformationDto> contactInformation;
    private Set<ParkDto> equipmentPark;
    private Set<ParkDto> requirementPark;
    private Set<EventDto> events;

    public ClientDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdpnou() {
        return edpnou;
    }

    public void setEdpnou(String edpnou) {
        this.edpnou = edpnou;
    }

    public String getItn() {
        return itn;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    public Set<ContactInformationDto> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Set<ContactInformationDto> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<ParkDto> getEquipmentPark() {
        return equipmentPark;
    }

    public void setEquipmentPark(Set<ParkDto> equipmentPark) {
        this.equipmentPark = equipmentPark;
    }

    public Set<ParkDto> getRequirementPark() {
        return requirementPark;
    }

    public void setRequirementPark(Set<ParkDto> requirementPark) {
        this.requirementPark = requirementPark;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDto> events) {
        this.events = events;
    }

    public Set<PersonDto> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonDto> persons) {
        this.persons = persons;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDto)) return false;
        ClientDto clientDto = (ClientDto) o;
        return getId() == clientDto.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
