package com.funny.geek.contract.zhihu;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.HotBean;

/**
 * Author: Funny
 * Time: 2018/11/14
 * Description: This is HotContract
 */
public interface HotContract {

    interface View extends IBaseView {

        void onShowContentView(HotBean hotBean);

        /**
         * 下拉刷新
         */
        void onPullDownRefresh();

    }

    interface Presenter extends IBasePresenter<View> {

        void doLoadData();

    }

}
