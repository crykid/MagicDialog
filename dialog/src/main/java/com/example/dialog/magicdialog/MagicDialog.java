package com.example.dialog.magicdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dialog.R;
import com.example.dialog.ScreenTool;

/**
 * Created by : blank
 * Created on : 2018/8/15 at 09:53
 * Description:百变dialog
 * <p>需要什么控件，就调用显示对应控件的方法，使用完之后，不需要显示的调用dismiss()或者cancle()</p>
 * <br/
 * <h6>用法</h6>
 * <p>
 * MagicDialog d = new MagicDialog.Builder(this)
 * .title("title")
 * .content("content")
 * .icon(R.drawable.ic_launcher)
 * .input("", "edite")
 * .positiveEvent("sure", new MagicDialog.OnPositiveClickListener() {
 *
 * @Override public void onPositive(View view) {
 * <p>
 * }
 * })
 * .negativeEvent("cancle", new MagicDialog.OnNegativeClickListener() {
 * @Override public void onNegative(View view) {
 * <p>
 * }
 * })
 * .build();
 * <p>
 * d.show();
 */

public class MagicDialog extends Dialog {

    TextView tvTitle;
    //注：这个图片在xml中设置了最大宽高100dp
    ImageView ivIcon;
    //title下的横线
    View viewLine;
    //主内容
    TextView tvContent;
    //内容输入框
    EditText etContent;
    //列表
    RecyclerView recyclerView;
    //按钮区域顶部的横线
    View viewLine2;
    //表否定的按钮
    Button btnNegative;
    //两个按钮中间的竖线
    View viewLine3;
    //表积极性的按钮
    Button btnPositive;
    //当需要引导用户点击积极性按钮时，底部关闭按钮
    ImageView ivBtnClose;

    private Context mContext;

    /**
     * 分割线默认宽度
     */
    private static final int DEFAULT_DIVIDER_WIDTH = 1;

    /**
     * 是否有title
     * 注：当有title的时候需要显示顶部横线
     */
    private boolean hasTitle = false;
    private boolean hasIcon = false;
    private boolean hasContent = false;
    private boolean contentEditAble = false;

    private boolean hasNegative;
    private boolean hasPositive;


    private String title;

    private int contentGravity = -1;

    private int iconRes;
    private Uri iconUri;
    private String iconPath;

    private String content;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SpannableString spanContent;

    private String inputContent;
    private String hint;
    private String negativeBtnText;
    private String positiveBtnText;

    private final boolean hasBottomNegative;

    private boolean cancelAble;

    //是否包含分割线
    private boolean hasDivider = true;
    //分割线颜色
    private final int dividerColor;
    //分割线宽度
    private final int dividerWidth;

    /**
     * 当这两个都不为空的时候，需要显示中间的竖线
     */
    private OnPositiveClickListener mPositiveListener = null;
    private OnNegativeClickListener mNegativeListener = null;


    public static class Builder {

        private Context mContext;
        private boolean hasTitle = false;
        private boolean hasIcon = false;
        private boolean hasContent = false;
        private boolean contentEditAble = false;

        private boolean hasNegative;
        private boolean hasPositive;


        private String title;

        private int iconRes;
        private Uri iconUri;
        private String iconPath;

        private String content;

        private RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.Adapter mAdapter;

        private int contentGravity = -1;
        private SpannableString spanContent;
        private String inputContent;
        private String hint;
        private String negativeBtnText;
        private String positiveBtnText;

        private OnPositiveClickListener mPositiveListener = null;
        private OnNegativeClickListener mNegativeListener = null;

        private boolean cancelAble = true;
        private boolean hasBottomNegative;
        //是否有分割线
        private boolean hasDivider = true;
        private int dividerWidth;
        private int dividerColor;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置dialog的Title
         *
         * @param title
         * @return
         */
        public Builder title(String title) {
            this.hasTitle = true;
            this.title = title;
            return this;
        }

