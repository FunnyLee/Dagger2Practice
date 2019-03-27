package com.funny.geek.contract.gank;

import com.funny.geek.base.IBasePresenter;
import com.funny.geek.base.IBaseView;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is AndroidContract
 */
public interface AndroidContract {

    public interface View extends IBaseView{

    }

    public interface Presenter extends IBasePresenter<View>{

    }

}
