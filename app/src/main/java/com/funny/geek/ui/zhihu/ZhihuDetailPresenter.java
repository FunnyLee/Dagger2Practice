package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.ZhihuDetailContract;
import com.funny.geek.model.bean.RealmFavoriteBean;
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

    @Override
    public void addFavorite(ZhihuDetailBean zhihuDetailBean) {
        RealmFavoriteBean realmFavoriteBean = new RealmFavoriteBean();
        realmFavoriteBean.id = String.valueOf(zhihuDetailBean.id);
        realmFavoriteBean.image = zhihuDetailBean.image;
        realmFavoriteBean.title = zhihuDetailBean.title;
        realmFavoriteBean.url = zhihuDetailBean.share_url;
        realmFavoriteBean.type = zhihuDetailBean.type;
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
