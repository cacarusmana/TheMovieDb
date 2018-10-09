package com.caca.themoviedb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.caca.themoviedb.R;
import com.caca.themoviedb.listener.OnItemClickListener;
import com.caca.themoviedb.model.Movie;
import com.caca.themoviedb.util.Constant;

import java.util.List;

/**
 * @author caca rusmana
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private OnItemClickListener onItemClick;

    public HomeAdapter(Context context, List<Movie> movieList, OnItemClickListener onItemClick) {
        this.context = context;
        this.movieList = movieList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public HomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.movie_item_row, parent, false);
        return new HomeAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterViewHolder holder, final int position) {
        Movie object = movieList.get(position);

        Glide.with(context).load(Constant.BASE_IMAGE_URL + Constant.IMAGE_WIDTH_500_PARAM + object.getPosterPath())
                .apply(new RequestOptions().placeholder(R.drawable.image_place_holder).error(R.drawable.image_place_holder))
                .into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieList == null)
            return 0;
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class HomeAdapterViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;

        public HomeAdapterViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }


}
