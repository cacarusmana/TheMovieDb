package com.caca.themoviedb.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author caca rusmana
 */
public class Dates implements Serializable {

    private static final long serialVersionUID = -3621815864731648737L;

    private Date minimum;
    private Date maximum;

    public Date getMinimum() {
        return minimum;
    }

    public void setMinimum(Date minimum) {
        this.minimum = minimum;
    }

    public Date getMaximum() {
        return maximum;
    }

    public void setMaximum(Date maximum) {
        this.maximum = maximum;
    }
}
