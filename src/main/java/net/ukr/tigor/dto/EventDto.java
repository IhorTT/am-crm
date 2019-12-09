package net.ukr.tigor.dto;

import net.ukr.tigor.domain.Enums.StateOfEvent;
import net.ukr.tigor.domain.Enums.TypeOfEvent;

import java.time.LocalDateTime;

public class EventDto {
    private int id = 0;
    private int number = 0;
    private DateCreationDto date = new DateCreationDto(LocalDateTime.now());
    private DateCreationDto dateStart = new DateCreationDto(LocalDateTime.now());
    private DateCreationDto dateEnd = new DateCreationDto(LocalDateTime.now());
    private String type = TypeOfEvent.PHONE.label;
    private String state = StateOfEvent.PLANNED.label;
    private ClientDto client;
    private PersonDto person;
    private String subject = "";
    private String content = "";
    private ManagerDto manager;

    public EventDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DateCreationDto getDate() {
        return date;
    }

    public void setDate(DateCreationDto date) {
        this.date = date;
    }

    public DateCreationDto getDateStart() {
        return dateStart;
    }

    public void setDateStart(DateCreationDto dateStart) {
        this.dateStart = dateStart;
    }

    public DateCreationDto getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateCreationDto dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }
}
