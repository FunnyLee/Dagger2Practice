package com.funny.geek.ui.zhihu;

import android.os.Bundle;

import com.funny.geek.base.AllBaseFragment;
import com.funny.geek.R;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is SubjectFragment
 */
public class SubjectFragment extends AllBaseFragment {

    public static SubjectFragment newInstance() {
        Bundle args = new Bundle();
        SubjectFragment fragment = new SubjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_subject;
    }
}
