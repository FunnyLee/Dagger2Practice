package com.funny.geek.contract.zhihu;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.SectionListBean;

/**
 * Author: Funny
 * Time: 2019/1/17
 * Description: This is SpecialContract
 */
public interface SpecialContract {

    interface View extends IBaseView{

        void onShowContentView(SectionListBean sectionListBean);

    }

    interface Presenter extends IBasePresenter<View>{

        void doLoadData();

    }

}
