package com.zgz.playandroid.ui.official;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zgz.playandroid.R;
import com.zgz.playandroid.base.BaseFragment;
import com.zgz.playandroid.model.bean.OfficialAccount;
import com.zgz.playandroid.ui.official.artical.ArticleFragment;
import com.zgz.playandroid.widget.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 * 公众号
 */
public class OfficialFragment extends BaseFragment<OfficialPresenter> implements OfficialContract.View {

    @BindView(R.id.v_official_indicator)
    MagicIndicator vOfficialIndicator;
    @BindView(R.id.v_official_pager)
    ViewPager vOfficialPager;

    private List<OfficialAccount> officialAccounts = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public static OfficialFragment newInstance() {

        Bundle args = new Bundle();

        OfficialFragment fragment = new OfficialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getOfficialAccount();
    }

    @Override
    public void setOfficialAccount(List<OfficialAccount> data) {
        officialAccounts.clear();
        officialAccounts.addAll(data);
        for (OfficialAccount account : data) {
            fragments.add(ArticleFragment.newInstance(account));
        }
        initViewPager();
        initMagicIndicator();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        vOfficialPager.setOffscreenPageLimit(4);
        vOfficialPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return officialAccounts.get(position).getName();
            }
        });
    }

    /**
     * 初始化tab页
     */
    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return officialAccounts.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int i) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(officialAccounts.get(i).getName());
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.white));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.white));

                simplePagerTitleView.setOnClickListener(v ->
                        vOfficialPager.setCurrentItem(i)
                );
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setLineHeight(6);
                linePagerIndicator.setYOffset(10);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.white));
                return linePagerIndicator;
            }
        });
        vOfficialIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(vOfficialIndicator, vOfficialPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.official_fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
