package com.senla.courses.model;

import java.io.Serializable;

public abstract class AId implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
