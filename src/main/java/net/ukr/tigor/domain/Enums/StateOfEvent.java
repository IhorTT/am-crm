package net.ukr.tigor.domain.Enums;

public enum StateOfEvent {
    PLANNED("Запланировано"), COMPLETED("Завершено"), CANCELED("Отменено");
    public final String label;

    StateOfEvent(String name) {
        this.label = name;
    }

    public static StateOfEvent valueOfLabel(String label) {
        for (StateOfEvent e : values()) {
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
