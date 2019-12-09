package net.ukr.tigor.domain.Enums;

public enum TypeOfContactInformation {

    ADDRESS("Адрес"), PHONE("Телефон"), EMAIL("e-mail"), OTHER("Другое");

    public final String label;

    TypeOfContactInformation(String name) {
        this.label = name;
    }

    public static TypeOfContactInformation valueOfLabel(String label) {
        for (TypeOfContactInformation e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}
