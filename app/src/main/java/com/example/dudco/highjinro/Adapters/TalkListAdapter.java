package com.example.dudco.highjinro.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dudco.highjinro.Datas.DashBoardData;
import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.ItemTalkBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dudco on 2017. 2. 14..
 */

public class TalkListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<DashBoardData> items = new ArrayList<>();

    private static final String FONT = "fonts/08SeoulNamsanL.ttf";
    private static final String FONT_M = "fonts/08SeoulNamsanM.ttf";

    public TalkListAdapter(Context context, List<DashBoardData> items) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemTalkBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_talk, null, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else{
            binding = (ItemTalkBinding) convertView.getTag();
        }

        binding.setDashboard(items.get(position));
        binding.itemCardTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_M));
        binding.itemCardAuthor.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_M));
        binding.itemCardHeartNum.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_M));
        binding.itemCardReplyNum.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_M));
        binding.itemCardDesc.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT));

        return convertView;
    }

    @Override
    public DashBoardData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
