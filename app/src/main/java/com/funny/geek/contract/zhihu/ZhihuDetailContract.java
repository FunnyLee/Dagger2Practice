package com.funny.geek.contract.zhihu;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;
import com.funny.geek.model.bean.ZhihuDetailBean;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is ZhihuDetailContract
 */
public interface ZhihuDetailContract {

    interface View extends IBaseView {

        void onShowContentView(ZhihuDetailBean zhihuDetailBean);

        void onShowFavoriteState(boolean state);
    }

    interface Presenter extends IBasePresenter<View> {

        void doLoadData(int id);

        void addFavorite(ZhihuDetailBean zhihuDetailBean);

        void deleteFavorite(String id);

        void queryFavorite(String id);

    }
}
