package com.example.md_anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;



public class NavigationView extends View {
    public NavigationView(Context context) {
        super(context);
    }

    public NavigationView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(0xfff38181);
        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(50);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

//        canvas.drawCircle(0, 0, 100, paint);
        RectF rectF = new RectF(10, 10, 100, 200);
        canvas.drawArc(rectF, 0, 90, true, paint);

    }
}
