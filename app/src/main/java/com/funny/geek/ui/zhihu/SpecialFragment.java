package com.funny.geek.ui.zhihu;

import android.os.Bundle;

import com.funny.geek.base.AllBaseFragment;
import com.funny.geek.R;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is SpecialFragment
 */
public class SpecialFragment extends AllBaseFragment {

    public static SpecialFragment newInstance() {
        Bundle args = new Bundle();
        SpecialFragment fragment = new SpecialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special;
    }
}
