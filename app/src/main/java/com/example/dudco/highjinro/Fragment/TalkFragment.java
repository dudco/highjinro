package com.example.dudco.highjinro.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudco.highjinro.Adapters.TalkListAdapter;
import com.example.dudco.highjinro.Datas.DashBoardData;
import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.FragmentTalkBinding;

import java.util.ArrayList;
import java.util.List;


public class TalkFragment extends Fragment {
    private FragmentTalkBinding binding;
    private List<DashBoardData> items = new ArrayList<>();
    private TalkListAdapter talkListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_talk, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentTalkBinding.bind(getView());
        talkListAdapter = new TalkListAdapter(getContext(), items);

        binding.talkList.setAdapter(talkListAdapter);

        final DashBoardData data = new DashBoardData("선린인터넷고 멀티미디어과", "가나다라마바사..","NICK NAME", "4", "2");
        binding.talkFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems(data);
            }
        });
    }

    private void addItems(DashBoardData data){
        items.add(data);
        talkListAdapter.notifyDataSetChanged();
    }
}
