package com.funny.geek.base;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is IBasePresenter
 */
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();

}
