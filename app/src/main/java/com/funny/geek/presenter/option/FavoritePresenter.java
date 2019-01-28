package com.funny.geek.presenter.option;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.option.FavoriteContract;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is FavoritePresenter
 */
public class FavoritePresenter extends RxPresenter<FavoriteContract.View> implements FavoriteContract.Presenter {

    @Inject
    public FavoritePresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> lifecycleProvider) {
        super(dataHelper, lifecycleProvider);
    }

    @Override
    public void doLoadData() {

    }
}
