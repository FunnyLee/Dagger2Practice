package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyBean;
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
                .subscribe(new Consumer<DailyBean>() {
                    @Override
                    public void accept(DailyBean dailyListBean) throws Exception {
                        mView.onShowContentView(dailyListBean);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doRefresh(String date) {
        add(mDataHelper.fetchDailyBeforeList(date))
                .subscribe(new Consumer<DailyBean>() {
                    @Override
                    public void accept(DailyBean dailyBean) throws Exception {
                        mView.onShowContentView(dailyBean);
                    }
                });
    }

}
