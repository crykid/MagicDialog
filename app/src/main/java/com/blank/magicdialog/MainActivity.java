package com.blank.magicdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
                        //添加title
                        .title("this is title")
                        //添加图标
                        .icon(R.mipmap.ic_launcher_round)
                        //添加主要提示内容
                        .content("this is content")
                        //添加输入框
                        .input("please enter", "")
                        //添加"确定"等积极性意义的按钮及回调
                        .positiveEvent("confirm", view -> {
                            //doSth
                        })
                        //添加"取消"等否定性意义的按钮及回调
                        .negativeEvent("cancel", view -> {
                            //doSth
                        })
                        //添加列表并添加adapter
                        .recyclerView(null)
                        //当需要引导用户点击确认时底部的关闭按钮
                        .bottomDismissEvent(view -> {
                            //doSth
                        })
                        //是否允许点击外部或返回取消弹窗
                        .cancelAble(false)
                        .build()
                        .show();
            }
        });


    }
}
