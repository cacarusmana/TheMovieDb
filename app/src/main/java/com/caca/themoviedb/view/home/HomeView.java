package com.caca.themoviedb.view.home;

import com.caca.themoviedb.model.Movie;
import com.caca.themoviedb.model.MovieDetailResponse;
import com.caca.themoviedb.view.base.BaseView;

import java.util.List;

/**
 * @author caca rusmana
 */
public interface HomeView extends BaseView {

    void showDialog(boolean dialog);

    void hideDialog(boolean dialog);

    void displayMovies(List<Movie> movies, int totalPages);

    void onErrorResponse(String message);

    void openDetailMovie(MovieDetailResponse movieDetail);

    void onLoadMoreSucceed(List<Movie> movies);

    void onLoadMoreError(String message);
}
