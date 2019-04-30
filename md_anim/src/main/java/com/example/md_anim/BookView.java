package com.example.md_anim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class BookView extends android.support.v7.widget.AppCompatImageView {
    public BookView(Context context) {
        super(context);
    }

    public BookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制drawable尺寸的大小
        int height = getHeight(); //300dp -> 450
        int width = getWidth(); //200dp -> 300 他是什么单位啊，英寸吗？


        String words = "尺寸" + String.valueOf(width) + "X" + String.valueOf(height);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawText(words, 20, 20, paint);

        //弄明白了大小怎么获得之后，就是如何获取边界的颜色，怎么画线。

        //第一个问题：imageView设置的大小就那么大，你要在控件外画线吗？是不是应该重新设置imageView的大小属性？
        //用过ImageView.setBackgroundDrawable()函数的读者，应该都清楚，这个函数在设置背景的时候，所设置的背景会忽略源图像中的padding属性。
        // 所以我们给源图像添加padding，而背景没有padding，背景图像自然比源图像要大

        //第二个问题，加了padding之后，如何获取image的位置
        //僵硬的设置吧？padding为10，所以是15
        canvas.drawLine(15, 0, 0, 15, paint);
        canvas.drawLine(0, 15, 0, 450, paint);
        canvas.drawLine(0, 450, 15, 435, paint);
        canvas.drawLine(0, 449, 285, 449, paint);
        canvas.drawLine(285, 450, 300, 435, paint);



    }
}
