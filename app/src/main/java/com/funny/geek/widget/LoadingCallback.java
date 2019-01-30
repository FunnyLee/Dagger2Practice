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
        return false;
    }
}
