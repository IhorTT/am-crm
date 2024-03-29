package net.ukr.tigor.dto;

import java.util.Objects;

public class ManagerDto {

    private int id = 0;
    private String name = "";

    public ManagerDto() {
    }

    public ManagerDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerDto)) return false;
        ManagerDto that = (ManagerDto) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
