package br.fxd.com.fxd.model;

/**
 * Created by matheuscatossi on 23/09/17.
 */

public class User {

    private String name;
    private int age;
    private String bike;
    private String lat;
    private String lng;
    private int id;

    public User() {

    }

    public User(int id, String name, int age, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lat = lat;
        this.lng = lng;
    }

    public User(int id, String name, int age, String bike, String lat, String lng) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lat = lat;
        this.lng = lng;
        this.bike = bike;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
