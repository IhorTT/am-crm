package net.ukr.tigor.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER)
    private Set<EquipmentPark> equipmentPark;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
        this.id = 0l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EquipmentPark> getEquipmentPark() {
        return equipmentPark;
    }

    public void setEquipmentPark(Set<EquipmentPark> equipmentParks) {
        this.equipmentPark = equipmentParks;
    }

}
