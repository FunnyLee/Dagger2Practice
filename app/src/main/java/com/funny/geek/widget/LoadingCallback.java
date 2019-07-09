package com.funny.geek.widget;

import com.funny.geek.R;
import com.kingja.loadsir.callback.Callback;

/**
 * Author: Funny
 * Time: 2019/1/30
 * Description: This is 加载页面
 */
public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading_view;
    }

    @Override
    public boolean getSuccessVisible() {
        //是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
        return false;
    }
}
