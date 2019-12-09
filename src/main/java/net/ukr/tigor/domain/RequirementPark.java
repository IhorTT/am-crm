package net.ukr.tigor.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Requirement_park")
public class RequirementPark {
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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateBuying;

    private Integer count;

    private String description;

    private LocalDateTime dateCreation;

    public RequirementPark() {
    }

    public RequirementPark(Client client, Manufacturer manufacturer, TypeOfAgriculturalMachinery typeOfAgrMach,
                           Integer count, Date dateBuying, String description, LocalDateTime dateCreation
    ) {
        this.client = client;
        this.manufacturer = manufacturer;
        this.typeOfAgrMach = typeOfAgrMach;
        this.count = count;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateBuying = dateBuying;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Date getDateBuying() {
        return dateBuying;
    }

    public void setDateBuying(Date dateBuying) {
        this.dateBuying = dateBuying;
    }


}
