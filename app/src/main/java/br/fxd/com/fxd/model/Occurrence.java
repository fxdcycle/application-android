package br.fxd.com.fxd.model;

/**
 * Created by matheuscatossi on 23/09/17.
 */

public class Occurrence {

    private int id;
    private String type;
    private String lat;
    private String lng;

    public Occurrence() {

    }

    public Occurrence(int id, String type, String lat, String lng) {
        this.id = id;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public Occurrence(String type, String lat, String lng) {
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
