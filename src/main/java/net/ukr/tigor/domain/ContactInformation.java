package net.ukr.tigor.domain;

import net.ukr.tigor.domain.Enums.TypeOfContactInformation;

import javax.persistence.*;

@Entity
@Table(name = "contact_information")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_ci")
    private TypeOfContactInformation typeCI;

    private String details;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public ContactInformation(TypeOfContactInformation typeCI, String details, Client client) {
        this.typeCI = typeCI;
        this.details = details;
        this.client = client;
    }

    public ContactInformation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfContactInformation getTypeCI() {
        return typeCI;
    }

    public void setTypeCI(TypeOfContactInformation typeCI) {
        this.typeCI = typeCI;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
