package net.ukr.tigor.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCreationDto {
    private String listForm;
    private String editForm;

    public DateCreationDto() {
    }

    public DateCreationDto(LocalDateTime localDateTime) {
        this.editForm = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16);
        this.listForm = localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss"));
    }

    public String getListForm() {
        return listForm;
    }

    public void setListForm(String listForm) {
        this.listForm = listForm;
    }

    public String getEditForm() {
        return editForm;
    }

    public void setEditForm(String editForm) {
        this.editForm = editForm;
    }
}
