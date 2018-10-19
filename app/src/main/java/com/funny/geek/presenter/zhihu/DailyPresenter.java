package com.funny.geek.presenter.zhihu;

import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyListBean;
import com.funny.geek.model.net.DataHelper;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is DailyPresenter
 */
public class DailyPresenter implements DailyContract.Presenter {

    private DataHelper mDataHelper;

    @Inject
    public DailyPresenter(DataHelper dataHelper) {
        mDataHelper = dataHelper;
    }

    @Override
    public void doLoadData() {
        Observable<DailyListBean> dailyListBeanObservable = mDataHelper.fetchDailyListInfo();
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void attachView(DailyContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
