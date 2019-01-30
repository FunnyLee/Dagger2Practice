package com.funny.geek.presenter.zhihu;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.net.DataHelper;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.Calendar;

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
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onShowErrorView();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doRefresh(String date) {
        //处理时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(6, 8)));
        calendar.add(Calendar.DATE, 1);
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(calendar.get(Calendar.DATE));
        if (day.length() == 1) {
            day = "0" + day;
        }
        StringBuilder dateStr = new StringBuilder();
        dateStr.append(calendar.get(Calendar.YEAR)).append(month).append(day);
        add(mDataHelper.fetchDailyBeforeList(dateStr.toString()))
                .subscribe(new Consumer<DailyBean>() {
                    @Override
                    public void accept(DailyBean dailyBean) throws Exception {
                        mView.onShowContentView(dailyBean);
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
    public void doGetBeforeDaily(String data) {
        add(mDataHelper.fetchDailyBeforeList(data))
                .subscribe(new Consumer<DailyBean>() {
                    @Override
                    public void accept(DailyBean dailyBean) throws Exception {
                        mView.onShowContentView(dailyBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onShowErrorView();
                    }
                });
    }

}
