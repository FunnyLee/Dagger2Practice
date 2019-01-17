package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.SpecialContract;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/1/17
 * Description: This is SpecialPresenter
 */
public class SpecialPresenter extends RxPresenter<SpecialContract.View> implements SpecialContract.Presenter {

    @Inject
    public SpecialPresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> provider) {
        super(dataHelper, provider);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData() {
       add(mDataHelper.fetchSectionList())
               .subscribe(new Consumer<SectionListBean>() {
                   @Override
                   public void accept(SectionListBean sectionListBean) throws Exception {
                       mView.onShowContentView(sectionListBean);
                   }
               });
    }
}
