package com.torv.db.doumovide.intheaters;

import com.torv.db.doumovide.common.ICallback;

/**
 * Created by admin on 16/6/25.
 */
public interface IInTheatersInteractor {

    public void loadData(ICallback<InTheaterBean> callback);
}
