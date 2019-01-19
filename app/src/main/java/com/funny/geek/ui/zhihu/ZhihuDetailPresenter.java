package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.ZhihuDetailContract;
import com.funny.geek.model.bean.ZhihuDetailBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is ZhihuDetailPresenter
 */
public class ZhihuDetailPresenter extends RxPresenter<ZhihuDetailContract.View> implements ZhihuDetailContract.Presenter {

    @Inject
    public ZhihuDetailPresenter(DataHelper dataHelper, LifecycleProvider<ActivityEvent> provider) {
        super(dataHelper, provider);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(int id) {
        add(mDataHelper.fetchDetailInfo(id))
                .subscribe(new Consumer<ZhihuDetailBean>() {
                    @Override
                    public void accept(ZhihuDetailBean zhihuDetailBean) throws Exception {
                        mView.onShowContentView(zhihuDetailBean);
                    }
                });
    }
}
