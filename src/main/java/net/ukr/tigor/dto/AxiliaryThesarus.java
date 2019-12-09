package net.ukr.tigor.dto;

public class AxiliaryThesarus {
    private String name = "";
    private int id = 0;

    public AxiliaryThesarus() {
    }

    public AxiliaryThesarus(String name, int id) {
        this.name = name;
        this.id = id;
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

}
