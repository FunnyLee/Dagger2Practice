package com.funny.geek.contract.zhihu;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.DailyBean;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is DailyContract
 */
public interface DailyContract {

    interface View extends IBaseView {

        void onShowContentView(DailyBean dailyListBean);

        void onRefresh();

    }

    interface Presenter extends IBasePresenter<View> {

        void doLoadData();

        void doRefresh();

    }

}
