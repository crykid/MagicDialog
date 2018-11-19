package com.blank.magicdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.dialog.magicdialog.MagicDialog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(v ->
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
                        .divider(R.color.default_blue)
                        .recyclerView(null)
                        .bottomDismissEvent()
                        .cancelAble(false)
                        .build()
                        .show());

        findViewById(R.id.btn_main_simple).setOnClickListener((v) -> {

            new MagicDialog
                    .Builder(MainActivity.this)
                    .content("this is msg")
                    .positiveEvent("sure")
                    .build()
                    .show();

        });
        findViewById(R.id.btn_main_sys).setOnClickListener((v) -> {
            new AlertDialog
                    .Builder(MainActivity.this)
                    .setTitle("system dialog")
                    .setMessage("this is content")
                    .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create()
                    .show();
        });


    }
}
