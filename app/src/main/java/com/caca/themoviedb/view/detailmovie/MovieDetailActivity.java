package com.caca.themoviedb.view.detailmovie;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caca.themoviedb.R;
import com.caca.themoviedb.listener.AsyncTaskCompleteListener;
import com.caca.themoviedb.model.response.MovieDetailResponse;
import com.caca.themoviedb.network.GetBackdropBitmapTask;
import com.caca.themoviedb.presenter.DetailMoviePresenter;
import com.caca.themoviedb.util.Constant;
import com.caca.themoviedb.view.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.blurry.Blurry;

/**
 * @author caca rusmana
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailView, AsyncTaskCompleteListener<Object> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.thumbnail)
    ImageView thumbnail;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.release_year)
    TextView releaseYear;
    @BindView(R.id.release_year_content)
    TextView releaseYearContent;

    private DetailMoviePresenter detailMoviePresenter;
    private MovieDetailResponse detailMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        init();
    }


    @Override
    public void init() {
        detailMovie = (MovieDetailResponse) getIntent().getSerializableExtra(Constant.DATA);
        initToolbar(toolbar, detailMovie.getTitle());
        initCollapsingToolbar();

        detailMoviePresenter = new DetailMoviePresenter();
        onAttachView();

        displayData(detailMovie);
    }

    @Override
    public void onAttachView() {
        detailMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        detailMoviePresenter.onDetach();
    }

    @Override
    public void displayData(MovieDetailResponse detailMovie) {
        new GetBackdropBitmapTask(this, detailMovie.getBackdropPath(), this).execute();
        Glide.with(this).load(Constant.BASE_IMAGE_URL + Constant.IMAGE_WIDTH_500_PARAM + detailMovie.getPosterPath())
                .apply(new RequestOptions().placeholder(R.drawable.image_place_holder).error(R.drawable.image_place_holder))
                .into(thumbnail);

        overview.setText(detailMovie.getOverview());
        title.setText(detailMovie.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat(Constant.YEAR_FORMAT, Locale.getDefault());
        releaseYear.setText(sdf.format(detailMovie.getReleaseDate()));
        releaseYearContent.setText(sdf.format(detailMovie.getReleaseDate()));
        detailMoviePresenter.displayGenres(detailMovie.getGenres());
    }

    @Override
    public void onTaskComplete(Object... params) {
        Bitmap bitmap = (Bitmap) params[0];
        if (bitmap != null) {
            Blurry.with(MovieDetailActivity.this).color(Color.argb(66, 8, 35, 41)).from(bitmap).into(backdrop);
        }
    }

    @Override
    public void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(detailMovie.getTitle());

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onDisplayGenres(String genres) {
        genre.setText(genres);
    }
}
