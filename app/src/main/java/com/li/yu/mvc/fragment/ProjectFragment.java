package com.li.yu.mvc.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.li.yu.mvc.R;
import com.li.yu.mvc.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;


public class ProjectFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    public static ProjectFragment getInstance() {
        ProjectFragment fragment = new ProjectFragment();
        return fragment;
    }

    private String [] mData  = {"头条","精选","娱乐","汽车","运动"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initEventAndData() {
        for (String s : mData) {
            mFragments.add(NewsListFragment.getInstance(s));
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData[position];
            }
        });
        //viewPager.setOffscreenPageLimit(mData.length);//设置加载几页默认2
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void loadData() {

    }
}
