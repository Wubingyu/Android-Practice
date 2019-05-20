package com.example.project_practice.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.example.project_practice.R;

/**
 * view的宽或者高大于我们的bitmap宽或者高岂不是会拉伸？
 *
 * 嗯，我们会为BitmapShader设置一个matrix，去适当的放大或者缩小图片，不会让“ view的宽或者高大于我们的bitmap宽或者高 ”此条件成立的。
 *
 * 到此我们的原理基本介绍完毕了，拿到drawable转化为bitmap，然后直接初始化BitmapShader，画笔设置Shader，最后在onDraw里面进行画圆就行了。
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint bitmapPaint;
    private BitmapShader bitmapShader;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;

    //View的宽高
    private int mWidth;
    //半径
    private int mRadius;

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        mMatrix = new Matrix();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //强制改变view的宽高一致，以小值为准
        //get获得当前的  set设置当前的。
        mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = mWidth / 2;
        setMeasuredDimension(mWidth, mWidth); //设置view的大小
    }


    /**
     * 确定了要花多大，每个位置怎么上色
     * 剩下的就是画一个圆了
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setUpShader(); //设置绘画方式
        canvas.drawCircle(mRadius, mRadius, mRadius, bitmapPaint);
    }



    /**
     * 初始化BitmapShader
     */
    private void setUpShader()
    {
        Drawable drawable = getDrawable();
        if (drawable == null)
        {
            return;
        }

        Bitmap bmp = drawableToBitmap(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        bitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        // 拿到bitmap宽或高的小值
        int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
        scale = mWidth * 1.0f / bSize; //设置缩放值，当前控件如果比bitmap大，就scale>1变大
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵。
        bitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        bitmapPaint.setShader(bitmapShader);
    }


    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}