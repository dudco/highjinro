package com.example.dudco.highjinro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.dudco.highjinro.databinding.ActivitySerachMySchoolLastBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SerachMySchoolLast extends AppCompatActivity {
    ActivitySerachMySchoolLastBinding binding;
    List<String> items = new ArrayList<>();
    String mySchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_my_school_last);

        String sex = "여";

        String first = getIntent().getStringExtra("first");
        String second = getIntent().getStringExtra("second");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_serach_my_school_last);

        AQuery aq = new AQuery(this);
        String url = "hacka.iwin247.kr/schools/tag/"+first+","+second+","+sex;
        Log.d("dudco", url);
        aq.ajax("http://hacka.iwin247.kr/schools/tag/"+first+","+second+","+sex, JSONArray.class, new AjaxCallback<JSONArray>(){
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d("dudco", object.toString());

                for(int i = 0; i< object.length() ; i++){
                    try {
                        JSONObject item = (JSONObject) object.get(i);
                        JSONArray schools = item.getJSONArray("schools");
                        for(int j = 0 ; j < schools.length() ; j++){
                            items.add((String) schools.get(j));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SerachMySchoolLast.this, android.R.layout.simple_list_item_1, items);

                binding.list1.setAdapter(adapter);
                binding.list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SerachMySchoolLast.this, items.get(position)+" 선택!", Toast.LENGTH_SHORT).show();
                        mySchool = items.get(position);
                    }
                });
                binding.btnFinalNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SerachMySchoolLast.this, MainActivity.class);
                        intent.putExtra("title", mySchool);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
