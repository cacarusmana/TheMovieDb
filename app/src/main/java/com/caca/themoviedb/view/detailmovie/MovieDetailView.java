package com.caca.themoviedb.view.detailmovie;

import com.caca.themoviedb.model.MovieDetailResponse;
import com.caca.themoviedb.view.base.BaseView;

/**
 * @author caca rusmana
 */
public interface MovieDetailView extends BaseView {

    void displayData(MovieDetailResponse detailMovie);

    void initCollapsingToolbar();

    void onDisplayGenres(String genres);
}