        /**
         * 设置内容区域的图片
         *
         * @param iconPath String,文件路径
         * @return
         */
        public Builder icon(String iconPath) {
            this.hasIcon = true;
            this.iconPath = iconPath;
            return this;
        }

        /**
         * 设置内容区域图片
         *
         * @param iconRes drawable路径
         * @return
         */
        public Builder icon(@DrawableRes int iconRes) {
            this.hasIcon = true;
            this.iconRes = iconRes;
            return this;
        }

        /**
         * 设置内容区域图片
         *
         * @param iconUri 相册路径
         * @return
         */
        public Builder icon(Uri iconUri) {
            this.hasIcon = true;
            this.iconUri = iconUri;
            return this;
        }

        /**
         * 设置内容区域文字
         *
         * @param content
         * @return
         */
        public Builder content(String content) {
            this.hasContent = true;
            this.content = content;
            return this;
        }

        /**
         * 设置内容区域文字
         *
         * @param content String 要显示的文字
         * @param gravity int 在文本中显示方式，eg：Gravity.CENTER
         * @return
         */
        public Builder content(SpannableString content, int gravity) {
            this.hasContent = true;
            this.spanContent = content;
            this.contentGravity = gravity;
            return this;
        }

        /**
         * 设置内容区域文字
         *
         * @param contentId
         * @return
         */
        public Builder content(@StringRes int contentId) {
            this.hasContent = true;
            String localContent = mContext.getString(contentId);
            this.content = localContent;
            return this;
        }

        /**
         * 显示RecyclerView，默认垂直排列
         *
         * @param adapter RecyclerViewAdapter 列表适配器
         * @return
         */
        public Builder recyclerView(RecyclerView.Adapter adapter) {
            this.mAdapter = adapter;
            return this;
        }

        /**
         * 显示RecyclerView
         *
         * @param manager RecyclerView.LayoutManager
         * @param adapter RecyclerViewAdapter
         * @return
         */
        public Builder recyclerView(RecyclerView.LayoutManager manager, RecyclerView.Adapter adapter) {
            this.mLayoutManager = manager;
            this.mAdapter = adapter;
            return this;
        }


        /**
         * 设置输入框
         *
         * @param hint
         * @param initValue 初始值
         * @return
         */
        public Builder input(String hint, String initValue) {
            this.contentEditAble = true;
            this.hint = hint;
            this.inputContent = initValue;
            return this;
        }

        /**
         * 设置取消/返回等类型的按钮
         *
         * @param text     按钮文字
         * @param listener 回调
         * @return
         */
        public Builder negativeEvent(String text, OnNegativeClickListener listener) {
            this.hasNegative = true;
            this.negativeBtnText = text;
            this.mNegativeListener = listener;
            return this;
        }

        /**
         * 设置取消/返回等类型的按钮
         *
         * @param text String   按钮文字
         * @return
         */
        public Builder negativeEvent(String text) {
            this.hasNegative = true;
            this.negativeBtnText = text;
            return this;
        }


        /**
         * 设置确定/确认等类型的按钮
         *
         * @param text     按钮文字
         * @param listener 回调
         * @return
         */
        public Builder positiveEvent(String text, OnPositiveClickListener listener) {
            this.hasPositive = true;
            this.positiveBtnText = text;
            this.mPositiveListener = listener;
            return this;
        }

        /**
         * 没有回调的"积极性"按钮
         *
         * @param text
         * @return
         */
        public Builder positiveEvent(String text) {
            this.hasPositive = true;
            this.positiveBtnText = text;
            return this;
        }


        /**
         * 设置底部取消/返回等类型的按钮
         *
         * @param listener 回调
         * @return
         */
        public Builder bottomDismissEvent(OnNegativeClickListener listener) {
            this.hasBottomNegative = true;
            this.mNegativeListener = listener;
            return this;
        }

        /**
         * 设置底部取消/返回等类型的按钮
         *
         * @return
         */
        public Builder bottomDismissEvent() {
            this.hasBottomNegative = true;
            return this;
        }

