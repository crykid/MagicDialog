package com.blank.magicdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dialog.magicdialog.MagicDialog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MagicDialog
                        .Builder(MainActivity.this)
                        .title("this is title")
                        .icon(R.mipmap.ic_launcher_round)
                        .content("this is content")
                        .input("please enter", "")
                        .positiveEvent("confirm", view -> {

                        })
                        .negativeEvent("cancel", view -> {

                        })
                        .recyclerView(null)
                        .bottomDismissEvent()
                        .cancelAble(false)
                        .build()
                        .show();
            }
        });

    }
}
