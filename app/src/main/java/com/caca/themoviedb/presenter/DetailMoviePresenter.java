package com.caca.themoviedb.presenter;

import com.caca.themoviedb.model.Genre;
import com.caca.themoviedb.view.detailmovie.MovieDetailView;

import java.util.List;

/**
 * @author caca rusmana
 */
public class DetailMoviePresenter implements BasePresenter<MovieDetailView> {

    private MovieDetailView detailView;

    public DetailMoviePresenter() {
    }

    @Override
    public void onAttach(MovieDetailView view) {
        detailView = view;
    }

    @Override
    public void onDetach() {
        detailView = null;
    }

    public void displayGenres(List<Genre> genres) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Genre genre : genres) {
            stringBuilder.append(genre.getName()).append(", ");
        }

        String result = stringBuilder.toString();

        result = result.length() == 0 ? "-" : result.substring(0, result.length() - 2).trim();

        detailView.onDisplayGenres(result);
    }

}