        /**
         * 是否允许点击弹窗外部或返回按键隐藏弹窗
         *
         * @param enable boolean ：ture-允许，fasle-不允许
         * @return
         */
        public Builder cancelAble(boolean enable) {
            this.cancelAble = enable;
            return this;
        }

        /**
         * 不要分割线
         *
         * @return
         */
        public Builder withoutDivider() {
            this.hasDivider = false;
            return this;
        }

        public Builder divider(@ColorRes int colorId) {
            return divider(DEFAULT_DIVIDER_WIDTH, colorId);
        }

        /**
         * 设置分割线
         *
         * @param width   分割线宽度
         * @param colorId 分割线颜色
         * @return
         */
        public Builder divider(int width, @ColorRes int colorId) {
            this.hasDivider = true;
            this.dividerWidth = width;
            this.dividerColor = ContextCompat.getColor(mContext, colorId);
            return this;
        }

        public MagicDialog build() {
            return new MagicDialog(this);
        }

    }

    /**
     * 通过Builder初始化
     *
     * @param builder
     */
    private MagicDialog(Builder builder) {
        super(builder.mContext, R.style.PopDialog);

        this.mContext = builder.mContext;
        this.hasTitle = builder.hasTitle;
        this.hasIcon = builder.hasIcon;
        this.hasContent = builder.hasContent;
        this.contentEditAble = builder.contentEditAble;

        this.hasNegative = builder.hasNegative;
        this.hasBottomNegative = builder.hasBottomNegative;
        this.hasPositive = builder.hasPositive;


        this.title = builder.title;

        this.iconRes = builder.iconRes;
        this.iconUri = builder.iconUri;
        this.iconPath = builder.iconPath;

        this.content = builder.content;

        this.mLayoutManager = builder.mLayoutManager;
        this.mAdapter = builder.mAdapter;

        this.spanContent = builder.spanContent;
        this.contentGravity = builder.contentGravity;
        this.inputContent = builder.inputContent;
        this.hint = builder.hint;
        this.negativeBtnText = builder.negativeBtnText;
        this.positiveBtnText = builder.positiveBtnText;
        this.mNegativeListener = builder.mNegativeListener;
        this.mPositiveListener = builder.mPositiveListener;
        this.cancelAble = builder.cancelAble;

        this.hasDivider = builder.hasDivider;
        this.dividerColor = builder.dividerColor;
        this.dividerWidth = builder.dividerWidth;

        initView();


    }


    public void initView() {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_magic, null, false);
        setContentView(rootView);
        findView(rootView);

        //设置分割线颜色
        if (hasDivider && dividerColor != 0) {
            viewLine.setBackgroundColor(this.dividerColor);
            viewLine2.setBackgroundColor(this.dividerColor);
            viewLine3.setBackgroundColor(this.dividerColor);
        }


