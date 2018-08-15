package com.blank.magicdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blank.magicdialog.magicdialog.MagicDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MagicDialog.Builder(MainActivity.this)
                        .title("this is title")
                        .icon(R.drawable.ic_launcher_background)
                        .content("this is content")
                        .input("please enter", "")
                        .positiveEvent("confim", new MagicDialog.OnPositiveClickListener() {
                            @Override
                            public void onPositive(View view) {

                            }
                        })
                        .negativeEvent("cancle", new MagicDialog.OnNegativeClickListener() {
                            @Override
                            public void onNegative(View view) {

                            }
                        })
                        .build()
                        .show();
            }
        });

    }
}
