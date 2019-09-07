package com.gerp83.varutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gerp83.variousutils.Navigator;

/**
 * Created by GErP83
 *
 */

public class Fragment extends androidx.fragment.app.Fragment {

    Button openActivity;
    Button openActivityContext;
    Button openActivityContextAndFlag;
    Button openActivityAndClose;
    Button openActivityGetResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openActivity = view.findViewById(R.id.openActivity);
        openActivityContext = view.findViewById(R.id.openActivityContext);
        openActivityContextAndFlag = view.findViewById(R.id.openActivityContextAndFlag);
        openActivityAndClose = view.findViewById(R.id.openActivityAndClose);
        openActivityGetResult = view.findViewById(R.id.openActivityGetResult);

        openActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(Fragment.this).to(ReturnResultActivity.class);
            }
        });

        openActivityContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getContext()).to(ReturnResultActivity.class);
            }
        });

        openActivityContextAndFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(getContext())
                        .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .to(ReturnResultActivity.class);
            }
        });

        openActivityAndClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(Fragment.this).finishWith(ReturnResultActivity.class);
            }
        });

        openActivityGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.from(Fragment.this)
                        .data("result", true)
                        .forResult(ReturnResultActivity.class, 111);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == 111) {
            Toast.makeText(getActivity(), "Return", Toast.LENGTH_SHORT).show();
        }
    }

}
