package com.api.example.cacheexample.entities;

/**
 *
 * @author francocaramanico
 */
public class Deal {
    
    private Long id;
    private String title;
    private String countryCode;
    private String channel;

    public Deal(Long id, String title, String countryCode, String channel) {
        this.id = id;
        this.title = title;
        this.countryCode = countryCode;
        this.channel = channel;
    }

    public Long getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Deal{id=" + id + '}';
    }
    
}
