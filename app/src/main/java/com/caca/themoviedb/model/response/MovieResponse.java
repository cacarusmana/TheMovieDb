package com.caca.themoviedb.model.response;

import com.caca.themoviedb.model.Dates;
import com.caca.themoviedb.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author caca rusmana
 */
public class MovieResponse extends BaseResponse implements Serializable {

    private static final long serialVersionUID = -7693800586762177629L;

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("dates")
    private Dates dates;
    @SerializedName("total_pages")
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
