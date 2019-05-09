package com.zgz.playandroid.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zgz.playandroid.R;
import com.zgz.playandroid.base.SimpleActivity;
import com.zgz.playandroid.ui.home.HomeFragment;
import com.zgz.playandroid.ui.mine.MineFragment;
import com.zgz.playandroid.ui.official.OfficialFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class MainActivity extends SimpleActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_classify)
    RadioButton rbClassify;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    private List<Fragment> fragments = new ArrayList<>();
    private Integer[] rbs = {R.id.rb_home, R.id.rb_classify, R.id.rb_mine};

    @Override
    protected void initEventAndData() {
        fragments.add(HomeFragment.newInstance());
        fragments.add(OfficialFragment.newInstance());
        fragments.add(MineFragment.newInstance());

        rg.check(rbs[0]);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    vp.setCurrentItem(0, false);
                    break;
                case R.id.rb_classify:
                    vp.setCurrentItem(1, false);
                    break;
                case R.id.rb_mine:
                    vp.setCurrentItem(2, false);
                    break;
                default:
                    break;
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                rg.check(rbs[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }
}
