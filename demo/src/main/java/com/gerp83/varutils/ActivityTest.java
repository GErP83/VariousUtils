package com.gerp83.varutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gerp83.variousutils.Navigator;

/**
 * Created by GErP83
 *
 */

public class ActivityTest extends AppCompatActivity{

    Button openActivity;
    Button openActivityContext;
    Button openActivityContextAndFlag;
    Button openActivityAndClose;
    Button openActivityGetResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        openActivity = findViewById(R.id.openActivity);
        openActivityContext = findViewById(R.id.openActivityContext);
        openActivityContextAndFlag = findViewById(R.id.openActivityContextAndFlag);
        openActivityAndClose = findViewById(R.id.openActivityAndClose);
        openActivityGetResult = findViewById(R.id.openActivityGetResult);

        openActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(ActivityTest.this).to(ReturnResultActivity.class);
            }
        });

        openActivityContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getApplicationContext()).to(ReturnResultActivity.class);
            }
        });

        openActivityContextAndFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getApplicationContext())
                        .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .to(ReturnResultActivity.class);
            }
        });

        openActivityAndClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(ActivityTest.this).finishWith(ReturnResultActivity.class);
            }
        });

        openActivityGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(ActivityTest.this)
                        .data("result", true)
                        .forResult(ReturnResultActivity.class, 111);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == 111) {
            Toast.makeText(ActivityTest.this, "Return", Toast.LENGTH_SHORT).show();
        }

    }

}
