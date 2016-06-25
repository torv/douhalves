package com.torv.db.doumovide.intheaters;

import com.torv.db.doumovide.common.DMError;
import com.torv.db.doumovide.common.ICallback;
import com.torv.db.doumovide.util.UrlConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by admin on 16/6/25.
 */
public class InTheatersInteractorImpl implements IInTheatersInteractor{


    @Override
    public void loadData(final ICallback<InTheaterBean> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.getMovieBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InTheater inTheater = retrofit.create(InTheater.class);
        Call<InTheaterBean> call = inTheater.getInTheaters();
        call.enqueue(new Callback<InTheaterBean>() {
            @Override
            public void onResponse(Call<InTheaterBean> call, Response<InTheaterBean> response) {
                if(response.isSuccessful()) {
                    InTheaterBean bean = response.body();
                    callback.onData(bean);
                } else {
                    callback.onError(DMError.SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(Call<InTheaterBean> call, Throwable t) {
                callback.onError(DMError.SERVER_ERROR);
            }
        });
    }

    public interface InTheater{
        @GET(UrlConstant.IN_THEATERS)
        Call<InTheaterBean> getInTheaters();
    }
}
