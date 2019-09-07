package com.gerp83.varutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gerp83.variousutils.Navigator;

/**
 * Created by GErP83
 *
 */

public class ReturnResultActivity extends AppCompatActivity{

    Button returnWithResult;
    Button clear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        returnWithResult = findViewById(R.id.returnWithResult);
        clear = findViewById(R.id.clear);

        if(getIntent() != null && getIntent().hasExtra("result")) {
            returnWithResult.setEnabled(true);
        }

        returnWithResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(ReturnResultActivity.this).returnResult();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getApplicationContext()).clearWith(ClearActivity.class);
            }
        });

    }
}
