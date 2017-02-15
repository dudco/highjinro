package com.example.dudco.highjinro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dudco.highjinro.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    private static final String FONT_B = "fonts/08SeoulNamsanM.ttf";
    private static final String FONT = "fonts/08SeoulNamsanL.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        binding.register.setTypeface(Typeface.createFromAsset(getAssets(), FONT_B));
        ((TextView)binding.regBtn.getChildAt(0)).setTypeface(Typeface.createFromAsset(getAssets(), FONT));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SearchMySchool.class);
                sharedPreferencesEditor.putString("nickname", binding.regName.getText().toString());
                startActivity(intent);
            }
        });
    }
}
