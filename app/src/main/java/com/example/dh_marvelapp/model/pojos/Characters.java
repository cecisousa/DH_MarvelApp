
package com.example.dh_marvelapp.model.pojos;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Characters {

    @Expose
    private Long available;
    @Expose
    private String collectionURI;
    @Expose
    private List<Item> items;
    @Expose
    private Long returned;

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getReturned() {
        return returned;
    }

    public void setReturned(Long returned) {
        this.returned = returned;
    }

}
