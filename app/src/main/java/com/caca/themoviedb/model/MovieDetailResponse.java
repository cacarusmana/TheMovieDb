package com.caca.themoviedb.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author caca rusmana
 */
public class MovieDetailResponse extends Movie implements Serializable {

    private static final long serialVersionUID = -6867905819671319848L;

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