        //title
        if (hasTitle) {
            tvTitle.setVisibility(View.VISIBLE);
            //分割线
            if (hasDivider) {
                viewLine.setVisibility(View.VISIBLE);
            } else {
                viewLine.setVisibility(View.GONE);
            }
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        }
        //图片
        if (hasIcon) {
            ivIcon.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(iconPath)) {
                Glide.with(mContext).load(iconPath).into(ivIcon);
            } else if (iconUri != null) {
                Glide.with(mContext).load(iconUri).into(ivIcon);
            } else if (iconRes > 0) {
                Glide.with(mContext).load(iconRes).into(ivIcon);
            } else {
                ivIcon.setVisibility(View.GONE);
            }
        } else {
            ivIcon.setVisibility(View.GONE);
        }

        //显示内容
        if (hasContent) {
            tvContent.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(content))
                tvContent.setText(content);
            if (spanContent != null) {
                tvContent.setText(spanContent);
            }
            if (contentGravity > 0) {
                tvContent.setGravity(contentGravity);
            }
        } else {
            tvContent.setVisibility(View.GONE);
        }


        //列表
        if (mAdapter != null) {
            recyclerView.setVisibility(View.VISIBLE);

            if (mLayoutManager != null) {
                recyclerView.setLayoutManager(mLayoutManager);
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            }

            recyclerView.setAdapter(mAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);

        }

        //内容输入框
        if (contentEditAble) {
            etContent.setVisibility(View.VISIBLE);
            etContent.setText(inputContent);
            etContent.setHint(hint);
        } else {
            etContent.setVisibility(View.GONE);
        }


        //取消按钮
        if (hasNegative) {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(negativeBtnText);
            btnNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancel();
                    if (mNegativeListener != null) {
                        mNegativeListener.onNegative(view);
                    }
                }
            });
        } else {
            btnNegative.setVisibility(View.GONE);
        }
        btnNegative.setEnabled(hasNegative);


        //确定按钮
        if (hasPositive) {
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setText(positiveBtnText);
            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancel();
                    if (mPositiveListener != null) {
                        mPositiveListener.onPositive(view);
                    }
                }
            });
        } else {
            btnPositive.setVisibility(View.GONE);
        }
        btnNegative.setEnabled(hasPositive);

        //按钮上部分割线
        if (hasNegative || hasPositive) {
            if (hasDivider) {
                viewLine2.setVisibility(View.VISIBLE);
            } else {
                viewLine2.setVisibility(View.GONE);

            }
        }

        //两个按钮都存在时显示中间竖线
        if (hasPositive && hasNegative && hasDivider) {
            viewLine3.setVisibility(View.VISIBLE);
        } else {
            viewLine3.setVisibility(View.GONE);
        }

        //底部关闭按钮
        if (hasBottomNegative) {
            ivBtnClose.setVisibility(View.VISIBLE);
            ivBtnClose.setEnabled(true);
            ivBtnClose.setOnClickListener(v -> {
                cancel();
                if (mNegativeListener != null) {
                    mNegativeListener.onNegative(v);
                }
            });
        } else {
            ivBtnClose.setVisibility(View.GONE);
            ivBtnClose.setEnabled(false);
        }


        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.centerInOut);
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.width = (int) (ScreenTool.getScreenWidth(mContext) * 0.8);
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            wl.gravity = Gravity.CENTER;
            //透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setAttributes(wl);

            this.setCancelable(cancelAble);
            this.setCanceledOnTouchOutside(cancelAble);
        }

    }

    private void findView(View rootView) {
        tvTitle = rootView.findViewById(R.id.tv_dialog_magic_title);

        //注：这个图片在xml中设置了最大宽高100dp
        ivIcon = rootView.findViewById(R.id.iv_dialog_magic_icon);
        viewLine = rootView.findViewById(R.id.view_dialog_magic_line);
        tvContent = rootView.findViewById(R.id.tv_dialog_magic_content);
        etContent = rootView.findViewById(R.id.et_dialog_magic_content);
        recyclerView = rootView.findViewById(R.id.recyclerv_dialog_magic_recyclerv);
        viewLine2 = rootView.findViewById(R.id.view_dialog_magic_line2);
        btnNegative = rootView.findViewById(R.id.btn_dialog_magic_negative);
        viewLine3 = rootView.findViewById(R.id.view_dialog_magic_line3);
        btnPositive = rootView.findViewById(R.id.btn_dialog_magic_positive);
        ivBtnClose = rootView.findViewById(R.id.iv_dialog_magic_close);
    }

    /**
     * 获取输入框输入的内容
     *
     * @return
     */
    public String getInputContent() {
        if (!contentEditAble) {
            return "";
        }
        return etContent.getText().toString();
    }

    /**
     * 表示否定/取消等的回调
     */
    public interface OnNegativeClickListener {
        void onNegative(View view);
    }

    /**
     * 表示确认/是等的回调
     */
    public interface OnPositiveClickListener {
        void onPositive(View view);
    }


}
