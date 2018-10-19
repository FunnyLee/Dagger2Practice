package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyListBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is DailyPresenter
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    @Inject
    public DailyPresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> provider) {
        super(dataHelper, provider);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData() {
        add(mDataHelper.fetchDailyListInfo())
                .subscribe(new Consumer<DailyListBean>() {
                    @Override
                    public void accept(DailyListBean dailyListBean) throws Exception {
                        mView.onShowContent();
                    }
                });
    }

    @Override
    public void doRefresh() {

    }

}
