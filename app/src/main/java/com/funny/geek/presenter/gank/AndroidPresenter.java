package com.funny.geek.presenter.gank;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is AndroidPresenter
 */
public class AndroidPresenter extends RxPresenter<AndroidContract.View> implements AndroidContract.Presenter {

    @Inject
    public AndroidPresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> lifecycleProvider) {
        super(dataHelper, lifecycleProvider);
    }


}
