package com.example.dudco.highjinro.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.RoundedAvatarDrawable;
import com.example.dudco.highjinro.databinding.FragmentMyInfoBinding;

public class MyInfoFragment extends Fragment {
    private FragmentMyInfoBinding binding;

    private static final String FONT_M = "fonts/08SeoulNamsanM.ttf";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentMyInfoBinding.bind(getView());

        BitmapDrawable drawable1 = (BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.default_profile);
        binding.myinfoProfile.setImageBitmap(new RoundedAvatarDrawable(drawable1.getBitmap()).getBitmap());
        BitmapDrawable drawable2 = (BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.camera);
        binding.myinfoCamera.setImageBitmap(new RoundedAvatarDrawable(drawable2.getBitmap()).getBitmap());

        binding.myinfoName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), FONT_M));
        binding.myinfoGoalText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), FONT_M));
        binding.myinfoAppsetting.setTypeface(Typeface.createFromAsset(getContext().getAssets(), FONT_M));
        binding.myinfoLogout.setTypeface(Typeface.createFromAsset(getContext().getAssets(), FONT_M));

        binding.myinfoGoalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"목표 설정",Toast.LENGTH_SHORT).show();
            }
        });
        binding.myinfoAppsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"어플리케이션 설정",Toast.LENGTH_SHORT).show();
            }
        });
        binding.myinfoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"로그아웃",Toast.LENGTH_SHORT).show();

            }
        });
        final View alertView = LayoutInflater.from(getContext()).inflate(R.layout.alertview, null);
        final EditText alertEdit = (EditText) alertView.findViewById(R.id.alert_edit);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        final AlertDialog alert = alertDialogBuilder.setTitle("이름 바꾸기").setView(alertView)
        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                binding.myinfoName.setText(alertEdit.getText().toString());
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create();

        binding.myinfoChangename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }
}
