package com.torv.db.doumovide.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.torv.db.doumovide.R;
import com.torv.db.doumovide.common.DMError;
import com.torv.db.doumovide.common.Movie;
import com.torv.db.doumovide.intheaters.IInTheatersContract;
import com.torv.db.doumovide.intheaters.InTheaterBean;
import com.torv.db.doumovide.intheaters.InTheatersPresenter;
import com.torv.db.doumovide.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/6/25.
 */
public class InTheatersFrgmt extends Fragment implements IInTheatersContract.IView{

    private IInTheatersContract.IPresenter mPresenter;

    RecyclerView mRecyclerView;
    InTheaterAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<Movie> mMovieList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d("lifeCycle");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("lifeCycle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.d("lifeCycle");
        View view = inflater.inflate(R.layout.fragment_in_theaters, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_frgmt_in_theaters_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new InTheaterAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_frgmt_in_theaters_srl);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.d("lifeCycle");

        mPresenter = new InTheatersPresenter(this);
        mPresenter.loadData();
    }

    @Override
    public void onStart() {
        super.onStart();
        L.d("lifeCycle");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.d("lifeCycle");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.d("lifeCycle");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.d("lifeCycle");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("lifeCycle");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d("lifeCycle");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.d("lifeCycle");
    }

    @Override
    public void onData(InTheaterBean data) {
        if(data != null && data.subjects != null){
            mMovieList.clear();
            mMovieList.addAll(data.subjects);
            if(mAdapter != null){
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError(DMError error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean show) {
        if(show){
            setRefreshing(true);
        }else {
            setRefreshing(false);
        }
    }

    SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPresenter.loadData();
        }
    };

    public void setRefreshing(final boolean refreshing){
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }

    class InTheaterAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_in_theaters_item, parent, false);
            ViewHolder viewHolder= new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Movie movie = mMovieList.get(position);

            holder.avater.setImageURI(movie.images.medium);
            holder.title.setText(movie.title);
            holder.year.setText(movie.year);
            holder.titleEn.setText(movie.original_title);
            holder.score.setText(String.valueOf(movie.rating.average));

            String directors = "导演  ";
            for(Movie.Cast cast : movie.directors){
                directors += cast.name + "  ";
            }
            holder.director.setText(directors);

            String actors = "主演  ";
            for(Movie.Cast cast : movie.casts){
                actors += cast.name + "  ";
            }
            holder.actors.setText(actors);
        }

        @Override
        public int getItemCount() {
            return mMovieList.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView avater;
        TextView title;
        TextView year;
        TextView titleEn;
        TextView score;
        TextView director;
        TextView actors;
        public ViewHolder(View view) {
            super(view);
            avater = (SimpleDraweeView) view.findViewById(R.id.id_frgmt_in_theaters_item_avatar);
            title = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_title);
            year = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_year);
            titleEn = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_title_en);
            score = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_score);
            director = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_director);
            actors = (TextView) view.findViewById(R.id.id_frgmt_in_theaters_item_actors);
        }
    }
}
