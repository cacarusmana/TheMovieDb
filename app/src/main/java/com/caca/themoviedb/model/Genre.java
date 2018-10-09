package com.caca.themoviedb.model;

import java.io.Serializable;

/**
 * @author caca rusmana
 */
public class Genre implements Serializable {

    private static final long serialVersionUID = -3014103451424599902L;


    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
