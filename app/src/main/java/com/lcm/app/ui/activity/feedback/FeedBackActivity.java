package com.lcm.app.ui.activity.feedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.feedback.Comment;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.feedback.FeedbackThread;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.data.Contract;
import com.lcm.app.utils.MediaUtility;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 下午3:00
 * Desc:
 * *****************************************************************
 */

public class FeedBackActivity extends BaseActivity {

    public static int REQUEST_FILE_PICKER = 0x00;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;
    @BindView(R.id.btn_feedback)
    Button btnFeedBack;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.edt_feedback)
    EditText edtFeedBack;
    private String picPath;
    private KProgressHUD hud;
    protected View snackBarRootView;

    @Override
    protected int rootView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("意见反馈");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.inflateMenu(R.menu.menu_image_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        snackBarRootView = findViewById(android.R.id.content);

    }

    @Override
    protected void initData() {
        RxTextView.textChanges(edtFeedBack)
                .subscribe(charSequence -> {
                    tvCount.setText(charSequence.length() + "/300");
                    if (charSequence.length() > 300) {
                        edtFeedBack.setBackground(getResources().getDrawable(R.drawable.shape_bg_input_error));
                        tvCount.setTextColor(Color.parseColor("#FF0007"));
                        edtFeedBack.setTextColor(Color.parseColor("#FF0007"));
                    } else {
                        edtFeedBack.setBackground(getResources().getDrawable(R.drawable.shape_bg_input_focus));
                        tvCount.setTextColor(Color.parseColor("#666666"));
                        edtFeedBack.setTextColor(Color.parseColor("#333333"));
                    }
                });

        RxView.clicks(btnFeedBack)
                .map(o -> {
                    if (TextUtils.isEmpty(edtFeedBack.getText().toString().trim())) {
                        edtFeedBack.setBackground(getResources().getDrawable(R.drawable.shape_bg_input_error));
                        SnackbarUtils.with(findViewById(android.R.id.content))
                                .setBgColor(Color.parseColor("#a0000000"))
                                .setMessage("反馈信息不能为空！")
                                .setMessageColor(Color.parseColor("#FF0007"))
                                .show();
                        return false;
                    } else {
                        return true;
                    }
                })
                .subscribe(aBoolean -> {
                    if (aBoolean) feedBack();
                });
    }

    private void feedBack() {
        showLoading();
        FeedbackAgent agent = new FeedbackAgent(getApplicationContext());
        FeedbackThread thread = agent.getDefaultThread();
        thread.setContact(SPUtils.getInstance(Contract.SPNAME).getString(Contract.USEREMAIL));
        Comment strComment = new Comment(edtFeedBack.getText().toString());
        thread.getCommentsList().add(strComment);


        if (picPath != null) {
            try {
                File file = new File(picPath);
                Comment comment = new Comment(file);
                thread.getCommentsList().add(comment);
            } catch (AVException e) {
                e.printStackTrace();
            }
        }


        thread.sync(new FeedbackThread.SyncCallback() {
            @Override
            public void onCommentsSend(List<Comment> list, AVException e) {
                hideLoading();
            }

            @Override
            public void onCommentsFetch(List<Comment> list, AVException e) {
                hideLoading();
                SnackbarUtils.with(snackBarRootView)
                        .setMessage("感谢您的反馈！（*＾ワ＾*）")
                        .setAction("关闭", v -> finish())
                        .setDuration(SnackbarUtils.LENGTH_LONG)
                        .showSuccess();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.iv_add_image)
    public void addImage() {
        chosePic();
    }


    /**
     * 打开相册
     */
    private void chosePic() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FILE_PICKER && resultCode == Activity.RESULT_OK) {
            picPath = MediaUtility.getPath(mContext,
                    data.getData());

            if (picPath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                ivImage.setImageBitmap(bitmap);
                ivImage.setVisibility(View.VISIBLE);
                ivAddImage.setVisibility(View.GONE);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showLoading() {
        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).show();
    }

    public void hideLoading() {
        if (hud != null && hud.isShowing()) hud.dismiss();
    }
}
