package com.example.dudco.highjinro;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudco.highjinro.databinding.ActivityMainBinding;
import com.example.dudco.highjinro.databinding.ItemTabBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ItemTabBinding tabTalkBinding,tabShcoolBinding,tabMyInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //toolbar custom
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View toolbarView = inflater.inflate(R.layout.toolbar_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        toolbarView.setLayoutParams(params);

        setSupportActionBar(binding.mainTool);
        getSupportActionBar().setCustomView(toolbarView, params);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        View tab_talk = inflater.inflate(R.layout.item_tab, null);
        tabTalkBinding = ItemTabBinding.bind(tab_talk);
        tabTalkBinding.tabText.setText("HIGH TALK");
        tabTalkBinding.tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tabTalkBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_talk_click));

        View tab_school = inflater.inflate(R.layout.item_tab, null);
        tabShcoolBinding = ItemTabBinding.bind(tab_school);
        tabShcoolBinding.tabText.setText("학교 찾기");
        tabShcoolBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_school_nonclick));

        View tab_myInfo = inflater.inflate(R.layout.item_tab, null);
        tabMyInfoBinding = ItemTabBinding.bind(tab_myInfo);
        tabMyInfoBinding.tabText.setText("내 정보");
        tabMyInfoBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_human_nonclick));

//        View tab_layout = inflater.inflate(R.layout.item_tab, null);

        binding.mainTab.addTab(binding.mainTab.newTab().setCustomView(tab_talk));
        binding.mainTab.addTab(binding.mainTab.newTab().setCustomView(tab_school));
        binding.mainTab.addTab(binding.mainTab.newTab().setCustomView(tab_myInfo));
        binding.mainTab.setSelectedTabIndicatorHeight(0);


        binding.mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.mainPager.setCurrentItem(tab.getPosition(), true);

                tabTalkBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_talk_nonclick));
                tabShcoolBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_school_nonclick));
                tabMyInfoBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_human_nonclick));

                tabTalkBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                tabShcoolBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                tabMyInfoBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                switch (tab.getPosition()){
                    case 0:
                        tabTalkBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_talk_click));
                        tabTalkBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                        break;
                    case 1:
                        tabShcoolBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_school_click));
                        tabShcoolBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                        break;
                    case 2:
                        tabMyInfoBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_human_click));
                        tabMyInfoBinding.tabText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.mainPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        binding.mainPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.mainTab));
    }
}
