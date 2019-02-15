package com.funny.geek.presenter.weChat;

import android.annotation.SuppressLint;

import com.funny.geek.base.RxPresenter;
import com.funny.geek.contract.weChat.WeChatMainContract;
import com.funny.geek.model.bean.WeChatBean;
import com.funny.geek.model.net.DataHelper;
import com.funny.geek.util.Constants;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is WeChatMainPresenter
 */
public class WeChatMainPresenter extends RxPresenter<WeChatMainContract.View> implements WeChatMainContract.Presenter {

    public static final int PAGE_NUM = 10;
    private int current_page = 1;

    @Inject
    public WeChatMainPresenter(DataHelper dataHelper, LifecycleProvider<FragmentEvent> lifecycleProvider) {
        super(dataHelper, lifecycleProvider);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData() {
        add(mDataHelper.fetchWeChatList(Constants.KEY_API, PAGE_NUM, current_page))
                .map(new Function<WeChatBean, List<WeChatBean.NewslistBean>>() {
                    @Override
                    public List<WeChatBean.NewslistBean> apply(WeChatBean weChatBean) throws Exception {
                        return weChatBean.newslist;
                    }
                })
                .subscribe(new Consumer<List<WeChatBean.NewslistBean>>() {
                    @Override
                    public void accept(List<WeChatBean.NewslistBean> newslistBeans) throws Exception {
                        mView.onShowContentView(newslistBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onShowErrorView();
                    }
                });
    }
}
