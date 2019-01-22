package com.funny.geek.presenter.zhihu;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.SelectDateContract;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2019/1/22
 * Description: This is SelectDatePresenter
 */
public class SelectDatePresenter extends RxPresenter<SelectDateContract.View> implements SelectDateContract.Presenter {

    //这里一定要添加ActivityEvent泛型，不然Dagger2编译会报错
    @Inject
    public SelectDatePresenter(DataHelper dataHelper, LifecycleProvider<ActivityEvent> provider) {
        super(dataHelper, provider);
    }

    @Override
    public void doLoadData() {

    }
}
