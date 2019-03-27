package com.funny.geek.contract.gank;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.GankBean;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is AndroidContract
 */
public interface AndroidContract {

    public interface View extends IBaseView {

        void onShowContentView(GankBean gankBean);

        void onShowErrorView();

    }

    public interface Presenter extends IBasePresenter<View> {

        void doGetTechList(String tech, int num, int page);

    }

}
