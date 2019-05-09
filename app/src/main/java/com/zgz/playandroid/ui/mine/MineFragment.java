package com.zgz.playandroid.ui.mine;

import android.os.Bundle;

import com.zgz.playandroid.R;
import com.zgz.playandroid.base.BaseFragment;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }
}
