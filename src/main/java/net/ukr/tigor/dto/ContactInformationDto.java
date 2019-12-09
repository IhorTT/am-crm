package net.ukr.tigor.dto;

public class ContactInformationDto {

    private String id;
    private String typeCI;
    private String details;
    private ClientDto client;
    private ManagerDto manager;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCI() {
        return typeCI;
    }

    public void setTypeCI(String typeCI) {
        this.typeCI = typeCI;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }
}
