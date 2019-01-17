package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.HotContract;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2018/11/14
 * Description: This is HotPresenter
 */
public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {

    @Inject
    public HotPresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> provider) {
        super(dataHelper, provider);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData() {
        add(mDataHelper.fetchHotList()).subscribe(new Consumer<HotBean>() {
            @Override
            public void accept(HotBean hotBean) throws Exception {
                mView.onShowContentView(hotBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // TODO: 2019/1/17 处理失败页面
            }
        });
    }
}
