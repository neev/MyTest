package com.example.neerajal.mytest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatAPIJson {

    private List<Object> breeds = null;
    private List<Object> categories = null;
    private String id;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Object> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Object> breeds) {
        this.breeds = breeds;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
