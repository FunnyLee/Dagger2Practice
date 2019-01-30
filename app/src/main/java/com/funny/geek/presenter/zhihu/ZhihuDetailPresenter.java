package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.ZhihuDetailContract;
import com.funny.geek.model.bean.RealmFavoriteBean;
import com.funny.geek.model.bean.ZhihuDetailBean;
import com.funny.geek.model.net.DataHelper;
import com.funny.geek.util.Constants;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

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
                .subscribe(zhihuDetailBean -> mView.onShowContentView(zhihuDetailBean), throwable -> mView.onShowErrorView());
    }

    @Override
    public void addFavorite(ZhihuDetailBean zhihuDetailBean) {
        RealmFavoriteBean realmFavoriteBean = new RealmFavoriteBean();
        realmFavoriteBean.id = String.valueOf(zhihuDetailBean.id);
        realmFavoriteBean.image = zhihuDetailBean.image;
        realmFavoriteBean.title = zhihuDetailBean.title;
        realmFavoriteBean.url = zhihuDetailBean.share_url;
        realmFavoriteBean.type = Constants.TYPE_ZHIHU;
        realmFavoriteBean.time = System.currentTimeMillis();
        mDataHelper.insertFavorite(realmFavoriteBean);
    }

    @Override
    public void deleteFavorite(String id) {
        mDataHelper.deleteFavorite(id);
    }

    @SuppressLint("CheckResult")
    @Override
    public void queryFavorite(String id) {
        RealmFavoriteBean realmFavoriteBean = mDataHelper.queryFavorite(id);
        mView.onShowFavoriteState(realmFavoriteBean == null ? false : true);
    }
}
