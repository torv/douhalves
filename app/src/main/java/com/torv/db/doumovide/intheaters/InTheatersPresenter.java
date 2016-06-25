package com.torv.db.doumovide.intheaters;

import com.torv.db.doumovide.common.DMError;
import com.torv.db.doumovide.common.ICallback;

/**
 * Created by admin on 16/6/25.
 */
public class InTheatersPresenter implements IInTheatersContract.IPresenter{

    private IInTheatersContract.IView mView;
    private IInTheatersInteractor mInteractor;

    private InTheatersPresenter(){
        throw new UnsupportedOperationException("need view params");
    }

    public InTheatersPresenter(IInTheatersContract.IView view){
        mView = view;
        mInteractor = new InTheatersInteractorImpl();
    }

    @Override
    public void loadData() {
        if(mView != null){
            mView.showLoading(true);
        }
        mInteractor.loadData(new ICallback<InTheaterBean>() {
            @Override
            public void onData(InTheaterBean inTheaterBean) {
                if(mView != null){
                    mView.showLoading(false);
                    mView.onData(inTheaterBean);
                }
            }

            @Override
            public void onError(DMError error) {
                if(mView != null){
                    mView.showLoading(false);
                    mView.onError(error);
                }
            }
        });
    }
}
