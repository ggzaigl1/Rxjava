package com.jypj.yuexiu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jypj.yuexiu.adapter.TabAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.fragment.IncontentFragment;
import com.jypj.yuexiu.fragment.NoticeFragment;
import com.jypj.yuexiu.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 * OA界面
 */

public class OAactivity extends BaseActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        mTabLayout= (TabLayout) findViewById(R.id.tabs);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        getTablayout();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_oa;
    }

    private void getTablayout(){
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IncontentFragment());
        fragments.add(new TodoFragment());
        fragments.add(new NoticeFragment());

        //创建ViewPager的adapter
        TabAdapter adapter=new TabAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue)); //设置线的颜色
        mTabLayout.setTabTextColors(getResources().getColor(R.color.black), getResources().getColor(R.color.blue)); //设置文本的颜色
    }

}
