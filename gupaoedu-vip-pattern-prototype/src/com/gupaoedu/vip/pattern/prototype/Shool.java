package com.gupaoedu.vip.pattern.prototype;

import java.io.Serializable;

public class Shool implements Cloneable,Serializable {
    private String name;
    private Integer range;

    public Shool() {}

    public Shool(String name, Integer range) {
        this.name = name;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
