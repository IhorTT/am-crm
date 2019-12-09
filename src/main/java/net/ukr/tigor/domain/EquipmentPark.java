package net.ukr.tigor.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipment_park")
public class EquipmentPark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeOfAgrMach_id")
    private TypeOfAgriculturalMachinery typeOfAgrMach;

    private String model;
    private String serialNumber;
    private Integer count;
    private Integer yearOfManufacture;
    private String description;

    private LocalDateTime dateCreation;

    public EquipmentPark() {
    }

    public EquipmentPark(Client client, Manufacturer manufacturer, TypeOfAgriculturalMachinery typeOfAgrMach,
                         String model, String serialNumber, Integer count, Integer yearOfManufacture,
                         String description, LocalDateTime dateCreation
    ) {
        this.client = client;
        this.manufacturer = manufacturer;
        this.typeOfAgrMach = typeOfAgrMach;
        this.model = model;
        this.serialNumber = serialNumber;
        this.count = count;
        this.yearOfManufacture = yearOfManufacture;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TypeOfAgriculturalMachinery getTypeOfAgrMach() {
        return typeOfAgrMach;
    }

    public void setTypeOfAgrMach(TypeOfAgriculturalMachinery typeOfAgrMach) {
        this.typeOfAgrMach = typeOfAgrMach;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

}
