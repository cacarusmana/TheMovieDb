package com.caca.themoviedb.network.services;

import com.caca.themoviedb.model.MovieDetailResponse;
import com.caca.themoviedb.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author caca rusmana
 */
public interface MovieServices {

    @GET("movie/now_playing")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey,
                                  @Query("page") Integer page);

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getDetailMovie(@Path("movie_id") Long movieId,
                                             @Query("api_key") String apiKey);
}
