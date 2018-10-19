package com.caca.themoviedb.presenter;

import com.caca.themoviedb.BuildConfig;
import com.caca.themoviedb.model.MovieDetailResponse;
import com.caca.themoviedb.model.MovieResponse;
import com.caca.themoviedb.network.NetworkApi;
import com.caca.themoviedb.network.services.MovieServices;
import com.caca.themoviedb.view.home.HomeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author caca rusmana
 */
public class HomePresenter implements BasePresenter<HomeView> {

    private MovieServices movieServices;
    private HomeView homeView;

    public HomePresenter() {
        movieServices = NetworkApi.getNetworkApi().create(MovieServices.class);
    }

    @Override
    public void onAttach(HomeView view) {
        homeView = view;
    }

    @Override
    public void onDetach() {
        homeView = null;
    }

    public void loadMovies(int page) {
        homeView.showDialog(false);
        Call<MovieResponse> movieResponseCall = movieServices.getMovies(BuildConfig.API_KEY, page);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                homeView.hideDialog(false);
                MovieResponse movieResponse = response.body();
                homeView.displayMovies(movieResponse.getResults(), movieResponse.getTotalPages());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                homeView.hideDialog(false);
                homeView.onErrorResponse(t.getMessage());
            }

        });
    }

    public void getDetailMovie(Long id) {
        homeView.showDialog(true);
        Call<MovieDetailResponse> movieResponseCall = movieServices.getDetailMovie(id, BuildConfig.API_KEY);
        movieResponseCall.enqueue(new Callback<MovieDetailResponse>() {

            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                homeView.hideDialog(true);
                homeView.openDetailMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                homeView.hideDialog(true);
                t.printStackTrace();
                homeView.onErrorResponse(t.getMessage());
            }
        });
    }

    public void loadMoreMovies(int page) {
        homeView.showDialog(true);
        Call<MovieResponse> movieResponseCall = movieServices.getMovies(BuildConfig.API_KEY, page);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                homeView.hideDialog(true);
                homeView.onLoadMoreSucceed(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                homeView.hideDialog(true);
                t.printStackTrace();
                homeView.onLoadMoreError(t.getMessage());
            }

        });
    }


}
