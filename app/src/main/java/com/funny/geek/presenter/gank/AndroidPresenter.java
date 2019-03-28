package com.funny.geek.presenter.gank;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.GankGirlBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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


    @SuppressLint("CheckResult")
    @Override
    public void doGetTechList(String tech, int num, int page) {
        add(mDataHelper.fetchTechList(tech, num, page))
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean gankBean) throws Exception {
                        mView.onShowContentView(gankBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onShowErrorView();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doGetGankGirlList(int num) {
        add(mDataHelper.fetchGankGirlList(num))
                .map(new Function<GankGirlBean, List<GankGirlBean.ResultsBean>>() {
                    @Override
                    public List<GankGirlBean.ResultsBean> apply(GankGirlBean gankGirlBean) throws Exception {
                        return gankGirlBean.results;
                    }
                })
                .subscribe(new Consumer<List<GankGirlBean.ResultsBean>>() {
                    @Override
                    public void accept(List<GankGirlBean.ResultsBean> resultsBeans) throws Exception {
                        mView.onShowGankGirl(resultsBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
