package com.funny.geek.contract.gank;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.GankGirlBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is GankTechContract
 */
public interface GankTechContract {

    public interface View extends IBaseView {

        void onShowContentView(GankBean gankBean);

        void onShowGankGirl(List<GankGirlBean.ResultsBean> girlList);

        void onShowErrorView();

    }

    public interface Presenter extends IBasePresenter<View> {

        void doGetTechList(String tech, int num, int page);

        void doGetGankGirlList(int num);

    }

}
