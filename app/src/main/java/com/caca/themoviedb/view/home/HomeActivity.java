package com.caca.themoviedb.view.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.caca.themoviedb.R;
import com.caca.themoviedb.adapter.HomeAdapter;
import com.caca.themoviedb.listener.OnItemClickListener;
import com.caca.themoviedb.model.Movie;
import com.caca.themoviedb.model.response.MovieDetailResponse;
import com.caca.themoviedb.presenter.HomePresenter;
import com.caca.themoviedb.util.Constant;
import com.caca.themoviedb.util.GridSpacingItemDecoration;
import com.caca.themoviedb.util.Utility;
import com.caca.themoviedb.view.base.BaseActivity;
import com.caca.themoviedb.view.detailmovie.MovieDetailActivity;
import com.caca.themoviedb.viewmodel.HomeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author caca rusmana
 */
public class HomeActivity extends BaseActivity implements HomeView, OnItemClickListener {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private HomeAdapter homeAdapter;
    private HomePresenter homePresenter;
    private boolean isLoading;

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);

        homePresenter = new HomePresenter();
        onAttachView();

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, Utility.dpToPx(this, 5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        homeAdapter = new HomeAdapter(this, homeViewModel.getMovieList(), this);
        recyclerView.setAdapter(homeAdapter);

        recyclerView.addOnScrollListener(endlessScrollListener);

        if (homeViewModel.getMovieList().isEmpty())
            homePresenter.loadMovies(homeViewModel.getPage());
    }


    private RecyclerView.OnScrollListener endlessScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) {
                final int visibleThreshold = 2;

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                int currentTotalCount = layoutManager.getItemCount();

                if (currentTotalCount <= lastItem + visibleThreshold) {
                    if (homeViewModel.getPage() < homeViewModel.getTotalRecord() && !isLoading) {
                        homeViewModel.increasePage();
                        isLoading = true;
                        homePresenter.loadMoreMovies(homeViewModel.getPage());
                    }

                }
            }
        }
    };

    @Override
    public void onAttachView() {
        homePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        homePresenter.onDetach();
    }

    @Override
    public void displayMovies(List<Movie> movies, final int totalPages) {
        homeViewModel.setTotalRecord(totalPages);
        homeViewModel.setMovieList(movies);
        homeAdapter.setMovieList(homeViewModel.getMovieList());
    }

    @Override
    public void onErrorResponse(String message) {
        showToast(message == null ? getString(R.string.error_connection) : message);
    }

    @Override
    public void openDetailMovie(MovieDetailResponse movieDetail) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constant.DATA, movieDetail);
        startActivity(intent);
    }

    @Override
    public void onLoadMoreSucceed(List<Movie> movies) {
        isLoading = false;
        homeViewModel.setMovieList(movies);
        homeAdapter.setMovieList(homeViewModel.getMovieList());
    }

    @Override
    public void onLoadMoreError(String message) {
        isLoading = false;
        homeViewModel.decreasePage();
        showToast(message);
    }

    @Override
    public void showDialog(boolean dialog) {
        if (dialog) {
            showProgressDialog();
        } else {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideDialog(boolean dialog) {
        if (dialog) {
            hideProgressDialog();
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position) {
        homePresenter.getDetailMovie(homeViewModel.getMovieList().get(position).getId());
    }

}
