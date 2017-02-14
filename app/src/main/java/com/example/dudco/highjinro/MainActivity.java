package com.example.dudco.highjinro;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dudco.highjinro.databinding.ActivityMainBinding;
import com.example.dudco.highjinro.databinding.ItemTabBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ItemTabBinding tabTalkBinding,tabShcoolBinding,tabMyInfoBinding;

    private static final String FONT_B = "fonts/08SeoulNamsanM.ttf";
    private static final String FONT = "fonts/08SeoulNamsanL.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.mainTool);


        final ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //toolbar custom
        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View toolbarView = inflater.inflate(R.layout.toolbar_layout, null);
        TextView title = (TextView) toolbarView.findViewById(R.id.toolbar_title);
        ImageView search = (ImageView) toolbarView.findViewById(R.id.toolbar_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.toolbar_search_layout, null);
                final EditText edit = (EditText) view.findViewById(R.id.search_edit);
                ImageView close = (ImageView) view.findViewById(R.id.search_close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSupportActionBar().setCustomView(toolbarView, params);
                    }
                });
                edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(actionId == EditorInfo.IME_ACTION_SEARCH){
                            String searchText = edit.getText().toString();
                            Toast.makeText(MainActivity.this, searchText, Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                getSupportActionBar().setCustomView(view, params);
            }
        });
        title.setTypeface(Typeface.createFromAsset(getAssets(), FONT_B));

        getSupportActionBar().setCustomView(toolbarView, params);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        View tab_talk = inflater.inflate(R.layout.item_tab, null);
        tabTalkBinding = ItemTabBinding.bind(tab_talk); //binding
        tabTalkBinding.tabText.setText("HIGH TALK");
        tabTalkBinding.tabText.setTypeface(Typeface.createFromAsset(getAssets(), FONT)); //setfont
        tabTalkBinding.tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tabTalkBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_talk_click));

        View tab_school = inflater.inflate(R.layout.item_tab, null);
        tabShcoolBinding = ItemTabBinding.bind(tab_school); //binding
        tabShcoolBinding.tabText.setTypeface(Typeface.createFromAsset(getAssets(), FONT)); //setfont
        tabShcoolBinding.tabText.setText("학교 찾기");
        tabShcoolBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_school_nonclick));

        View tab_myInfo = inflater.inflate(R.layout.item_tab, null);
        tabMyInfoBinding = ItemTabBinding.bind(tab_myInfo); //binding
        tabMyInfoBinding.tabText.setTypeface(Typeface.createFromAsset(getAssets(), FONT));//setfont
        tabMyInfoBinding.tabText.setText("내 정보");
        tabMyInfoBinding.tabIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_human_nonclick));

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

                binding.mainTabIndicator.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.md_grey_300));

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
                        binding.mainTabIndicator.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.md_grey_400));
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
        binding.mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, position + positionOffset);
                binding.mainTabMargin.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
