package eus.ehu.tta.practica1.model;

/**
 * Created by edwin on 16/01/18.
 */

public class ResourceType {
    private int id;
    private String description;
    private String mime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
