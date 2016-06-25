package com.torv.db.doumovide.intheaters;

/**
 * Created by admin on 16/6/25.
 */
public interface InTheatersContract {

    public interface View<T>{

        public void onData(T t);

    }

    public interface Presenter{

    }
}
