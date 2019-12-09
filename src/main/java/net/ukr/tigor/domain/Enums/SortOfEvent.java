package net.ukr.tigor.domain.Enums;

import org.springframework.data.domain.Sort;

public enum SortOfEvent {
    NUMASC("Номер возр.", "number", Sort.Direction.ASC), NUMDESC("Номер убыв.", "number", Sort.Direction.DESC),
    DATEASC("Дата возр.", "date", Sort.Direction.ASC), DATEDESC("Дата убыв.", "date", Sort.Direction.DESC),
    CLIENTNAMEASC("Клиент а-я", "client.name", Sort.Direction.ASC), CLIENTNAMEDESC("Клиент я-а", "client.name", Sort.Direction.DESC);

    public final String label;
    public final String field;
    public final Sort.Direction direction;

    SortOfEvent(String label, String field, Sort.Direction direction) {
        this.label = label;
        this.field = field;
        this.direction = direction;
    }

    public static SortOfEvent valueOfLabel(String label) {
        for (SortOfEvent e : values()) {
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
