package net.ukr.tigor.domain.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("Администратор"), USER("Пользователь"),
    READONLY("Только просмотр"), USINGOWNOBJECTS("Видит свои объекты");

    public final String label;

    Role(String label) {
        this.label = label;
    }

    public static Role valueOfLabel(String label) {
        for (Role e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
