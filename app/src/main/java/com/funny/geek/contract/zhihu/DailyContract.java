package com.funny.geek.contract.zhihu;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is DailyContract
 */
public interface DailyContract {

    interface View extends IBaseView {

        void onShowContent();

        void onRefresh();

    }

    interface Presenter extends IBasePresenter<View> {

        void doLoadData();

        void doRefresh();

    }

}
