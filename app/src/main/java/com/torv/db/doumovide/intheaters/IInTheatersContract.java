package com.torv.db.doumovide.intheaters;

import com.torv.db.doumovide.common.DMError;

/**
 * Created by admin on 16/6/25.
 */
public interface IInTheatersContract {

    public interface IView{

        public void onData(InTheaterBean data);

        public void onError(DMError error);

        public void showLoading(boolean show);
    }

    public interface IPresenter{

        public void loadData();
    }
}
