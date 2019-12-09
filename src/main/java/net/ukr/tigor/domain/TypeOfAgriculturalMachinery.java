package net.ukr.tigor.domain;

import javax.persistence.*;

@Entity
@Table(name = "type_am")
public class TypeOfAgriculturalMachinery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public TypeOfAgriculturalMachinery() {
    }

    public TypeOfAgriculturalMachinery(String name) {
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
}
