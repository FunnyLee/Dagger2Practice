package com.funny.geek.contract.option;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is FavoritePresenter
 */
public interface FavoriteContract {

    interface View extends IBaseView {
        void onShowContentView();
    }

    interface Presenter extends IBasePresenter<View> {
        void doLoadData();
    }

}
