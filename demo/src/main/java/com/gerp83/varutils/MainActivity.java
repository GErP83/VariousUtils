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

public class MainActivity extends AppCompatActivity{

    Button activityTest;
    Button fragmentTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        activityTest = findViewById(R.id.activityTest);
        fragmentTest = findViewById(R.id.fragmentTest);


        activityTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(MainActivity.this).to(ActivityTest.class);
            }
        });

        fragmentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getApplicationContext()).to(FragmentTest.class);
            }
        });
    }

}
