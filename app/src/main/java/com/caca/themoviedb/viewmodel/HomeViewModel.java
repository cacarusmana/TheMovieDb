package com.caca.themoviedb.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.caca.themoviedb.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private int page = 1;
    private int totalRecord;
    private List<Movie> movieList;

    public int getPage() {
        return page;
    }

    public void increasePage() {
        this.page++;
    }

    public void decreasePage() {
        this.page--;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }


    public List<Movie> getMovieList() {
        if (movieList == null)
            movieList = new ArrayList<>();

        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.addAll(movieList);
    }
}
