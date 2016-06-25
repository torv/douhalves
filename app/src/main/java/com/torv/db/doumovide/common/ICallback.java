package com.torv.db.doumovide.common;

/**
 * Created by admin on 16/6/25.
 */
public interface ICallback<T> {

    public void onData(T t);
    public void onError(DMError error);
}
