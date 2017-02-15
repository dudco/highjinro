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
        DashBoardData data1 = new DashBoardData("선린인터넷고 어디과가 좋나요?", "제목 그대로 어디과가 가장 좋나요?? 아직 진로를 정하지 못하였습니다.", "이찬우", "10", "3");
        DashBoardData data2 = new DashBoardData("특성화고 인식", "저희 부모님은 특성화고에대해 안좋은 시각을 가지고 있으셔요 ㅠㅠ\n" + "다른분들도 그런가요??.", "박태준", "14", "24");
        DashBoardData data3 = new DashBoardData("소프트웨어 개발 입문자입니다.", "개발공부를 할려고 하는데 시작언어로 C언어가 좋을까요 자바가 좋을까요???", "김도원", "20", "23");

        addItems(data1);
        addItems(data2);
        addItems(data3);
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
