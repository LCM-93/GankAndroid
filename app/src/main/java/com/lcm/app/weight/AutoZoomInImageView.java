package com.lcm.app.weight;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/12 上午10:47
 * Desc:
 * *****************************************************************
 */
@SuppressLint("NewApi")
public class AutoZoomInImageView extends android.support.v7.widget.AppCompatImageView {
    private Drawable mDrawable;
    private int mDrawableW;
    private int mDrawableH;

    private int mImageViewW;
    private int mImageViewH;

    private Matrix mMatrix;
    private float[] mValues = new float[9];

    private float mScaleDelta = 0.2f;
    private long mDurationMillis = 700;

    public AutoZoomInImageView(Context context) {
        super(context);
        this.setScaleType(ScaleType.MATRIX);
    }

    public AutoZoomInImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setScaleType(ScaleType.MATRIX);
    }

    public AutoZoomInImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setScaleType(ScaleType.MATRIX);
    }

    public AutoZoomInImageView init() {
        initInternalValues();
        initPicturePosition();
        return this;
    }

    public void init(Drawable drawable) {
        initInternalValues(drawable);
        initPicturePosition();
    }

    private void initInternalValues() {
        mDrawable = getDrawable();

        if (mDrawable == null) {
            throw new IllegalArgumentException("please set the source of AutoZoomInImageView");
        }

        mDrawableW = mDrawable.getIntrinsicWidth();
        mDrawableH = mDrawable.getIntrinsicHeight();

        mImageViewW = getMeasuredWidth();
        mImageViewH = getMeasuredHeight();

        mMatrix = getImageMatrix();
        mMatrix.getValues(mValues);
    }

    private void initInternalValues(Drawable drawable) {
        mDrawable = drawable;
        if (mDrawable == null) {
            throw new IllegalArgumentException("please set the source of AutoZoomInImageView");
        }

        mDrawableW = mDrawable.getIntrinsicWidth();
        mDrawableH = mDrawable.getIntrinsicHeight();

        mImageViewW = getMeasuredWidth();
        mImageViewH = getMeasuredHeight();
        mMatrix = getImageMatrix();
        mMatrix.getValues(mValues);
    }

    private void initPicturePosition() {
        updateMatrixValuesOrigin(mMatrix, mValues, mDrawableW, mDrawableH, mImageViewW, mImageViewH);
        setImageMatrix(mMatrix);
    }

    private void startZoomInByScaleDelta(final float scaleDelta, long duration) {

        final float oriScaleX = mValues[0];
        final float oriScaleY = mValues[4];

        ValueAnimator va = ValueAnimator.ofFloat(0, scaleDelta);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                if (mOnZoomListener != null)
                    mOnZoomListener.onUpdate(AutoZoomInImageView.this, value / scaleDelta);
                updateMatrixValuesSpan(mValues, mDrawableW, mDrawableH, mImageViewW, mImageViewH,
                        oriScaleX, oriScaleY, value);
                mMatrix.setValues(mValues);
                setImageMatrix(mMatrix);
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mOnZoomListener != null) mOnZoomListener.onStart(AutoZoomInImageView.this);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnZoomListener != null) mOnZoomListener.onEnd(AutoZoomInImageView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        va.setDuration(duration);
        va.start();
    }

    /**
     * 132      * 开始放大动画
     * 133      * start zooming in
     * 134      * @param scaleDelta        放大的增大倍数，如果是0.2，那么最后大小放大至1.2倍。
     * 135      *                          the scale that the image will add to original scale
     * 136      * @param durationMillis    放大效果的持续时间，单位毫秒。
     * 137      *                          the duration of zoomin animation, in millisecond.
     * 138      * @param delayMillis       开始放大效果的延迟时间，单位毫秒。delayed毫秒后开始放大动画效果。
     * 139      *                          the delayed time of starting zoomin animation, in millisecond.
     * 140
     */
    public void startZoomInByScaleDeltaAndDuration(final float scaleDelta, final long durationMillis, long delayMillis) {
        if (scaleDelta < 0) {
            throw new IllegalArgumentException("scaleDelta should be larger than 0, now scaleDelta is " + scaleDelta);
        }
        if (durationMillis < 0) {
            throw new IllegalArgumentException("durationMillis should not be less than 0, now durationMillis is " + durationMillis);
        }
        if (delayMillis < 0) {
            throw new IllegalArgumentException("delayMillis should not be less than 0, now delayMillis is " + delayMillis);
        }

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startZoomInByScaleDelta(scaleDelta, durationMillis);
            }
        }, delayMillis);
    }

    /**
     * 161      * 放大的增大倍数，如果是0.2，那么最后大小放大至1.2倍。
     * 162      * the scale that the image will add to original scale
     * 163      * @param scaleDelta
     * 164      * @return
     * 165
     */
    public AutoZoomInImageView setScaleDelta(float scaleDelta) {
        mScaleDelta = scaleDelta;
        return this;
    }

    /**
     * 172      * 放大效果的持续时间，单位毫秒。
     * 173      * the duration of zoomin animation, in millisecond.
     * 174      * @param durationMillis
     * 175      * @return
     * 176
     */
    public AutoZoomInImageView setDurationMillis(long durationMillis) {
        mDurationMillis = durationMillis;
        return this;
    }

    /**
     * 183      * 动画结束的回调
     * 184      * callback when zoomin animation finished
     * 185      * @param onZoomListener
     * 186      * @return
     * 187
     */
    public AutoZoomInImageView setOnZoomListener(OnZoomListener onZoomListener) {
        mOnZoomListener = onZoomListener;
        return this;
    }

    /**
     * 194      * 开始放大效果
     * 195      * start animation of zoomin
     * 196      * @param delayMillis       开始放大效果的延迟时间，单位毫秒。delayed毫秒后开始放大动画效果
     * 197      *                          the delayed time of starting zoomin animation, in millisecond.
     * 198
     */
    public void start(long delayMillis) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                startZoomInByScaleDelta(mScaleDelta, mDurationMillis);
            }
        }, delayMillis);
    }

    private void updateMatrixValuesOrigin(Matrix outMatrix, float[] outValues, float drawW, float drawH, float imageW, float imageH) {

        if (outMatrix == null || outValues == null) {
            throw new IllegalArgumentException("please set the source of AutoZoomInImageView's matrix and values");
        }

        outMatrix.reset();

        if ((imageH * drawW > drawH * imageW)) {
            float scale1 = (imageH) / (drawH);
            float offset1 = (drawW * scale1 - imageW) / 2;

            outMatrix.postScale(scale1, scale1);
            outMatrix.postTranslate(-offset1, 0);

        } else {
            float scale2 = (imageW) / (drawW);
            float offset2 = (drawH * scale2 - imageH) / 2;

            outMatrix.postScale(scale2, scale2);
            outMatrix.postTranslate(0, -offset2);
        }
        outMatrix.getValues(outValues);
    }

    private void updateMatrixValuesSpan(float[] outValues,
                                        float drawW, float drawH,
                                        float imageW, float imageH,
                                        float oriScaleX, float oriScaleY,
                                        float scaleDelta) {
        //根据四个参数：图片的宽高、控件的宽高，动态的计算出输出的矩阵（float数组）的值
        outValues[0] = oriScaleX * (1 + scaleDelta);
        outValues[4] = oriScaleY * (1 + scaleDelta);
        float offsetwidth = (drawW * outValues[0] - imageW) / 2;
        outValues[2] = -offsetwidth;
        float offsetHeight = (drawH * outValues[0] - imageH) / 2;
        outValues[5] = -offsetHeight;
    }

    private OnZoomListener mOnZoomListener;

    public interface OnZoomListener {
        /**
         * 250          * 动画更新时执行的回调
         * 251          * @param view      返回此AutoZoomInImageView
         * 252          * @param progress  返回动画进行过程，范围是[0,1]
         * 253
         */
        void onUpdate(View view, float progress);

        void onEnd(View view);

        void onStart(View view);
    }


    //function for log
    public String printMyMatrix(Matrix m) {
        float[] valueFloat = new float[9];
        m.getValues(valueFloat);

        String s = "";
        for (int i = 0; i < 9; i++) {
            s = s + " [ " + valueFloat[i] + " ] ";
        }
        return s;
    }

    //function for log
    public String printMyValue(float[] valueFloat) {
        String s = "";
        for (int i = 0; i < 9; i++) {
            s = s + " [ " + valueFloat[i] + " ] ";
        }
        return s;
    }

}
