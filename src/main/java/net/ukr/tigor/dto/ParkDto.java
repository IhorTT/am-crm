package net.ukr.tigor.dto;

import java.time.LocalDateTime;

public class ParkDto {

    private int id = 0;
    private ClientDto client;
    private String model = "";
    private String serialNumber = "";
    private String count = "";
    private String yearOfManufacture = "";
    private String description = "";
    private AxiliaryThesarus manufacturer = new AxiliaryThesarus("", 0);
    private AxiliaryThesarus typeOfAgrMach = new AxiliaryThesarus("", 0);
    private String dateBuying = "";
    private DateCreationDto dateCreation = new DateCreationDto(LocalDateTime.now());
    private ManagerDto manager;

    public ParkDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateCreationDto getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(DateCreationDto dateCreation) {
        this.dateCreation = dateCreation;
    }

    public AxiliaryThesarus getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(AxiliaryThesarus manufacturer) {
        this.manufacturer = manufacturer;
    }

    public AxiliaryThesarus getTypeOfAgrMach() {
        return typeOfAgrMach;
    }

    public void setTypeOfAgrMach(AxiliaryThesarus typeOfAgrMach) {
        this.typeOfAgrMach = typeOfAgrMach;
    }

    public String getDateBuying() {
        return dateBuying;
    }

    public void setDateBuying(String dateBuying) {
        this.dateBuying = dateBuying;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }
}
