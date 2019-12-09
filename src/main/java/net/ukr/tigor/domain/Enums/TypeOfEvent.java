package net.ukr.tigor.domain.Enums;

public enum TypeOfEvent {
    PHONE("Телефон"), MEETING("Личная встреча"), EMAIL("Электронное письмо"),
    MAIL("Почтовое письмо"), DEFECT("Дефектовка"), OTHER("Прочее");
    public final String label;

    TypeOfEvent(String name) {
        this.label = name;
    }

    public static TypeOfEvent valueOfLabel(String label) {
        for (TypeOfEvent e : values()) {
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
