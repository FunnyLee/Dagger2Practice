package com.funny.geek.contract.weChat;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.WeChatBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is WeChatMainContract
 */
public interface WeChatMainContract {

    interface View extends IBaseView {

        void onShowContentView(List<WeChatBean.NewslistBean> newslist);

        void onShowErrorView();

    }

    interface Presenter extends IBasePresenter<View> {

        void doLoadData();

    }

}
