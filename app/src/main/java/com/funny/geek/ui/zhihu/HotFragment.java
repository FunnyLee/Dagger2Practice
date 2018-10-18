package com.funny.geek.ui.zhihu;

import android.os.Bundle;

import com.funny.geek.base.AllBaseFragment;
import com.funny.geek.R;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is HotFragment
 */
public class HotFragment extends AllBaseFragment {

    public static HotFragment newInstance() {
        Bundle args = new Bundle();
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }
}
