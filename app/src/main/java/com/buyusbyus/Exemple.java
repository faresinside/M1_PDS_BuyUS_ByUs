package com.buyusbyus;

import com.buyusbyus.Model.Rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exemple {

    private List<Rules> items = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Rules> getItems() {
        return items;
    }

    public void setItems(List<Rules> items) {
        this.items = items;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}