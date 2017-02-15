package com.example.dudco.highjinro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dudco.highjinro.databinding.ActivitySearchMySchoolBinding;

public class SearchMySchool extends AppCompatActivity {
    ActivitySearchMySchoolBinding binding;

    boolean isYes = false;
    String first;
    String second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_my_school);
        binding.btnNo.setBackground(ContextCompat.getDrawable(this, R.drawable.backgroud_box));
        binding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isYes){
                    binding.btnNo.setBackground(ContextCompat.getDrawable(SearchMySchool.this, R.drawable.backgroud_box));
                    binding.btnYes.setBackground(ContextCompat.getDrawable(SearchMySchool.this, R.drawable.backgroud_btn));
                    isYes = false;
                }
            }
        });
        binding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isYes){
                    binding.btnYes.setBackground(ContextCompat.getDrawable(SearchMySchool.this, R.drawable.backgroud_box));
                    binding.btnNo.setBackground(ContextCompat.getDrawable(SearchMySchool.this, R.drawable.backgroud_btn));
                    isYes = true;
                }
            }
        });
//        binding.spinerOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        binding.spinerTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String[] array = getResources().getStringArray(R.array.array_list2);
//                second = array[position];
//            }
//        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchMySchool.this, SerachMySchoolLast.class);
                String[] array = getResources().getStringArray(R.array.array_list1);
                first = array[binding.spinerOne.getSelectedItemPosition()];
                second = array[binding.spinerTwo.getSelectedItemPosition()];
                Log.d("dudco", first + " " +second);

                intent.putExtra("isSex", isYes);
                intent.putExtra("first", first);
                intent.putExtra("second", second);

                startActivity(intent);
            }
        });
    }
}
