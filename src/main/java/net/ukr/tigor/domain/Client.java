package net.ukr.tigor.domain;

import net.ukr.tigor.service.HeadProperty;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @HeadProperty

    private String name;
    @HeadProperty

    private String edpnou;// National State Registry of Ukrainian Enterprises and Organizations)

    @HeadProperty
    private String itn; //individual Taxpayer Number

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactInformation> contactInformation;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactPerson> contactPersons;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EquipmentPark> equipmentPark;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequirementPark> requirementPark;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events;

    @HeadProperty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User manager;

    public Client() {

    }

    public Client(String name, String edpnou, String itn) {
        this.name = name;
        this.edpnou = edpnou;
        this.itn = itn;
        contactPersons = new HashSet<>();
        contactInformation = new HashSet<>();
        equipmentPark = new HashSet<>();
        requirementPark = new HashSet<>();
        events = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(Set<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public Set<EquipmentPark> getEquipmentPark() {
        return equipmentPark;
    }

    public void setEquipmentPark(Set<EquipmentPark> equipmentPark) {
        this.equipmentPark = equipmentPark;
    }

    public Set<RequirementPark> getRequirementPark() {
        return requirementPark;
    }

    public void setRequirementPark(Set<RequirementPark> requirementPark) {
        this.requirementPark = requirementPark;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setHeadProperties(Client client) {
        Field[] fields = Client.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(HeadProperty.class)) {
                field.setAccessible(true);
                try {
                    field.set(this, field.get(client));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
